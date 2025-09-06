package com.google.android.gms.internal.measurement;

import java.util.List;

public final class zzav extends zzaw {
    public zzav() {
        this.zza.add(zzbl.zze);
        this.zza.add(zzbl.zzf);
        this.zza.add(zzbl.zzg);
        this.zza.add(zzbl.zzh);
        this.zza.add(zzbl.zzi);
        this.zza.add(zzbl.zzj);
        this.zza.add(zzbl.zzk);
    }

    @Override  // com.google.android.gms.internal.measurement.zzaw
    public final zzap zza(String s, zzg zzg0, List list0) {
        switch(zzh.zze(s).ordinal()) {
            case 4: {
                zzh.zzh("BITWISE_AND", 2, list0);
                return new zzah(((double)(zzh.zzb(((double)zzg0.zzb(((zzap)list0.get(0))).zzh())) & zzh.zzb(((double)zzg0.zzb(((zzap)list0.get(1))).zzh())))));
            }
            case 5: {
                zzh.zzh("BITWISE_LEFT_SHIFT", 2, list0);
                return new zzah(((double)(zzh.zzb(((double)zzg0.zzb(((zzap)list0.get(0))).zzh())) << ((int)(zzh.zzd(((double)zzg0.zzb(((zzap)list0.get(1))).zzh())) & 0x1FL)))));
            }
            case 6: {
                zzh.zzh("BITWISE_NOT", 1, list0);
                return new zzah(((double)(~zzh.zzb(((double)zzg0.zzb(((zzap)list0.get(0))).zzh())))));
            }
            case 7: {
                zzh.zzh("BITWISE_OR", 2, list0);
                return new zzah(((double)(zzh.zzb(((double)zzg0.zzb(((zzap)list0.get(0))).zzh())) | zzh.zzb(((double)zzg0.zzb(((zzap)list0.get(1))).zzh())))));
            }
            case 8: {
                zzh.zzh("BITWISE_RIGHT_SHIFT", 2, list0);
                return new zzah(((double)(zzh.zzb(((double)zzg0.zzb(((zzap)list0.get(0))).zzh())) >> ((int)(zzh.zzd(((double)zzg0.zzb(((zzap)list0.get(1))).zzh())) & 0x1FL)))));
            }
            case 9: {
                zzh.zzh("BITWISE_UNSIGNED_RIGHT_SHIFT", 2, list0);
                return new zzah(((double)(zzh.zzd(((double)zzg0.zzb(((zzap)list0.get(0))).zzh())) >>> ((int)(zzh.zzd(((double)zzg0.zzb(((zzap)list0.get(1))).zzh())) & 0x1FL)))));
            }
            case 10: {
                zzh.zzh("BITWISE_XOR", 2, list0);
                return new zzah(((double)(zzh.zzb(((double)zzg0.zzb(((zzap)list0.get(0))).zzh())) ^ zzh.zzb(((double)zzg0.zzb(((zzap)list0.get(1))).zzh())))));
            }
            default: {
                return super.zzb(s);
            }
        }
    }
}

