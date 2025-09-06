package com.google.android.gms.internal.measurement;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public abstract class zzai implements zzal, zzap {
    protected final String zzd;
    protected final Map zze;

    public zzai(String s) {
        this.zze = new HashMap();
        this.zzd = s;
    }

    @Override
    public final boolean equals(Object object0) {
        if(this == object0) {
            return true;
        }
        if(!(object0 instanceof zzai)) {
            return false;
        }
        return this.zzd == null ? false : this.zzd.equals(((zzai)object0).zzd);
    }

    @Override
    public final int hashCode() {
        return this.zzd == null ? 0 : this.zzd.hashCode();
    }

    public abstract zzap zza(zzg arg1, List arg2);

    @Override  // com.google.android.gms.internal.measurement.zzap
    public final zzap zzbU(String s, zzg zzg0, List list0) {
        return "toString".equals(s) ? new zzat(this.zzd) : zzaj.zza(this, new zzat(s), zzg0, list0);
    }

    public final String zzc() {
        return this.zzd;
    }

    @Override  // com.google.android.gms.internal.measurement.zzap
    public zzap zzd() {
        return this;
    }

    // 去混淆评级： 低(20)
    @Override  // com.google.android.gms.internal.measurement.zzal
    public final zzap zzf(String s) {
        return this.zze.containsKey(s) ? ((zzap)this.zze.get(s)) : zzai.zzf;
    }

    @Override  // com.google.android.gms.internal.measurement.zzap
    public final Boolean zzg() {
        return true;
    }

    @Override  // com.google.android.gms.internal.measurement.zzap
    public final Double zzh() {
        return NaN;
    }

    @Override  // com.google.android.gms.internal.measurement.zzap
    public final String zzi() {
        return this.zzd;
    }

    @Override  // com.google.android.gms.internal.measurement.zzap
    public final Iterator zzl() {
        return zzaj.zzb(this.zze);
    }

    @Override  // com.google.android.gms.internal.measurement.zzal
    public final void zzr(String s, zzap zzap0) {
        if(zzap0 == null) {
            this.zze.remove(s);
            return;
        }
        this.zze.put(s, zzap0);
    }

    @Override  // com.google.android.gms.internal.measurement.zzal
    public final boolean zzt(String s) {
        return this.zze.containsKey(s);
    }
}

