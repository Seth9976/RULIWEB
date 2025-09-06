package com.google.android.gms.measurement.internal;

import java.util.concurrent.atomic.AtomicReference;

final class zzid implements Runnable {
    final AtomicReference zza;
    final zzik zzb;

    zzid(zzik zzik0, AtomicReference atomicReference0) {
        this.zzb = zzik0;
        this.zza = atomicReference0;
        super();
    }

    @Override
    public final void run() {
        synchronized(this.zza) {
            try {
                String s = this.zzb.zzt.zzh().zzl();
                Double double0 = this.zzb.zzt.zzf().zza(s, zzeg.zzO);
                this.zza.set(double0);
            }
            finally {
                this.zza.notify();
            }
        }
    }
}

