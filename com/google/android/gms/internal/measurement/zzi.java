package com.google.android.gms.internal.measurement;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public final class zzi {
    public static zzap zza(zzgy zzgy0) {
        if(zzgy0 == null) {
            return zzap.zzf;
        }
        switch(zzgy0.zzj() - 1) {
            case 1: {
                return zzgy0.zzi() ? new zzat("") : zzap.zzm;
            }
            case 2: {
                return zzgy0.zzh() ? new zzah(zzgy0.zza()) : new zzah(null);
            }
            case 3: {
                return zzgy0.zzg() ? new zzaf(Boolean.valueOf(zzgy0.zzf())) : new zzaf(null);
            }
            case 4: {
                ArrayList arrayList0 = new ArrayList();
                for(Object object0: zzgy0.zze()) {
                    arrayList0.add(zzi.zza(((zzgy)object0)));
                }
                return new zzaq("", arrayList0);
            }
            default: {
                throw new IllegalArgumentException("Unknown type found. Cannot convert entity");
            }
        }
    }

    public static zzap zzb(Object object0) {
        if(object0 == null) {
            return zzap.zzg;
        }
        if(object0 instanceof String) {
            return new zzat(((String)object0));
        }
        if(object0 instanceof Double) {
            return new zzah(((Double)object0));
        }
        if(object0 instanceof Long) {
            return new zzah(((Long)object0).doubleValue());
        }
        if(object0 instanceof Integer) {
            return new zzah(((Integer)object0).doubleValue());
        }
        if(object0 instanceof Boolean) {
            return new zzaf(((Boolean)object0));
        }
        if(object0 instanceof Map) {
            zzap zzap0 = new zzam();
            for(String s: ((Map)object0).keySet()) {
                zzap zzap1 = zzi.zzb(((Map)object0).get(s));
                if(s != null) {
                    if(!(s instanceof String)) {
                        s = s.toString();
                    }
                    ((zzam)zzap0).zzr(s, zzap1);
                }
            }
            return zzap0;
        }
        if(!(object0 instanceof List)) {
            throw new IllegalArgumentException("Invalid value type");
        }
        zzap zzap2 = new zzae();
        for(Object object1: ((List)object0)) {
            zzap zzap3 = zzi.zzb(object1);
            ((zzae)zzap2).zzq(((zzae)zzap2).zzc(), zzap3);
        }
        return zzap2;
    }
}

