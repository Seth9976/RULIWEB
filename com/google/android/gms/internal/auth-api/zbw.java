package com.google.android.gms.internal.auth-api;

import com.google.android.gms.auth.api.identity.AuthorizationRequest;
import com.google.android.gms.common.api.internal.RemoteCall;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.tasks.TaskCompletionSource;

public final class zbw implements RemoteCall {
    public final zbz zba;
    public final AuthorizationRequest zbb;

    public zbw(zbz zbz0, AuthorizationRequest authorizationRequest0) {
        this.zba = zbz0;
        this.zbb = authorizationRequest0;
    }

    @Override  // com.google.android.gms.common.api.internal.RemoteCall
    public final void accept(Object object0, Object object1) {
        zby zby0 = new zby(this.zba, ((TaskCompletionSource)object1));
        ((zbj)((zbf)object0).getService()).zbc(zby0, ((AuthorizationRequest)Preconditions.checkNotNull(this.zbb)));
    }
}

