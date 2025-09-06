package com.google.android.gms.fido.u2f;

import com.google.android.gms.common.api.internal.RemoteCall;
import com.google.android.gms.fido.u2f.api.common.RegisterRequestParams;
import com.google.android.gms.internal.fido.zzw;
import com.google.android.gms.internal.fido.zzy;
import com.google.android.gms.tasks.TaskCompletionSource;

public final class zzb implements RemoteCall {
    public final U2fApiClient zza;
    public final RegisterRequestParams zzb;

    public zzb(U2fApiClient u2fApiClient0, RegisterRequestParams registerRequestParams0) {
        this.zza = u2fApiClient0;
        this.zzb = registerRequestParams0;
    }

    @Override  // com.google.android.gms.common.api.internal.RemoteCall
    public final void accept(Object object0, Object object1) {
        zzc zzc0 = new zzc(this.zza, ((TaskCompletionSource)object1));
        ((zzw)((zzy)object0).getService()).zzc(zzc0, this.zzb);
    }
}

