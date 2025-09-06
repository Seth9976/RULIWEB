package com.google.android.gms.fido.fido2;

import com.google.android.gms.common.api.internal.RemoteCall;
import com.google.android.gms.internal.fido.zzk;
import com.google.android.gms.internal.fido.zzn;
import com.google.android.gms.tasks.TaskCompletionSource;

public final class zzp implements RemoteCall {
    public final Fido2PrivilegedApiClient zza;

    public zzp(Fido2PrivilegedApiClient fido2PrivilegedApiClient0) {
        this.zza = fido2PrivilegedApiClient0;
    }

    @Override  // com.google.android.gms.common.api.internal.RemoteCall
    public final void accept(Object object0, Object object1) {
        zzu zzu0 = new zzu(this.zza, ((TaskCompletionSource)object1));
        ((zzn)((zzk)object0).getService()).zzf(zzu0);
    }
}

