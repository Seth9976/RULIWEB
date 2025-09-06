package com.google.android.gms.internal.measurement;

import java.util.ArrayList;
import java.util.List;

public final class zzaz extends zzaw {
    protected zzaz() {
        this.zza.add(zzbl.zzc);
        this.zza.add(zzbl.zzl);
        this.zza.add(zzbl.zzm);
        this.zza.add(zzbl.zzn);
        this.zza.add(zzbl.zzt);
        this.zza.add(zzbl.zzp);
        this.zza.add(zzbl.zzu);
        this.zza.add(zzbl.zzz);
        this.zza.add(zzbl.zzP);
        this.zza.add(zzbl.zzac);
        this.zza.add(zzbl.zzaf);
        this.zza.add(zzbl.zzai);
        this.zza.add(zzbl.zzaj);
    }

    @Override  // com.google.android.gms.internal.measurement.zzaw
    public final zzap zza(String s, zzg zzg0, List list0) {
        switch(zzh.zze(s).ordinal()) {
            case 2: {
                zzh.zzh("APPLY", 3, list0);
                zzap zzap0 = zzg0.zzb(((zzap)list0.get(0)));
                String s1 = zzg0.zzb(((zzap)list0.get(1))).zzi();
                zzap zzap1 = zzg0.zzb(((zzap)list0.get(2)));
                if(!(zzap1 instanceof zzae)) {
                    throw new IllegalArgumentException(String.format("Function arguments for Apply are not a list found %s", zzap1.getClass().getCanonicalName()));
                }
                if(s1.isEmpty()) {
                    throw new IllegalArgumentException("Function name for apply is undefined");
                }
                return zzap0.zzbU(s1, zzg0, ((zzae)zzap1).zzm());
            }
            case 11: {
                return zzg0.zza().zzc(new zzae(list0));
            }
            case 12: {
                zzh.zzh("BREAK", 0, list0);
                return zzap.zzi;
            }
            case 15: {
                zzh.zzh("BREAK", 0, list0);
                return zzap.zzh;
            }
            case 13: 
            case 19: {
                if(list0.isEmpty()) {
                    return zzap.zzf;
                }
                zzap zzap2 = zzg0.zzb(((zzap)list0.get(0)));
                return zzap2 instanceof zzae ? zzg0.zzc(((zzae)zzap2)) : zzap.zzf;
            }
            case 20: {
                zzh.zzi("DEFINE_FUNCTION", 2, list0);
                zzap zzap3 = zzaz.zzc(zzg0, list0);
                if(((zzai)zzap3).zzc() == null) {
                    zzg0.zzg("", zzap3);
                    return zzap3;
                }
                zzg0.zzg(((zzai)zzap3).zzc(), zzap3);
                return zzap3;
            }
            case 25: {
                return zzaz.zzc(zzg0, list0);
            }
            case 41: {
                zzh.zzi("IF", 2, list0);
                zzap zzap4 = zzg0.zzb(((zzap)list0.get(0)));
                zzap zzap5 = zzg0.zzb(((zzap)list0.get(1)));
                zzap zzap6 = list0.size() <= 2 ? null : zzg0.zzb(((zzap)list0.get(2)));
                zzap zzap7 = zzap.zzf;
                if(zzap4.zzg().booleanValue()) {
                    zzap7 = zzg0.zzc(((zzae)zzap5));
                    return zzap7 instanceof zzag ? zzap7 : zzap.zzf;
                }
                if(zzap6 != null) {
                    zzap7 = zzg0.zzc(((zzae)zzap6));
                }
                return zzap7 instanceof zzag ? zzap7 : zzap.zzf;
            }
            case 54: {
                return new zzae(list0);
            }
            case 57: {
                if(list0.isEmpty()) {
                    return zzap.zzj;
                }
                zzh.zzh("RETURN", 1, list0);
                return new zzag("return", zzg0.zzb(((zzap)list0.get(0))));
            }
            case 60: {
                zzh.zzh("SWITCH", 3, list0);
                zzap zzap8 = zzg0.zzb(((zzap)list0.get(0)));
                zzap zzap9 = zzg0.zzb(((zzap)list0.get(1)));
                zzap zzap10 = zzg0.zzb(((zzap)list0.get(2)));
                if(!(zzap9 instanceof zzae)) {
                    throw new IllegalArgumentException("Malformed SWITCH statement, cases are not a list");
                }
                if(!(zzap10 instanceof zzae)) {
                    throw new IllegalArgumentException("Malformed SWITCH statement, case statements are not a list");
                }
                boolean z = false;
                for(int v = 0; v < ((zzae)zzap9).zzc(); ++v) {
                    if(z || zzap8.equals(zzg0.zzb(((zzae)zzap9).zze(v)))) {
                        zzap zzap11 = zzg0.zzb(((zzae)zzap10).zze(v));
                        if(zzap11 instanceof zzag) {
                            return ((zzag)zzap11).zzc().equals("break") ? zzap.zzf : zzap11;
                        }
                        z = true;
                    }
                    else {
                        z = false;
                    }
                }
                if(((zzae)zzap9).zzc() + 1 == ((zzae)zzap10).zzc()) {
                    zzap zzap12 = zzg0.zzb(((zzae)zzap10).zze(((zzae)zzap9).zzc()));
                    if(zzap12 instanceof zzag) {
                        String s2 = ((zzag)zzap12).zzc();
                        return !s2.equals("return") && !s2.equals("continue") ? zzap.zzf : zzap12;
                    }
                }
                return zzap.zzf;
            }
            case 61: {
                zzh.zzh("TERNARY", 3, list0);
                return zzg0.zzb(((zzap)list0.get(0))).zzg().booleanValue() ? zzg0.zzb(((zzap)list0.get(1))) : zzg0.zzb(((zzap)list0.get(2)));
            }
            default: {
                return super.zzb(s);
            }
        }
    }

    private static zzap zzc(zzg zzg0, List list0) {
        zzh.zzi("FN", 2, list0);
        zzap zzap0 = zzg0.zzb(((zzap)list0.get(0)));
        zzap zzap1 = zzg0.zzb(((zzap)list0.get(1)));
        if(!(zzap1 instanceof zzae)) {
            throw new IllegalArgumentException(String.format("FN requires an ArrayValue of parameter names found %s", zzap1.getClass().getCanonicalName()));
        }
        List list1 = ((zzae)zzap1).zzm();
        List list2 = new ArrayList();
        if(list0.size() > 2) {
            list2 = list0.subList(2, list0.size());
        }
        return new zzao(zzap0.zzi(), list1, list2, zzg0);
    }
}

