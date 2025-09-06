package com.google.android.gms.measurement.internal;

import com.google.android.gms.internal.measurement.zzcf;

final class zzn implements Runnable {
    final zzcf zza;
    final AppMeasurementDynamiteService zzb;

    zzn(AppMeasurementDynamiteService appMeasurementDynamiteService0, zzcf zzcf0) {
        this.zzb = appMeasurementDynamiteService0;
        this.zza = zzcf0;
        super();
    }

    @Override
    public final void run() {
        zzlp zzlp0 = this.zzb.zza.zzv();
        boolean z = this.zzb.zza.zzI();
        zzlp0.zzQ(this.zza, z);
    }
}

