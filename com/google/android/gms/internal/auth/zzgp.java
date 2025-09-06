package com.google.android.gms.internal.auth;

import java.util.Map.Entry;

final class zzgp implements Comparable, Map.Entry {
    final zzgv zza;
    private final Comparable zzb;
    private Object zzc;

    zzgp(zzgv zzgv0, Comparable comparable0, Object object0) {
        this.zza = zzgv0;
        super();
        this.zzb = comparable0;
        this.zzc = object0;
    }

    @Override
    public final int compareTo(Object object0) {
        return this.zzb.compareTo(((zzgp)object0).zzb);
    }

    @Override
    public final boolean equals(Object object0) {
        if(object0 == this) {
            return true;
        }
        if(!(object0 instanceof Map.Entry)) {
            return false;
        }
        Object object1 = ((Map.Entry)object0).getKey();
        return zzgp.zzb(this.zzb, object1) && zzgp.zzb(this.zzc, ((Map.Entry)object0).getValue());
    }

    @Override
    public final Object getKey() {
        return this.zzb;
    }

    @Override
    public final Object getValue() {
        return this.zzc;
    }

    @Override
    public final int hashCode() {
        int v = 0;
        int v1 = this.zzb == null ? 0 : this.zzb.hashCode();
        Object object0 = this.zzc;
        if(object0 != null) {
            v = object0.hashCode();
        }
        return v1 ^ v;
    }

    @Override
    public final Object setValue(Object object0) {
        this.zza.zzn();
        Object object1 = this.zzc;
        this.zzc = object0;
        return object1;
    }

    @Override
    public final String toString() {
        return this.zzb + "=" + this.zzc;
    }

    public final Comparable zza() {
        return this.zzb;
    }

    private static final boolean zzb(Object object0, Object object1) {
        return object0 == null ? object1 == null : object0.equals(object1);
    }
}

