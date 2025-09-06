package com.google.android.gms.internal.auth-api-phone;

import com.google.android.gms.common.api.internal.RemoteCall;
import com.google.android.gms.tasks.TaskCompletionSource;

public final class zzl implements RemoteCall {
    public final zzr zza;
    public final String zzb;

    public zzl(zzr zzr0, String s) {
        this.zza = zzr0;
        this.zzb = s;
    }

    @Override  // com.google.android.gms.common.api.internal.RemoteCall
    public final void accept(Object object0, Object object1) {
        zzq zzq0 = new zzq(this.zza, ((TaskCompletionSource)object1));
        ((zzh)((zzw)object0).getService()).zzd(this.zzb, zzq0);
    }
}

