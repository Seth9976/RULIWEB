package com.google.android.gms.measurement.internal;

final class zzb implements Runnable {
    final String zza;
    final long zzb;
    final zzd zzc;

    zzb(zzd zzd0, String s, long v) {
        this.zzc = zzd0;
        this.zza = s;
        this.zzb = v;
        super();
    }

    @Override
    public final void run() {
        zzd.zzb(this.zzc, this.zza, this.zzb);
    }
}

