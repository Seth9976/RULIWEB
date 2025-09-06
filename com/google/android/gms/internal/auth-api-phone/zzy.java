package com.google.android.gms.internal.auth-api-phone;

import com.google.android.gms.common.api.internal.RemoteCall;
import com.google.android.gms.tasks.TaskCompletionSource;

public final class zzy implements RemoteCall {
    public final zzab zza;
    public final String zzb;

    public zzy(zzab zzab0, String s) {
        this.zza = zzab0;
        this.zzb = s;
    }

    @Override  // com.google.android.gms.common.api.internal.RemoteCall
    public final void accept(Object object0, Object object1) {
        zzh zzh0 = (zzh)((zzw)object0).getService();
        zzaa zzaa0 = new zzaa(this.zza, ((TaskCompletionSource)object1));
        zzh0.zzh(this.zzb, zzaa0);
    }
}

