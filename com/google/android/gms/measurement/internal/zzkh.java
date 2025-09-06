package com.google.android.gms.measurement.internal;

final class zzkh implements Runnable {
    final long zza;
    final zzkp zzb;

    zzkh(zzkp zzkp0, long v) {
        this.zzb = zzkp0;
        this.zza = v;
        super();
    }

    @Override
    public final void run() {
        zzkp.zzl(this.zzb, this.zza);
    }
}

