package com.google.android.gms.internal.measurement;

import java.util.Iterator;
import java.util.List;

public final class zzaf implements zzap {
    private final boolean zza;

    public zzaf(Boolean boolean0) {
        this.zza = boolean0 == null ? false : boolean0.booleanValue();
    }

    @Override
    public final boolean equals(Object object0) {
        if(this == object0) {
            return true;
        }
        return object0 instanceof zzaf ? this.zza == ((zzaf)object0).zza : false;
    }

    @Override
    public final int hashCode() {
        return Boolean.valueOf(this.zza).hashCode();
    }

    @Override
    public final String toString() {
        return String.valueOf(this.zza);
    }

    @Override  // com.google.android.gms.internal.measurement.zzap
    public final zzap zzbU(String s, zzg zzg0, List list0) {
        if(!"toString".equals(s)) {
            throw new IllegalArgumentException(String.format("%s.%s is not a function.", Boolean.toString(this.zza), s));
        }
        return new zzat(Boolean.toString(this.zza));
    }

    @Override  // com.google.android.gms.internal.measurement.zzap
    public final zzap zzd() {
        return new zzaf(Boolean.valueOf(this.zza));
    }

    @Override  // com.google.android.gms.internal.measurement.zzap
    public final Boolean zzg() {
        return Boolean.valueOf(this.zza);
    }

    @Override  // com.google.android.gms.internal.measurement.zzap
    public final Double zzh() {
        return this.zza ? 1.0 : 0.0;
    }

    @Override  // com.google.android.gms.internal.measurement.zzap
    public final String zzi() {
        return Boolean.toString(this.zza);
    }

    @Override  // com.google.android.gms.internal.measurement.zzap
    public final Iterator zzl() {
        return null;
    }
}

