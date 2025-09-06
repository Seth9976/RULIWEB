package com.google.android.gms.internal.measurement;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

final class zzls extends zzlw {
    private static final Class zza;

    static {
        zzls.zza = Collections.unmodifiableList(Collections.EMPTY_LIST).getClass();
    }

    private zzls() {
        super(null);
    }

    zzls(zzlr zzlr0) {
        super(null);
    }

    @Override  // com.google.android.gms.internal.measurement.zzlw
    final void zza(Object object0, long v) {
        List list1;
        List list0 = (List)zznu.zzf(object0, v);
        if(list0 instanceof zzlq) {
            list1 = ((zzlq)list0).zze();
            zznu.zzs(object0, v, list1);
            return;
        }
        Class class0 = list0.getClass();
        if(!zzls.zza.isAssignableFrom(class0)) {
            if(!(list0 instanceof zzmp) || !(list0 instanceof zzli)) {
                list1 = Collections.unmodifiableList(list0);
                zznu.zzs(object0, v, list1);
            }
            else if(((zzli)list0).zzc()) {
                ((zzli)list0).zzb();
            }
        }
    }

    @Override  // com.google.android.gms.internal.measurement.zzlw
    final void zzb(Object object0, Object object1, long v) {
        ArrayList arrayList0;
        List list0 = (List)zznu.zzf(object1, v);
        int v1 = list0.size();
        List list1 = (List)zznu.zzf(object0, v);
        if(list1.isEmpty()) {
            if(list1 instanceof zzlq) {
                list1 = new zzlp(v1);
            }
            else if(!(list1 instanceof zzmp) || !(list1 instanceof zzli)) {
                list1 = new ArrayList(v1);
            }
            else {
                list1 = ((zzli)list1).zzd(v1);
            }
            zznu.zzs(object0, v, list1);
        }
        else {
            Class class0 = list1.getClass();
            if(zzls.zza.isAssignableFrom(class0)) {
                arrayList0 = new ArrayList(list1.size() + v1);
                arrayList0.addAll(list1);
                zznu.zzs(object0, v, arrayList0);
                list1 = arrayList0;
            }
            else if(list1 instanceof zznp) {
                arrayList0 = new zzlp(list1.size() + v1);
                ((zzjl)arrayList0).addAll(((zzlp)arrayList0).size(), ((zznp)list1));
                zznu.zzs(object0, v, arrayList0);
                list1 = arrayList0;
            }
            else if(list1 instanceof zzmp && list1 instanceof zzli && !((zzli)list1).zzc()) {
                list1 = ((zzli)list1).zzd(list1.size() + v1);
                zznu.zzs(object0, v, list1);
            }
        }
        int v2 = list1.size();
        if(v2 > 0 && list0.size() > 0) {
            list1.addAll(list0);
        }
        if(v2 > 0) {
            list0 = list1;
        }
        zznu.zzs(object0, v, list0);
    }
}

