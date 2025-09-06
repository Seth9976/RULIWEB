package com.google.android.gms.measurement.internal;

final class zzgt implements Runnable {
    final zzq zza;
    final zzgv zzb;

    zzgt(zzgv zzgv0, zzq zzq0) {
        this.zzb = zzgv0;
        this.zza = zzq0;
        super();
    }

    @Override
    public final void run() {
        zzgv.zzc(this.zzb).zzA();
        zzgv.zzc(this.zzb).zzL(this.zza);
    }
}

