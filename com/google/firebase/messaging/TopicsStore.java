package com.google.firebase.messaging;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.Executor;

final class TopicsStore {
    private static final String DIVIDER_QUEUE_OPERATIONS = ",";
    static final String KEY_TOPIC_OPERATIONS_QUEUE = "topic_operation_queue";
    static final String PREFERENCES = "com.google.android.gms.appid";
    private final SharedPreferences sharedPreferences;
    private final Executor syncExecutor;
    private SharedPreferencesQueue topicOperationsQueue;
    private static WeakReference topicsStoreWeakReference;

    private TopicsStore(SharedPreferences sharedPreferences0, Executor executor0) {
        this.syncExecutor = executor0;
        this.sharedPreferences = sharedPreferences0;
    }

    boolean addTopicOperation(TopicOperation topicOperation0) {
        synchronized(this) {
            return this.topicOperationsQueue.add(topicOperation0.serialize());
        }
    }

    static void clearCaches() {
        synchronized(TopicsStore.class) {
            WeakReference weakReference0 = TopicsStore.topicsStoreWeakReference;
            if(weakReference0 != null) {
                weakReference0.clear();
            }
        }
    }

    void clearTopicOperations() {
        synchronized(this) {
            this.topicOperationsQueue.clear();
        }
    }

    public static TopicsStore getInstance(Context context0, Executor executor0) {
        synchronized(TopicsStore.class) {
            TopicsStore topicsStore0 = TopicsStore.topicsStoreWeakReference == null ? null : ((TopicsStore)TopicsStore.topicsStoreWeakReference.get());
            if(topicsStore0 == null) {
                topicsStore0 = new TopicsStore(context0.getSharedPreferences("com.google.android.gms.appid", 0), executor0);
                topicsStore0.initStore();
                TopicsStore.topicsStoreWeakReference = new WeakReference(topicsStore0);
            }
            return topicsStore0;
        }
    }

    TopicOperation getNextTopicOperation() {
        synchronized(this) {
            return TopicOperation.from(this.topicOperationsQueue.peek());
        }
    }

    List getOperations() {
        synchronized(this) {
            List list0 = this.topicOperationsQueue.toList();
            List list1 = new ArrayList(list0.size());
            for(Object object0: list0) {
                list1.add(TopicOperation.from(((String)object0)));
            }
            return list1;
        }
    }

    private void initStore() {
        synchronized(this) {
            this.topicOperationsQueue = SharedPreferencesQueue.createInstance(this.sharedPreferences, "topic_operation_queue", ",", this.syncExecutor);
        }
    }

    TopicOperation pollTopicOperation() {
        synchronized(this) {
            try {
                return TopicOperation.from(this.topicOperationsQueue.remove());
            }
            catch(NoSuchElementException unused_ex) {
                Log.e("FirebaseMessaging", "Polling operation queue failed");
                return null;
            }
        }
    }

    boolean removeTopicOperation(TopicOperation topicOperation0) {
        synchronized(this) {
            return this.topicOperationsQueue.remove(topicOperation0.serialize());
        }
    }
}

