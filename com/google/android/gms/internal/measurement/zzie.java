package com.google.android.gms.internal.measurement;

import javax.annotation.CheckForNull;

final class zzie extends zzii {
    static final zzie zza;

    static {
        zzie.zza = new zzie();
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

    @Override  // com.google.android.gms.internal.measurement.zzii
    public final Object zza() {
        throw new IllegalStateException("Optional.get() cannot be called on an absent value");
    }

    @Override  // com.google.android.gms.internal.measurement.zzii
    public final boolean zzb() {
        return false;
    }
}

