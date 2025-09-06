package com.google.android.gms.measurement.internal;

import com.google.android.gms.internal.measurement.zzqu;

final class zzif implements Runnable {
    final zzhb zza;
    final long zzb;
    final long zzc;
    final boolean zzd;
    final zzhb zze;
    final zzik zzf;

    zzif(zzik zzik0, zzhb zzhb0, long v, long v1, boolean z, zzhb zzhb1) {
        this.zzf = zzik0;
        this.zza = zzhb0;
        this.zzb = v;
        this.zzc = v1;
        this.zzd = z;
        this.zze = zzhb1;
        super();
    }

    @Override
    public final void run() {
        this.zzf.zzV(this.zza);
        this.zzf.zzL(this.zzb, false);
        zzik.zzw(this.zzf, this.zza, this.zzc, true, this.zzd);
        zzqu.zzc();
        if(this.zzf.zzt.zzf().zzs(null, zzeg.zzan)) {
            zzik.zzv(this.zzf, this.zza, this.zze);
        }
    }
}

