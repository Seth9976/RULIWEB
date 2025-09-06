package com.google.android.gms.internal.measurement;

import java.util.Comparator;

final class zzjs implements Comparator {
    @Override
    public final int compare(Object object0, Object object1) {
        zzjr zzjr0 = new zzjr(((zzka)object0));
        zzjr zzjr1 = new zzjr(((zzka)object1));
        while(zzjr0.hasNext() && zzjr1.hasNext()) {
            int v = ((int)(zzjr0.zza() & 0xFF)).compareTo(((int)(zzjr1.zza() & 0xFF)));
            if(v != 0) {
                return v;
            }
            if(false) {
                break;
            }
        }
        return ((zzka)object0).zzd().compareTo(((zzka)object1).zzd());
    }
}

