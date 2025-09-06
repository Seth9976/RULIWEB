package com.google.android.gms.internal.measurement;

import java.util.NoSuchElementException;

final class zzjr extends zzjt {
    final zzka zza;
    private int zzb;
    private final int zzc;

    zzjr(zzka zzka0) {
        this.zza = zzka0;
        super();
        this.zzb = 0;
        this.zzc = zzka0.zzd();
    }

    @Override
    public final boolean hasNext() {
        return this.zzb < this.zzc;
    }

    @Override  // com.google.android.gms.internal.measurement.zzjv
    public final byte zza() {
        int v = this.zzb;
        if(v >= this.zzc) {
            throw new NoSuchElementException();
        }
        this.zzb = v + 1;
        return this.zza.zzb(v);
    }
}

