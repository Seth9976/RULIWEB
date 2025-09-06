package com.google.firebase.messaging;

import android.content.SharedPreferences.Editor;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Log;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;

final class SharedPreferencesQueue {
    private boolean bulkOperation;
    final ArrayDeque internalQueue;
    private final String itemSeparator;
    private final String queueName;
    private final SharedPreferences sharedPreferences;
    private final Executor syncExecutor;

    // 检测为 Lambda 实现
    public static void $r8$lambda$E9XW8fIBuBlfy6ibDQdajXfq4Mo(SharedPreferencesQueue sharedPreferencesQueue0) [...]

    private SharedPreferencesQueue(SharedPreferences sharedPreferences0, String s, String s1, Executor executor0) {
        this.internalQueue = new ArrayDeque();
        this.bulkOperation = false;
        this.sharedPreferences = sharedPreferences0;
        this.queueName = s;
        this.itemSeparator = s1;
        this.syncExecutor = executor0;
    }

    public boolean add(String s) {
        if(!TextUtils.isEmpty(s) && !s.contains(this.itemSeparator)) {
            ArrayDeque arrayDeque0 = this.internalQueue;
            return this.checkAndSyncState(this.internalQueue.add(s));
        }
        return false;
    }

    public void beginTransaction() {
        this.bulkOperation = true;
    }

    void beginTransactionSync() {
        synchronized(this.internalQueue) {
            this.beginTransaction();
        }
    }

    private String checkAndSyncState(String s) {
        this.checkAndSyncState(s != null);
        return s;
    }

    private boolean checkAndSyncState(boolean z) {
        if(z && !this.bulkOperation) {
            this.syncStateAsync();
        }
        return z;
    }

    public void clear() {
        synchronized(this.internalQueue) {
            this.internalQueue.clear();
            this.checkAndSyncState(true);
        }
    }

    public void commitTransaction() {
        this.bulkOperation = false;
        this.syncStateAsync();
    }

    void commitTransactionSync() {
        synchronized(this.internalQueue) {
            this.commitTransaction();
        }
    }

    static SharedPreferencesQueue createInstance(SharedPreferences sharedPreferences0, String s, String s1, Executor executor0) {
        SharedPreferencesQueue sharedPreferencesQueue0 = new SharedPreferencesQueue(sharedPreferences0, s, s1, executor0);
        sharedPreferencesQueue0.initQueue();
        return sharedPreferencesQueue0;
    }

    private void initQueue() {
        synchronized(this.internalQueue) {
            this.internalQueue.clear();
            String s = this.sharedPreferences.getString(this.queueName, "");
            if(!TextUtils.isEmpty(s) && s.contains(this.itemSeparator)) {
                String[] arr_s = s.split(this.itemSeparator, -1);
                if(arr_s.length == 0) {
                    Log.e("FirebaseMessaging", "Corrupted queue. Please check the queue contents and item separator provided");
                }
                for(int v1 = 0; v1 < arr_s.length; ++v1) {
                    String s1 = arr_s[v1];
                    if(!TextUtils.isEmpty(s1)) {
                        this.internalQueue.add(s1);
                    }
                }
            }
        }
    }

    public String peek() {
        synchronized(this.internalQueue) {
        }
        return (String)this.internalQueue.peek();
    }

    public String remove() {
        synchronized(this.internalQueue) {
        }
        return this.checkAndSyncState(((String)this.internalQueue.remove()));
    }

    public boolean remove(Object object0) {
        synchronized(this.internalQueue) {
        }
        return this.checkAndSyncState(this.internalQueue.remove(object0));
    }

    public String serialize() {
        StringBuilder stringBuilder0 = new StringBuilder();
        for(Object object0: this.internalQueue) {
            stringBuilder0.append(((String)object0));
            stringBuilder0.append(this.itemSeparator);
        }
        return stringBuilder0.toString();
    }

    public String serializeSync() {
        synchronized(this.internalQueue) {
        }
        return this.serialize();
    }

    public int size() {
        synchronized(this.internalQueue) {
        }
        return this.internalQueue.size();
    }

    private void syncState() {
        synchronized(this.internalQueue) {
            SharedPreferences.Editor sharedPreferences$Editor0 = this.sharedPreferences.edit();
            String s = this.serialize();
            sharedPreferences$Editor0.putString(this.queueName, s).commit();
        }
    }

    private void syncStateAsync() {
        SharedPreferencesQueue..ExternalSyntheticLambda0 sharedPreferencesQueue$$ExternalSyntheticLambda00 = () -> this.syncState();
        this.syncExecutor.execute(sharedPreferencesQueue$$ExternalSyntheticLambda00);
    }

    public List toList() {
        synchronized(this.internalQueue) {
        }
        return new ArrayList(this.internalQueue);
    }
}

