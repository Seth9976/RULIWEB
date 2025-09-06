package com.google.android.gms.internal.auth;

import com.google.android.gms.auth.api.proxy.ProxyResponse;

final class zzbp extends zzbd {
    final zzbq zza;

    zzbp(zzbq zzbq0) {
        this.zza = zzbq0;
        super();
    }

    @Override  // com.google.android.gms.internal.auth.zzbd
    public final void zzb(ProxyResponse proxyResponse0) {
        zzbu zzbu0 = new zzbu(proxyResponse0);
        this.zza.setResult(zzbu0);
    }
}

