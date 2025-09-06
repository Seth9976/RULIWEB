package com.google.android.gms.measurement.internal;

import java.util.concurrent.atomic.AtomicReference;

final class zzib implements Runnable {
    final AtomicReference zza;
    final zzik zzb;

    zzib(zzik zzik0, AtomicReference atomicReference0) {
        this.zzb = zzik0;
        this.zza = atomicReference0;
        super();
    }

    @Override
    public final void run() {
        synchronized(this.zza) {
            try {
                String s = this.zzb.zzt.zzh().zzl();
                Long long0 = this.zzb.zzt.zzf().zzi(s, zzeg.zzM);
                this.zza.set(long0);
            }
            finally {
                this.zza.notify();
            }
        }
    }
}

