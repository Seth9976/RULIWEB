package com.google.android.gms.measurement.internal;

final class zzgr implements Runnable {
    final zzlk zza;
    final zzq zzb;
    final zzgv zzc;

    zzgr(zzgv zzgv0, zzlk zzlk0, zzq zzq0) {
        this.zzc = zzgv0;
        this.zza = zzlk0;
        this.zzb = zzq0;
        super();
    }

    @Override
    public final void run() {
        zzgv.zzc(this.zzc).zzA();
        if(this.zza.zza() == null) {
            zzgv.zzc(this.zzc).zzP(this.zza.zzb, this.zzb);
            return;
        }
        zzgv.zzc(this.zzc).zzW(this.zza, this.zzb);
    }
}

