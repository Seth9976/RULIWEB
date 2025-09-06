package com.google.android.gms.measurement.internal;

import com.google.android.gms.internal.measurement.zzcf;

final class zzi implements Runnable {
    final zzcf zza;
    final AppMeasurementDynamiteService zzb;

    zzi(AppMeasurementDynamiteService appMeasurementDynamiteService0, zzcf zzcf0) {
        this.zzb = appMeasurementDynamiteService0;
        this.zza = zzcf0;
        super();
    }

    @Override
    public final void run() {
        this.zzb.zza.zzt().zzt(this.zza);
    }
}

