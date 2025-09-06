package com.google.firebase.concurrent;

import com.google.firebase.components.Preconditions;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.Semaphore;

class LimitedConcurrencyExecutor implements Executor {
    private final Executor delegate;
    private final LinkedBlockingQueue queue;
    private final Semaphore semaphore;

    LimitedConcurrencyExecutor(Executor executor0, int v) {
        this.queue = new LinkedBlockingQueue();
        Preconditions.checkArgument(v > 0, "concurrency must be positive.");
        this.delegate = executor0;
        this.semaphore = new Semaphore(v, true);
    }

    private Runnable decorate(Runnable runnable0) {
        return () -> try {
            runnable0.run();
        }
        finally {
            this.semaphore.release();
            this.maybeEnqueueNext();
        };
    }

    @Override
    public void execute(Runnable runnable0) {
        this.queue.offer(runnable0);
        this.maybeEnqueueNext();
    }

    // 检测为 Lambda 实现
    void lambda$decorate$0$com-google-firebase-concurrent-LimitedConcurrencyExecutor(Runnable runnable0) [...]

    private void maybeEnqueueNext() {
        while(this.semaphore.tryAcquire()) {
            Runnable runnable0 = (Runnable)this.queue.poll();
            if(runnable0 == null) {
                this.semaphore.release();
                if(true) {
                    break;
                }
            }
            else {
                Runnable runnable1 = this.decorate(runnable0);
                this.delegate.execute(runnable1);
            }
        }
    }
}

