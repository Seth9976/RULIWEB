package com.google.android.gms.measurement.internal;

final class zzgl implements Runnable {
    final zzq zza;
    final zzgv zzb;

    zzgl(zzgv zzgv0, zzq zzq0) {
        this.zzb = zzgv0;
        this.zza = zzq0;
        super();
    }

    @Override
    public final void run() {
        zzgv.zzc(this.zzb).zzA();
        zzgv.zzc(this.zzb).zzQ(this.zza);
    }
}

