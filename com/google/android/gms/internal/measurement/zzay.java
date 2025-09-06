package com.google.android.gms.internal.measurement;

import java.util.List;

public final class zzay extends zzaw {
    public zzay() {
        this.zza.add(zzbl.zzx);
        this.zza.add(zzbl.zzL);
        this.zza.add(zzbl.zzM);
        this.zza.add(zzbl.zzN);
        this.zza.add(zzbl.zzO);
        this.zza.add(zzbl.zzQ);
        this.zza.add(zzbl.zzR);
        this.zza.add(zzbl.zzW);
    }

    @Override  // com.google.android.gms.internal.measurement.zzaw
    public final zzap zza(String s, zzg zzg0, List list0) {
        zzh.zzh(zzh.zze(s).name(), 2, list0);
        zzap zzap0 = zzg0.zzb(((zzap)list0.get(0)));
        zzap zzap1 = zzg0.zzb(((zzap)list0.get(1)));
        switch(zzh.zze(s).ordinal()) {
            case 23: {
                return zzay.zzc(zzap0, zzap1) ? zzap.zzk : zzap.zzl;
            }
            case 37: {
                return zzay.zzd(zzap1, zzap0) ? zzap.zzk : zzap.zzl;
            }
            case 38: {
                return zzay.zze(zzap1, zzap0) ? zzap.zzk : zzap.zzl;
            }
            case 39: {
                return zzh.zzl(zzap0, zzap1) ? zzap.zzk : zzap.zzl;
            }
            case 40: {
                return zzh.zzl(zzap0, zzap1) ? zzap.zzl : zzap.zzk;
            }
            case 42: {
                return zzay.zzd(zzap0, zzap1) ? zzap.zzk : zzap.zzl;
            }
            case 43: {
                return zzay.zze(zzap0, zzap1) ? zzap.zzk : zzap.zzl;
            }
            case 0x30: {
                return zzay.zzc(zzap0, zzap1) ? zzap.zzl : zzap.zzk;
            }
            default: {
                return super.zzb(s);
            }
        }
    }

    private static boolean zzc(zzap zzap0, zzap zzap1) {
        if(zzap0.getClass().equals(zzap1.getClass())) {
            if(!(zzap0 instanceof zzau) && !(zzap0 instanceof zzan)) {
                if(zzap0 instanceof zzah) {
                    return !Double.isNaN(((double)zzap0.zzh())) && !Double.isNaN(((double)zzap1.zzh())) && ((double)zzap0.zzh()) == ((double)zzap1.zzh());
                }
                if(zzap0 instanceof zzat) {
                    return zzap0.zzi().equals(zzap1.zzi());
                }
                return zzap0 instanceof zzaf ? zzap0.zzg().equals(zzap1.zzg()) : zzap0 == zzap1;
            }
            return true;
        }
        if(!(zzap0 instanceof zzau) && !(zzap0 instanceof zzan) || !(zzap1 instanceof zzau) && !(zzap1 instanceof zzan)) {
            if(zzap0 instanceof zzah && zzap1 instanceof zzat) {
                return zzay.zzc(zzap0, new zzah(zzap1.zzh()));
            }
            if(zzap0 instanceof zzat && zzap1 instanceof zzah) {
                return zzay.zzc(new zzah(zzap0.zzh()), zzap1);
            }
            if(zzap0 instanceof zzaf) {
                return zzay.zzc(new zzah(zzap0.zzh()), zzap1);
            }
            if(zzap1 instanceof zzaf) {
                return zzay.zzc(zzap0, new zzah(zzap1.zzh()));
            }
            if(!(zzap0 instanceof zzat) && !(zzap0 instanceof zzah) || !(zzap1 instanceof zzal)) {
                return !(zzap0 instanceof zzal) || !(zzap1 instanceof zzat) && !(zzap1 instanceof zzah) ? false : zzay.zzc(new zzat(zzap0.zzi()), zzap1);
            }
            return zzay.zzc(zzap0, new zzat(zzap1.zzi()));
        }
        return true;
    }

    private static boolean zzd(zzap zzap0, zzap zzap1) {
        if(zzap0 instanceof zzal) {
            zzap0 = new zzat(zzap0.zzi());
        }
        if(zzap1 instanceof zzal) {
            zzap1 = new zzat(zzap1.zzi());
        }
        if(zzap0 instanceof zzat && zzap1 instanceof zzat) {
            return zzap0.zzi().compareTo(zzap1.zzi()) < 0;
        }
        double f = (double)zzap0.zzh();
        double f1 = (double)zzap1.zzh();
        if(!Double.isNaN(f) && !Double.isNaN(f1)) {
            int v = Double.compare(f, 0.0);
            return (v != 0 || f1 != 0.0) && (v != 0 || f1 != 0.0) ? Double.compare(f, f1) < 0 : false;
        }
        return false;
    }

    private static boolean zze(zzap zzap0, zzap zzap1) {
        if(zzap0 instanceof zzal) {
            zzap0 = new zzat(zzap0.zzi());
        }
        if(zzap1 instanceof zzal) {
            zzap1 = new zzat(zzap1.zzi());
        }
        return (zzap0 instanceof zzat && zzap1 instanceof zzat || !Double.isNaN(((double)zzap0.zzh())) && !Double.isNaN(((double)zzap1.zzh()))) && !zzay.zzd(zzap1, zzap0);
    }
}

