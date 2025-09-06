package com.google.android.gms.internal.measurement;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public final class zzkn {
    static final zzkn zza = null;
    public static final int zzb = 0;
    private static volatile boolean zzc = false;
    private static volatile zzkn zzd;
    private final Map zze;

    static {
        zzkn.zza = new zzkn(true);
    }

    zzkn() {
        this.zze = new HashMap();
    }

    zzkn(boolean z) {
        this.zze = Collections.EMPTY_MAP;
    }

    public static zzkn zza() {
        zzkn zzkn0 = zzkn.zzd;
        if(zzkn0 != null) {
            return zzkn0;
        }
        synchronized(zzkn.class) {
            zzkn zzkn1 = zzkn.zzd;
            if(zzkn1 != null) {
                return zzkn1;
            }
            zzkn zzkn2 = zzkv.zzb(zzkn.class);
            zzkn.zzd = zzkn2;
            return zzkn2;
        }
    }

    public final zzkz zzb(zzmi zzmi0, int v) {
        zzkm zzkm0 = new zzkm(zzmi0, v);
        return (zzkz)this.zze.get(zzkm0);
    }
}

