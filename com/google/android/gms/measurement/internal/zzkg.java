package com.google.android.gms.measurement.internal;

import android.app.job.JobParameters;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import com.google.android.gms.common.internal.Preconditions;

public final class zzkg {
    private final Context zza;

    public zzkg(Context context0) {
        Preconditions.checkNotNull(context0);
        this.zza = context0;
    }

    public final int zza(Intent intent0, int v, int v1) {
        zzet zzet0 = zzgd.zzp(this.zza, null, null).zzaA();
        if(intent0 == null) {
            zzet0.zzk().zza("AppMeasurementService started with null intent");
            return 2;
        }
        String s = intent0.getAction();
        zzet0.zzj().zzc("Local AppMeasurementService called. startId, action", v1, s);
        if("com.google.android.gms.measurement.UPLOAD".equals(s)) {
            this.zzh(() -> if(((zzkf)this.zza).zzc(v1)) {
                zzet0.zzj().zzb("Local AppMeasurementService processed last upload request. StartId", v1);
                this.zzk().zzj().zza("Completed wakeful intent.");
                ((zzkf)this.zza).zza(intent0);
            });
        }
        return 2;
    }

    public final IBinder zzb(Intent intent0) {
        if(intent0 == null) {
            this.zzk().zzd().zza("onBind called with null intent");
            return null;
        }
        String s = intent0.getAction();
        if("com.google.android.gms.measurement.START".equals(s)) {
            return new zzgv(zzlh.zzt(this.zza), null);
        }
        this.zzk().zzk().zzb("onBind received unknown action", s);
        return null;
    }

    // 检测为 Lambda 实现
    final void zzc(int v, zzet zzet0, Intent intent0) [...]

    // 检测为 Lambda 实现
    final void zzd(zzet zzet0, JobParameters jobParameters0) [...]

    public final void zze() {
        zzgd.zzp(this.zza, null, null).zzaA().zzj().zza("Local AppMeasurementService is starting up");
    }

    public final void zzf() {
        zzgd.zzp(this.zza, null, null).zzaA().zzj().zza("Local AppMeasurementService is shutting down");
    }

    public final void zzg(Intent intent0) {
        if(intent0 == null) {
            this.zzk().zzd().zza("onRebind called with null intent");
            return;
        }
        String s = intent0.getAction();
        this.zzk().zzj().zzb("onRebind called. action", s);
    }

    public final void zzh(Runnable runnable0) {
        zzlh zzlh0 = zzlh.zzt(this.zza);
        zzlh0.zzaB().zzp(new zzke(this, zzlh0, runnable0));
    }

    public final boolean zzi(JobParameters jobParameters0) {
        zzet zzet0 = zzgd.zzp(this.zza, null, null).zzaA();
        String s = jobParameters0.getExtras().getString("action");
        zzet0.zzj().zzb("Local AppMeasurementJobService called. action", s);
        if("com.google.android.gms.measurement.UPLOAD".equals(s)) {
            this.zzh(() -> {
                zzet0.zzj().zza("AppMeasurementJobService processed last upload request.");
                ((zzkf)this.zza).zzb(jobParameters0, false);
            });
        }
        return true;
    }

    public final boolean zzj(Intent intent0) {
        if(intent0 == null) {
            this.zzk().zzd().zza("onUnbind called with null intent");
            return true;
        }
        String s = intent0.getAction();
        this.zzk().zzj().zzb("onUnbind called for intent. action", s);
        return true;
    }

    private final zzet zzk() {
        return zzgd.zzp(this.zza, null, null).zzaA();
    }
}

