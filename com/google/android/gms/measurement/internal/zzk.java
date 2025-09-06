package com.google.android.gms.measurement.internal;

import com.google.android.gms.internal.measurement.zzcf;

final class zzk implements Runnable {
    final zzcf zza;
    final String zzb;
    final String zzc;
    final boolean zzd;
    final AppMeasurementDynamiteService zze;

    zzk(AppMeasurementDynamiteService appMeasurementDynamiteService0, zzcf zzcf0, String s, String s1, boolean z) {
        this.zze = appMeasurementDynamiteService0;
        this.zza = zzcf0;
        this.zzb = s;
        this.zzc = s1;
        this.zzd = z;
        super();
    }

    @Override
    public final void run() {
        this.zze.zza.zzt().zzy(this.zza, this.zzb, this.zzc, this.zzd);
    }
}

