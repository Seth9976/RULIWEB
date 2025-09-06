package com.google.android.gms.measurement.internal;

import java.util.concurrent.Callable;

final class zzgi implements Callable {
    final String zza;
    final String zzb;
    final String zzc;
    final zzgv zzd;

    zzgi(zzgv zzgv0, String s, String s1, String s2) {
        this.zzd = zzgv0;
        this.zza = s;
        this.zzb = s1;
        this.zzc = s2;
        super();
    }

    @Override
    public final Object call() throws Exception {
        zzgv.zzc(this.zzd).zzA();
        return zzgv.zzc(this.zzd).zzh().zzv(this.zza, this.zzb, this.zzc);
    }
}

