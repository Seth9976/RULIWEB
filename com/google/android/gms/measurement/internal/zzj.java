package com.google.android.gms.measurement.internal;

import com.google.android.gms.internal.measurement.zzcf;

final class zzj implements Runnable {
    final zzcf zza;
    final zzau zzb;
    final String zzc;
    final AppMeasurementDynamiteService zzd;

    zzj(AppMeasurementDynamiteService appMeasurementDynamiteService0, zzcf zzcf0, zzau zzau0, String s) {
        this.zzd = appMeasurementDynamiteService0;
        this.zza = zzcf0;
        this.zzb = zzau0;
        this.zzc = s;
        super();
    }

    @Override
    public final void run() {
        this.zzd.zza.zzt().zzB(this.zza, this.zzb, this.zzc);
    }
}

