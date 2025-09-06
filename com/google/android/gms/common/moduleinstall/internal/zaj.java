package com.google.android.gms.common.moduleinstall.internal;

import com.google.android.gms.common.api.internal.RemoteCall;
import com.google.android.gms.tasks.TaskCompletionSource;

public final class zaj implements RemoteCall {
    public final zay zaa;
    public final zaab zab;

    public zaj(zay zay0, zaab zaab0) {
        this.zaa = zay0;
        this.zab = zaab0;
    }

    @Override  // com.google.android.gms.common.api.internal.RemoteCall
    public final void accept(Object object0, Object object1) {
        zav zav0 = new zav(this.zaa, ((TaskCompletionSource)object1));
        ((zaf)((zaz)object0).getService()).zai(zav0, this.zab);
    }
}

