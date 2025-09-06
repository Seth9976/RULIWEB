package com.google.android.gms.internal.measurement;

import java.util.List;

public final class zzbi extends zzaw {
    protected zzbi() {
        this.zza.add(zzbl.zza);
        this.zza.add(zzbl.zzv);
        this.zza.add(zzbl.zzS);
        this.zza.add(zzbl.zzT);
        this.zza.add(zzbl.zzU);
        this.zza.add(zzbl.zzaa);
        this.zza.add(zzbl.zzab);
        this.zza.add(zzbl.zzad);
        this.zza.add(zzbl.zzae);
        this.zza.add(zzbl.zzah);
    }

    @Override  // com.google.android.gms.internal.measurement.zzaw
    public final zzap zza(String s, zzg zzg0, List list0) {
        switch(zzh.zze(s).ordinal()) {
            case 0: {
                zzh.zzh("ADD", 2, list0);
                zzap zzap0 = zzg0.zzb(((zzap)list0.get(0)));
                zzap zzap1 = zzg0.zzb(((zzap)list0.get(1)));
                return !(zzap0 instanceof zzal) && !(zzap0 instanceof zzat) && !(zzap1 instanceof zzal) && !(zzap1 instanceof zzat) ? new zzah(((double)(((double)zzap0.zzh()) + ((double)zzap1.zzh())))) : new zzat(zzap0.zzi() + zzap1.zzi());
            }
            case 21: {
                zzh.zzh("DIVIDE", 2, list0);
                return new zzah(((double)(((double)zzg0.zzb(((zzap)list0.get(0))).zzh()) / ((double)zzg0.zzb(((zzap)list0.get(1))).zzh()))));
            }
            case 44: {
                zzh.zzh("MODULUS", 2, list0);
                return new zzah(((double)(((double)zzg0.zzb(((zzap)list0.get(0))).zzh()) % ((double)zzg0.zzb(((zzap)list0.get(1))).zzh()))));
            }
            case 45: {
                zzh.zzh("MULTIPLY", 2, list0);
                return new zzah(((double)(((double)zzg0.zzb(((zzap)list0.get(0))).zzh()) * ((double)zzg0.zzb(((zzap)list0.get(1))).zzh()))));
            }
            case 46: {
                zzh.zzh("NEGATE", 1, list0);
                return new zzah(((double)(-((double)zzg0.zzb(((zzap)list0.get(0))).zzh()))));
            }
            case 52: 
            case 53: {
                zzh.zzh(s, 2, list0);
                zzap zzap2 = zzg0.zzb(((zzap)list0.get(0)));
                zzg0.zzb(((zzap)list0.get(1)));
                return zzap2;
            }
            case 55: 
            case 56: {
                zzh.zzh(s, 1, list0);
                return zzg0.zzb(((zzap)list0.get(0)));
            }
            case 59: {
                zzh.zzh("SUBTRACT", 2, list0);
                zzap zzap3 = zzg0.zzb(((zzap)list0.get(0)));
                zzah zzah0 = new zzah(((double)(-((double)zzg0.zzb(((zzap)list0.get(1))).zzh()))));
                return new zzah(((double)(((double)zzap3.zzh()) + ((double)zzah0.zzh()))));
            }
            default: {
                return super.zzb(s);
            }
        }
    }
}

