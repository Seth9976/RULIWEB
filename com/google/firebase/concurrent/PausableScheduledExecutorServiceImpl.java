package com.google.firebase.concurrent;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

final class PausableScheduledExecutorServiceImpl extends DelegatingScheduledExecutorService implements PausableScheduledExecutorService {
    private final PausableExecutorService delegate;

    PausableScheduledExecutorServiceImpl(PausableExecutorService pausableExecutorService0, ScheduledExecutorService scheduledExecutorService0) {
        super(pausableExecutorService0, scheduledExecutorService0);
        this.delegate = pausableExecutorService0;
    }

    @Override  // com.google.firebase.concurrent.PausableExecutor
    public boolean isPaused() {
        return this.delegate.isPaused();
    }

    @Override  // com.google.firebase.concurrent.PausableExecutor
    public void pause() {
        this.delegate.pause();
    }

    @Override  // com.google.firebase.concurrent.PausableExecutor
    public void resume() {
        this.delegate.resume();
    }

    @Override  // com.google.firebase.concurrent.DelegatingScheduledExecutorService
    public ScheduledFuture scheduleAtFixedRate(Runnable runnable0, long v, long v1, TimeUnit timeUnit0) {
        throw new UnsupportedOperationException();
    }

    @Override  // com.google.firebase.concurrent.DelegatingScheduledExecutorService
    public ScheduledFuture scheduleWithFixedDelay(Runnable runnable0, long v, long v1, TimeUnit timeUnit0) {
        throw new UnsupportedOperationException();
    }
}

