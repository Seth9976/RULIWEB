package com.google.firebase.messaging;

import android.content.Context;
import com.google.android.gms.tasks.TaskCompletionSource;

public final class ProxyNotificationInitializer..ExternalSyntheticLambda2 implements Runnable {
    public final Context f$0;
    public final boolean f$1;
    public final TaskCompletionSource f$2;

    public ProxyNotificationInitializer..ExternalSyntheticLambda2(Context context0, boolean z, TaskCompletionSource taskCompletionSource0) {
        this.f$0 = context0;
        this.f$1 = z;
        this.f$2 = taskCompletionSource0;
    }

    @Override
    public final void run() {
        ProxyNotificationInitializer.lambda$setEnableProxyNotification$0(this.f$0, this.f$1, this.f$2);
    }
}

