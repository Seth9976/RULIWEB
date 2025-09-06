package com.google.firebase.messaging;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;
import androidx.profileinstaller.ProfileInstallReceiver..ExternalSyntheticLambda0;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import java.util.concurrent.ExecutorService;

public abstract class EnhancedIntentService extends Service {
    static final long MESSAGE_TIMEOUT_S = 20L;
    private static final String TAG = "EnhancedIntentService";
    private Binder binder;
    final ExecutorService executor;
    private int lastStartId;
    private final Object lock;
    private int runningTasks;

    public EnhancedIntentService() {
        this.executor = FcmExecutors.newIntentHandleExecutor();
        this.lock = new Object();
        this.runningTasks = 0;
    }

    private void finishTask(Intent intent0) {
        if(intent0 != null) {
            WakeLockHolder.completeWakefulIntent(intent0);
        }
        synchronized(this.lock) {
            int v1 = this.runningTasks - 1;
            this.runningTasks = v1;
            if(v1 == 0) {
                this.stopSelfResultHook(this.lastStartId);
            }
        }
    }

    protected Intent getStartCommandIntent(Intent intent0) {
        return intent0;
    }

    public abstract void handleIntent(Intent arg1);

    public boolean handleIntentOnMainThread(Intent intent0) [...] // Inlined contents

    // 检测为 Lambda 实现
    void lambda$onStartCommand$1$com-google-firebase-messaging-EnhancedIntentService(Intent intent0, Task task0) [...]

    // 检测为 Lambda 实现
    void lambda$processIntent$0$com-google-firebase-messaging-EnhancedIntentService(Intent intent0, TaskCompletionSource taskCompletionSource0) [...]

    @Override  // android.app.Service
    public final IBinder onBind(Intent intent0) {
        synchronized(this) {
            if(Log.isLoggable("EnhancedIntentService", 3)) {
                Log.d("EnhancedIntentService", "Service received bind request");
            }
            if(this.binder == null) {
                this.binder = new WithinAppServiceBinder((Intent intent0) -> {
                    TaskCompletionSource taskCompletionSource0 = new TaskCompletionSource();
                    EnhancedIntentService..ExternalSyntheticLambda1 enhancedIntentService$$ExternalSyntheticLambda10 = () -> try {
                        EnhancedIntentService.this.handleIntent(intent0);
                    }
                    finally {
                        taskCompletionSource0.setResult(null);
                    };
                    EnhancedIntentService.this.executor.execute(enhancedIntentService$$ExternalSyntheticLambda10);
                    return taskCompletionSource0.getTask();
                });
            }
            return this.binder;
        }

        class com.google.firebase.messaging.EnhancedIntentService.1 implements IntentHandler {
            @Override  // com.google.firebase.messaging.WithinAppServiceBinder$IntentHandler
            public Task handle(Intent intent0) {
                return EnhancedIntentService.this.processIntent(intent0);
            }
        }

    }

    @Override  // android.app.Service
    public void onDestroy() {
        this.executor.shutdown();
        super.onDestroy();
    }

    @Override  // android.app.Service
    public final int onStartCommand(Intent intent0, int v, int v1) {
        synchronized(this.lock) {
            this.lastStartId = v1;
            ++this.runningTasks;
        }
        Intent intent1 = this.getStartCommandIntent(intent0);
        if(intent1 == null) {
            this.finishTask(intent0);
            return 2;
        }
        Task task0 = this.processIntent(intent1);
        if(task0.isComplete()) {
            this.finishTask(intent0);
            return 2;
        }
        task0.addOnCompleteListener(new ProfileInstallReceiver..ExternalSyntheticLambda0(), (Task task0) -> this.finishTask(intent0));
        return 3;
    }

    // 检测为 Lambda 实现
    private Task processIntent(Intent intent0) [...]

    boolean stopSelfResultHook(int v) {
        return this.stopSelfResult(v);
    }
}

