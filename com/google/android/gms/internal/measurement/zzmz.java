package com.google.android.gms.internal.measurement;

import java.util.Iterator;

final class zzmz {
    private static final Iterator zza;
    private static final Iterable zzb;

    static {
        zzmz.zza = new zzmx();
        zzmz.zzb = () -> zzmz.zza;
    }

    static Iterable zza() {
        return zzmz.zzb;
    }

    // 检测为 Lambda 实现
    static Iterator zzb() [...]
}

