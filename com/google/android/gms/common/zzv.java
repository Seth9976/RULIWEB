package com.google.android.gms.common;

import java.util.concurrent.Callable;

final class zzv extends zzx {
    private final Callable zze;

    zzv(Callable callable0, zzu zzu0) {
        super(false, 1, 5, null, null, null);
        this.zze = callable0;
    }

    @Override  // com.google.android.gms.common.zzx
    final String zza() {
        try {
            return (String)this.zze.call();
        }
        catch(Exception exception0) {
            throw new RuntimeException(exception0);
        }
    }
}

