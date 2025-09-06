package com.google.android.gms.common.moduleinstall.internal;

import com.google.android.gms.common.api.internal.RemoteCall;
import com.google.android.gms.tasks.TaskCompletionSource;

public final class zao implements RemoteCall {
    public final zay zaa;
    public final ApiFeatureRequest zab;

    public zao(zay zay0, ApiFeatureRequest apiFeatureRequest0) {
        this.zaa = zay0;
        this.zab = apiFeatureRequest0;
    }

    @Override  // com.google.android.gms.common.api.internal.RemoteCall
    public final void accept(Object object0, Object object1) {
        zat zat0 = new zat(this.zaa, ((TaskCompletionSource)object1));
        ((zaf)((zaz)object0).getService()).zag(zat0, this.zab, null);
    }
}

