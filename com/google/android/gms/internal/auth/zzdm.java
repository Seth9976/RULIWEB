package com.google.android.gms.internal.auth;

import javax.annotation.CheckForNull;

final class zzdm implements zzdj {
    private static final zzdj zza;
    private volatile zzdj zzb;
    @CheckForNull
    private Object zzc;

    static {
        zzdm.zza = () -> throw new IllegalStateException();
    }

    zzdm(zzdj zzdj0) {
        this.zzb = zzdj0;
    }

    @Override
    public final String toString() {
        zzdj zzdj0 = this.zzb;
        if(zzdj0 == zzdm.zza) {
            zzdj0 = "<supplier that returned " + this.zzc + ">";
        }
        return "Suppliers.memoize(" + zzdj0 + ")";
    }

    @Override  // com.google.android.gms.internal.auth.zzdj
    public final Object zza() {
        zzdj zzdj0 = zzdm.zza;
        if(this.zzb != zzdj0) {
            synchronized(this) {
                if(this.zzb != zzdj0) {
                    Object object0 = this.zzb.zza();
                    this.zzc = object0;
                    this.zzb = zzdj0;
                    return object0;
                }
                return this.zzc;
            }
        }
        return this.zzc;
    }
}

