package com.google.android.gms.internal.auth;

import com.google.android.gms.auth.api.proxy.ProxyApi.SpatulaHeaderResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.Preconditions;
import javax.annotation.Nonnull;

public final class zzbv implements SpatulaHeaderResult {
    private final Status zza;
    private final String zzb;

    public zzbv(@Nonnull Status status0) {
        this.zza = (Status)Preconditions.checkNotNull(status0);
        this.zzb = "";
    }

    public zzbv(@Nonnull String s) {
        this.zzb = (String)Preconditions.checkNotNull(s);
        this.zza = Status.RESULT_SUCCESS;
    }

    @Override  // com.google.android.gms.auth.api.proxy.ProxyApi$SpatulaHeaderResult
    public final String getSpatulaHeader() {
        return this.zzb;
    }

    @Override  // com.google.android.gms.common.api.Result
    public final Status getStatus() {
        return this.zza;
    }
}

