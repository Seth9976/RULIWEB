package com.google.android.gms.fido.fido2;

import com.google.android.gms.common.api.internal.RemoteCall;
import com.google.android.gms.fido.fido2.api.common.PublicKeyCredentialCreationOptions;
import com.google.android.gms.internal.fido.zzp;
import com.google.android.gms.internal.fido.zzs;
import com.google.android.gms.tasks.TaskCompletionSource;

public final class zzd implements RemoteCall {
    public final Fido2ApiClient zza;
    public final PublicKeyCredentialCreationOptions zzb;

    public zzd(Fido2ApiClient fido2ApiClient0, PublicKeyCredentialCreationOptions publicKeyCredentialCreationOptions0) {
        this.zza = fido2ApiClient0;
        this.zzb = publicKeyCredentialCreationOptions0;
    }

    @Override  // com.google.android.gms.common.api.internal.RemoteCall
    public final void accept(Object object0, Object object1) {
        zzh zzh0 = new zzh(this.zza, ((TaskCompletionSource)object1));
        ((zzs)((zzp)object0).getService()).zzc(zzh0, this.zzb);
    }
}

