package com.google.android.gms.internal.measurement;

import java.util.List;

final class zzm extends zzai {
    final zzo zza;

    zzm(zzn zzn0, String s, zzo zzo0) {
        this.zza = zzo0;
        super("getValue");
    }

    @Override  // com.google.android.gms.internal.measurement.zzai
    public final zzap zza(zzg zzg0, List list0) {
        zzh.zzh("getValue", 2, list0);
        zzap zzap0 = zzg0.zzb(((zzap)list0.get(0)));
        zzap zzap1 = zzg0.zzb(((zzap)list0.get(1)));
        String s = zzap0.zzi();
        String s1 = this.zza.zza(s);
        return s1 != null ? new zzat(s1) : zzap1;
    }
}

