package com.google.android.gms.internal.auth;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

final class zzgf {
    private static final zzgf zza;
    private final zzgj zzb;
    private final ConcurrentMap zzc;

    static {
        zzgf.zza = new zzgf();
    }

    private zzgf() {
        this.zzc = new ConcurrentHashMap();
        this.zzb = new zzfp();
    }

    public static zzgf zza() {
        return zzgf.zza;
    }

    public final zzgi zzb(Class class0) {
        zzfa.zzc(class0, "messageType");
        zzgi zzgi0 = (zzgi)this.zzc.get(class0);
        if(zzgi0 == null) {
            zzgi zzgi1 = this.zzb.zza(class0);
            zzfa.zzc(class0, "messageType");
            zzgi zzgi2 = (zzgi)this.zzc.putIfAbsent(class0, zzgi1);
            return zzgi2 == null ? zzgi1 : zzgi2;
        }
        return zzgi0;
    }
}

