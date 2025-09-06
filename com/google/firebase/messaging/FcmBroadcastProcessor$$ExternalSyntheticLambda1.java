package com.google.firebase.messaging;

import android.content.Context;
import android.content.Intent;
import java.util.concurrent.Callable;

public final class FcmBroadcastProcessor..ExternalSyntheticLambda1 implements Callable {
    public final Context f$0;
    public final Intent f$1;

    public FcmBroadcastProcessor..ExternalSyntheticLambda1(Context context0, Intent intent0) {
        this.f$0 = context0;
        this.f$1 = intent0;
    }

    @Override
    public final Object call() {
        return FcmBroadcastProcessor.lambda$startMessagingService$0(this.f$0, this.f$1);
    }
}

