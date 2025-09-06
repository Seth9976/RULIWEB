package com.google.android.gms.internal.auth;

import com.google.android.gms.auth.api.proxy.ProxyApi.ProxyResult;
import com.google.android.gms.auth.api.proxy.ProxyResponse;
import com.google.android.gms.common.api.Status;

final class zzbu implements ProxyResult {
    private final Status zza;
    private ProxyResponse zzb;

    public zzbu(ProxyResponse proxyResponse0) {
        this.zzb = proxyResponse0;
        this.zza = Status.RESULT_SUCCESS;
    }

    public zzbu(Status status0) {
        this.zza = status0;
    }

    @Override  // com.google.android.gms.auth.api.proxy.ProxyApi$ProxyResult
    public final ProxyResponse getResponse() {
        return this.zzb;
    }

    @Override  // com.google.android.gms.common.api.Result
    public final Status getStatus() {
        return this.zza;
    }
}

