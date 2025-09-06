package com.google.firebase.messaging;

import android.content.Intent;
import com.google.android.gms.tasks.TaskCompletionSource;

public final class EnhancedIntentService..ExternalSyntheticLambda1 implements Runnable {
    public final EnhancedIntentService f$0;
    public final Intent f$1;
    public final TaskCompletionSource f$2;

    public EnhancedIntentService..ExternalSyntheticLambda1(EnhancedIntentService enhancedIntentService0, Intent intent0, TaskCompletionSource taskCompletionSource0) {
        this.f$0 = enhancedIntentService0;
        this.f$1 = intent0;
        this.f$2 = taskCompletionSource0;
    }

    @Override
    public final void run() {
        this.f$0.lambda$processIntent$0$com-google-firebase-messaging-EnhancedIntentService(this.f$1, this.f$2);
    }
}

