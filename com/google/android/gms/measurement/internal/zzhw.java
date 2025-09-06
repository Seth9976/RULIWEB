package com.google.android.gms.measurement.internal;

import java.util.concurrent.atomic.AtomicReference;

final class zzhw implements Runnable {
    final AtomicReference zza;
    final zzik zzb;

    zzhw(zzik zzik0, AtomicReference atomicReference0) {
        this.zzb = zzik0;
        this.zza = atomicReference0;
        super();
    }

    @Override
    public final void run() {
        synchronized(this.zza) {
            try {
                String s = this.zzb.zzt.zzh().zzl();
                Boolean boolean0 = Boolean.valueOf(this.zzb.zzt.zzf().zzs(s, zzeg.zzK));
                this.zza.set(boolean0);
            }
            finally {
                this.zza.notify();
            }
        }
    }
}

