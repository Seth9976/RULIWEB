package com.google.android.gms.fido.fido2;

import com.google.android.gms.common.api.internal.RemoteCall;
import com.google.android.gms.fido.fido2.api.common.BrowserPublicKeyCredentialRequestOptions;
import com.google.android.gms.internal.fido.zzk;
import com.google.android.gms.tasks.TaskCompletionSource;

public final class zzn implements RemoteCall {
    public final Fido2PrivilegedApiClient zza;
    public final BrowserPublicKeyCredentialRequestOptions zzb;

    public zzn(Fido2PrivilegedApiClient fido2PrivilegedApiClient0, BrowserPublicKeyCredentialRequestOptions browserPublicKeyCredentialRequestOptions0) {
        this.zza = fido2PrivilegedApiClient0;
        this.zzb = browserPublicKeyCredentialRequestOptions0;
    }

    @Override  // com.google.android.gms.common.api.internal.RemoteCall
    public final void accept(Object object0, Object object1) {
        zzt zzt0 = new zzt(this.zza, ((TaskCompletionSource)object1));
        ((com.google.android.gms.internal.fido.zzn)((zzk)object0).getService()).zze(zzt0, this.zzb);
    }
}

