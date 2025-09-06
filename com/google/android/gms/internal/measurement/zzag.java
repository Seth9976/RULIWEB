package com.google.android.gms.internal.measurement;

import java.util.Iterator;
import java.util.List;

public final class zzag implements zzap {
    private final zzap zza;
    private final String zzb;

    public zzag() {
        throw null;
    }

    public zzag(String s) {
        this.zza = zzag.zzf;
        this.zzb = s;
    }

    public zzag(String s, zzap zzap0) {
        this.zza = zzap0;
        this.zzb = s;
    }

    @Override
    public final boolean equals(Object object0) {
        if(object0 == this) {
            return true;
        }
        return object0 instanceof zzag ? this.zzb.equals(((zzag)object0).zzb) && this.zza.equals(((zzag)object0).zza) : false;
    }

    @Override
    public final int hashCode() {
        return this.zzb.hashCode() * 0x1F + this.zza.hashCode();
    }

    public final zzap zzb() {
        return this.zza;
    }

    @Override  // com.google.android.gms.internal.measurement.zzap
    public final zzap zzbU(String s, zzg zzg0, List list0) {
        throw new IllegalStateException("Control does not have functions");
    }

    public final String zzc() {
        return this.zzb;
    }

    @Override  // com.google.android.gms.internal.measurement.zzap
    public final zzap zzd() {
        zzap zzap0 = this.zza.zzd();
        return new zzag(this.zzb, zzap0);
    }

    @Override  // com.google.android.gms.internal.measurement.zzap
    public final Boolean zzg() {
        throw new IllegalStateException("Control is not a boolean");
    }

    @Override  // com.google.android.gms.internal.measurement.zzap
    public final Double zzh() {
        throw new IllegalStateException("Control is not a double");
    }

    @Override  // com.google.android.gms.internal.measurement.zzap
    public final String zzi() {
        throw new IllegalStateException("Control is not a String");
    }

    @Override  // com.google.android.gms.internal.measurement.zzap
    public final Iterator zzl() {
        return null;
    }
}

