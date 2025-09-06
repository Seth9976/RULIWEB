package com.google.android.gms.internal.measurement;

import java.util.Iterator;
import java.util.Map.Entry;

final class zzmd {
    public static final int zza(int v, Object object0, Object object1) {
        zzmb zzmb0 = (zzmb)object1;
        if(!((zzmc)object0).isEmpty()) {
            Iterator iterator0 = ((zzmc)object0).entrySet().iterator();
            if(iterator0.hasNext()) {
                Object object2 = iterator0.next();
                ((Map.Entry)object2).getKey();
                ((Map.Entry)object2).getValue();
                throw null;
            }
        }
        return 0;
    }

    public static final Object zzb(Object object0, Object object1) {
        zzmc zzmc0 = (zzmc)object0;
        if(!((zzmc)object1).isEmpty()) {
            if(!zzmc0.zze()) {
                zzmc0 = zzmc0.zzb();
            }
            zzmc0.zzd(((zzmc)object1));
        }
        return zzmc0;
    }
}

