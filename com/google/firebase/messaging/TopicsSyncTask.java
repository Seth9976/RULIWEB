package com.google.firebase.messaging;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build.VERSION;
import android.os.PowerManager.WakeLock;
import android.os.PowerManager;
import android.util.Log;
import java.io.IOException;

class TopicsSyncTask implements Runnable {
    class ConnectivityChangeReceiver extends BroadcastReceiver {
        private TopicsSyncTask task;

        public ConnectivityChangeReceiver(TopicsSyncTask topicsSyncTask1) {
            this.task = topicsSyncTask1;
        }

        @Override  // android.content.BroadcastReceiver
        public void onReceive(Context context0, Intent intent0) {
            synchronized(this) {
                TopicsSyncTask topicsSyncTask0 = this.task;
                if(topicsSyncTask0 == null) {
                    return;
                }
                if(!topicsSyncTask0.isDeviceConnected()) {
                    return;
                }
                if(TopicsSyncTask.isLoggable()) {
                    Log.d("FirebaseMessaging", "Connectivity changed. Starting background sync.");
                }
                this.task.topicsSubscriber.scheduleSyncTaskWithDelaySeconds(this.task, 0L);
                context0.unregisterReceiver(this);
                this.task = null;
            }
        }

        public void registerReceiver() {
            if(TopicsSyncTask.isLoggable()) {
                Log.d("FirebaseMessaging", "Connectivity change received registered");
            }
            TopicsSyncTask.this.context.registerReceiver(this, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
        }
    }

    private static final Object TOPIC_SYNC_TASK_LOCK;
    private final Context context;
    private static Boolean hasAccessNetworkStatePermission;
    private static Boolean hasWakeLockPermission;
    private final Metadata metadata;
    private final long nextDelaySeconds;
    private final PowerManager.WakeLock syncWakeLock;
    private final TopicsSubscriber topicsSubscriber;

    static {
        TopicsSyncTask.TOPIC_SYNC_TASK_LOCK = new Object();
    }

    TopicsSyncTask(TopicsSubscriber topicsSubscriber0, Context context0, Metadata metadata0, long v) {
        this.topicsSubscriber = topicsSubscriber0;
        this.context = context0;
        this.nextDelaySeconds = v;
        this.metadata = metadata0;
        this.syncWakeLock = ((PowerManager)context0.getSystemService("power")).newWakeLock(1, "wake:com.google.firebase.messaging");
    }

    private static String createPermissionMissingLog(String s) [...] // Inlined contents

    private static boolean hasAccessNetworkStatePermission(Context context0) {
        Boolean boolean0;
        synchronized(TopicsSyncTask.TOPIC_SYNC_TASK_LOCK) {
            boolean0 = Boolean.valueOf((TopicsSyncTask.hasAccessNetworkStatePermission == null ? TopicsSyncTask.hasPermission(context0, "android.permission.ACCESS_NETWORK_STATE", null) : TopicsSyncTask.hasAccessNetworkStatePermission.booleanValue()));
            TopicsSyncTask.hasAccessNetworkStatePermission = boolean0;
        }
        return boolean0.booleanValue();
    }

    private static boolean hasPermission(Context context0, String s, Boolean boolean0) {
        if(boolean0 != null) {
            return boolean0.booleanValue();
        }
        boolean z = context0.checkCallingOrSelfPermission(s) == 0;
        if(!z && Log.isLoggable("FirebaseMessaging", 3)) {
            Log.d("FirebaseMessaging", "Missing Permission: " + s + ". This permission should normally be included by the manifest merger, but may needed to be manually added to your manifest");
        }
        return z;
    }

    private static boolean hasWakeLockPermission(Context context0) {
        Boolean boolean0;
        synchronized(TopicsSyncTask.TOPIC_SYNC_TASK_LOCK) {
            boolean0 = Boolean.valueOf((TopicsSyncTask.hasWakeLockPermission == null ? TopicsSyncTask.hasPermission(context0, "android.permission.WAKE_LOCK", null) : TopicsSyncTask.hasWakeLockPermission.booleanValue()));
            TopicsSyncTask.hasWakeLockPermission = boolean0;
        }
        return boolean0.booleanValue();
    }

    private boolean isDeviceConnected() {
        synchronized(this) {
            ConnectivityManager connectivityManager0 = (ConnectivityManager)this.context.getSystemService("connectivity");
            NetworkInfo networkInfo0 = connectivityManager0 == null ? null : connectivityManager0.getActiveNetworkInfo();
            return networkInfo0 != null && networkInfo0.isConnected();
        }
    }

    // 去混淆评级： 低(30)
    private static boolean isLoggable() {
        return Log.isLoggable("FirebaseMessaging", 3) || Build.VERSION.SDK_INT == 23 && Log.isLoggable("FirebaseMessaging", 3);
    }

    @Override
    public void run() {
        PowerManager.WakeLock powerManager$WakeLock0;
        if(TopicsSyncTask.hasWakeLockPermission(this.context)) {
            this.syncWakeLock.acquire(Constants.WAKE_LOCK_ACQUIRE_TIMEOUT_MILLIS);
        }
        try {
            this.topicsSubscriber.setSyncScheduledOrRunning(true);
            if(!this.metadata.isGmscorePresent()) {
                goto label_16;
            }
            else if(!TopicsSyncTask.hasAccessNetworkStatePermission(this.context) || this.isDeviceConnected()) {
                if(this.topicsSubscriber.syncTopics()) {
                    this.topicsSubscriber.setSyncScheduledOrRunning(false);
                }
                else {
                    this.topicsSubscriber.syncWithDelaySecondsInternal(this.nextDelaySeconds);
                }
                goto label_9;
            }
            else {
                goto label_12;
            }
            return;
        }
        catch(IOException iOException0) {
            goto label_19;
        }
        catch(Throwable throwable0) {
            goto label_25;
        }
    label_9:
        if(TopicsSyncTask.hasWakeLockPermission(this.context)) {
            try {
                powerManager$WakeLock0 = this.syncWakeLock;
                goto label_32;
            }
            catch(RuntimeException unused_ex) {
                Log.i("FirebaseMessaging", "TopicsSyncTask\'s wakelock was already released due to timeout.");
                return;
            }
            try {
            label_12:
                new ConnectivityChangeReceiver(this, this).registerReceiver();
            }
            catch(IOException iOException0) {
                goto label_19;
            }
            catch(Throwable throwable0) {
                goto label_25;
            }
            if(TopicsSyncTask.hasWakeLockPermission(this.context)) {
                try {
                    powerManager$WakeLock0 = this.syncWakeLock;
                    goto label_32;
                }
                catch(RuntimeException unused_ex) {
                    Log.i("FirebaseMessaging", "TopicsSyncTask\'s wakelock was already released due to timeout.");
                    return;
                }
                try {
                    try {
                    label_16:
                        this.topicsSubscriber.setSyncScheduledOrRunning(false);
                        goto label_30;
                    }
                    catch(IOException iOException0) {
                    }
                label_19:
                    Log.e("FirebaseMessaging", "Failed to sync topics. Won\'t retry sync. " + iOException0.getMessage());
                    this.topicsSubscriber.setSyncScheduledOrRunning(false);
                }
                catch(Throwable throwable0) {
                    goto label_25;
                }
                if(TopicsSyncTask.hasWakeLockPermission(this.context)) {
                    try {
                        this.syncWakeLock.release();
                    }
                    catch(RuntimeException unused_ex) {
                        Log.i("FirebaseMessaging", "TopicsSyncTask\'s wakelock was already released due to timeout.");
                    }
                    return;
                label_25:
                    if(TopicsSyncTask.hasWakeLockPermission(this.context)) {
                        try {
                            this.syncWakeLock.release();
                        }
                        catch(RuntimeException unused_ex) {
                            Log.i("FirebaseMessaging", "TopicsSyncTask\'s wakelock was already released due to timeout.");
                        }
                    }
                    throw throwable0;
                label_30:
                    if(TopicsSyncTask.hasWakeLockPermission(this.context)) {
                        try {
                            powerManager$WakeLock0 = this.syncWakeLock;
                        label_32:
                            powerManager$WakeLock0.release();
                        }
                        catch(RuntimeException unused_ex) {
                            Log.i("FirebaseMessaging", "TopicsSyncTask\'s wakelock was already released due to timeout.");
                        }
                    }
                }
            }
        }
    }
}

