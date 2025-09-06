package com.google.android.gms.internal.auth;

import java.util.Iterator;

final class zzgo {
    private static final Iterator zza;
    private static final Iterable zzb;

    static {
        zzgo.zza = new zzgm();
        zzgo.zzb = () -> zzgo.zza;
    }

    static Iterable zza() {
        return zzgo.zzb;
    }

    // 检测为 Lambda 实现
    static Iterator zzb() [...]
}

