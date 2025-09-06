package com.google.android.gms.common.moduleinstall.internal;

import com.google.android.gms.common.api.internal.RemoteCall;
import com.google.android.gms.common.moduleinstall.InstallStatusListener;
import com.google.android.gms.tasks.TaskCompletionSource;
import java.util.concurrent.atomic.AtomicReference;

public final class zai implements RemoteCall {
    public final zay zaa;
    public final AtomicReference zab;
    public final InstallStatusListener zac;
    public final ApiFeatureRequest zad;
    public final zaab zae;

    public zai(zay zay0, AtomicReference atomicReference0, InstallStatusListener installStatusListener0, ApiFeatureRequest apiFeatureRequest0, zaab zaab0) {
        this.zaa = zay0;
        this.zab = atomicReference0;
        this.zac = installStatusListener0;
        this.zad = apiFeatureRequest0;
        this.zae = zaab0;
    }

    @Override  // com.google.android.gms.common.api.internal.RemoteCall
    public final void accept(Object object0, Object object1) {
        zau zau0 = new zau(this.zaa, this.zab, ((TaskCompletionSource)object1), this.zac);
        ((zaf)((zaz)object0).getService()).zag(zau0, this.zad, this.zae);
    }
}

