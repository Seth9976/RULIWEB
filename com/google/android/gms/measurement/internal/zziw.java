package com.google.android.gms.measurement.internal;

final class zziw implements Runnable {
    final long zza;
    final zziz zzb;

    zziw(zziz zziz0, long v) {
        this.zzb = zziz0;
        this.zza = v;
        super();
    }

    @Override
    public final void run() {
        this.zzb.zzt.zzd().zzf(this.zza);
        this.zzb.zza = null;
    }
}

