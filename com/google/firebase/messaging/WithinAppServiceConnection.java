package com.google.firebase.messaging;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.util.Log;
import com.google.android.gms.common.stats.ConnectionTracker;
import com.google.android.gms.common.util.concurrent.NamedThreadFactory;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

class WithinAppServiceConnection implements ServiceConnection {
    static class BindRequest {
        final Intent intent;
        private final TaskCompletionSource taskCompletionSource;

        BindRequest(Intent intent0) {
            this.taskCompletionSource = new TaskCompletionSource();
            this.intent = intent0;
        }

        void arrangeTimeout(ScheduledExecutorService scheduledExecutorService0) {
            this.getTask().addOnCompleteListener(scheduledExecutorService0, (Task task0) -> scheduledExecutorService0.schedule(() -> {
                Log.w("FirebaseMessaging", "Service took too long to process intent: " + this.intent.getAction() + " finishing.");
                this.finish();
            }, 20L, TimeUnit.SECONDS).cancel(false));
        }

        void finish() {
            this.taskCompletionSource.trySetResult(null);
        }

        Task getTask() {
            return this.taskCompletionSource.getTask();
        }

        // 检测为 Lambda 实现
        void lambda$arrangeTimeout$0$com-google-firebase-messaging-WithinAppServiceConnection$BindRequest() [...]

        // 检测为 Lambda 实现
        static void lambda$arrangeTimeout$1(ScheduledFuture scheduledFuture0, Task task0) [...]
    }

    private WithinAppServiceBinder binder;
    private boolean connectionInProgress;
    private final Intent connectionIntent;
    private final Context context;
    private final Queue intentQueue;
    private final ScheduledExecutorService scheduledExecutorService;

    WithinAppServiceConnection(Context context0, String s) {
        this(context0, s, new ScheduledThreadPoolExecutor(0, new NamedThreadFactory("Firebase-FirebaseInstanceIdServiceConnection")));
    }

    WithinAppServiceConnection(Context context0, String s, ScheduledExecutorService scheduledExecutorService0) {
        this.intentQueue = new ArrayDeque();
        this.connectionInProgress = false;
        this.context = context0.getApplicationContext();
        this.connectionIntent = new Intent(s).setPackage("com.ruliweb.www.ruliapp");
        this.scheduledExecutorService = scheduledExecutorService0;
    }

    private void finishAllInQueue() {
        while(!this.intentQueue.isEmpty()) {
            ((BindRequest)this.intentQueue.poll()).finish();
        }
    }

    private void flushQueue() {
        synchronized(this) {
            if(Log.isLoggable("FirebaseMessaging", 3)) {
                Log.d("FirebaseMessaging", "flush queue called");
            }
            while(!this.intentQueue.isEmpty()) {
                if(Log.isLoggable("FirebaseMessaging", 3)) {
                    Log.d("FirebaseMessaging", "found intent to be delivered");
                }
                if(this.binder != null && this.binder.isBinderAlive()) {
                    if(Log.isLoggable("FirebaseMessaging", 3)) {
                        Log.d("FirebaseMessaging", "binder is alive, sending the intent.");
                    }
                    BindRequest withinAppServiceConnection$BindRequest0 = (BindRequest)this.intentQueue.poll();
                    this.binder.send(withinAppServiceConnection$BindRequest0);
                    continue;
                }
                this.startConnectionIfNeeded();
                return;
            }
        }
    }

    @Override  // android.content.ServiceConnection
    public void onServiceConnected(ComponentName componentName0, IBinder iBinder0) {
        synchronized(this) {
            if(Log.isLoggable("FirebaseMessaging", 3)) {
                Log.d("FirebaseMessaging", "onServiceConnected: " + componentName0);
            }
            this.connectionInProgress = false;
            if(!(iBinder0 instanceof WithinAppServiceBinder)) {
                Log.e("FirebaseMessaging", "Invalid service connection: " + iBinder0);
                this.finishAllInQueue();
                return;
            }
            this.binder = (WithinAppServiceBinder)iBinder0;
            this.flushQueue();
        }
    }

    @Override  // android.content.ServiceConnection
    public void onServiceDisconnected(ComponentName componentName0) {
        if(Log.isLoggable("FirebaseMessaging", 3)) {
            Log.d("FirebaseMessaging", "onServiceDisconnected: " + componentName0);
        }
        this.flushQueue();
    }

    Task sendIntent(Intent intent0) {
        synchronized(this) {
            if(Log.isLoggable("FirebaseMessaging", 3)) {
                Log.d("FirebaseMessaging", "new intent queued in the bind-strategy delivery");
            }
            BindRequest withinAppServiceConnection$BindRequest0 = new BindRequest(intent0);
            withinAppServiceConnection$BindRequest0.arrangeTimeout(this.scheduledExecutorService);
            this.intentQueue.add(withinAppServiceConnection$BindRequest0);
            this.flushQueue();
            return withinAppServiceConnection$BindRequest0.getTask();
        }
    }

    private void startConnectionIfNeeded() {
        if(Log.isLoggable("FirebaseMessaging", 3)) {
            Log.d("FirebaseMessaging", "binder is dead. start connection? " + !this.connectionInProgress);
        }
        try {
            if(this.connectionInProgress) {
                return;
            }
            this.connectionInProgress = true;
            if(ConnectionTracker.getInstance().bindService(this.context, this.connectionIntent, this, 65)) {
                return;
            }
            Log.e("FirebaseMessaging", "binding to the service failed");
        }
        catch(SecurityException securityException0) {
            Log.e("FirebaseMessaging", "Exception while binding the service", securityException0);
        }
        this.connectionInProgress = false;
        this.finishAllInQueue();
    }
}

