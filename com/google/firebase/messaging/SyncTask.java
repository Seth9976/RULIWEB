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
import com.google.android.gms.common.util.concurrent.NamedThreadFactory;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

class SyncTask implements Runnable {
    static class ConnectivityChangeReceiver extends BroadcastReceiver {
        private SyncTask task;

        public ConnectivityChangeReceiver(SyncTask syncTask0) {
            this.task = syncTask0;
        }

        @Override  // android.content.BroadcastReceiver
        public void onReceive(Context context0, Intent intent0) {
            if(this.task == null || !this.task.isDeviceConnected()) {
                return;
            }
            if(SyncTask.isDebugLogEnabled()) {
                Log.d("FirebaseMessaging", "Connectivity changed. Starting background sync.");
            }
            this.task.firebaseMessaging.enqueueTaskWithDelaySeconds(this.task, 0L);
            this.task.getContext().unregisterReceiver(this);
            this.task = null;
        }

        public void registerReceiver() {
            if(SyncTask.isDebugLogEnabled()) {
                Log.d("FirebaseMessaging", "Connectivity change received registered");
            }
            IntentFilter intentFilter0 = new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE");
            this.task.getContext().registerReceiver(this, intentFilter0);
        }
    }

    private final FirebaseMessaging firebaseMessaging;
    private final long nextDelaySeconds;
    ExecutorService processorExecutor;
    private final PowerManager.WakeLock syncWakeLock;

    public SyncTask(FirebaseMessaging firebaseMessaging0, long v) {
        this.processorExecutor = new ThreadPoolExecutor(0, 1, 30L, TimeUnit.SECONDS, new LinkedBlockingQueue(), new NamedThreadFactory("firebase-iid-executor"));
        this.firebaseMessaging = firebaseMessaging0;
        this.nextDelaySeconds = v;
        PowerManager.WakeLock powerManager$WakeLock0 = ((PowerManager)this.getContext().getSystemService("power")).newWakeLock(1, "fiid-sync");
        this.syncWakeLock = powerManager$WakeLock0;
        powerManager$WakeLock0.setReferenceCounted(false);
    }

    Context getContext() {
        return this.firebaseMessaging.getApplicationContext();
    }

    // 去混淆评级： 低(30)
    static boolean isDebugLogEnabled() {
        return Log.isLoggable("FirebaseMessaging", 3) || Build.VERSION.SDK_INT == 23 && Log.isLoggable("FirebaseMessaging", 3);
    }

    boolean isDeviceConnected() {
        ConnectivityManager connectivityManager0 = (ConnectivityManager)this.getContext().getSystemService("connectivity");
        NetworkInfo networkInfo0 = connectivityManager0 == null ? null : connectivityManager0.getActiveNetworkInfo();
        return networkInfo0 != null && networkInfo0.isConnected();
    }

    boolean maybeRefreshToken() throws IOException {
        try {
            if(this.firebaseMessaging.blockingGetToken() == null) {
                Log.e("FirebaseMessaging", "Token retrieval failed: null");
                return false;
            }
            if(Log.isLoggable("FirebaseMessaging", 3)) {
                Log.d("FirebaseMessaging", "Token successfully retrieved");
            }
            return true;
        }
        catch(IOException iOException0) {
            if(GmsRpc.isErrorMessageForRetryableError(iOException0.getMessage())) {
                Log.w("FirebaseMessaging", "Token retrieval failed: " + iOException0.getMessage() + ". Will retry token retrieval");
                return false;
            }
            if(iOException0.getMessage() != null) {
                throw iOException0;
            }
            Log.w("FirebaseMessaging", "Token retrieval failed without exception message. Will retry token retrieval");
            return false;
        }
        catch(SecurityException unused_ex) {
            Log.w("FirebaseMessaging", "Token retrieval failed with SecurityException. Will retry token retrieval");
            return false;
        }
    }

    @Override
    public void run() {
        if(ServiceStarter.getInstance().hasWakeLockPermission(this.getContext())) {
            this.syncWakeLock.acquire();
        }
        try {
            this.firebaseMessaging.setSyncScheduledOrRunning(true);
            if(!this.firebaseMessaging.isGmsCorePresent()) {
                goto label_14;
            }
            else if(!ServiceStarter.getInstance().hasAccessNetworkStatePermission(this.getContext()) || this.isDeviceConnected()) {
                if(this.maybeRefreshToken()) {
                    this.firebaseMessaging.setSyncScheduledOrRunning(false);
                }
                else {
                    this.firebaseMessaging.syncWithDelaySecondsInternal(this.nextDelaySeconds);
                }
                goto label_9;
            }
            else {
                goto label_11;
            }
            return;
        }
        catch(IOException iOException0) {
            goto label_17;
        }
        catch(Throwable throwable0) {
            goto label_23;
        }
    label_9:
        if(ServiceStarter.getInstance().hasWakeLockPermission(this.getContext())) {
            this.syncWakeLock.release();
            return;
            try {
            label_11:
                new ConnectivityChangeReceiver(this).registerReceiver();
            }
            catch(IOException iOException0) {
                goto label_17;
            }
            catch(Throwable throwable0) {
                goto label_23;
            }
            if(ServiceStarter.getInstance().hasWakeLockPermission(this.getContext())) {
                this.syncWakeLock.release();
                return;
                try {
                    try {
                    label_14:
                        this.firebaseMessaging.setSyncScheduledOrRunning(false);
                        goto label_26;
                    }
                    catch(IOException iOException0) {
                    }
                label_17:
                    Log.e("FirebaseMessaging", "Topic sync or token retrieval failed on hard failure exceptions: " + iOException0.getMessage() + ". Won\'t retry the operation.");
                    this.firebaseMessaging.setSyncScheduledOrRunning(false);
                }
                catch(Throwable throwable0) {
                    goto label_23;
                }
                if(ServiceStarter.getInstance().hasWakeLockPermission(this.getContext())) {
                    this.syncWakeLock.release();
                    return;
                label_23:
                    if(ServiceStarter.getInstance().hasWakeLockPermission(this.getContext())) {
                        this.syncWakeLock.release();
                    }
                    throw throwable0;
                label_26:
                    if(ServiceStarter.getInstance().hasWakeLockPermission(this.getContext())) {
                        this.syncWakeLock.release();
                    }
                }
            }
        }
    }
}

