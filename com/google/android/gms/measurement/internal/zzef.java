package com.google.android.gms.measurement.internal;

public final class zzef {
    private static final Object zza;
    private final String zzb;
    private final zzec zzc;
    private final Object zzd;
    private final Object zze;
    private final Object zzf;
    private volatile Object zzg;
    private volatile Object zzh;

    static {
        zzef.zza = new Object();
    }

    zzef(String s, Object object0, Object object1, zzec zzec0, zzee zzee0) {
        this.zzf = new Object();
        this.zzg = null;
        this.zzh = null;
        this.zzb = s;
        this.zzd = object0;
        this.zze = object1;
        this.zzc = zzec0;
    }

    public final Object zza(Object object0) {
        Object object4;
        synchronized(this.zzf) {
        }
        if(object0 != null) {
            return object0;
        }
        if(zzed.zza == null) {
            return this.zzd;
        }
        synchronized(zzef.zza) {
            if(zzab.zza()) {
                return this.zzh == null ? this.zzd : this.zzh;
            }
        }
        try {
            for(Object object3: zzeg.zzaJ) {
                zzef zzef0 = (zzef)object3;
                if(zzab.zza()) {
                    throw new IllegalStateException("Refreshing flag cache must be done on a worker thread.");
                }
                try {
                    object4 = null;
                    zzec zzec0 = zzef0.zzc;
                    if(zzec0 != null) {
                        object4 = zzec0.zza();
                    }
                }
                catch(IllegalStateException unused_ex) {
                }
                synchronized(zzef.zza) {
                    zzef0.zzh = object4;
                }
            }
        }
        catch(SecurityException unused_ex) {
        }
        zzec zzec1 = this.zzc;
        if(zzec1 == null) {
            return this.zzd;
        }
        try {
            return zzec1.zza();
        }
        catch(SecurityException unused_ex) {
            return this.zzd;
        }
        catch(IllegalStateException unused_ex) {
            return this.zzd;
        }
    }

    public final String zzb() {
        return this.zzb;
    }
}

