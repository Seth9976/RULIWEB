package com.google.firebase.messaging;

import android.content.Intent;

public final class FcmLifecycleCallbacks..ExternalSyntheticLambda0 implements Runnable {
    public final FcmLifecycleCallbacks f$0;
    public final Intent f$1;

    public FcmLifecycleCallbacks..ExternalSyntheticLambda0(FcmLifecycleCallbacks fcmLifecycleCallbacks0, Intent intent0) {
        this.f$0 = fcmLifecycleCallbacks0;
        this.f$1 = intent0;
    }

    @Override
    public final void run() {
        this.f$0.lambda$onActivityCreated$0$com-google-firebase-messaging-FcmLifecycleCallbacks(this.f$1);
    }
}

