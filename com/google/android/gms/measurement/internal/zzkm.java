package com.google.android.gms.measurement.internal;

final class zzkm extends zzan {
    final zzkn zza;

    zzkm(zzkn zzkn0, zzgy zzgy0) {
        this.zza = zzkn0;
        super(zzgy0);
    }

    @Override  // com.google.android.gms.measurement.internal.zzan
    public final void zzc() {
        this.zza.zzc.zzg();
        long v = this.zza.zzc.zzt.zzax().elapsedRealtime();
        this.zza.zzd(false, false, v);
        this.zza.zzc.zzt.zzd().zzf(this.zza.zzc.zzt.zzax().elapsedRealtime());
    }
}

