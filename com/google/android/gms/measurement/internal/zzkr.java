package com.google.android.gms.measurement.internal;

final class zzkr extends zzan {
    final zzks zza;

    zzkr(zzks zzks0, zzgy zzgy0) {
        this.zza = zzks0;
        super(zzgy0);
    }

    @Override  // com.google.android.gms.measurement.internal.zzan
    public final void zzc() {
        this.zza.zza();
        this.zza.zzt.zzaA().zzj().zza("Starting upload from DelayedRunnable");
        this.zza.zzf.zzX();
    }
}

