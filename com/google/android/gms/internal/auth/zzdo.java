package com.google.android.gms.internal.auth;

import java.io.Serializable;

public final class zzdo {
    public static zzdj zza(zzdj zzdj0) {
        if(!(zzdj0 instanceof zzdm) && !(zzdj0 instanceof zzdk)) {
            return zzdj0 instanceof Serializable ? new zzdk(zzdj0) : new zzdm(zzdj0);
        }
        return zzdj0;
    }

    public static zzdj zzb(Object object0) {
        return new zzdn(object0);
    }
}

