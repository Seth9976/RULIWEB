package com.google.android.gms.measurement;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.content.Intent;
import com.google.android.gms.measurement.internal.zzkf;
import com.google.android.gms.measurement.internal.zzkg;

public final class AppMeasurementJobService extends JobService implements zzkf {
    private zzkg zza;

    @Override  // android.app.Service
    public void onCreate() {
        super.onCreate();
        this.zzd().zze();
    }

    @Override  // android.app.Service
    public void onDestroy() {
        this.zzd().zzf();
        super.onDestroy();
    }

    @Override  // android.app.Service
    public void onRebind(Intent intent0) {
        this.zzd().zzg(intent0);
    }

    @Override  // android.app.job.JobService
    public boolean onStartJob(JobParameters jobParameters0) {
        this.zzd().zzi(jobParameters0);
        return true;
    }

    @Override  // android.app.job.JobService
    public boolean onStopJob(JobParameters jobParameters0) {
        return false;
    }

    @Override  // android.app.Service
    public boolean onUnbind(Intent intent0) {
        this.zzd().zzj(intent0);
        return true;
    }

    @Override  // com.google.android.gms.measurement.internal.zzkf
    public final void zza(Intent intent0) {
    }

    @Override  // com.google.android.gms.measurement.internal.zzkf
    public final void zzb(JobParameters jobParameters0, boolean z) {
        this.jobFinished(jobParameters0, false);
    }

    @Override  // com.google.android.gms.measurement.internal.zzkf
    public final boolean zzc(int v) {
        throw new UnsupportedOperationException();
    }

    private final zzkg zzd() {
        if(this.zza == null) {
            this.zza = new zzkg(this);
        }
        return this.zza;
    }
}

