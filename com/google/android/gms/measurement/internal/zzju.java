package com.google.android.gms.measurement.internal;

import android.content.ComponentName;

final class zzju implements Runnable {
    final ComponentName zza;
    final zzjy zzb;

    zzju(zzjy zzjy0, ComponentName componentName0) {
        this.zzb = zzjy0;
        this.zza = componentName0;
        super();
    }

    @Override
    public final void run() {
        zzjz.zzo(this.zzb.zza, this.zza);
    }
}

