package com.google.android.gms.common.internal;

import android.util.Log;

public abstract class zzc {
    private Object zza;
    private boolean zzb;
    final BaseGmsClient zzd;

    public zzc(BaseGmsClient baseGmsClient0, Object object0) {
        this.zzd = baseGmsClient0;
        super();
        this.zza = object0;
        this.zzb = false;
    }

    protected abstract void zza(Object arg1);

    protected abstract void zzc();

    public final void zze() {
        Object object0;
        synchronized(this) {
            object0 = this.zza;
            if(this.zzb) {
                Log.w("GmsClient", "Callback proxy " + this.toString() + " being reused. This is not safe.");
            }
        }
        if(object0 != null) {
            this.zza(object0);
        }
        synchronized(this) {
            this.zzb = true;
        }
        this.zzg();
    }

    public final void zzf() {
        synchronized(this) {
            this.zza = null;
        }
    }

    public final void zzg() {
        this.zzf();
        synchronized(this.zzd.zzt) {
            this.zzd.zzt.remove(this);
        }
    }
}

