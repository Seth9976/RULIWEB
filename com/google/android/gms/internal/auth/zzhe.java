package com.google.android.gms.internal.auth;

import java.util.AbstractList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.RandomAccess;

@Deprecated
public final class zzhe extends AbstractList implements zzff, RandomAccess {
    private final zzff zza;

    public zzhe(zzff zzff0) {
        this.zza = zzff0;
    }

    @Override
    public final Object get(int v) {
        return ((zzfe)this.zza).zzf(v);
    }

    @Override
    public final Iterator iterator() {
        return new zzhd(this);
    }

    @Override
    public final ListIterator listIterator(int v) {
        return new zzhc(this, v);
    }

    @Override
    public final int size() {
        return this.zza.size();
    }

    static zzff zza(zzhe zzhe0) {
        return zzhe0.zza;
    }

    @Override  // com.google.android.gms.internal.auth.zzff
    public final zzff zze() {
        return this;
    }

    @Override  // com.google.android.gms.internal.auth.zzff
    public final List zzg() {
        return this.zza.zzg();
    }
}

