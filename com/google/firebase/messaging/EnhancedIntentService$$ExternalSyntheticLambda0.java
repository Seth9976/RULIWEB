package com.google.firebase.messaging;

import android.content.Intent;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

public final class EnhancedIntentService..ExternalSyntheticLambda0 implements OnCompleteListener {
    public final EnhancedIntentService f$0;
    public final Intent f$1;

    public EnhancedIntentService..ExternalSyntheticLambda0(EnhancedIntentService enhancedIntentService0, Intent intent0) {
        this.f$0 = enhancedIntentService0;
        this.f$1 = intent0;
    }

    @Override  // com.google.android.gms.tasks.OnCompleteListener
    public final void onComplete(Task task0) {
        this.f$0.lambda$onStartCommand$1$com-google-firebase-messaging-EnhancedIntentService(this.f$1, task0);
    }
}

