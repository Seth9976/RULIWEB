package com.google.android.gms.measurement.internal;

import android.os.Handler;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.measurement.zzby;

abstract class zzan {
    private static volatile Handler zza;
    private final zzgy zzb;
    private final Runnable zzc;
    private volatile long zzd;

    zzan(zzgy zzgy0) {
        Preconditions.checkNotNull(zzgy0);
        this.zzb = zzgy0;
        this.zzc = new zzam(this, zzgy0);
    }

    static void zza(zzan zzan0, long v) {
        zzan0.zzd = 0L;
    }

    final void zzb() {
        this.zzd = 0L;
        this.zzf().removeCallbacks(this.zzc);
    }

    public abstract void zzc();

    public final void zzd(long v) {
        this.zzb();
        if(v >= 0L) {
            this.zzd = this.zzb.zzax().currentTimeMillis();
            if(!this.zzf().postDelayed(this.zzc, v)) {
                this.zzb.zzaA().zzd().zzb("Failed to schedule delayed post. time", v);
            }
        }
    }

    public final boolean zze() {
        return this.zzd != 0L;
    }

    private final Handler zzf() {
        if(zzan.zza != null) {
            return zzan.zza;
        }
        synchronized(zzan.class) {
            if(zzan.zza == null) {
                zzan.zza = new zzby(this.zzb.zzaw().getMainLooper());
            }
            return zzan.zza;
        }
    }
}

