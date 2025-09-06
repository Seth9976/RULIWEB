package com.google.firebase.messaging;

import com.google.android.gms.common.util.concurrent.NamedThreadFactory;
import com.google.firebase.messaging.threads.PoolableExecutors;
import com.google.firebase.messaging.threads.ThreadPriority;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

class FcmExecutors {
    private static final String THREAD_FILE = "Firebase-Messaging-File";
    static final String THREAD_FILE_IO = "Firebase-Messaging-File-Io";
    private static final String THREAD_INIT = "Firebase-Messaging-Init";
    private static final String THREAD_INTENT_HANDLE = "Firebase-Messaging-Intent-Handle";
    private static final String THREAD_NETWORK_IO = "Firebase-Messaging-Network-Io";
    static final String THREAD_RPC_TASK = "Firebase-Messaging-Rpc-Task";
    private static final String THREAD_TASK = "Firebase-Messaging-Task";
    private static final String THREAD_TOPICS_IO = "Firebase-Messaging-Topics-Io";

    private static Executor newCachedSingleThreadExecutor(String s) {
        return new ThreadPoolExecutor(0, 1, 30L, TimeUnit.SECONDS, new LinkedBlockingQueue(), new NamedThreadFactory(s));
    }

    static ExecutorService newFileExecutor() {
        return Executors.newSingleThreadExecutor(new NamedThreadFactory("Firebase-Messaging-File"));
    }

    static Executor newFileIOExecutor() {
        return FcmExecutors.newCachedSingleThreadExecutor("Firebase-Messaging-File-Io");
    }

    static ScheduledExecutorService newInitExecutor() {
        return new ScheduledThreadPoolExecutor(1, new NamedThreadFactory("Firebase-Messaging-Init"));
    }

    static ExecutorService newIntentHandleExecutor() {
        return PoolableExecutors.factory().newSingleThreadExecutor(new NamedThreadFactory("Firebase-Messaging-Intent-Handle"), ThreadPriority.HIGH_SPEED);
    }

    static ExecutorService newNetworkIOExecutor() {
        return Executors.newSingleThreadExecutor(new NamedThreadFactory("Firebase-Messaging-Network-Io"));
    }

    static Executor newRpcTasksExecutor() {
        return FcmExecutors.newCachedSingleThreadExecutor("Firebase-Messaging-Rpc-Task");
    }

    static ExecutorService newTaskExecutor() {
        return Executors.newSingleThreadExecutor(new NamedThreadFactory("Firebase-Messaging-Task"));
    }

    static ScheduledExecutorService newTopicsSyncExecutor() {
        return new ScheduledThreadPoolExecutor(1, new NamedThreadFactory("Firebase-Messaging-Topics-Io"));
    }
}

