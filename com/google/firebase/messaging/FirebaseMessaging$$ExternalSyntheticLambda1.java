package com.google.firebase.messaging;

import com.google.android.gms.tasks.SuccessContinuation;
import com.google.android.gms.tasks.Task;

public final class FirebaseMessaging..ExternalSyntheticLambda1 implements SuccessContinuation {
    public final FirebaseMessaging f$0;
    public final String f$1;
    public final Token f$2;

    public FirebaseMessaging..ExternalSyntheticLambda1(FirebaseMessaging firebaseMessaging0, String s, Token store$Token0) {
        this.f$0 = firebaseMessaging0;
        this.f$1 = s;
        this.f$2 = store$Token0;
    }

    @Override  // com.google.android.gms.tasks.SuccessContinuation
    public final Task then(Object object0) {
        return this.f$0.lambda$blockingGetToken$9$com-google-firebase-messaging-FirebaseMessaging(this.f$1, this.f$2, ((String)object0));
    }
}

