package com.google.android.gms.internal.measurement;

import java.util.Iterator;
import java.util.List;

public final class zzbh extends zzaw {
    protected zzbh() {
        this.zza.add(zzbl.zzA);
        this.zza.add(zzbl.zzB);
        this.zza.add(zzbl.zzC);
        this.zza.add(zzbl.zzD);
        this.zza.add(zzbl.zzE);
        this.zza.add(zzbl.zzF);
        this.zza.add(zzbl.zzG);
        this.zza.add(zzbl.zzan);
    }

    @Override  // com.google.android.gms.internal.measurement.zzaw
    public final zzap zza(String s, zzg zzg0, List list0) {
        int v = zzh.zze(s).ordinal();
        if(v != 65) {
            switch(v) {
                case 26: {
                    zzh.zzh("FOR_IN", 3, list0);
                    if(!(list0.get(0) instanceof zzat)) {
                        throw new IllegalArgumentException("Variable name in FOR_IN must be a string");
                    }
                    String s1 = ((zzap)list0.get(0)).zzi();
                    zzap zzap0 = zzg0.zzb(((zzap)list0.get(1)));
                    zzap zzap1 = zzg0.zzb(((zzap)list0.get(2)));
                    return zzbh.zzd(new zzbg(zzg0, s1), zzap0, zzap1);
                }
                case 27: {
                    zzh.zzh("FOR_IN_CONST", 3, list0);
                    if(!(list0.get(0) instanceof zzat)) {
                        throw new IllegalArgumentException("Variable name in FOR_IN_CONST must be a string");
                    }
                    String s2 = ((zzap)list0.get(0)).zzi();
                    zzap zzap2 = zzg0.zzb(((zzap)list0.get(1)));
                    zzap zzap3 = zzg0.zzb(((zzap)list0.get(2)));
                    return zzbh.zzd(new zzbd(zzg0, s2), zzap2, zzap3);
                }
                case 28: {
                    zzh.zzh("FOR_IN_LET", 3, list0);
                    if(!(list0.get(0) instanceof zzat)) {
                        throw new IllegalArgumentException("Variable name in FOR_IN_LET must be a string");
                    }
                    String s3 = ((zzap)list0.get(0)).zzi();
                    zzap zzap4 = zzg0.zzb(((zzap)list0.get(1)));
                    zzap zzap5 = zzg0.zzb(((zzap)list0.get(2)));
                    return zzbh.zzd(new zzbe(zzg0, s3), zzap4, zzap5);
                }
                case 29: {
                    zzh.zzh("FOR_LET", 4, list0);
                    zzap zzap6 = zzg0.zzb(((zzap)list0.get(0)));
                    if(!(zzap6 instanceof zzae)) {
                        throw new IllegalArgumentException("Initializer variables in FOR_LET must be an ArrayList");
                    }
                    zzap zzap7 = (zzap)list0.get(1);
                    zzap zzap8 = (zzap)list0.get(2);
                    zzap zzap9 = zzg0.zzb(((zzap)list0.get(3)));
                    zzg zzg1 = zzg0.zza();
                    for(int v1 = 0; v1 < ((zzae)zzap6).zzc(); ++v1) {
                        String s4 = ((zzae)zzap6).zze(v1).zzi();
                        zzg1.zzg(s4, zzg0.zzd(s4));
                    }
                    while(zzg0.zzb(zzap7).zzg().booleanValue()) {
                        zzap zzap10 = zzg0.zzc(((zzae)zzap9));
                        if(zzap10 instanceof zzag) {
                            if("break".equals(((zzag)zzap10).zzc())) {
                                return zzap.zzf;
                            }
                            if("return".equals(((zzag)zzap10).zzc())) {
                                return (zzag)zzap10;
                            }
                        }
                        zzg zzg2 = zzg0.zza();
                        for(int v2 = 0; v2 < ((zzae)zzap6).zzc(); ++v2) {
                            String s5 = ((zzae)zzap6).zze(v2).zzi();
                            zzg2.zzg(s5, zzg1.zzd(s5));
                        }
                        zzg2.zzb(zzap8);
                        zzg1 = zzg2;
                    }
                    return zzap.zzf;
                }
                case 30: {
                    zzh.zzh("FOR_OF", 3, list0);
                    if(!(list0.get(0) instanceof zzat)) {
                        throw new IllegalArgumentException("Variable name in FOR_OF must be a string");
                    }
                    String s6 = ((zzap)list0.get(0)).zzi();
                    zzap zzap11 = zzg0.zzb(((zzap)list0.get(1)));
                    zzap zzap12 = zzg0.zzb(((zzap)list0.get(2)));
                    return zzbh.zze(new zzbg(zzg0, s6), zzap11, zzap12);
                }
                case 0x1F: {
                    zzh.zzh("FOR_OF_CONST", 3, list0);
                    if(!(list0.get(0) instanceof zzat)) {
                        throw new IllegalArgumentException("Variable name in FOR_OF_CONST must be a string");
                    }
                    String s7 = ((zzap)list0.get(0)).zzi();
                    zzap zzap13 = zzg0.zzb(((zzap)list0.get(1)));
                    zzap zzap14 = zzg0.zzb(((zzap)list0.get(2)));
                    return zzbh.zze(new zzbd(zzg0, s7), zzap13, zzap14);
                }
                case 0x20: {
                    zzh.zzh("FOR_OF_LET", 3, list0);
                    if(!(list0.get(0) instanceof zzat)) {
                        throw new IllegalArgumentException("Variable name in FOR_OF_LET must be a string");
                    }
                    String s8 = ((zzap)list0.get(0)).zzi();
                    zzap zzap15 = zzg0.zzb(((zzap)list0.get(1)));
                    zzap zzap16 = zzg0.zzb(((zzap)list0.get(2)));
                    return zzbh.zze(new zzbe(zzg0, s8), zzap15, zzap16);
                }
                default: {
                    return super.zzb(s);
                }
            }
        }
        zzh.zzh("WHILE", 4, list0);
        zzap zzap17 = (zzap)list0.get(0);
        zzap zzap18 = (zzap)list0.get(1);
        zzap zzap19 = (zzap)list0.get(2);
        zzap zzap20 = zzg0.zzb(((zzap)list0.get(3)));
        if(zzg0.zzb(zzap19).zzg().booleanValue()) {
            zzap zzap21 = zzg0.zzc(((zzae)zzap20));
            if(zzap21 instanceof zzag) {
                if("break".equals(((zzag)zzap21).zzc())) {
                    return zzap.zzf;
                }
                if("return".equals(((zzag)zzap21).zzc())) {
                    return (zzag)zzap21;
                }
            }
        }
        while(zzg0.zzb(zzap17).zzg().booleanValue()) {
            zzap zzap22 = zzg0.zzc(((zzae)zzap20));
            if(zzap22 instanceof zzag) {
                if("break".equals(((zzag)zzap22).zzc())) {
                    return zzap.zzf;
                }
                if("return".equals(((zzag)zzap22).zzc())) {
                    return (zzag)zzap22;
                }
            }
            zzg0.zzb(zzap18);
        }
        return zzap.zzf;
    }

    private static zzap zzc(zzbf zzbf0, Iterator iterator0, zzap zzap0) {
        if(iterator0 != null) {
            while(iterator0.hasNext()) {
                Object object0 = iterator0.next();
                zzap zzap1 = zzbf0.zza(((zzap)object0)).zzc(((zzae)zzap0));
                if(zzap1 instanceof zzag) {
                    if("break".equals(((zzag)zzap1).zzc())) {
                        return zzap.zzf;
                    }
                    if("return".equals(((zzag)zzap1).zzc())) {
                        return (zzag)zzap1;
                    }
                    if(false) {
                        break;
                    }
                }
            }
        }
        return zzap.zzf;
    }

    private static zzap zzd(zzbf zzbf0, zzap zzap0, zzap zzap1) {
        return zzbh.zzc(zzbf0, zzap0.zzl(), zzap1);
    }

    private static zzap zze(zzbf zzbf0, zzap zzap0, zzap zzap1) {
        if(!(zzap0 instanceof Iterable)) {
            throw new IllegalArgumentException("Non-iterable type in for...of loop.");
        }
        return zzbh.zzc(zzbf0, ((Iterable)zzap0).iterator(), zzap1);
    }
}

