package com.google.firebase.messaging;

import com.google.android.gms.tasks.SuccessContinuation;
import com.google.android.gms.tasks.Task;

public final class FirebaseMessaging..ExternalSyntheticLambda7 implements SuccessContinuation {
    public final String f$0;

    public FirebaseMessaging..ExternalSyntheticLambda7(String s) {
        this.f$0 = s;
    }

    @Override  // com.google.android.gms.tasks.SuccessContinuation
    public final Task then(Object object0) {
        return FirebaseMessaging.lambda$unsubscribeFromTopic$8(this.f$0, ((TopicsSubscriber)object0));
    }
}

