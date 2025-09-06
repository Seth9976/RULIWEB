package com.google.android.gms.measurement.internal;

final class zzki implements Runnable {
    final long zza;
    final zzkp zzb;

    zzki(zzkp zzkp0, long v) {
        this.zzb = zzkp0;
        this.zza = v;
        super();
    }

    @Override
    public final void run() {
        zzkp.zzj(this.zzb, this.zza);
    }
}

