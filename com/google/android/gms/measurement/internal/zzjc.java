package com.google.android.gms.measurement.internal;

import com.google.android.gms.common.internal.Preconditions;

final class zzjc implements Runnable {
    final zzq zza;
    final boolean zzb;
    final zzlk zzc;
    final zzjz zzd;

    zzjc(zzjz zzjz0, zzq zzq0, boolean z, zzlk zzlk0) {
        this.zzd = zzjz0;
        this.zza = zzq0;
        this.zzb = z;
        this.zzc = zzlk0;
        super();
    }

    @Override
    public final void run() {
        zzjz zzjz0 = this.zzd;
        zzej zzej0 = zzjz.zzh(zzjz0);
        if(zzej0 == null) {
            zzjz0.zzt.zzaA().zzd().zza("Discarding data. Failed to set user property");
            return;
        }
        Preconditions.checkNotNull(this.zza);
        this.zzd.zzD(zzej0, (this.zzb ? null : this.zzc), this.zza);
        zzjz.zzp(this.zzd);
    }
}

