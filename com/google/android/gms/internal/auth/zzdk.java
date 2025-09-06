package com.google.android.gms.internal.auth;

import java.io.Serializable;
import javax.annotation.CheckForNull;

final class zzdk implements zzdj, Serializable {
    final zzdj zza;
    volatile transient boolean zzb;
    @CheckForNull
    transient Object zzc;

    zzdk(zzdj zzdj0) {
        this.zza = zzdj0;
    }

    // 去混淆评级： 低(20)
    @Override
    public final String toString() {
        return this.zzb ? "Suppliers.memoize(" + ("<supplier that returned " + this.zzc + ">").toString() + ")" : "Suppliers.memoize(" + this.zza.toString() + ")";
    }

    @Override  // com.google.android.gms.internal.auth.zzdj
    public final Object zza() {
        if(!this.zzb) {
            synchronized(this) {
                if(!this.zzb) {
                    Object object0 = this.zza.zza();
                    this.zzc = object0;
                    this.zzb = true;
                    return object0;
                }
                return this.zzc;
            }
        }
        return this.zzc;
    }
}

