package com.google.android.gms.measurement.internal;

import android.os.Bundle;
import com.google.android.gms.internal.measurement.zzqo;

public final class zzkj implements Runnable {
    public final zzkk zza;

    public zzkj(zzkk zzkk0) {
        this.zza = zzkk0;
    }

    @Override
    public final void run() {
        zzkl zzkl0 = this.zza.zzc;
        long v = this.zza.zza;
        long v1 = this.zza.zzb;
        zzkl0.zza.zzg();
        zzkl0.zza.zzt.zzaA().zzc().zza("Application going to the background");
        zzkl0.zza.zzt.zzm().zzm.zza(true);
        zzkl0.zza.zzm(true);
        if(!zzkl0.zza.zzt.zzf().zzu()) {
            zzkl0.zza.zzb.zzb(v1);
            zzkl0.zza.zzb.zzd(false, false, v1);
        }
        zzqo.zzc();
        if(zzkl0.zza.zzt.zzf().zzs(null, zzeg.zzaB)) {
            zzkl0.zza.zzt.zzaA().zzi().zzb("Application backgrounded at: timestamp_millis", v);
            return;
        }
        zzkl0.zza.zzt.zzq().zzH("auto", "_ab", v, new Bundle());
    }
}

