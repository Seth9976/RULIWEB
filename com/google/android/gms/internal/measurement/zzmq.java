package com.google.android.gms.internal.measurement;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

final class zzmq {
    private static final zzmq zza;
    private final zzmu zzb;
    private final ConcurrentMap zzc;

    static {
        zzmq.zza = new zzmq();
    }

    private zzmq() {
        this.zzc = new ConcurrentHashMap();
        this.zzb = new zzma();
    }

    public static zzmq zza() {
        return zzmq.zza;
    }

    public final zzmt zzb(Class class0) {
        zzlj.zzc(class0, "messageType");
        zzmt zzmt0 = (zzmt)this.zzc.get(class0);
        if(zzmt0 == null) {
            zzmt zzmt1 = this.zzb.zza(class0);
            zzlj.zzc(class0, "messageType");
            zzlj.zzc(zzmt1, "schema");
            zzmt zzmt2 = (zzmt)this.zzc.putIfAbsent(class0, zzmt1);
            return zzmt2 == null ? zzmt1 : zzmt2;
        }
        return zzmt0;
    }
}

