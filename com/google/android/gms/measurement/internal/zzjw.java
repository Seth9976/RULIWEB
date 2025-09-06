package com.google.android.gms.measurement.internal;

import android.content.ComponentName;

final class zzjw implements Runnable {
    final zzjy zza;

    zzjw(zzjy zzjy0) {
        this.zza = zzjy0;
        super();
    }

    @Override
    public final void run() {
        ComponentName componentName0 = new ComponentName(this.zza.zza.zzt.zzaw(), "com.google.android.gms.measurement.AppMeasurementService");
        zzjz.zzo(this.zza.zza, componentName0);
    }
}

