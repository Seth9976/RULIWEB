package androidx.loader.content;

import android.os.Binder;
import android.os.Handler;
import android.os.Looper;
import android.os.Process;
import android.util.Log;
import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.FutureTask;
import java.util.concurrent.atomic.AtomicBoolean;

abstract class ModernAsyncTask {
    public static enum Status {
        PENDING,
        RUNNING,
        FINISHED;

    }

    private static final String LOG_TAG = "AsyncTask";
    final AtomicBoolean mCancelled;
    private final FutureTask mFuture;
    private volatile Status mStatus;
    final AtomicBoolean mTaskInvoked;
    private static Handler sHandler;

    ModernAsyncTask() {
        this.mStatus = Status.PENDING;
        this.mCancelled = new AtomicBoolean();
        this.mTaskInvoked = new AtomicBoolean();
        this.mFuture = new FutureTask(new Callable() {
            @Override
            public Object call() {
                ModernAsyncTask.this.mTaskInvoked.set(true);
                Object object0 = null;
                try {
                    Process.setThreadPriority(10);
                    object0 = ModernAsyncTask.this.doInBackground();
                    Binder.flushPendingCommands();
                }
                catch(Throwable throwable0) {
                    try {
                        ModernAsyncTask.this.mCancelled.set(true);
                        throw throwable0;
                    }
                    catch(Throwable throwable1) {
                        ModernAsyncTask.this.postResult(object0);
                        throw throwable1;
                    }
                }
                ModernAsyncTask.this.postResult(object0);
                return object0;
            }
        }) {
            @Override
            protected void done() {
                try {
                    Object object0 = this.get();
                    ModernAsyncTask.this.postResultIfNotInvoked(object0);
                }
                catch(InterruptedException interruptedException0) {
                    Log.w("AsyncTask", interruptedException0);
                }
                catch(ExecutionException executionException0) {
                    throw new RuntimeException("An error occurred while executing doInBackground()", executionException0.getCause());
                }
                catch(CancellationException unused_ex) {
                    ModernAsyncTask.this.postResultIfNotInvoked(null);
                }
                catch(Throwable throwable0) {
                    throw new RuntimeException("An error occurred while executing doInBackground()", throwable0);
                }
            }
        };
    }

    public final boolean cancel(boolean z) {
        this.mCancelled.set(true);
        return this.mFuture.cancel(z);
    }

    protected abstract Object doInBackground();

    public final void executeOnExecutor(Executor executor0) {
        if(this.mStatus != Status.PENDING) {
            switch(androidx.loader.content.ModernAsyncTask.4.$SwitchMap$androidx$loader$content$ModernAsyncTask$Status[this.mStatus.ordinal()]) {
                case 1: {
                    throw new IllegalStateException("Cannot execute task: the task is already running.");
                }
                case 2: {
                    throw new IllegalStateException("Cannot execute task: the task has already been executed (a task can be executed only once)");
                }
                default: {
                    throw new IllegalStateException("We should never reach this state");
                }
            }
        }
        this.mStatus = Status.RUNNING;
        executor0.execute(this.mFuture);
    }

    // 检测为 Lambda 实现
    void finish(Object object0) [...]

    private static Handler getHandler() {
        synchronized(ModernAsyncTask.class) {
            if(ModernAsyncTask.sHandler == null) {
                ModernAsyncTask.sHandler = new Handler(Looper.getMainLooper());
            }
            return ModernAsyncTask.sHandler;
        }
    }

    public final boolean isCancelled() {
        return this.mCancelled.get();
    }

    protected void onCancelled(Object object0) {
    }

    protected void onPostExecute(Object object0) {
    }

    void postResult(Object object0) {
        ModernAsyncTask.getHandler().post(() -> {
            if(ModernAsyncTask.this.isCancelled()) {
                ModernAsyncTask.this.onCancelled(this.val$result);
            }
            else {
                ModernAsyncTask.this.onPostExecute(this.val$result);
            }
            ModernAsyncTask.this.mStatus = Status.FINISHED;
        });

        class androidx.loader.content.ModernAsyncTask.3 implements Runnable {
            androidx.loader.content.ModernAsyncTask.3(Object object0) {
            }

            @Override
            public void run() {
                ModernAsyncTask.this.finish(this.val$result);
            }
        }

    }

    void postResultIfNotInvoked(Object object0) {
        if(!this.mTaskInvoked.get()) {
            this.postResult(object0);
        }
    }

    class androidx.loader.content.ModernAsyncTask.4 {
        static final int[] $SwitchMap$androidx$loader$content$ModernAsyncTask$Status;

        static {
            int[] arr_v = new int[Status.values().length];
            androidx.loader.content.ModernAsyncTask.4.$SwitchMap$androidx$loader$content$ModernAsyncTask$Status = arr_v;
            try {
                arr_v[Status.RUNNING.ordinal()] = 1;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                androidx.loader.content.ModernAsyncTask.4.$SwitchMap$androidx$loader$content$ModernAsyncTask$Status[Status.FINISHED.ordinal()] = 2;
            }
            catch(NoSuchFieldError unused_ex) {
            }
        }
    }

}

