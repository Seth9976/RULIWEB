package com.google.android.gms.internal.measurement;

import java.util.List;

public final class zzbc extends zzaw {
    protected zzbc() {
        this.zza.add(zzbl.zzb);
        this.zza.add(zzbl.zzV);
        this.zza.add(zzbl.zzY);
    }

    @Override  // com.google.android.gms.internal.measurement.zzaw
    public final zzap zza(String s, zzg zzg0, List list0) {
        switch(zzh.zze(s).ordinal()) {
            case 1: {
                zzh.zzh("AND", 2, list0);
                zzap zzap0 = zzg0.zzb(((zzap)list0.get(0)));
                return zzap0.zzg().booleanValue() ? zzg0.zzb(((zzap)list0.get(1))) : zzap0;
            }
            case 0x2F: {
                zzh.zzh("NOT", 1, list0);
                return new zzaf(Boolean.valueOf(!zzg0.zzb(((zzap)list0.get(0))).zzg().booleanValue()));
            }
            case 50: {
                zzh.zzh("OR", 2, list0);
                zzap zzap1 = zzg0.zzb(((zzap)list0.get(0)));
                return zzap1.zzg().booleanValue() ? zzap1 : zzg0.zzb(((zzap)list0.get(1)));
            }
            default: {
                return super.zzb(s);
            }
        }
    }
}

