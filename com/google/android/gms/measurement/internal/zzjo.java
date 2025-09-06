package com.google.android.gms.measurement.internal;

import com.google.android.gms.common.internal.Preconditions;

final class zzjo implements Runnable {
    final zzq zza;
    final boolean zzb;
    final zzau zzc;
    final String zzd;
    final zzjz zze;

    zzjo(zzjz zzjz0, boolean z, zzq zzq0, boolean z1, zzau zzau0, String s) {
        this.zze = zzjz0;
        this.zza = zzq0;
        this.zzb = z1;
        this.zzc = zzau0;
        this.zzd = s;
        super();
    }

    @Override
    public final void run() {
        zzjz zzjz0 = this.zze;
        zzej zzej0 = zzjz.zzh(zzjz0);
        if(zzej0 == null) {
            zzjz0.zzt.zzaA().zzd().zza("Discarding data. Failed to send event to service");
            return;
        }
        Preconditions.checkNotNull(this.zza);
        this.zze.zzD(zzej0, (this.zzb ? null : this.zzc), this.zza);
        zzjz.zzp(this.zze);
    }
}

