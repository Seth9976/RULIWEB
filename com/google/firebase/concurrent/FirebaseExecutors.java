package com.google.firebase.concurrent;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ScheduledExecutorService;

public class FirebaseExecutors {
    static enum DirectExecutor implements Executor {
        INSTANCE;

        @Override
        public void execute(Runnable runnable0) {
            runnable0.run();
        }
    }

    public static Executor directExecutor() {
        return DirectExecutor.INSTANCE;
    }

    public static Executor newLimitedConcurrencyExecutor(Executor executor0, int v) {
        return new LimitedConcurrencyExecutor(executor0, v);
    }

    public static ExecutorService newLimitedConcurrencyExecutorService(ExecutorService executorService0, int v) {
        return new LimitedConcurrencyExecutorService(executorService0, v);
    }

    public static ScheduledExecutorService newLimitedConcurrencyScheduledExecutorService(ExecutorService executorService0, int v) {
        return new DelegatingScheduledExecutorService(FirebaseExecutors.newLimitedConcurrencyExecutorService(executorService0, v), ((ScheduledExecutorService)ExecutorsRegistrar.SCHEDULER.get()));
    }

    public static PausableExecutor newPausableExecutor(Executor executor0) {
        return new PausableExecutorImpl(false, executor0);
    }

    public static PausableExecutorService newPausableExecutorService(ExecutorService executorService0) {
        return new PausableExecutorServiceImpl(false, executorService0);
    }

    public static PausableScheduledExecutorService newPausableScheduledExecutorService(ScheduledExecutorService scheduledExecutorService0) {
        return new PausableScheduledExecutorServiceImpl(FirebaseExecutors.newPausableExecutorService(scheduledExecutorService0), ((ScheduledExecutorService)ExecutorsRegistrar.SCHEDULER.get()));
    }

    public static Executor newSequentialExecutor(Executor executor0) {
        return new SequentialExecutor(executor0);
    }
}

