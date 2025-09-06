package com.google.android.gms.measurement.internal;

final class zzix implements Runnable {
    final zzir zza;
    final long zzb;
    final zziz zzc;

    zzix(zziz zziz0, zzir zzir0, long v) {
        this.zzc = zziz0;
        this.zza = zzir0;
        this.zzb = v;
        super();
    }

    @Override
    public final void run() {
        zziz.zzq(this.zzc, this.zza, false, this.zzb);
        this.zzc.zza = null;
        this.zzc.zzt.zzt().zzG(null);
    }
}

