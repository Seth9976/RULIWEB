package com.google.android.gms.fido.fido2;

import com.google.android.gms.common.api.internal.RemoteCall;
import com.google.android.gms.fido.fido2.api.common.BrowserPublicKeyCredentialRequestOptions;
import com.google.android.gms.internal.fido.zzk;
import com.google.android.gms.internal.fido.zzn;
import com.google.android.gms.tasks.TaskCompletionSource;

public final class zzm implements RemoteCall {
    public final Fido2PrivilegedApiClient zza;
    public final BrowserPublicKeyCredentialRequestOptions zzb;

    public zzm(Fido2PrivilegedApiClient fido2PrivilegedApiClient0, BrowserPublicKeyCredentialRequestOptions browserPublicKeyCredentialRequestOptions0) {
        this.zza = fido2PrivilegedApiClient0;
        this.zzb = browserPublicKeyCredentialRequestOptions0;
    }

    @Override  // com.google.android.gms.common.api.internal.RemoteCall
    public final void accept(Object object0, Object object1) {
        zzr zzr0 = new zzr(this.zza, ((TaskCompletionSource)object1));
        ((zzn)((zzk)object0).getService()).zze(zzr0, this.zzb);
    }
}

