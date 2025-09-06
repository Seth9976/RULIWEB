package com.google.android.gms.internal.measurement;

import java.util.List;

public final class zzv extends zzai {
    private final zzz zza;

    public zzv(zzz zzz0) {
        super("internal.registerCallback");
        this.zza = zzz0;
    }

    @Override  // com.google.android.gms.internal.measurement.zzai
    public final zzap zza(zzg zzg0, List list0) {
        zzh.zzh(this.zzd, 3, list0);
        String s = zzg0.zzb(((zzap)list0.get(0))).zzi();
        zzap zzap0 = zzg0.zzb(((zzap)list0.get(1)));
        if(!(zzap0 instanceof zzao)) {
            throw new IllegalArgumentException("Invalid callback type");
        }
        zzap zzap1 = zzg0.zzb(((zzap)list0.get(2)));
        if(!(zzap1 instanceof zzam)) {
            throw new IllegalArgumentException("Invalid callback params");
        }
        if(!((zzam)zzap1).zzt("type")) {
            throw new IllegalArgumentException("Undefined rule type");
        }
        String s1 = ((zzam)zzap1).zzf("type").zzi();
        int v = ((zzam)zzap1).zzt("priority") ? zzh.zzb(((double)((zzam)zzap1).zzf("priority").zzh())) : 1000;
        this.zza.zza(s, v, ((zzao)zzap0), s1);
        return zzap.zzf;
    }
}

