package com.google.android.gms.measurement.internal;

final class zzjj extends zzan {
    final zzjz zza;

    zzjj(zzjz zzjz0, zzgy zzgy0) {
        this.zza = zzjz0;
        super(zzgy0);
    }

    @Override  // com.google.android.gms.measurement.internal.zzan
    public final void zzc() {
        zzjz zzjz0 = this.zza;
        zzjz0.zzg();
        if(!zzjz0.zzL()) {
            return;
        }
        zzjz0.zzt.zzaA().zzj().zza("Inactivity, disconnecting from the service");
        zzjz0.zzs();
    }
}

