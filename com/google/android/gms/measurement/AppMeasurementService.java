package com.google.android.gms.measurement;

import android.app.Service;
import android.app.job.JobParameters;
import android.content.Intent;
import android.os.IBinder;
import com.google.android.gms.measurement.internal.zzkf;
import com.google.android.gms.measurement.internal.zzkg;

public final class AppMeasurementService extends Service implements zzkf {
    private zzkg zza;

    @Override  // android.app.Service
    public IBinder onBind(Intent intent0) {
        return this.zzd().zzb(intent0);
    }

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

    @Override  // android.app.Service
    public int onStartCommand(Intent intent0, int v, int v1) {
        this.zzd().zza(intent0, v, v1);
        return 2;
    }

    @Override  // android.app.Service
    public boolean onUnbind(Intent intent0) {
        this.zzd().zzj(intent0);
        return true;
    }

    @Override  // com.google.android.gms.measurement.internal.zzkf
    public final void zza(Intent intent0) {
        AppMeasurementReceiver.completeWakefulIntent(intent0);
    }

    @Override  // com.google.android.gms.measurement.internal.zzkf
    public final void zzb(JobParameters jobParameters0, boolean z) {
        throw new UnsupportedOperationException();
    }

    @Override  // com.google.android.gms.measurement.internal.zzkf
    public final boolean zzc(int v) {
        return this.stopSelfResult(v);
    }

    private final zzkg zzd() {
        if(this.zza == null) {
            this.zza = new zzkg(this);
        }
        return this.zza;
    }
}

