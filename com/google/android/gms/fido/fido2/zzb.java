package com.google.android.gms.fido.fido2;

import com.google.android.gms.common.api.internal.RemoteCall;
import com.google.android.gms.internal.fido.zzp;
import com.google.android.gms.internal.fido.zzs;
import com.google.android.gms.tasks.TaskCompletionSource;

public final class zzb implements RemoteCall {
    public final Fido2ApiClient zza;

    public zzb(Fido2ApiClient fido2ApiClient0) {
        this.zza = fido2ApiClient0;
    }

    @Override  // com.google.android.gms.common.api.internal.RemoteCall
    public final void accept(Object object0, Object object1) {
        zzj zzj0 = new zzj(this.zza, ((TaskCompletionSource)object1));
        ((zzs)((zzp)object0).getService()).zze(zzj0);
    }
}

