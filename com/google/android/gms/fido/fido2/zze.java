package com.google.android.gms.fido.fido2;

import com.google.android.gms.common.api.internal.RemoteCall;
import com.google.android.gms.fido.fido2.api.common.PublicKeyCredentialRequestOptions;
import com.google.android.gms.internal.fido.zzp;
import com.google.android.gms.internal.fido.zzs;
import com.google.android.gms.tasks.TaskCompletionSource;

public final class zze implements RemoteCall {
    public final Fido2ApiClient zza;
    public final PublicKeyCredentialRequestOptions zzb;

    public zze(Fido2ApiClient fido2ApiClient0, PublicKeyCredentialRequestOptions publicKeyCredentialRequestOptions0) {
        this.zza = fido2ApiClient0;
        this.zzb = publicKeyCredentialRequestOptions0;
    }

    @Override  // com.google.android.gms.common.api.internal.RemoteCall
    public final void accept(Object object0, Object object1) {
        zzg zzg0 = new zzg(this.zza, ((TaskCompletionSource)object1));
        ((zzs)((zzp)object0).getService()).zzd(zzg0, this.zzb);
    }
}

