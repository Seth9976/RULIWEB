package com.google.android.gms.internal.auth;

import com.google.android.gms.common.api.internal.RemoteCall;
import com.google.android.gms.tasks.TaskCompletionSource;

public final class zzbk implements RemoteCall {
    public final zzbo zza;

    public zzbk(zzbo zzbo0) {
        this.zza = zzbo0;
    }

    @Override  // com.google.android.gms.common.api.internal.RemoteCall
    public final void accept(Object object0, Object object1) {
        zzbn zzbn0 = new zzbn(this.zza, ((TaskCompletionSource)object1));
        ((zzbh)((zzbe)object0).getService()).zzd(zzbn0);
    }
}

