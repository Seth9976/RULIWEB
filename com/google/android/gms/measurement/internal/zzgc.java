package com.google.android.gms.measurement.internal;

final class zzgc implements Runnable {
    final zzhi zza;
    final zzgd zzb;

    zzgc(zzgd zzgd0, zzhi zzhi0) {
        this.zzb = zzgd0;
        this.zza = zzhi0;
        super();
    }

    @Override
    public final void run() {
        zzgd.zzA(this.zzb, this.zza);
        this.zzb.zzH(this.zza.zzg);
    }
}

