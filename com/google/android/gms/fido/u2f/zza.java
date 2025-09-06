package com.google.android.gms.fido.u2f;

import com.google.android.gms.common.api.internal.RemoteCall;
import com.google.android.gms.fido.u2f.api.common.SignRequestParams;
import com.google.android.gms.internal.fido.zzw;
import com.google.android.gms.internal.fido.zzy;
import com.google.android.gms.tasks.TaskCompletionSource;

public final class zza implements RemoteCall {
    public final U2fApiClient zza;
    public final SignRequestParams zzb;

    public zza(U2fApiClient u2fApiClient0, SignRequestParams signRequestParams0) {
        this.zza = u2fApiClient0;
        this.zzb = signRequestParams0;
    }

    @Override  // com.google.android.gms.common.api.internal.RemoteCall
    public final void accept(Object object0, Object object1) {
        zzd zzd0 = new zzd(this.zza, ((TaskCompletionSource)object1));
        ((zzw)((zzy)object0).getService()).zzd(zzd0, this.zzb);
    }
}

