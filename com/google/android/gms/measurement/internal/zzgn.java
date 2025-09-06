package com.google.android.gms.measurement.internal;

import com.google.android.gms.common.internal.Preconditions;

final class zzgn implements Runnable {
    final zzq zza;
    final zzgv zzb;

    zzgn(zzgv zzgv0, zzq zzq0) {
        this.zzb = zzgv0;
        this.zza = zzq0;
        super();
    }

    @Override
    public final void run() {
        zzgv.zzc(this.zzb).zzA();
        zzlh zzlh0 = zzgv.zzc(this.zzb);
        zzq zzq0 = this.zza;
        zzlh0.zzaB().zzg();
        zzlh0.zzB();
        Preconditions.checkNotEmpty(zzq0.zza);
        zzhb zzhb0 = zzhb.zzc(zzq0.zzv, 100);
        zzhb zzhb1 = zzlh0.zzq(zzq0.zza);
        zzlh0.zzaA().zzj().zzc("Setting consent, package, consent", zzq0.zza, zzhb0);
        zzlh0.zzV(zzq0.zza, zzhb0);
        if(zzhb0.zzm(zzhb1)) {
            zzlh0.zzQ(zzq0);
        }
    }
}

