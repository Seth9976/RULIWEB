package com.google.android.gms.internal.measurement;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public final class zzaq implements zzap {
    private final String zza;
    private final ArrayList zzb;

    public zzaq(String s, List list0) {
        this.zza = s;
        ArrayList arrayList0 = new ArrayList();
        this.zzb = arrayList0;
        arrayList0.addAll(list0);
    }

    @Override
    public final boolean equals(Object object0) {
        if(this == object0) {
            return true;
        }
        if(!(object0 instanceof zzaq)) {
            return false;
        }
        String s = this.zza;
        if(s != null) {
            return s.equals(((zzaq)object0).zza) ? this.zzb.equals(((zzaq)object0).zzb) : false;
        }
        return ((zzaq)object0).zza == null ? this.zzb.equals(((zzaq)object0).zzb) : false;
    }

    @Override
    public final int hashCode() {
        return this.zza == null ? this.zzb.hashCode() : this.zza.hashCode() * 0x1F + this.zzb.hashCode();
    }

    public final String zzb() {
        return this.zza;
    }

    @Override  // com.google.android.gms.internal.measurement.zzap
    public final zzap zzbU(String s, zzg zzg0, List list0) {
        throw new IllegalStateException("Statement is not an evaluated entity");
    }

    public final ArrayList zzc() {
        return this.zzb;
    }

    @Override  // com.google.android.gms.internal.measurement.zzap
    public final zzap zzd() {
        return this;
    }

    @Override  // com.google.android.gms.internal.measurement.zzap
    public final Boolean zzg() {
        throw new IllegalStateException("Statement cannot be cast as Boolean");
    }

    @Override  // com.google.android.gms.internal.measurement.zzap
    public final Double zzh() {
        throw new IllegalStateException("Statement cannot be cast as Double");
    }

    @Override  // com.google.android.gms.internal.measurement.zzap
    public final String zzi() {
        throw new IllegalStateException("Statement cannot be cast as String");
    }

    @Override  // com.google.android.gms.internal.measurement.zzap
    public final Iterator zzl() {
        return null;
    }
}

