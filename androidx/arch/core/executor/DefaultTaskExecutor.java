package androidx.arch.core.executor;

import android.os.Build.VERSION;
import android.os.Handler.Callback;
import android.os.Handler;
import android.os.Looper;
import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

public class DefaultTaskExecutor extends TaskExecutor {
    static class Api28Impl {
        public static Handler createAsync(Looper looper0) {
            return Handler.createAsync(looper0);
        }
    }

    private final ExecutorService mDiskIO;
    private final Object mLock;
    private volatile Handler mMainHandler;

    public DefaultTaskExecutor() {
        this.mLock = new Object();
        this.mDiskIO = Executors.newFixedThreadPool(4, new ThreadFactory() {
            private static final String THREAD_NAME_STEM = "arch_disk_io_";
            private final AtomicInteger mThreadId;

            {
                this.mThreadId = new AtomicInteger(0);
            }

            @Override
            public Thread newThread(Runnable runnable0) {
                Thread thread0 = new Thread(runnable0);
                thread0.setName("arch_disk_io_" + this.mThreadId.getAndIncrement());
                return thread0;
            }
        });
    }

    private static Handler createAsync(Looper looper0) {
        if(Build.VERSION.SDK_INT >= 28) {
            return Api28Impl.createAsync(looper0);
        }
        try {
            return (Handler)Handler.class.getDeclaredConstructor(Looper.class, Handler.Callback.class, Boolean.TYPE).newInstance(looper0, null, Boolean.TRUE);
        }
        catch(IllegalAccessException | InstantiationException | NoSuchMethodException unused_ex) {
            return new Handler(looper0);
        }
        catch(InvocationTargetException unused_ex) {
            return new Handler(looper0);
        }
    }

    @Override  // androidx.arch.core.executor.TaskExecutor
    public void executeOnDiskIO(Runnable runnable0) {
        this.mDiskIO.execute(runnable0);
    }

    @Override  // androidx.arch.core.executor.TaskExecutor
    public boolean isMainThread() {
        return Looper.getMainLooper().getThread() == Thread.currentThread();
    }

    @Override  // androidx.arch.core.executor.TaskExecutor
    public void postToMainThread(Runnable runnable0) {
        if(this.mMainHandler == null) {
            Object object0 = this.mLock;
            synchronized(object0) {
                if(this.mMainHandler == null) {
                    this.mMainHandler = DefaultTaskExecutor.createAsync(Looper.getMainLooper());
                }
            }
        }
        this.mMainHandler.post(runnable0);
    }
}

