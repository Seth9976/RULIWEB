package com.google.android.gms.measurement.internal;

final class zzkx implements Runnable {
    final zzli zza;
    final zzlh zzb;

    zzkx(zzlh zzlh0, zzli zzli0) {
        this.zzb = zzlh0;
        this.zza = zzli0;
        super();
    }

    @Override
    public final void run() {
        zzlh.zzy(this.zzb, this.zza);
        this.zzb.zzS();
    }
}

