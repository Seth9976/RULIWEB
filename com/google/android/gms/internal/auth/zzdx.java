package com.google.android.gms.internal.auth;

import java.util.Comparator;

final class zzdx implements Comparator {
    @Override
    public final int compare(Object object0, Object object1) {
        zzdw zzdw0 = new zzdw(((zzef)object0));
        zzdw zzdw1 = new zzdw(((zzef)object1));
        while(zzdw0.hasNext() && zzdw1.hasNext()) {
            int v = ((int)(zzdw0.zza() & 0xFF)).compareTo(((int)(zzdw1.zza() & 0xFF)));
            if(v != 0) {
                return v;
            }
            if(false) {
                break;
            }
        }
        return ((zzef)object0).zzd().compareTo(((zzef)object1).zzd());
    }
}

