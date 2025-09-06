package com.google.firebase.messaging;

import android.content.Context;
import android.content.Intent;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.Task;

public final class FcmBroadcastProcessor..ExternalSyntheticLambda2 implements Continuation {
    public final Context f$0;
    public final Intent f$1;
    public final boolean f$2;

    public FcmBroadcastProcessor..ExternalSyntheticLambda2(Context context0, Intent intent0, boolean z) {
        this.f$0 = context0;
        this.f$1 = intent0;
        this.f$2 = z;
    }

    @Override  // com.google.android.gms.tasks.Continuation
    public final Object then(Task task0) {
        return FcmBroadcastProcessor.lambda$startMessagingService$2(this.f$0, this.f$1, this.f$2, task0);
    }
}

