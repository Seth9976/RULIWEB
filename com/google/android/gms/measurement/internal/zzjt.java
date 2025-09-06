package com.google.android.gms.measurement.internal;

final class zzjt implements Runnable {
    final zzej zza;
    final zzjy zzb;

    zzjt(zzjy zzjy0, zzej zzej0) {
        this.zzb = zzjy0;
        this.zza = zzej0;
        super();
    }

    @Override
    public final void run() {
        synchronized(this.zzb) {
            zzjy.zza(this.zzb, false);
            if(!this.zzb.zza.zzL()) {
                this.zzb.zza.zzt.zzaA().zzj().zza("Connected to service");
                this.zzb.zza.zzJ(this.zza);
            }
        }
    }
}

