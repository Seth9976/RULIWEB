package com.google.android.gms.fido.fido2;

import com.google.android.gms.common.api.internal.RemoteCall;
import com.google.android.gms.internal.fido.zzn;
import com.google.android.gms.tasks.TaskCompletionSource;

public final class zzk implements RemoteCall {
    public final Fido2PrivilegedApiClient zza;
    public final String zzb;

    public zzk(Fido2PrivilegedApiClient fido2PrivilegedApiClient0, String s) {
        this.zza = fido2PrivilegedApiClient0;
        this.zzb = s;
    }

    @Override  // com.google.android.gms.common.api.internal.RemoteCall
    public final void accept(Object object0, Object object1) {
        zzv zzv0 = new zzv(this.zza, ((TaskCompletionSource)object1));
        ((zzn)((com.google.android.gms.internal.fido.zzk)object0).getService()).zzc(zzv0, this.zzb);
    }
}

