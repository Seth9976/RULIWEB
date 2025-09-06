package com.google.android.gms.internal.measurement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Map;

public class zzam implements zzal, zzap {
    final Map zza;

    public zzam() {
        this.zza = new HashMap();
    }

    @Override
    public final boolean equals(Object object0) {
        if(this == object0) {
            return true;
        }
        return object0 instanceof zzam ? this.zza.equals(((zzam)object0).zza) : false;
    }

    @Override
    public final int hashCode() {
        return this.zza.hashCode();
    }

    @Override
    public final String toString() {
        StringBuilder stringBuilder0 = new StringBuilder("{");
        if(!this.zza.isEmpty()) {
            for(Object object0: this.zza.keySet()) {
                stringBuilder0.append(String.format("%s: %s,", ((String)object0), this.zza.get(((String)object0))));
            }
            stringBuilder0.deleteCharAt(stringBuilder0.lastIndexOf(","));
        }
        stringBuilder0.append("}");
        return stringBuilder0.toString();
    }

    public final List zzb() {
        return new ArrayList(this.zza.keySet());
    }

    @Override  // com.google.android.gms.internal.measurement.zzap
    public zzap zzbU(String s, zzg zzg0, List list0) {
        return "toString".equals(s) ? new zzat(this.toString()) : zzaj.zza(this, new zzat(s), zzg0, list0);
    }

    @Override  // com.google.android.gms.internal.measurement.zzap
    public final zzap zzd() {
        zzap zzap0 = new zzam();
        for(Object object0: this.zza.entrySet()) {
            Map.Entry map$Entry0 = (Map.Entry)object0;
            if(map$Entry0.getValue() instanceof zzal) {
                String s = (String)map$Entry0.getKey();
                zzap zzap1 = (zzap)map$Entry0.getValue();
                zzap0.zza.put(s, zzap1);
            }
            else {
                String s1 = (String)map$Entry0.getKey();
                zzap zzap2 = ((zzap)map$Entry0.getValue()).zzd();
                zzap0.zza.put(s1, zzap2);
            }
        }
        return zzap0;
    }

    // 去混淆评级： 低(20)
    @Override  // com.google.android.gms.internal.measurement.zzal
    public final zzap zzf(String s) {
        return this.zza.containsKey(s) ? ((zzap)this.zza.get(s)) : zzam.zzf;
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
        return "[object Object]";
    }

    @Override  // com.google.android.gms.internal.measurement.zzap
    public final Iterator zzl() {
        return zzaj.zzb(this.zza);
    }

    @Override  // com.google.android.gms.internal.measurement.zzal
    public final void zzr(String s, zzap zzap0) {
        if(zzap0 == null) {
            this.zza.remove(s);
            return;
        }
        this.zza.put(s, zzap0);
    }

    @Override  // com.google.android.gms.internal.measurement.zzal
    public final boolean zzt(String s) {
        return this.zza.containsKey(s);
    }
}

