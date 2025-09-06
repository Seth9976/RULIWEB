package com.google.firebase.messaging;

import com.google.android.gms.tasks.Task;

public final class FirebaseMessaging..ExternalSyntheticLambda8 implements GetTokenRequest {
    public final FirebaseMessaging f$0;
    public final String f$1;
    public final Token f$2;

    public FirebaseMessaging..ExternalSyntheticLambda8(FirebaseMessaging firebaseMessaging0, String s, Token store$Token0) {
        this.f$0 = firebaseMessaging0;
        this.f$1 = s;
        this.f$2 = store$Token0;
    }

    @Override  // com.google.firebase.messaging.RequestDeduplicator$GetTokenRequest
    public final Task start() {
        return this.f$0.lambda$blockingGetToken$10$com-google-firebase-messaging-FirebaseMessaging(this.f$1, this.f$2);
    }
}

