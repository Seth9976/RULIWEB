package com.google.android.gms.internal.auth;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

final class zzfh extends zzfl {
    private static final Class zza;

    static {
        zzfh.zza = Collections.unmodifiableList(Collections.EMPTY_LIST).getClass();
    }

    private zzfh() {
        super(null);
    }

    zzfh(zzfg zzfg0) {
        super(null);
    }

    @Override  // com.google.android.gms.internal.auth.zzfl
    final void zza(Object object0, long v) {
        List list1;
        List list0 = (List)zzhj.zzf(object0, v);
        if(list0 instanceof zzff) {
            list1 = ((zzff)list0).zze();
            zzhj.zzp(object0, v, list1);
            return;
        }
        Class class0 = list0.getClass();
        if(!zzfh.zza.isAssignableFrom(class0)) {
            if(!(list0 instanceof zzge) || !(list0 instanceof zzez)) {
                list1 = Collections.unmodifiableList(list0);
                zzhj.zzp(object0, v, list1);
            }
            else if(((zzez)list0).zzc()) {
                ((zzez)list0).zzb();
            }
        }
    }

    @Override  // com.google.android.gms.internal.auth.zzfl
    final void zzb(Object object0, Object object1, long v) {
        ArrayList arrayList0;
        List list0 = (List)zzhj.zzf(object1, v);
        int v1 = list0.size();
        List list1 = (List)zzhj.zzf(object0, v);
        if(list1.isEmpty()) {
            if(list1 instanceof zzff) {
                list1 = new zzfe(v1);
            }
            else if(!(list1 instanceof zzge) || !(list1 instanceof zzez)) {
                list1 = new ArrayList(v1);
            }
            else {
                list1 = ((zzez)list1).zzd(v1);
            }
            zzhj.zzp(object0, v, list1);
        }
        else {
            Class class0 = list1.getClass();
            if(zzfh.zza.isAssignableFrom(class0)) {
                arrayList0 = new ArrayList(list1.size() + v1);
                arrayList0.addAll(list1);
                zzhj.zzp(object0, v, arrayList0);
                list1 = arrayList0;
            }
            else if(list1 instanceof zzhe) {
                arrayList0 = new zzfe(list1.size() + v1);
                ((zzdr)arrayList0).addAll(((zzfe)arrayList0).size(), ((zzhe)list1));
                zzhj.zzp(object0, v, arrayList0);
                list1 = arrayList0;
            }
            else if(list1 instanceof zzge && list1 instanceof zzez && !((zzez)list1).zzc()) {
                list1 = ((zzez)list1).zzd(list1.size() + v1);
                zzhj.zzp(object0, v, list1);
            }
        }
        int v2 = list1.size();
        if(v2 > 0 && list0.size() > 0) {
            list1.addAll(list0);
        }
        if(v2 > 0) {
            list0 = list1;
        }
        zzhj.zzp(object0, v, list0);
    }
}

