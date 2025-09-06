package com.google.android.gms.measurement.internal;

import java.util.concurrent.atomic.AtomicReference;

final class zzhr implements Runnable {
    final AtomicReference zza;
    final boolean zzb;
    final zzik zzc;

    zzhr(zzik zzik0, AtomicReference atomicReference0, boolean z) {
        this.zzc = zzik0;
        this.zza = atomicReference0;
        this.zzb = z;
        super();
    }

    @Override
    public final void run() {
        this.zzc.zzt.zzt().zzx(this.zza, this.zzb);
    }
}

