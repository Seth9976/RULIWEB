package com.google.android.gms.fido.fido2;

import com.google.android.gms.common.api.internal.RemoteCall;
import com.google.android.gms.fido.fido2.api.common.BrowserPublicKeyCredentialCreationOptions;
import com.google.android.gms.internal.fido.zzk;
import com.google.android.gms.internal.fido.zzn;
import com.google.android.gms.tasks.TaskCompletionSource;

public final class zzl implements RemoteCall {
    public final Fido2PrivilegedApiClient zza;
    public final BrowserPublicKeyCredentialCreationOptions zzb;

    public zzl(Fido2PrivilegedApiClient fido2PrivilegedApiClient0, BrowserPublicKeyCredentialCreationOptions browserPublicKeyCredentialCreationOptions0) {
        this.zza = fido2PrivilegedApiClient0;
        this.zzb = browserPublicKeyCredentialCreationOptions0;
    }

    @Override  // com.google.android.gms.common.api.internal.RemoteCall
    public final void accept(Object object0, Object object1) {
        zzq zzq0 = new zzq(this.zza, ((TaskCompletionSource)object1));
        ((zzn)((zzk)object0).getService()).zzd(zzq0, this.zzb);
    }
}

