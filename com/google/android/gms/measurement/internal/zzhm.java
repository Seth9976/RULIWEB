package com.google.android.gms.measurement.internal;

public final class zzhm implements Runnable {
    public final zzik zza;

    public zzhm(zzik zzik0) {
        this.zza = zzik0;
    }

    @Override
    public final void run() {
        zzik zzik0 = this.zza;
        zzik0.zzg();
        if(!zzik0.zzt.zzm().zzn.zzb()) {
            long v = zzik0.zzt.zzm().zzo.zza();
            zzik0.zzt.zzm().zzo.zzb(v + 1L);
            if(v >= 5L) {
                zzik0.zzt.zzaA().zzk().zza("Permanently failed to retrieve Deferred Deep Link. Reached maximum retries.");
                zzik0.zzt.zzm().zzn.zza(true);
                return;
            }
            zzik0.zzt.zzE();
            return;
        }
        zzik0.zzt.zzaA().zzc().zza("Deferred Deep Link already retrieved. Not fetching again.");
    }
}

