package com.google.android.gms.internal.auth;

import javax.annotation.CheckForNull;

final class zzdf extends zzdh {
    static final zzdf zza;

    static {
        zzdf.zza = new zzdf();
    }

    @Override
    public final boolean equals(@CheckForNull Object object0) {
        return object0 == this;
    }

    @Override
    public final int hashCode() {
        return 2040732332;
    }

    @Override
    public final String toString() {
        return "Optional.absent()";
    }

    @Override  // com.google.android.gms.internal.auth.zzdh
    public final Object zza() {
        throw new IllegalStateException("Optional.get() cannot be called on an absent value");
    }

    @Override  // com.google.android.gms.internal.auth.zzdh
    public final boolean zzb() {
        return false;
    }
}

