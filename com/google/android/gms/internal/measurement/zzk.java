package com.google.android.gms.internal.measurement;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class zzk extends zzai {
    private final zzab zza;

    public zzk(zzab zzab0) {
        super("internal.eventLogger");
        this.zza = zzab0;
    }

    @Override  // com.google.android.gms.internal.measurement.zzai
    public final zzap zza(zzg zzg0, List list0) {
        zzh.zzh(this.zzd, 3, list0);
        String s = zzg0.zzb(((zzap)list0.get(0))).zzi();
        long v = (long)zzh.zza(((double)zzg0.zzb(((zzap)list0.get(1))).zzh()));
        zzap zzap0 = zzg0.zzb(((zzap)list0.get(2)));
        Map map0 = zzap0 instanceof zzam ? zzh.zzg(((zzam)zzap0)) : new HashMap();
        this.zza.zze(s, v, map0);
        return zzap.zzf;
    }
}

