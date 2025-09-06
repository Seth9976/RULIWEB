package com.google.android.gms.measurement.internal;

import com.google.android.gms.common.internal.Preconditions;

final class zzgm implements Runnable {
    final zzq zza;
    final zzgv zzb;

    zzgm(zzgv zzgv0, zzq zzq0) {
        this.zzb = zzgv0;
        this.zza = zzq0;
        super();
    }

    @Override
    public final void run() {
        zzgv.zzc(this.zzb).zzA();
        zzlh zzlh0 = zzgv.zzc(this.zzb);
        zzlh0.zzaB().zzg();
        zzlh0.zzB();
        Preconditions.checkNotEmpty(this.zza.zza);
        zzlh0.zzd(this.zza);
    }
}

