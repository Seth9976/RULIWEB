package com.google.firebase.messaging;

import com.google.firebase.iid.internal.FirebaseInstanceIdInternal.NewTokenListener;

public final class FirebaseMessaging..ExternalSyntheticLambda2 implements NewTokenListener {
    public final FirebaseMessaging f$0;

    public FirebaseMessaging..ExternalSyntheticLambda2(FirebaseMessaging firebaseMessaging0) {
        this.f$0 = firebaseMessaging0;
    }

    @Override  // com.google.firebase.iid.internal.FirebaseInstanceIdInternal$NewTokenListener
    public final void onNewToken(String s) {
        this.f$0.lambda$new$0$com-google-firebase-messaging-FirebaseMessaging(s);
    }
}

