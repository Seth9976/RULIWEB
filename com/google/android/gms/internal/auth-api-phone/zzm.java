package com.google.android.gms.internal.auth-api-phone;

import com.google.android.gms.common.api.internal.RemoteCall;
import com.google.android.gms.tasks.TaskCompletionSource;

public final class zzm implements RemoteCall {
    public final zzr zza;

    public zzm(zzr zzr0) {
        this.zza = zzr0;
    }

    @Override  // com.google.android.gms.common.api.internal.RemoteCall
    public final void accept(Object object0, Object object1) {
        zzo zzo0 = new zzo(this.zza, ((TaskCompletionSource)object1));
        ((zzh)((zzw)object0).getService()).zze(zzo0);
    }
}

