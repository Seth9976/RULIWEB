package com.google.firebase.messaging;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.Task;

public final class GmsRpc..ExternalSyntheticLambda0 implements Continuation {
    public final GmsRpc f$0;

    public GmsRpc..ExternalSyntheticLambda0(GmsRpc gmsRpc0) {
        this.f$0 = gmsRpc0;
    }

    @Override  // com.google.android.gms.tasks.Continuation
    public final Object then(Task task0) {
        return this.f$0.lambda$extractResponseWhenComplete$0$com-google-firebase-messaging-GmsRpc(task0);
    }
}

