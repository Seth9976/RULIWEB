package com.google.android.gms.measurement.internal;

final class zzgu implements Runnable {
    final String zza;
    final String zzb;
    final String zzc;
    final long zzd;
    final zzgv zze;

    zzgu(zzgv zzgv0, String s, String s1, String s2, long v) {
        this.zze = zzgv0;
        this.zza = s;
        this.zzb = s1;
        this.zzc = s2;
        this.zzd = v;
        super();
    }

    @Override
    public final void run() {
        String s = this.zza;
        if(s == null) {
            this.zze.zza.zzR(this.zzb, null);
            return;
        }
        zzir zzir0 = new zzir(this.zzc, s, this.zzd);
        this.zze.zza.zzR(this.zzb, zzir0);
    }
}

