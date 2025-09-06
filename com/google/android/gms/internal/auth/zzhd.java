package com.google.android.gms.internal.auth;

import java.util.Iterator;

final class zzhd implements Iterator {
    final Iterator zza;
    final zzhe zzb;

    zzhd(zzhe zzhe0) {
        this.zzb = zzhe0;
        super();
        this.zza = zzhe.zza(zzhe0).iterator();
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

