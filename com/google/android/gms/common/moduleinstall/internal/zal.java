package com.google.android.gms.common.moduleinstall.internal;

import com.google.android.gms.common.api.internal.RemoteCall;
import com.google.android.gms.tasks.TaskCompletionSource;

public final class zal implements RemoteCall {
    public final zay zaa;
    public final ApiFeatureRequest zab;

    public zal(zay zay0, ApiFeatureRequest apiFeatureRequest0) {
        this.zaa = zay0;
        this.zab = apiFeatureRequest0;
    }

    @Override  // com.google.android.gms.common.api.internal.RemoteCall
    public final void accept(Object object0, Object object1) {
        zar zar0 = new zar(this.zaa, ((TaskCompletionSource)object1));
        ((zaf)((zaz)object0).getService()).zae(zar0, this.zab);
    }
}

