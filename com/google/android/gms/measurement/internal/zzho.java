package com.google.android.gms.measurement.internal;

final class zzho implements Runnable {
    final long zza;
    final zzik zzb;

    zzho(zzik zzik0, long v) {
        this.zzb = zzik0;
        this.zza = v;
        super();
    }

    @Override
    public final void run() {
        this.zzb.zzt.zzm().zzf.zzb(this.zza);
        this.zzb.zzt.zzaA().zzc().zzb("Session timeout duration set", this.zza);
    }
}

