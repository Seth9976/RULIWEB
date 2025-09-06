package com.google.android.gms.internal.measurement;

import android.os.Bundle;
import android.util.Log;
import java.util.concurrent.atomic.AtomicReference;

public final class zzbz extends zzce {
    private final AtomicReference zza;
    private boolean zzb;

    public zzbz() {
        this.zza = new AtomicReference();
    }

    public final Bundle zzb(long v) {
        synchronized(this.zza) {
            if(!this.zzb) {
                try {
                    this.zza.wait(v);
                }
                catch(InterruptedException unused_ex) {
                    return null;
                }
            }
        }
        return (Bundle)this.zza.get();
    }

    public final Long zzc(long v) {
        return (Long)zzbz.zzf(this.zzb(v), Long.class);
    }

    public final String zzd(long v) {
        return (String)zzbz.zzf(this.zzb(v), String.class);
    }

    @Override  // com.google.android.gms.internal.measurement.zzcf
    public final void zze(Bundle bundle0) {
        synchronized(this.zza) {
            try {
                this.zza.set(bundle0);
                this.zzb = true;
            }
            finally {
                this.zza.notify();
            }
        }
    }

    public static final Object zzf(Bundle bundle0, Class class0) {
        if(bundle0 != null) {
            Object object0 = bundle0.get("r");
            if(object0 != null) {
                try {
                    return class0.cast(object0);
                }
                catch(ClassCastException classCastException0) {
                    Log.w("AM", String.format("Unexpected object type. Expected, Received: %s, %s", class0.getCanonicalName(), object0.getClass().getCanonicalName()), classCastException0);
                    throw classCastException0;
                }
            }
        }
        return null;
    }
}

