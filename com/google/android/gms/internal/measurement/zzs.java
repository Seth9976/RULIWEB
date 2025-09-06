package com.google.android.gms.internal.measurement;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

final class zzs extends zzai {
    final boolean zza;
    final boolean zzb;
    final zzt zzc;

    public zzs(zzt zzt0, boolean z, boolean z1) {
        this.zzc = zzt0;
        super("log");
        this.zza = z;
        this.zzb = z1;
    }

    @Override  // com.google.android.gms.internal.measurement.zzai
    public final zzap zza(zzg zzg0, List list0) {
        int v2;
        zzh.zzi("log", 1, list0);
        if(list0.size() == 1) {
            this.zzc.zza.zza(3, zzg0.zzb(((zzap)list0.get(0))).zzi(), Collections.EMPTY_LIST, this.zza, this.zzb);
            return zzs.zzf;
        }
        int v = zzh.zzb(((double)zzg0.zzb(((zzap)list0.get(0))).zzh()));
        if(v == 2) {
            v2 = 4;
        }
        else {
            switch(v) {
                case 3: {
                    v2 = 1;
                    break;
                }
                case 5: {
                    v2 = 5;
                    break;
                }
                default: {
                    v2 = v == 6 ? 2 : 3;
                }
            }
        }
        String s = zzg0.zzb(((zzap)list0.get(1))).zzi();
        if(list0.size() == 2) {
            this.zzc.zza.zza(v2, s, Collections.EMPTY_LIST, this.zza, this.zzb);
            return zzs.zzf;
        }
        ArrayList arrayList0 = new ArrayList();
        for(int v1 = 2; v1 < Math.min(list0.size(), 5); ++v1) {
            arrayList0.add(zzg0.zzb(((zzap)list0.get(v1))).zzi());
        }
        this.zzc.zza.zza(v2, s, arrayList0, this.zza, this.zzb);
        return zzs.zzf;
    }
}

