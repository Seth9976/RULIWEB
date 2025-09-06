package com.google.android.gms.measurement.internal;

import java.util.concurrent.atomic.AtomicReference;

final class zzic implements Runnable {
    final AtomicReference zza;
    final zzik zzb;

    zzic(zzik zzik0, AtomicReference atomicReference0) {
        this.zzb = zzik0;
        this.zza = atomicReference0;
        super();
    }

    @Override
    public final void run() {
        synchronized(this.zza) {
            try {
                String s = this.zzb.zzt.zzh().zzl();
                Integer integer0 = this.zzb.zzt.zzf().zze(s, zzeg.zzN);
                this.zza.set(integer0);
            }
            finally {
                this.zza.notify();
            }
        }
    }
}

