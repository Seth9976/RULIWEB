package com.google.android.gms.measurement.internal;

import android.os.Handler;
import android.os.Looper;
import com.google.android.gms.internal.measurement.zzby;

public final class zzkp extends zzf {
    protected final zzko zza;
    protected final zzkn zzb;
    protected final zzkl zzc;
    private Handler zzd;
    private boolean zze;

    zzkp(zzgd zzgd0) {
        super(zzgd0);
        this.zze = true;
        this.zza = new zzko(this);
        this.zzb = new zzkn(this);
        this.zzc = new zzkl(this);
    }

    @Override  // com.google.android.gms.measurement.internal.zzf
    protected final boolean zzf() {
        return false;
    }

    static Handler zzh(zzkp zzkp0) {
        return zzkp0.zzd;
    }

    static void zzi(zzkp zzkp0) {
        zzkp0.zzp();
    }

    static void zzj(zzkp zzkp0, long v) {
        zzkp0.zzg();
        zzkp0.zzp();
        zzkp0.zzt.zzaA().zzj().zzb("Activity paused, time", v);
        zzkp0.zzc.zza(v);
        if(zzkp0.zzt.zzf().zzu()) {
            zzkp0.zzb.zzb(v);
        }
    }

    static void zzl(zzkp zzkp0, long v) {
        zzkp0.zzg();
        zzkp0.zzp();
        zzkp0.zzt.zzaA().zzj().zzb("Activity resumed, time", v);
        if(!zzkp0.zzt.zzf().zzs(null, zzeg.zzaG)) {
            if(zzkp0.zzt.zzf().zzu() || zzkp0.zzt.zzm().zzm.zzb()) {
                zzkp0.zzb.zzc(v);
            }
        }
        else if(zzkp0.zzt.zzf().zzu() || zzkp0.zze) {
            zzkp0.zzb.zzc(v);
        }
        zzkp0.zzc.zzb();
        zzko zzko0 = zzkp0.zza;
        zzko0.zza.zzg();
        if(!zzko0.zza.zzt.zzJ()) {
            return;
        }
        zzko0.zzb(zzko0.zza.zzt.zzax().currentTimeMillis(), false);
    }

    final void zzm(boolean z) {
        this.zzg();
        this.zze = z;
    }

    final boolean zzo() {
        this.zzg();
        return this.zze;
    }

    private final void zzp() {
        this.zzg();
        if(this.zzd == null) {
            this.zzd = new zzby(Looper.getMainLooper());
        }
    }
}

