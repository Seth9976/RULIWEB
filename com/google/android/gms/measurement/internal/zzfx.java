package com.google.android.gms.measurement.internal;

import com.google.android.gms.common.internal.Preconditions;

final class zzfx implements Thread.UncaughtExceptionHandler {
    final zzga zza;
    private final String zzb;

    public zzfx(zzga zzga0, String s) {
        this.zza = zzga0;
        super();
        Preconditions.checkNotNull(s);
        this.zzb = s;
    }

    @Override
    public final void uncaughtException(Thread thread0, Throwable throwable0) {
        synchronized(this) {
            this.zza.zzt.zzaA().zzd().zzb(this.zzb, throwable0);
        }
    }
}

