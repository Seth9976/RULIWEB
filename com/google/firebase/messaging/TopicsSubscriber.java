package com.google.firebase.messaging;

import android.content.Context;
import android.os.Build.VERSION;
import android.util.Log;
import androidx.collection.ArrayMap;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.gms.tasks.Tasks;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

class TopicsSubscriber {
    static final String ERROR_INTERNAL_SERVER_ERROR = "INTERNAL_SERVER_ERROR";
    static final String ERROR_SERVICE_NOT_AVAILABLE = "SERVICE_NOT_AVAILABLE";
    private static final long MAX_DELAY_SEC = 0L;
    private static final long MIN_DELAY_SEC = 30L;
    private static final long RPC_TIMEOUT_SEC = 30L;
    private final Context context;
    private final FirebaseMessaging firebaseMessaging;
    private final Metadata metadata;
    private final Map pendingOperations;
    private final GmsRpc rpc;
    private final TopicsStore store;
    private final ScheduledExecutorService syncExecutor;
    private boolean syncScheduledOrRunning;

    static {
        TopicsSubscriber.MAX_DELAY_SEC = TimeUnit.HOURS.toSeconds(8L);
    }

    private TopicsSubscriber(FirebaseMessaging firebaseMessaging0, Metadata metadata0, TopicsStore topicsStore0, GmsRpc gmsRpc0, Context context0, ScheduledExecutorService scheduledExecutorService0) {
        this.pendingOperations = new ArrayMap();
        this.syncScheduledOrRunning = false;
        this.firebaseMessaging = firebaseMessaging0;
        this.metadata = metadata0;
        this.store = topicsStore0;
        this.rpc = gmsRpc0;
        this.context = context0;
        this.syncExecutor = scheduledExecutorService0;
    }

    private void addToPendingOperations(TopicOperation topicOperation0, TaskCompletionSource taskCompletionSource0) {
        ArrayDeque arrayDeque0;
        synchronized(this.pendingOperations) {
            String s = topicOperation0.serialize();
            if(this.pendingOperations.containsKey(s)) {
                arrayDeque0 = (ArrayDeque)this.pendingOperations.get(s);
            }
            else {
                ArrayDeque arrayDeque1 = new ArrayDeque();
                this.pendingOperations.put(s, arrayDeque1);
                arrayDeque0 = arrayDeque1;
            }
            arrayDeque0.add(taskCompletionSource0);
        }
    }

    private static void awaitTask(Task task0) throws IOException {
        try {
            Tasks.await(task0, 30L, TimeUnit.SECONDS);
            return;
        }
        catch(ExecutionException executionException0) {
            Throwable throwable0 = executionException0.getCause();
            if(throwable0 instanceof IOException) {
                throw (IOException)throwable0;
            }
            if(throwable0 instanceof RuntimeException) {
                throw (RuntimeException)throwable0;
            }
            throw new IOException(executionException0);
        }
        catch(InterruptedException | TimeoutException exception0) {
            throw new IOException("SERVICE_NOT_AVAILABLE", exception0);
        }
    }

    private void blockingSubscribeToTopic(String s) throws IOException {
        String s1 = this.firebaseMessaging.blockingGetToken();
        TopicsSubscriber.awaitTask(this.rpc.subscribeToTopic(s1, s));
    }

    private void blockingUnsubscribeFromTopic(String s) throws IOException {
        String s1 = this.firebaseMessaging.blockingGetToken();
        TopicsSubscriber.awaitTask(this.rpc.unsubscribeFromTopic(s1, s));
    }

    static Task createInstance(FirebaseMessaging firebaseMessaging0, Metadata metadata0, GmsRpc gmsRpc0, Context context0, ScheduledExecutorService scheduledExecutorService0) {
        return Tasks.call(scheduledExecutorService0, () -> new TopicsSubscriber(firebaseMessaging0, metadata0, TopicsStore.getInstance(context0, scheduledExecutorService0), gmsRpc0, context0, scheduledExecutorService0));
    }

    TopicsStore getStore() {
        return this.store;
    }

    boolean hasPendingOperation() {
        return this.store.getNextTopicOperation() != null;
    }

    // 去混淆评级： 低(30)
    static boolean isDebugLogEnabled() {
        return Log.isLoggable("FirebaseMessaging", 3) || Build.VERSION.SDK_INT == 23 && Log.isLoggable("FirebaseMessaging", 3);
    }

    boolean isSyncScheduledOrRunning() {
        synchronized(this) {
        }
        return this.syncScheduledOrRunning;
    }

    // 检测为 Lambda 实现
    static TopicsSubscriber lambda$createInstance$0(Context context0, ScheduledExecutorService scheduledExecutorService0, FirebaseMessaging firebaseMessaging0, Metadata metadata0, GmsRpc gmsRpc0) throws Exception [...]

    private void markCompletePendingOperation(TopicOperation topicOperation0) {
        synchronized(this.pendingOperations) {
            String s = topicOperation0.serialize();
            if(!this.pendingOperations.containsKey(s)) {
                return;
            }
            ArrayDeque arrayDeque0 = (ArrayDeque)this.pendingOperations.get(s);
            TaskCompletionSource taskCompletionSource0 = (TaskCompletionSource)arrayDeque0.poll();
            if(taskCompletionSource0 != null) {
                taskCompletionSource0.setResult(null);
            }
            if(arrayDeque0.isEmpty()) {
                this.pendingOperations.remove(s);
            }
        }
    }

    boolean performTopicOperation(TopicOperation topicOperation0) throws IOException {
        try {
            switch(topicOperation0.getOperation()) {
                case "S": {
                    this.blockingSubscribeToTopic(topicOperation0.getTopic());
                    if(TopicsSubscriber.isDebugLogEnabled()) {
                        Log.d("FirebaseMessaging", "Subscribe to topic: " + topicOperation0.getTopic() + " succeeded.");
                        return true;
                    }
                    break;
                }
                case "U": {
                    this.blockingUnsubscribeFromTopic(topicOperation0.getTopic());
                    if(TopicsSubscriber.isDebugLogEnabled()) {
                        Log.d("FirebaseMessaging", "Unsubscribe from topic: " + topicOperation0.getTopic() + " succeeded.");
                        return true;
                    }
                    break;
                }
                default: {
                    if(TopicsSubscriber.isDebugLogEnabled()) {
                        Log.d("FirebaseMessaging", "Unknown topic operation" + topicOperation0 + ".");
                        return true;
                    }
                }
            }
            return true;
        }
        catch(IOException iOException0) {
            if(!"SERVICE_NOT_AVAILABLE".equals(iOException0.getMessage()) && !"INTERNAL_SERVER_ERROR".equals(iOException0.getMessage())) {
                if(iOException0.getMessage() != null) {
                    throw iOException0;
                }
                Log.e("FirebaseMessaging", "Topic operation failed without exception message. Will retry Topic operation.");
                return false;
            }
            Log.e("FirebaseMessaging", "Topic operation failed: " + iOException0.getMessage() + ". Will retry Topic operation.");
            return false;
        }
    }

    void scheduleSyncTaskWithDelaySeconds(Runnable runnable0, long v) {
        this.syncExecutor.schedule(runnable0, v, TimeUnit.SECONDS);
    }

    Task scheduleTopicOperation(TopicOperation topicOperation0) {
        this.store.addTopicOperation(topicOperation0);
        TaskCompletionSource taskCompletionSource0 = new TaskCompletionSource();
        this.addToPendingOperations(topicOperation0, taskCompletionSource0);
        return taskCompletionSource0.getTask();
    }

    void setSyncScheduledOrRunning(boolean z) {
        synchronized(this) {
            this.syncScheduledOrRunning = z;
        }
    }

    private void startSync() {
        if(!this.isSyncScheduledOrRunning()) {
            this.syncWithDelaySecondsInternal(0L);
        }
    }

    void startTopicsSyncIfNecessary() {
        if(this.hasPendingOperation()) {
            this.startSync();
        }
    }

    Task subscribeToTopic(String s) {
        Task task0 = this.scheduleTopicOperation(TopicOperation.subscribe(s));
        this.startTopicsSyncIfNecessary();
        return task0;
    }

    boolean syncTopics() throws IOException {
        TopicOperation topicOperation0;
        while(true) {
            synchronized(this) {
                topicOperation0 = this.store.getNextTopicOperation();
                if(topicOperation0 == null) {
                    if(TopicsSubscriber.isDebugLogEnabled()) {
                        Log.d("FirebaseMessaging", "topic sync succeeded");
                    }
                    return true;
                }
            }
            if(!this.performTopicOperation(topicOperation0)) {
                return false;
            }
            this.store.removeTopicOperation(topicOperation0);
            this.markCompletePendingOperation(topicOperation0);
        }
    }

    void syncWithDelaySecondsInternal(long v) {
        this.scheduleSyncTaskWithDelaySeconds(new TopicsSyncTask(this, this.context, this.metadata, Math.min(Math.max(30L, 2L * v), TopicsSubscriber.MAX_DELAY_SEC)), v);
        this.setSyncScheduledOrRunning(true);
    }

    Task unsubscribeFromTopic(String s) {
        Task task0 = this.scheduleTopicOperation(TopicOperation.unsubscribe(s));
        this.startTopicsSyncIfNecessary();
        return task0;
    }
}

