package com.google.firebase.messaging.threads;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class PoolableExecutors {
    static class DefaultExecutorFactory implements ExecutorFactory {
        private static final long CORE_THREAD_TIMEOUT_SECS = 60L;

        private DefaultExecutorFactory() {
        }

        DefaultExecutorFactory(com.google.firebase.messaging.threads.PoolableExecutors.1 poolableExecutors$10) {
        }

        @Override  // com.google.firebase.messaging.threads.ExecutorFactory
        public void executeOneOff(String s, String s1, ThreadPriority threadPriority0, Runnable runnable0) {
            new Thread(runnable0, s1).start();
        }

        @Override  // com.google.firebase.messaging.threads.ExecutorFactory
        public ScheduledExecutorService newScheduledThreadPool(int v, ThreadPriority threadPriority0) {
            return Executors.unconfigurableScheduledExecutorService(Executors.newScheduledThreadPool(v));
        }

        @Override  // com.google.firebase.messaging.threads.ExecutorFactory
        public ScheduledExecutorService newScheduledThreadPool(int v, ThreadFactory threadFactory0, ThreadPriority threadPriority0) {
            return Executors.unconfigurableScheduledExecutorService(Executors.newScheduledThreadPool(v, threadFactory0));
        }

        @Override  // com.google.firebase.messaging.threads.ExecutorFactory
        public ExecutorService newSingleThreadExecutor(ThreadPriority threadPriority0) {
            return this.newThreadPool(1, threadPriority0);
        }

        @Override  // com.google.firebase.messaging.threads.ExecutorFactory
        public ExecutorService newSingleThreadExecutor(ThreadFactory threadFactory0, ThreadPriority threadPriority0) {
            return this.newThreadPool(1, threadFactory0, threadPriority0);
        }

        @Override  // com.google.firebase.messaging.threads.ExecutorFactory
        public ExecutorService newThreadPool(int v, ThreadPriority threadPriority0) {
            return this.newThreadPool(v, Executors.defaultThreadFactory(), threadPriority0);
        }

        @Override  // com.google.firebase.messaging.threads.ExecutorFactory
        public ExecutorService newThreadPool(int v, ThreadFactory threadFactory0, ThreadPriority threadPriority0) {
            ThreadPoolExecutor threadPoolExecutor0 = new ThreadPoolExecutor(v, v, 60L, TimeUnit.SECONDS, new LinkedBlockingQueue(), threadFactory0);
            threadPoolExecutor0.allowCoreThreadTimeOut(true);
            return Executors.unconfigurableExecutorService(threadPoolExecutor0);
        }

        @Override  // com.google.firebase.messaging.threads.ExecutorFactory
        public ExecutorService newThreadPool(ThreadPriority threadPriority0) {
            return Executors.unconfigurableExecutorService(Executors.newCachedThreadPool());
        }

        @Override  // com.google.firebase.messaging.threads.ExecutorFactory
        public ExecutorService newThreadPool(ThreadFactory threadFactory0, ThreadPriority threadPriority0) {
            return Executors.unconfigurableExecutorService(Executors.newCachedThreadPool(threadFactory0));
        }

        @Override  // com.google.firebase.messaging.threads.ExecutorFactory
        public Future submitOneOff(String s, String s1, ThreadPriority threadPriority0, Runnable runnable0) {
            Future future0 = new FutureTask(runnable0, null);
            new Thread(((Runnable)future0), s1).start();
            return future0;
        }
    }

    private static final ExecutorFactory DEFAULT_INSTANCE;
    private static volatile ExecutorFactory instance;

    static {
        DefaultExecutorFactory poolableExecutors$DefaultExecutorFactory0 = new DefaultExecutorFactory(null);
        PoolableExecutors.DEFAULT_INSTANCE = poolableExecutors$DefaultExecutorFactory0;
        PoolableExecutors.instance = poolableExecutors$DefaultExecutorFactory0;
    }

    public static ExecutorFactory factory() {
        return PoolableExecutors.instance;
    }

    static void installExecutorFactory(ExecutorFactory executorFactory0) {
        if(PoolableExecutors.instance != PoolableExecutors.DEFAULT_INSTANCE) {
            throw new IllegalStateException("Trying to install an ExecutorFactory twice!");
        }
        PoolableExecutors.instance = executorFactory0;
    }

    class com.google.firebase.messaging.threads.PoolableExecutors.1 {
    }

}

