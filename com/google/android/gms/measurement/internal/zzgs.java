package com.google.android.gms.measurement.internal;

import java.util.concurrent.Callable;

final class zzgs implements Callable {
    final String zza;
    final zzgv zzb;

    zzgs(zzgv zzgv0, String s) {
        this.zzb = zzgv0;
        this.zza = s;
        super();
    }

    @Override
    public final Object call() throws Exception {
        zzgv.zzc(this.zzb).zzA();
        return zzgv.zzc(this.zzb).zzh().zzu(this.zza);
    }
}

