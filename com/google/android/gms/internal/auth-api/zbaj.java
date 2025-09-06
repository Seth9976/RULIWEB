package com.google.android.gms.internal.auth-api;

import com.google.android.gms.auth.api.identity.GetSignInIntentRequest;
import com.google.android.gms.common.api.internal.RemoteCall;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.tasks.TaskCompletionSource;

public final class zbaj implements RemoteCall {
    public final zbap zba;
    public final GetSignInIntentRequest zbb;

    public zbaj(zbap zbap0, GetSignInIntentRequest getSignInIntentRequest0) {
        this.zba = zbap0;
        this.zbb = getSignInIntentRequest0;
    }

    @Override  // com.google.android.gms.common.api.internal.RemoteCall
    public final void accept(Object object0, Object object1) {
        zban zban0 = new zban(this.zba, ((TaskCompletionSource)object1));
        ((zbv)((zbaq)object0).getService()).zbe(zban0, ((GetSignInIntentRequest)Preconditions.checkNotNull(this.zbb)));
    }
}

