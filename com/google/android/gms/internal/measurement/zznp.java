package com.google.android.gms.internal.measurement;

import java.util.AbstractList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.RandomAccess;

@Deprecated
public final class zznp extends AbstractList implements zzlq, RandomAccess {
    private final zzlq zza;

    public zznp(zzlq zzlq0) {
        this.zza = zzlq0;
    }

    @Override
    public final Object get(int v) {
        return ((zzlp)this.zza).zzg(v);
    }

    @Override
    public final Iterator iterator() {
        return new zzno(this);
    }

    @Override
    public final ListIterator listIterator(int v) {
        return new zznn(this, v);
    }

    @Override
    public final int size() {
        return this.zza.size();
    }

    static zzlq zza(zznp zznp0) {
        return zznp0.zza;
    }

    @Override  // com.google.android.gms.internal.measurement.zzlq
    public final zzlq zze() {
        return this;
    }

    @Override  // com.google.android.gms.internal.measurement.zzlq
    public final Object zzf(int v) {
        return this.zza.zzf(v);
    }

    @Override  // com.google.android.gms.internal.measurement.zzlq
    public final List zzh() {
        return this.zza.zzh();
    }

    @Override  // com.google.android.gms.internal.measurement.zzlq
    public final void zzi(zzka zzka0) {
        throw new UnsupportedOperationException();
    }
}

