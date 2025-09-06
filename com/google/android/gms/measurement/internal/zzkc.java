package com.google.android.gms.measurement.internal;

import android.app.job.JobParameters;

public final class zzkc implements Runnable {
    public final zzkg zza;
    public final zzet zzb;
    public final JobParameters zzc;

    public zzkc(zzkg zzkg0, zzet zzet0, JobParameters jobParameters0) {
        this.zza = zzkg0;
        this.zzb = zzet0;
        this.zzc = jobParameters0;
    }

    @Override
    public final void run() {
        this.zza.zzd(this.zzb, this.zzc);
    }
}

