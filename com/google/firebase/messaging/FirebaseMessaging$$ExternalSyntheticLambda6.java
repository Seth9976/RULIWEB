package com.google.firebase.messaging;

import com.google.android.gms.tasks.TaskCompletionSource;

public final class FirebaseMessaging..ExternalSyntheticLambda6 implements Runnable {
    public final FirebaseMessaging f$0;
    public final TaskCompletionSource f$1;

    public FirebaseMessaging..ExternalSyntheticLambda6(FirebaseMessaging firebaseMessaging0, TaskCompletionSource taskCompletionSource0) {
        this.f$0 = firebaseMessaging0;
        this.f$1 = taskCompletionSource0;
    }

    @Override
    public final void run() {
        this.f$0.lambda$getToken$4$com-google-firebase-messaging-FirebaseMessaging(this.f$1);
    }
}

