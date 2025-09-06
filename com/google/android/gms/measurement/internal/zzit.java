package com.google.android.gms.measurement.internal;

import android.os.Bundle;

final class zzit implements Runnable {
    final Bundle zza;
    final zzir zzb;
    final zzir zzc;
    final long zzd;
    final zziz zze;

    zzit(zziz zziz0, Bundle bundle0, zzir zzir0, zzir zzir1, long v) {
        this.zze = zziz0;
        this.zza = bundle0;
        this.zzb = zzir0;
        this.zzc = zzir1;
        this.zzd = v;
        super();
    }

    @Override
    public final void run() {
        zziz.zzp(this.zze, this.zza, this.zzb, this.zzc, this.zzd);
    }
}

