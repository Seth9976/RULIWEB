package com.google.firebase.messaging;

import android.content.Intent;
import android.os.Binder;
import android.os.Process;
import android.util.Log;
import androidx.profileinstaller.ProfileInstallReceiver..ExternalSyntheticLambda0;
import com.google.android.gms.tasks.Task;

class WithinAppServiceBinder extends Binder {
    interface IntentHandler {
        Task handle(Intent arg1);
    }

    private final IntentHandler intentHandler;

    WithinAppServiceBinder(IntentHandler withinAppServiceBinder$IntentHandler0) {
        this.intentHandler = withinAppServiceBinder$IntentHandler0;
    }

    // 检测为 Lambda 实现
    static void lambda$send$0(BindRequest withinAppServiceConnection$BindRequest0, Task task0) [...]

    void send(BindRequest withinAppServiceConnection$BindRequest0) {
        if(Binder.getCallingUid() != Process.myUid()) {
            throw new SecurityException("Binding only allowed within app");
        }
        if(Log.isLoggable("FirebaseMessaging", 3)) {
            Log.d("FirebaseMessaging", "service received new intent via bind strategy");
        }
        this.intentHandler.handle(withinAppServiceConnection$BindRequest0.intent).addOnCompleteListener(new ProfileInstallReceiver..ExternalSyntheticLambda0(), (Task task0) -> withinAppServiceConnection$BindRequest0.finish());
    }
}

