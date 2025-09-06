package com.google.android.gms.internal.auth;

import java.util.NoSuchElementException;

final class zzdw extends zzdy {
    final zzef zza;
    private int zzb;
    private final int zzc;

    zzdw(zzef zzef0) {
        this.zza = zzef0;
        super();
        this.zzb = 0;
        this.zzc = zzef0.zzd();
    }

    @Override
    public final boolean hasNext() {
        return this.zzb < this.zzc;
    }

    @Override  // com.google.android.gms.internal.auth.zzea
    public final byte zza() {
        int v = this.zzb;
        if(v >= this.zzc) {
            throw new NoSuchElementException();
        }
        this.zzb = v + 1;
        return this.zza.zzb(v);
    }
}

