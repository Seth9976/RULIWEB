package com.google.android.gms.measurement.internal;

final class zzhq implements Runnable {
    final String zza;
    final String zzb;
    final Object zzc;
    final long zzd;
    final zzik zze;

    zzhq(zzik zzik0, String s, String s1, Object object0, long v) {
        this.zze = zzik0;
        this.zza = s;
        this.zzb = s1;
        this.zzc = object0;
        this.zzd = v;
        super();
    }

    @Override
    public final void run() {
        this.zze.zzY(this.zza, this.zzb, this.zzc, this.zzd);
    }
}

