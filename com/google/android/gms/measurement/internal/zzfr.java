package com.google.android.gms.measurement.internal;

import androidx.collection.LruCache;
import com.google.android.gms.common.internal.Preconditions;

final class zzfr extends LruCache {
    final zzfu zza;

    zzfr(zzfu zzfu0, int v) {
        this.zza = zzfu0;
        super(20);
    }

    @Override  // androidx.collection.LruCache
    protected final Object create(Object object0) {
        Preconditions.checkNotEmpty(((String)object0));
        return zzfu.zzd(this.zza, ((String)object0));
    }
}

