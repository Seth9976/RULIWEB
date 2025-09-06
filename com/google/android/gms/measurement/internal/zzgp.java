package com.google.android.gms.measurement.internal;

final class zzgp implements Runnable {
    final zzau zza;
    final String zzb;
    final zzgv zzc;

    zzgp(zzgv zzgv0, zzau zzau0, String s) {
        this.zzc = zzgv0;
        this.zza = zzau0;
        this.zzb = s;
        super();
    }

    @Override
    public final void run() {
        zzgv.zzc(this.zzc).zzA();
        zzgv.zzc(this.zzc).zzF(this.zza, this.zzb);
    }
}

