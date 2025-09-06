package com.google.android.gms.internal.auth-api;

import com.google.android.gms.auth.api.identity.BeginSignInRequest;
import com.google.android.gms.common.api.internal.RemoteCall;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.tasks.TaskCompletionSource;

public final class zbai implements RemoteCall {
    public final zbap zba;
    public final BeginSignInRequest zbb;

    public zbai(zbap zbap0, BeginSignInRequest beginSignInRequest0) {
        this.zba = zbap0;
        this.zbb = beginSignInRequest0;
    }

    @Override  // com.google.android.gms.common.api.internal.RemoteCall
    public final void accept(Object object0, Object object1) {
        zbal zbal0 = new zbal(this.zba, ((TaskCompletionSource)object1));
        ((zbv)((zbaq)object0).getService()).zbc(zbal0, ((BeginSignInRequest)Preconditions.checkNotNull(this.zbb)));
    }
}

