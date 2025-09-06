package com.google.android.gms.internal.measurement;

import java.util.Iterator;
import java.util.NoSuchElementException;

final class zzad implements Iterator {
    final zzae zza;
    private int zzb;

    zzad(zzae zzae0) {
        this.zza = zzae0;
        super();
        this.zzb = 0;
    }

    @Override
    public final boolean hasNext() {
        return this.zzb < this.zza.zzc();
    }

    @Override
    public final Object next() {
        if(this.zzb >= this.zza.zzc()) {
            throw new NoSuchElementException("Out of bounds index: " + this.zzb);
        }
        int v = this.zzb;
        this.zzb = v + 1;
        return this.zza.zze(v);
    }
}

