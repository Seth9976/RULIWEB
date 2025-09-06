package com.google.android.gms.internal.measurement;

import java.util.Collections;
import java.util.TreeMap;

public final class zzz {
    final TreeMap zza;
    final TreeMap zzb;

    public zzz() {
        this.zza = new TreeMap();
        this.zzb = new TreeMap();
    }

    public final void zza(String s, int v, zzao zzao0, String s1) {
        TreeMap treeMap0;
        if("create".equals(s1)) {
            treeMap0 = this.zzb;
        }
        else if("edit".equals(s1)) {
            treeMap0 = this.zza;
        }
        else {
            throw new IllegalStateException("Unknown callback type: " + s1);
        }
        if(treeMap0.containsKey(v)) {
            v = ((int)(((Integer)treeMap0.lastKey()))) + 1;
        }
        treeMap0.put(v, zzao0);
    }

    public final void zzb(zzg zzg0, zzab zzab0) {
        zzl zzl0 = new zzl(zzab0);
        for(Object object0: this.zza.keySet()) {
            zzaa zzaa0 = zzab0.zzb().zzb();
            switch(zzz.zzc(zzg0, ((zzao)this.zza.get(((Integer)object0))), zzl0)) {
                case -1: 
                case 2: {
                    zzab0.zzf(zzaa0);
                }
            }
        }
        for(Object object1: this.zzb.keySet()) {
            zzz.zzc(zzg0, ((zzao)this.zzb.get(((Integer)object1))), zzl0);
        }
    }

    private static final int zzc(zzg zzg0, zzao zzao0, zzap zzap0) {
        zzap zzap1 = zzao0.zza(zzg0, Collections.singletonList(zzap0));
        return zzap1 instanceof zzah ? zzh.zzb(((double)zzap1.zzh())) : -1;
    }
}

