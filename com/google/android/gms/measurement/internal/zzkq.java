package com.google.android.gms.measurement.internal;

import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.Clock;

final class zzkq {
    private final Clock zza;
    private long zzb;

    public zzkq(Clock clock0) {
        Preconditions.checkNotNull(clock0);
        this.zza = clock0;
    }

    public final void zza() {
        this.zzb = 0L;
    }

    public final void zzb() {
        this.zzb = this.zza.elapsedRealtime();
    }

    public final boolean zzc(long v) {
        return this.zzb == 0L ? true : this.zza.elapsedRealtime() - this.zzb >= 3600000L;
    }
}

