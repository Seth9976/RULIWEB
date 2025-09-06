package com.google.android.gms.measurement.internal;

import com.google.android.gms.internal.measurement.zzcf;

final class zzm implements Runnable {
    final zzcf zza;
    final String zzb;
    final String zzc;
    final AppMeasurementDynamiteService zzd;

    zzm(AppMeasurementDynamiteService appMeasurementDynamiteService0, zzcf zzcf0, String s, String s1) {
        this.zzd = appMeasurementDynamiteService0;
        this.zza = zzcf0;
        this.zzb = s;
        this.zzc = s1;
        super();
    }

    @Override
    public final void run() {
        this.zzd.zza.zzt().zzv(this.zza, this.zzb, this.zzc);
    }
}

