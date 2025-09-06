package com.google.android.gms.measurement.internal;

final class zzih implements Runnable {
    final boolean zza;
    final zzik zzb;

    zzih(zzik zzik0, boolean z) {
        this.zzb = zzik0;
        this.zza = z;
        super();
    }

    @Override
    public final void run() {
        boolean z = this.zzb.zzt.zzJ();
        boolean z1 = this.zzb.zzt.zzI();
        this.zzb.zzt.zzF(this.zza);
        if(z1 == this.zza) {
            this.zzb.zzt.zzaA().zzj().zzb("Default data collection state already set to", Boolean.valueOf(this.zza));
        }
        if(this.zzb.zzt.zzJ() == z || this.zzb.zzt.zzJ() != this.zzb.zzt.zzI()) {
            this.zzb.zzt.zzaA().zzl().zzc("Default data collection is different than actual status", Boolean.valueOf(this.zza), Boolean.valueOf(z));
        }
        zzik.zzy(this.zzb);
    }
}

