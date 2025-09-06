package com.google.android.gms.measurement.internal;

public final class zzhl implements Runnable {
    public final zzik zza;
    public final String zzb;

    public zzhl(zzik zzik0, String s) {
        this.zza = zzik0;
        this.zzb = s;
    }

    @Override
    public final void run() {
        zzik zzik0 = this.zza;
        if(zzik0.zzt.zzh().zzp(this.zzb)) {
            zzik0.zzt.zzh().zzo();
        }
    }
}

