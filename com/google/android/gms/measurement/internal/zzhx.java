package com.google.android.gms.measurement.internal;

import java.util.concurrent.atomic.AtomicReference;

final class zzhx implements Runnable {
    final AtomicReference zza;
    final String zzb;
    final String zzc;
    final boolean zzd;
    final zzik zze;

    zzhx(zzik zzik0, AtomicReference atomicReference0, String s, String s1, String s2, boolean z) {
        this.zze = zzik0;
        this.zza = atomicReference0;
        this.zzb = s1;
        this.zzc = s2;
        this.zzd = z;
        super();
    }

    @Override
    public final void run() {
        this.zze.zzt.zzt().zzz(this.zza, null, this.zzb, this.zzc, this.zzd);
    }
}

