package com.google.android.gms.measurement.internal;

import com.google.android.gms.common.internal.Preconditions;
import java.util.concurrent.Callable;

final class zzla implements Callable {
    final zzq zza;
    final zzlh zzb;

    zzla(zzlh zzlh0, zzq zzq0) {
        this.zzb = zzlh0;
        this.zza = zzq0;
        super();
    }

    @Override
    public final Object call() throws Exception {
        String s = (String)Preconditions.checkNotNull(this.zza.zza);
        if(this.zzb.zzq(s).zzj(zzha.zzb) && zzhb.zzc(this.zza.zzv, 100).zzj(zzha.zzb)) {
            return this.zzb.zzd(this.zza).zzw();
        }
        this.zzb.zzaA().zzj().zza("Analytics storage consent denied. Returning null app instance id");
        return null;
    }
}

