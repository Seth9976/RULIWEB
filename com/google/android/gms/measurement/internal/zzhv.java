package com.google.android.gms.measurement.internal;

import java.util.concurrent.atomic.AtomicReference;

final class zzhv implements Runnable {
    final AtomicReference zza;
    final String zzb;
    final String zzc;
    final zzik zzd;

    zzhv(zzik zzik0, AtomicReference atomicReference0, String s, String s1, String s2) {
        this.zzd = zzik0;
        this.zza = atomicReference0;
        this.zzb = s1;
        this.zzc = s2;
        super();
    }

    @Override
    public final void run() {
        this.zzd.zzt.zzt().zzw(this.zza, null, this.zzb, this.zzc);
    }
}

