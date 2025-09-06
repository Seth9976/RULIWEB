package com.google.android.gms.measurement.internal;

import java.util.concurrent.atomic.AtomicReference;

final class zzhs implements Runnable {
    final long zza;
    final zzik zzb;

    zzhs(zzik zzik0, long v) {
        this.zzb = zzik0;
        this.zza = v;
        super();
    }

    @Override
    public final void run() {
        this.zzb.zzL(this.zza, true);
        this.zzb.zzt.zzt().zzu(new AtomicReference());
    }
}

