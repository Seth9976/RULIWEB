package com.google.android.gms.measurement.internal;

import android.content.Intent;

public final class zzkd implements Runnable {
    public final zzkg zza;
    public final int zzb;
    public final zzet zzc;
    public final Intent zzd;

    public zzkd(zzkg zzkg0, int v, zzet zzet0, Intent intent0) {
        this.zza = zzkg0;
        this.zzb = v;
        this.zzc = zzet0;
        this.zzd = intent0;
    }

    @Override
    public final void run() {
        this.zza.zzc(this.zzb, this.zzc, this.zzd);
    }
}

