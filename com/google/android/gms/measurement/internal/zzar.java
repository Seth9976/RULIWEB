package com.google.android.gms.measurement.internal;

import java.util.Iterator;

final class zzar implements Iterator {
    final Iterator zza;
    final zzas zzb;

    zzar(zzas zzas0) {
        this.zzb = zzas0;
        super();
        this.zza = zzas.zzb(zzas0).keySet().iterator();
    }

    @Override
    public final boolean hasNext() {
        return this.zza.hasNext();
    }

    @Override
    public final Object next() {
        return this.zza();
    }

    @Override
    public final void remove() {
        throw new UnsupportedOperationException("Remove not supported");
    }

    public final String zza() {
        return this.zza.next();
    }
}

