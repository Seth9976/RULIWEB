package com.google.firebase.messaging;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.Task;

public final class RequestDeduplicator..ExternalSyntheticLambda0 implements Continuation {
    public final RequestDeduplicator f$0;
    public final String f$1;

    public RequestDeduplicator..ExternalSyntheticLambda0(RequestDeduplicator requestDeduplicator0, String s) {
        this.f$0 = requestDeduplicator0;
        this.f$1 = s;
    }

    @Override  // com.google.android.gms.tasks.Continuation
    public final Object then(Task task0) {
        return this.f$0.lambda$getOrStartGetTokenRequest$0$com-google-firebase-messaging-RequestDeduplicator(this.f$1, task0);
    }
}

