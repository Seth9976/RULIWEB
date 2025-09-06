package com.google.android.gms.measurement.internal;

import android.app.ActivityManager.RunningAppProcessInfo;
import android.app.ActivityManager;
import android.os.Bundle;
import android.text.TextUtils;
import com.google.android.gms.internal.measurement.zzos;
import com.google.android.gms.internal.measurement.zzqu;

final class zzko {
    final zzkp zza;

    zzko(zzkp zzkp0) {
        this.zza = zzkp0;
        super();
    }

    final void zza() {
        this.zza.zzg();
        if(this.zza.zzt.zzm().zzk(this.zza.zzt.zzax().currentTimeMillis())) {
            this.zza.zzt.zzm().zzg.zza(true);
            ActivityManager.RunningAppProcessInfo activityManager$RunningAppProcessInfo0 = new ActivityManager.RunningAppProcessInfo();
            ActivityManager.getMyMemoryState(activityManager$RunningAppProcessInfo0);
            if(activityManager$RunningAppProcessInfo0.importance == 100) {
                this.zza.zzt.zzaA().zzj().zza("Detected application was in foreground");
                this.zzc(this.zza.zzt.zzax().currentTimeMillis(), false);
            }
        }
    }

    final void zzb(long v, boolean z) {
        this.zza.zzg();
        zzkp.zzi(this.zza);
        if(this.zza.zzt.zzm().zzk(v)) {
            this.zza.zzt.zzm().zzg.zza(true);
            zzqu.zzc();
            if(this.zza.zzt.zzf().zzs(null, zzeg.zzan)) {
                this.zza.zzt.zzh().zzo();
            }
        }
        this.zza.zzt.zzm().zzj.zzb(v);
        if(this.zza.zzt.zzm().zzg.zzb()) {
            this.zzc(v, z);
        }
    }

    final void zzc(long v, boolean z) {
        this.zza.zzg();
        if(this.zza.zzt.zzJ()) {
            this.zza.zzt.zzm().zzj.zzb(v);
            long v1 = this.zza.zzt.zzax().elapsedRealtime();
            this.zza.zzt.zzaA().zzj().zzb("Session started, time", v1);
            Long long0 = (long)(v / 1000L);
            this.zza.zzt.zzq().zzY("auto", "_sid", long0, v);
            zzfi zzfi0 = this.zza.zzt.zzm();
            long0.getClass();
            zzfi0.zzk.zzb(v / 1000L);
            this.zza.zzt.zzm().zzg.zza(false);
            Bundle bundle0 = new Bundle();
            long0.getClass();
            bundle0.putLong("_sid", v / 1000L);
            if(this.zza.zzt.zzf().zzs(null, zzeg.zzab) && z) {
                bundle0.putLong("_aib", 1L);
            }
            this.zza.zzt.zzq().zzH("auto", "_s", v, bundle0);
            zzos.zzc();
            if(this.zza.zzt.zzf().zzs(null, zzeg.zzae)) {
                String s = this.zza.zzt.zzm().zzp.zza();
                if(!TextUtils.isEmpty(s)) {
                    Bundle bundle1 = new Bundle();
                    bundle1.putString("_ffr", s);
                    this.zza.zzt.zzq().zzH("auto", "_ssr", v, bundle1);
                }
            }
        }
    }
}

