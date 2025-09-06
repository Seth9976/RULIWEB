package com.google.android.gms.measurement.internal;

final class zziu implements Runnable {
    final zzir zza;
    final zzir zzb;
    final long zzc;
    final boolean zzd;
    final zziz zze;

    zziu(zziz zziz0, zzir zzir0, zzir zzir1, long v, boolean z) {
        this.zze = zziz0;
        this.zza = zzir0;
        this.zzb = zzir1;
        this.zzc = v;
        this.zzd = z;
        super();
    }

    @Override
    public final void run() {
        zziz.zzo(this.zze, this.zza, this.zzb, this.zzc, this.zzd, null);
    }
}

