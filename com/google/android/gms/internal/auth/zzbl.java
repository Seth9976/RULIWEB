package com.google.android.gms.internal.auth;

import com.google.android.gms.auth.api.proxy.ProxyRequest;
import com.google.android.gms.common.api.internal.RemoteCall;
import com.google.android.gms.tasks.TaskCompletionSource;

public final class zzbl implements RemoteCall {
    public final zzbo zza;
    public final ProxyRequest zzb;

    public zzbl(zzbo zzbo0, ProxyRequest proxyRequest0) {
        this.zza = zzbo0;
        this.zzb = proxyRequest0;
    }

    @Override  // com.google.android.gms.common.api.internal.RemoteCall
    public final void accept(Object object0, Object object1) {
        zzbm zzbm0 = new zzbm(this.zza, ((TaskCompletionSource)object1));
        ((zzbh)((zzbe)object0).getService()).zze(zzbm0, this.zzb);
    }
}

