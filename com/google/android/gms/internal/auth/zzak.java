package com.google.android.gms.internal.auth;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;

final class zzak implements Result {
    private final Status zza;

    public zzak(Status status0) {
        this.zza = status0;
    }

    @Override  // com.google.android.gms.common.api.Result
    public final Status getStatus() {
        return this.zza;
    }
}

