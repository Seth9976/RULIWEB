package com.google.android.gms.measurement.internal;

final class zzl implements Runnable {
    final zzo zza;
    final AppMeasurementDynamiteService zzb;

    zzl(AppMeasurementDynamiteService appMeasurementDynamiteService0, zzo zzo0) {
        this.zzb = appMeasurementDynamiteService0;
        this.zza = zzo0;
        super();
    }

    @Override
    public final void run() {
        this.zzb.zza.zzq().zzT(this.zza);
    }
}

