package com.google.android.gms.internal.measurement;

import java.util.Iterator;
import java.util.NoSuchElementException;

final class zzas implements Iterator {
    final zzat zza;
    private int zzb;

    zzas(zzat zzat0) {
        this.zza = zzat0;
        super();
        this.zzb = 0;
    }

    @Override
    public final boolean hasNext() {
        return this.zzb < zzat.zzb(this.zza).length();
    }

    @Override
    public final Object next() {
        int v = this.zzb;
        zzat zzat0 = this.zza;
        if(v >= zzat.zzb(zzat0).length()) {
            throw new NoSuchElementException();
        }
        this.zzb = v + 1;
        return new zzat(String.valueOf(zzat.zzb(zzat0).charAt(v)));
    }
}

