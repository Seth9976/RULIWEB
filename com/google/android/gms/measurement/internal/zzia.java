package com.google.android.gms.measurement.internal;

import java.util.concurrent.atomic.AtomicReference;

final class zzia implements Runnable {
    final AtomicReference zza;
    final zzik zzb;

    zzia(zzik zzik0, AtomicReference atomicReference0) {
        this.zzb = zzik0;
        this.zza = atomicReference0;
        super();
    }

    @Override
    public final void run() {
        synchronized(this.zza) {
            try {
                String s = this.zzb.zzt.zzh().zzl();
                String s1 = this.zzb.zzt.zzf().zzo(s, zzeg.zzL);
                this.zza.set(s1);
            }
            finally {
                this.zza.notify();
            }
        }
    }
}

