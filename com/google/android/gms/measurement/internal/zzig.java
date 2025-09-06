package com.google.android.gms.measurement.internal;

import com.google.android.gms.internal.measurement.zzqu;

final class zzig implements Runnable {
    final zzhb zza;
    final long zzb;
    final boolean zzc;
    final zzhb zzd;
    final zzik zze;

    zzig(zzik zzik0, zzhb zzhb0, long v, boolean z, zzhb zzhb1) {
        this.zze = zzik0;
        this.zza = zzhb0;
        this.zzb = v;
        this.zzc = z;
        this.zzd = zzhb1;
        super();
    }

    @Override
    public final void run() {
        this.zze.zzV(this.zza);
        zzik.zzw(this.zze, this.zza, this.zzb, false, this.zzc);
        zzqu.zzc();
        if(this.zze.zzt.zzf().zzs(null, zzeg.zzan)) {
            zzik.zzv(this.zze, this.zza, this.zzd);
        }
    }
}

