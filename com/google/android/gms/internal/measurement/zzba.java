package com.google.android.gms.internal.measurement;

import java.util.Arrays;
import java.util.Comparator;

final class zzba implements Comparator {
    final zzai zza;
    final zzg zzb;

    zzba(zzai zzai0, zzg zzg0) {
        this.zza = zzai0;
        this.zzb = zzg0;
        super();
    }

    @Override
    public final int compare(Object object0, Object object1) {
        zzai zzai0 = this.zza;
        zzg zzg0 = this.zzb;
        if(((zzap)object0) instanceof zzau) {
            return ((zzap)object1) instanceof zzau ? 0 : 1;
        }
        if(((zzap)object1) instanceof zzau) {
            return -1;
        }
        return zzai0 == null ? ((zzap)object0).zzi().compareTo(((zzap)object1).zzi()) : ((int)zzh.zza(((double)zzai0.zza(zzg0, Arrays.asList(new zzap[]{((zzap)object0), ((zzap)object1)})).zzh())));
    }
}

