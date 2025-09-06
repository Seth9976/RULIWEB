package com.google.firebase.messaging;

import android.content.Context;
import java.util.concurrent.Callable;
import java.util.concurrent.ScheduledExecutorService;

public final class TopicsSubscriber..ExternalSyntheticLambda0 implements Callable {
    public final Context f$0;
    public final ScheduledExecutorService f$1;
    public final FirebaseMessaging f$2;
    public final Metadata f$3;
    public final GmsRpc f$4;

    public TopicsSubscriber..ExternalSyntheticLambda0(Context context0, ScheduledExecutorService scheduledExecutorService0, FirebaseMessaging firebaseMessaging0, Metadata metadata0, GmsRpc gmsRpc0) {
        this.f$0 = context0;
        this.f$1 = scheduledExecutorService0;
        this.f$2 = firebaseMessaging0;
        this.f$3 = metadata0;
        this.f$4 = gmsRpc0;
    }

    @Override
    public final Object call() {
        return TopicsSubscriber.lambda$createInstance$0(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4);
    }
}

