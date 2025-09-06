package com.google.android.gms.measurement.internal;

import android.os.Bundle;

final class zzhp implements Runnable {
    final String zza;
    final String zzb;
    final long zzc;
    final Bundle zzd;
    final boolean zze;
    final boolean zzf;
    final boolean zzg;
    final String zzh;
    final zzik zzi;

    zzhp(zzik zzik0, String s, String s1, long v, Bundle bundle0, boolean z, boolean z1, boolean z2, String s2) {
        this.zzi = zzik0;
        this.zza = s;
        this.zzb = s1;
        this.zzc = v;
        this.zzd = bundle0;
        this.zze = z;
        this.zzf = z1;
        this.zzg = z2;
        this.zzh = s2;
        super();
    }

    @Override
    public final void run() {
        this.zzi.zzI(this.zza, this.zzb, this.zzc, this.zzd, this.zze, this.zzf, this.zzg, this.zzh);
    }
}

