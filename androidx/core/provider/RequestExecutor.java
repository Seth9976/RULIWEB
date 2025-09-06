package androidx.core.provider;

import android.os.Handler;
import android.os.Process;
import androidx.core.util.Consumer;
import androidx.core.util.Preconditions;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

class RequestExecutor {
    static class DefaultThreadFactory implements ThreadFactory {
        static class ProcessPriorityThread extends Thread {
            private final int mPriority;

            ProcessPriorityThread(Runnable runnable0, String s, int v) {
                super(runnable0, s);
                this.mPriority = v;
            }

            @Override
            public void run() {
                Process.setThreadPriority(this.mPriority);
                super.run();
            }
        }

        private int mPriority;
        private String mThreadName;

        DefaultThreadFactory(String s, int v) {
            this.mThreadName = s;
            this.mPriority = v;
        }

        @Override
        public Thread newThread(Runnable runnable0) {
            return new ProcessPriorityThread(runnable0, this.mThreadName, this.mPriority);
        }
    }

    static class HandlerExecutor implements Executor {
        private final Handler mHandler;

        HandlerExecutor(Handler handler0) {
            this.mHandler = (Handler)Preconditions.checkNotNull(handler0);
        }

        @Override
        public void execute(Runnable runnable0) {
            Runnable runnable1 = (Runnable)Preconditions.checkNotNull(runnable0);
            if(!this.mHandler.post(runnable1)) {
                throw new RejectedExecutionException(this.mHandler + " is shutting down");
            }
        }
    }

    static class ReplyRunnable implements Runnable {
        private Callable mCallable;
        private Consumer mConsumer;
        private Handler mHandler;

        ReplyRunnable(Handler handler0, Callable callable0, Consumer consumer0) {
            this.mCallable = callable0;
            this.mConsumer = consumer0;
            this.mHandler = handler0;
        }

        @Override
        public void run() {
            Object object0 = null;
            try {
                object0 = this.mCallable.call();
            }
            catch(Exception unused_ex) {
            }
            this.mHandler.post(new Runnable() {
                @Override
                public void run() {
                    this.mConsumer.accept(object0);
                }
            });
        }
    }

    static ThreadPoolExecutor createDefaultExecutor(String s, int v, int v1) {
        DefaultThreadFactory requestExecutor$DefaultThreadFactory0 = new DefaultThreadFactory(s, v);
        ThreadPoolExecutor threadPoolExecutor0 = new ThreadPoolExecutor(0, 1, ((long)v1), TimeUnit.MILLISECONDS, new LinkedBlockingDeque(), requestExecutor$DefaultThreadFactory0);
        threadPoolExecutor0.allowCoreThreadTimeOut(true);
        return threadPoolExecutor0;
    }

    static Executor createHandlerExecutor(Handler handler0) {
        return new HandlerExecutor(handler0);
    }

    static void execute(Executor executor0, Callable callable0, Consumer consumer0) {
        executor0.execute(new ReplyRunnable(CalleeHandler.create(), callable0, consumer0));
    }

    static Object submit(ExecutorService executorService0, Callable callable0, int v) throws InterruptedException {
        Future future0 = executorService0.submit(callable0);
        try {
            return future0.get(((long)v), TimeUnit.MILLISECONDS);
        }
        catch(ExecutionException executionException0) {
            throw new RuntimeException(executionException0);
        }
        catch(InterruptedException interruptedException0) {
            throw interruptedException0;
        }
        catch(TimeoutException unused_ex) {
            throw new InterruptedException("timeout");
        }
    }
}

