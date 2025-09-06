package com.google.android.gms.internal.auth-api-phone;

import com.google.android.gms.common.api.internal.RemoteCall;
import com.google.android.gms.tasks.TaskCompletionSource;

public final class zzs implements RemoteCall {
    public final zzv zza;

    public zzs(zzv zzv0) {
        this.zza = zzv0;
    }

    @Override  // com.google.android.gms.common.api.internal.RemoteCall
    public final void accept(Object object0, Object object1) {
        zzu zzu0 = new zzu(this.zza, ((TaskCompletionSource)object1));
        ((zzh)((zzw)object0).getService()).zzf(zzu0);
    }
}

