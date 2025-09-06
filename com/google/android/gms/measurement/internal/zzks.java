package com.google.android.gms.measurement.internal;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.job.JobInfo.Builder;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Build.VERSION;
import android.os.PersistableBundle;
import com.google.android.gms.internal.measurement.zzbs;
import com.google.android.gms.internal.measurement.zzbt;

public final class zzks extends zzku {
    private final AlarmManager zza;
    private zzan zzb;
    private Integer zzc;

    protected zzks(zzlh zzlh0) {
        super(zzlh0);
        this.zza = (AlarmManager)this.zzt.zzaw().getSystemService("alarm");
    }

    public final void zza() {
        this.zzW();
        this.zzt.zzaA().zzj().zza("Unscheduling upload");
        AlarmManager alarmManager0 = this.zza;
        if(alarmManager0 != null) {
            alarmManager0.cancel(this.zzh());
        }
        this.zzi().zzb();
        if(Build.VERSION.SDK_INT >= 24) {
            this.zzj();
        }
    }

    @Override  // com.google.android.gms.measurement.internal.zzku
    protected final boolean zzb() {
        AlarmManager alarmManager0 = this.zza;
        if(alarmManager0 != null) {
            alarmManager0.cancel(this.zzh());
        }
        if(Build.VERSION.SDK_INT >= 24) {
            this.zzj();
        }
        return false;
    }

    public final void zzd(long v) {
        this.zzW();
        Context context0 = this.zzt.zzaw();
        if(!zzlp.zzal(context0)) {
            this.zzt.zzaA().zzc().zza("Receiver not registered/enabled");
        }
        if(!zzlp.zzam(context0, false)) {
            this.zzt.zzaA().zzc().zza("Service not registered/enabled");
        }
        this.zza();
        this.zzt.zzaA().zzj().zzb("Scheduling upload, millis", v);
        long v1 = this.zzt.zzax().elapsedRealtime();
        if(v < Math.max(0L, ((long)(((Long)zzeg.zzx.zza(null))))) && !this.zzi().zze()) {
            this.zzi().zzd(v);
        }
        if(Build.VERSION.SDK_INT >= 24) {
            Context context1 = this.zzt.zzaw();
            ComponentName componentName0 = new ComponentName(context1, "com.google.android.gms.measurement.AppMeasurementJobService");
            int v2 = this.zzf();
            PersistableBundle persistableBundle0 = new PersistableBundle();
            persistableBundle0.putString("action", "com.google.android.gms.measurement.UPLOAD");
            zzbt.zza(context1, new JobInfo.Builder(v2, componentName0).setMinimumLatency(v).setOverrideDeadline(v + v).setExtras(persistableBundle0).build(), "com.google.android.gms", "UploadAlarm");
            return;
        }
        AlarmManager alarmManager0 = this.zza;
        if(alarmManager0 != null) {
            alarmManager0.setInexactRepeating(2, v1 + v, Math.max(((long)(((Long)zzeg.zzs.zza(null)))), v), this.zzh());
        }
    }

    private final int zzf() {
        if(this.zzc == null) {
            this.zzc = 0xC5307407;
        }
        return (int)this.zzc;
    }

    private final PendingIntent zzh() {
        Context context0 = this.zzt.zzaw();
        return PendingIntent.getBroadcast(context0, 0, new Intent().setClassName(context0, "com.google.android.gms.measurement.AppMeasurementReceiver").setAction("com.google.android.gms.measurement.UPLOAD"), zzbs.zza);
    }

    private final zzan zzi() {
        if(this.zzb == null) {
            this.zzb = new zzkr(this, this.zzf.zzp());
        }
        return this.zzb;
    }

    private final void zzj() {
        JobScheduler jobScheduler0 = (JobScheduler)this.zzt.zzaw().getSystemService("jobscheduler");
        if(jobScheduler0 != null) {
            jobScheduler0.cancel(this.zzf());
        }
    }
}

