package com.google.android.gms.internal.auth-api;

import com.google.android.gms.common.api.internal.RemoteCall;
import com.google.android.gms.tasks.TaskCompletionSource;

public final class zbah implements RemoteCall {
    public final zbap zba;

    public zbah(zbap zbap0) {
        this.zba = zbap0;
    }

    @Override  // com.google.android.gms.common.api.internal.RemoteCall
    public final void accept(Object object0, Object object1) {
        this.zba.zbb(((zbaq)object0), ((TaskCompletionSource)object1));
    }
}

