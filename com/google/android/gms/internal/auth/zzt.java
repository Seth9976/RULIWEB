package com.google.android.gms.internal.auth;

import com.google.android.gms.common.api.internal.RemoteCall;
import com.google.android.gms.tasks.TaskCompletionSource;

public final class zzt implements RemoteCall {
    public final zzab zza;
    public final zzbw zzb;

    public zzt(zzab zzab0, zzbw zzbw0) {
        this.zza = zzab0;
        this.zzb = zzbw0;
    }

    @Override  // com.google.android.gms.common.api.internal.RemoteCall
    public final void accept(Object object0, Object object1) {
        ((zzp)((zzi)object0).getService()).zzd(new zzx(this.zza, ((TaskCompletionSource)object1)), this.zzb);
    }
}

