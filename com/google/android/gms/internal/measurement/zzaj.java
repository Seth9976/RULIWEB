package com.google.android.gms.internal.measurement;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

public final class zzaj {
    public static zzap zza(zzal zzal0, zzap zzap0, zzg zzg0, List list0) {
        if(zzal0.zzt(zzap0.zzi())) {
            zzap zzap1 = zzal0.zzf(zzap0.zzi());
            if(!(zzap1 instanceof zzai)) {
                throw new IllegalArgumentException(String.format("%s is not a function", zzap0.zzi()));
            }
            return ((zzai)zzap1).zza(zzg0, list0);
        }
        if(!"hasOwnProperty".equals(zzap0.zzi())) {
            throw new IllegalArgumentException(String.format("Object has no function %s", zzap0.zzi()));
        }
        zzh.zzh("hasOwnProperty", 1, list0);
        return zzal0.zzt(zzg0.zzb(((zzap)list0.get(0))).zzi()) ? zzap.zzk : zzap.zzl;
    }

    public static Iterator zzb(Map map0) {
        return new zzak(map0.keySet().iterator());
    }
}

