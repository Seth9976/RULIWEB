package com.google.android.gms.internal.measurement;

import javax.annotation.CheckForNull;

final class zzip implements zzim {
    private static final zzim zza;
    private volatile zzim zzb;
    @CheckForNull
    private Object zzc;

    static {
        zzip.zza = () -> throw new IllegalStateException();
    }

    zzip(zzim zzim0) {
        zzim0.getClass();
        this.zzb = zzim0;
    }

    @Override
    public final String toString() {
        zzim zzim0 = this.zzb;
        if(zzim0 == zzip.zza) {
            zzim0 = "<supplier that returned " + this.zzc + ">";
        }
        return "Suppliers.memoize(" + zzim0 + ")";
    }

    @Override  // com.google.android.gms.internal.measurement.zzim
    public final Object zza() {
        zzim zzim0 = zzip.zza;
        if(this.zzb != zzim0) {
            synchronized(this) {
                if(this.zzb != zzim0) {
                    Object object0 = this.zzb.zza();
                    this.zzc = object0;
                    this.zzb = zzim0;
                    return object0;
                }
                return this.zzc;
            }
        }
        return this.zzc;
    }
}

