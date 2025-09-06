package com.google.firebase.concurrent;

import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;

final class PausableExecutorImpl implements PausableExecutor {
    private final Executor delegate;
    private volatile boolean paused;
    final LinkedBlockingQueue queue;

    PausableExecutorImpl(boolean z, Executor executor0) {
        this.queue = new LinkedBlockingQueue();
        this.paused = z;
        this.delegate = executor0;
    }

    @Override
    public void execute(Runnable runnable0) {
        this.queue.offer(runnable0);
        this.maybeEnqueueNext();
    }

    @Override  // com.google.firebase.concurrent.PausableExecutor
    public boolean isPaused() {
        return this.paused;
    }

    private void maybeEnqueueNext() {
        if(!this.paused) {
            for(Runnable runnable0 = (Runnable)this.queue.poll(); runnable0 != null; runnable0 = this.paused ? null : ((Runnable)this.queue.poll())) {
                this.delegate.execute(runnable0);
            }
        }
    }

    @Override  // com.google.firebase.concurrent.PausableExecutor
    public void pause() {
        this.paused = true;
    }

    @Override  // com.google.firebase.concurrent.PausableExecutor
    public void resume() {
        this.paused = false;
        this.maybeEnqueueNext();
    }
}

