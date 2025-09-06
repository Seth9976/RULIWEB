package com.google.firebase.concurrent;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

class DelegatingScheduledExecutorService implements ScheduledExecutorService {
    private final ExecutorService delegate;
    private final ScheduledExecutorService scheduler;

    DelegatingScheduledExecutorService(ExecutorService executorService0, ScheduledExecutorService scheduledExecutorService0) {
        this.delegate = executorService0;
        this.scheduler = scheduledExecutorService0;
    }

    @Override
    public boolean awaitTermination(long v, TimeUnit timeUnit0) throws InterruptedException {
        return this.delegate.awaitTermination(v, timeUnit0);
    }

    @Override
    public void execute(Runnable runnable0) {
        this.delegate.execute(runnable0);
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
    static void lambda$schedule$0(Runnable runnable0, Completer delegatingScheduledFuture$Completer0) [...]

    // 检测为 Lambda 实现
    void lambda$schedule$1$com-google-firebase-concurrent-DelegatingScheduledExecutorService(Runnable runnable0, Completer delegatingScheduledFuture$Completer0) [...]

    // 检测为 Lambda 实现
    ScheduledFuture lambda$schedule$2$com-google-firebase-concurrent-DelegatingScheduledExecutorService(Runnable runnable0, long v, TimeUnit timeUnit0, Completer delegatingScheduledFuture$Completer0) [...]

    // 检测为 Lambda 实现
    static void lambda$schedule$3(Callable callable0, Completer delegatingScheduledFuture$Completer0) [...]

    // 检测为 Lambda 实现
    Future lambda$schedule$4$com-google-firebase-concurrent-DelegatingScheduledExecutorService(Callable callable0, Completer delegatingScheduledFuture$Completer0) throws Exception [...]

    // 检测为 Lambda 实现
    ScheduledFuture lambda$schedule$5$com-google-firebase-concurrent-DelegatingScheduledExecutorService(Callable callable0, long v, TimeUnit timeUnit0, Completer delegatingScheduledFuture$Completer0) [...]

    // 检测为 Lambda 实现
    static void lambda$scheduleAtFixedRate$6(Runnable runnable0, Completer delegatingScheduledFuture$Completer0) [...]

    // 检测为 Lambda 实现
    void lambda$scheduleAtFixedRate$7$com-google-firebase-concurrent-DelegatingScheduledExecutorService(Runnable runnable0, Completer delegatingScheduledFuture$Completer0) [...]

    // 检测为 Lambda 实现
    ScheduledFuture lambda$scheduleAtFixedRate$8$com-google-firebase-concurrent-DelegatingScheduledExecutorService(Runnable runnable0, long v, long v1, TimeUnit timeUnit0, Completer delegatingScheduledFuture$Completer0) [...]

    // 检测为 Lambda 实现
    void lambda$scheduleWithFixedDelay$10$com-google-firebase-concurrent-DelegatingScheduledExecutorService(Runnable runnable0, Completer delegatingScheduledFuture$Completer0) [...]

    // 检测为 Lambda 实现
    ScheduledFuture lambda$scheduleWithFixedDelay$11$com-google-firebase-concurrent-DelegatingScheduledExecutorService(Runnable runnable0, long v, long v1, TimeUnit timeUnit0, Completer delegatingScheduledFuture$Completer0) [...]

    // 检测为 Lambda 实现
    static void lambda$scheduleWithFixedDelay$9(Runnable runnable0, Completer delegatingScheduledFuture$Completer0) [...]

    @Override
    public ScheduledFuture schedule(Runnable runnable0, long v, TimeUnit timeUnit0) {
        return new DelegatingScheduledFuture((Completer delegatingScheduledFuture$Completer0) -> {
            DelegatingScheduledExecutorService..ExternalSyntheticLambda4 delegatingScheduledExecutorService$$ExternalSyntheticLambda40 = () -> {
                DelegatingScheduledExecutorService..ExternalSyntheticLambda6 delegatingScheduledExecutorService$$ExternalSyntheticLambda60 = () -> try {
                    runnable0.run();
                    delegatingScheduledFuture$Completer0.set(null);
                }
                catch(Exception exception0) {
                    delegatingScheduledFuture$Completer0.setException(exception0);
                };
                this.delegate.execute(delegatingScheduledExecutorService$$ExternalSyntheticLambda60);
            };
            return this.scheduler.schedule(delegatingScheduledExecutorService$$ExternalSyntheticLambda40, v, timeUnit0);
        });
    }

    @Override
    public ScheduledFuture schedule(Callable callable0, long v, TimeUnit timeUnit0) {
        return new DelegatingScheduledFuture((Completer delegatingScheduledFuture$Completer0) -> {
            DelegatingScheduledExecutorService..ExternalSyntheticLambda10 delegatingScheduledExecutorService$$ExternalSyntheticLambda100 = () -> {
                DelegatingScheduledExecutorService..ExternalSyntheticLambda9 delegatingScheduledExecutorService$$ExternalSyntheticLambda90 = () -> try {
                    delegatingScheduledFuture$Completer0.set(callable0.call());
                }
                catch(Exception exception0) {
                    delegatingScheduledFuture$Completer0.setException(exception0);
                };
                return this.delegate.submit(delegatingScheduledExecutorService$$ExternalSyntheticLambda90);
            };
            return this.scheduler.schedule(delegatingScheduledExecutorService$$ExternalSyntheticLambda100, v, timeUnit0);
        });
    }

    @Override
    public ScheduledFuture scheduleAtFixedRate(Runnable runnable0, long v, long v1, TimeUnit timeUnit0) {
        return new DelegatingScheduledFuture((Completer delegatingScheduledFuture$Completer0) -> {
            DelegatingScheduledExecutorService..ExternalSyntheticLambda3 delegatingScheduledExecutorService$$ExternalSyntheticLambda30 = () -> {
                DelegatingScheduledExecutorService..ExternalSyntheticLambda11 delegatingScheduledExecutorService$$ExternalSyntheticLambda110 = () -> try {
                    runnable0.run();
                }
                catch(Exception exception0) {
                    delegatingScheduledFuture$Completer0.setException(exception0);
                    throw exception0;
                };
                this.delegate.execute(delegatingScheduledExecutorService$$ExternalSyntheticLambda110);
            };
            return this.scheduler.scheduleAtFixedRate(delegatingScheduledExecutorService$$ExternalSyntheticLambda30, v, v1, timeUnit0);
        });
    }

    @Override
    public ScheduledFuture scheduleWithFixedDelay(Runnable runnable0, long v, long v1, TimeUnit timeUnit0) {
        return new DelegatingScheduledFuture((Completer delegatingScheduledFuture$Completer0) -> {
            DelegatingScheduledExecutorService..ExternalSyntheticLambda2 delegatingScheduledExecutorService$$ExternalSyntheticLambda20 = () -> {
                DelegatingScheduledExecutorService..ExternalSyntheticLambda1 delegatingScheduledExecutorService$$ExternalSyntheticLambda10 = () -> try {
                    runnable0.run();
                }
                catch(Exception exception0) {
                    delegatingScheduledFuture$Completer0.setException(exception0);
                };
                this.delegate.execute(delegatingScheduledExecutorService$$ExternalSyntheticLambda10);
            };
            return this.scheduler.scheduleWithFixedDelay(delegatingScheduledExecutorService$$ExternalSyntheticLambda20, v, v1, timeUnit0);
        });
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
        return this.delegate.submit(runnable0);
    }

    @Override
    public Future submit(Runnable runnable0, Object object0) {
        return this.delegate.submit(runnable0, object0);
    }

    @Override
    public Future submit(Callable callable0) {
        return this.delegate.submit(callable0);
    }
}

