package com.google.android.gms.internal.measurement;

import java.util.ListIterator;

final class zznn implements ListIterator {
    final ListIterator zza;
    final int zzb;
    final zznp zzc;

    zznn(zznp zznp0, int v) {
        this.zzc = zznp0;
        this.zzb = v;
        super();
        this.zza = zznp.zza(zznp0).listIterator(v);
    }

    @Override
    public final void add(Object object0) {
        String s = (String)object0;
        throw new UnsupportedOperationException();
    }

    @Override
    public final boolean hasNext() {
        return this.zza.hasNext();
    }

    @Override
    public final boolean hasPrevious() {
        return this.zza.hasPrevious();
    }

    @Override
    public final Object next() {
        return (String)this.zza.next();
    }

    @Override
    public final int nextIndex() {
        return this.zza.nextIndex();
    }

    @Override
    public final Object previous() {
        return (String)this.zza.previous();
    }

    @Override
    public final int previousIndex() {
        return this.zza.previousIndex();
    }

    @Override
    public final void remove() {
        throw new UnsupportedOperationException();
    }

    @Override
    public final void set(Object object0) {
        String s = (String)object0;
        throw new UnsupportedOperationException();
    }
}

