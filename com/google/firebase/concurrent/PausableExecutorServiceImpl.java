package com.google.firebase.concurrent;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

final class PausableExecutorServiceImpl implements PausableExecutorService {
    private final ExecutorService delegateService;
    private final PausableExecutor pausableDelegate;

    PausableExecutorServiceImpl(boolean z, ExecutorService executorService0) {
        this.delegateService = executorService0;
        this.pausableDelegate = new PausableExecutorImpl(z, executorService0);
    }

    @Override
    public boolean awaitTermination(long v, TimeUnit timeUnit0) throws InterruptedException {
        return this.delegateService.awaitTermination(v, timeUnit0);
    }

    @Override
    public void execute(Runnable runnable0) {
        this.pausableDelegate.execute(runnable0);
    }

    @Override
    public List invokeAll(Collection collection0) throws InterruptedException {
        return this.delegateService.invokeAll(collection0);
    }

    @Override
    public List invokeAll(Collection collection0, long v, TimeUnit timeUnit0) throws InterruptedException {
        return this.delegateService.invokeAll(collection0, v, timeUnit0);
    }

    @Override
    public Object invokeAny(Collection collection0) throws ExecutionException, InterruptedException {
        return this.delegateService.invokeAny(collection0);
    }

    @Override
    public Object invokeAny(Collection collection0, long v, TimeUnit timeUnit0) throws ExecutionException, InterruptedException, TimeoutException {
        return this.delegateService.invokeAny(collection0, v, timeUnit0);
    }

    @Override  // com.google.firebase.concurrent.PausableExecutor
    public boolean isPaused() {
        return this.pausableDelegate.isPaused();
    }

    @Override
    public boolean isShutdown() {
        return this.delegateService.isShutdown();
    }

    @Override
    public boolean isTerminated() {
        return this.delegateService.isTerminated();
    }

    // 检测为 Lambda 实现
    static Object lambda$submit$0(Runnable runnable0, Object object0) throws Exception [...]

    // 检测为 Lambda 实现
    static Object lambda$submit$1(Runnable runnable0) throws Exception [...]

    @Override  // com.google.firebase.concurrent.PausableExecutor
    public void pause() {
        this.pausableDelegate.pause();
    }

    @Override  // com.google.firebase.concurrent.PausableExecutor
    public void resume() {
        this.pausableDelegate.resume();
    }

    @Override
    public void shutdown() {
        throw new UnsupportedOperationException("Shutting down is not allowed.");
    }

    @Override
    public List shutdownNow() {
        throw new UnsupportedOperationException("Shutting down is not allowed.");
    }

    @Override
    public Future submit(Runnable runnable0) {
        return this.submit(() -> {
            runnable0.run();
            return null;
        });
    }

    @Override
    public Future submit(Runnable runnable0, Object object0) {
        return this.submit(() -> {
            runnable0.run();
            return object0;
        });
    }

    @Override
    public Future submit(Callable callable0) {
        Future future0 = new FutureTask(callable0);
        this.execute(((Runnable)future0));
        return future0;
    }
}

