package com.google.android.gms.measurement.internal;

final class zzgf implements Runnable {
    final zzac zza;
    final zzq zzb;
    final zzgv zzc;

    zzgf(zzgv zzgv0, zzac zzac0, zzq zzq0) {
        this.zzc = zzgv0;
        this.zza = zzac0;
        this.zzb = zzq0;
        super();
    }

    @Override
    public final void run() {
        zzgv.zzc(this.zzc).zzA();
        if(this.zza.zzc.zza() == null) {
            zzgv.zzc(this.zzc).zzO(this.zza, this.zzb);
            return;
        }
        zzgv.zzc(this.zzc).zzU(this.zza, this.zzb);
    }
}

