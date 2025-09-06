package com.google.android.gms.measurement.internal;

final class zzgo implements Runnable {
    final zzau zza;
    final zzq zzb;
    final zzgv zzc;

    zzgo(zzgv zzgv0, zzau zzau0, zzq zzq0) {
        this.zzc = zzgv0;
        this.zza = zzau0;
        this.zzb = zzq0;
        super();
    }

    @Override
    public final void run() {
        zzau zzau0 = this.zzc.zzb(this.zza, this.zzb);
        this.zzc.zzv(zzau0, this.zzb);
    }
}

