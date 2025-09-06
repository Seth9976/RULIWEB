package com.google.android.gms.internal.auth;

import com.google.android.gms.common.api.internal.RemoteCall;
import com.google.android.gms.tasks.TaskCompletionSource;

public final class zzq implements RemoteCall {
    public final zzab zza;
    public final String zzb;

    public zzq(zzab zzab0, String s) {
        this.zza = zzab0;
        this.zzb = s;
    }

    @Override  // com.google.android.gms.common.api.internal.RemoteCall
    public final void accept(Object object0, Object object1) {
        ((zzp)((zzi)object0).getService()).zzh(new zzy(this.zza, ((TaskCompletionSource)object1)), this.zzb);
    }
}

