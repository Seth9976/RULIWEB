package com.google.android.gms.internal.measurement;

import java.util.Iterator;

final class zzno implements Iterator {
    final Iterator zza;
    final zznp zzb;

    zzno(zznp zznp0) {
        this.zzb = zznp0;
        super();
        this.zza = zznp.zza(zznp0).iterator();
    }

    @Override
    public final boolean hasNext() {
        return this.zza.hasNext();
    }

    @Override
    public final Object next() {
        return this.zza.next();
    }

    @Override
    public final void remove() {
        throw new UnsupportedOperationException();
    }
}

