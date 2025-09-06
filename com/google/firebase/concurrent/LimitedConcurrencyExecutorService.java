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

final class LimitedConcurrencyExecutorService extends LimitedConcurrencyExecutor implements ExecutorService {
    private final ExecutorService delegate;

    LimitedConcurrencyExecutorService(ExecutorService executorService0, int v) {
        super(executorService0, v);
        this.delegate = executorService0;
    }

    @Override
    public boolean awaitTermination(long v, TimeUnit timeUnit0) throws InterruptedException {
        return this.delegate.awaitTermination(v, timeUnit0);
    }

    @Override
    public List invokeAll(Collection collection0) throws InterruptedException {
        return this.delegate.invokeAll(collection0);
    }

    @Override
    public List invokeAll(Collection collection0, long v, TimeUnit timeUnit0) throws InterruptedException {
        return this.delegate.invokeAll(collection0, v, timeUnit0);
    }

    @Override
    public Object invokeAny(Collection collection0) throws ExecutionException, InterruptedException {
        return this.delegate.invokeAny(collection0);
    }

    @Override
    public Object invokeAny(Collection collection0, long v, TimeUnit timeUnit0) throws ExecutionException, InterruptedException, TimeoutException {
        return this.delegate.invokeAny(collection0, v, timeUnit0);
    }

    @Override
    public boolean isShutdown() {
        return this.delegate.isShutdown();
    }

    @Override
    public boolean isTerminated() {
        return this.delegate.isTerminated();
    }

    // 检测为 Lambda 实现
    static Object lambda$submit$0(Runnable runnable0, Object object0) throws Exception [...]

    // 检测为 Lambda 实现
    static Object lambda$submit$1(Runnable runnable0) throws Exception [...]

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

