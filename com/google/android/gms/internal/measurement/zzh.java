package com.google.android.gms.internal.measurement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class zzh {
    public static double zza(double f) {
        if(Double.isNaN(f)) {
            return 0.0;
        }
        if(!Double.isInfinite(f)) {
            int v = Double.compare(f, 0.0);
            if(v != 0) {
                return v <= 0 ? -1.0 * Math.floor(Math.abs(f)) : 1.0 * Math.floor(Math.abs(f));
            }
        }
        return f;
    }

    public static int zzb(double f) {
        if(!Double.isNaN(f) && !Double.isInfinite(f)) {
            int v = Double.compare(f, 0.0);
            if(v != 0) {
                return v <= 0 ? ((int)(((long)(-1.0 * Math.floor(Math.abs(f)) % 4294967296.0)))) : ((int)(((long)(1.0 * Math.floor(Math.abs(f)) % 4294967296.0))));
            }
        }
        return 0;
    }

    public static int zzc(zzg zzg0) {
        int v = zzh.zzb(((double)zzg0.zzd("runtime.counter").zzh()) + 1.0);
        if(v > 1000000) {
            throw new IllegalStateException("Instructions allowed exceeded");
        }
        zzg0.zzg("runtime.counter", new zzah(((double)v)));
        return v;
    }

    public static long zzd(double f) {
        return ((long)zzh.zzb(f)) & 0xFFFFFFFFL;
    }

    public static zzbl zze(String s) {
        zzbl zzbl0 = s == null || s.isEmpty() ? null : zzbl.zza(Integer.parseInt(s));
        if(zzbl0 == null) {
            throw new IllegalArgumentException(String.format("Unsupported commandId %s", s));
        }
        return zzbl0;
    }

    public static Object zzf(zzap zzap0) {
        if(zzap.zzg.equals(zzap0)) {
            return null;
        }
        if(zzap.zzf.equals(zzap0)) {
            return "";
        }
        if(zzap0 instanceof zzam) {
            return zzh.zzg(((zzam)zzap0));
        }
        if(zzap0 instanceof zzae) {
            ArrayList arrayList0 = new ArrayList();
            for(Object object0: ((zzae)zzap0)) {
                Object object1 = zzh.zzf(((zzap)object0));
                if(object1 != null) {
                    arrayList0.add(object1);
                }
            }
            return arrayList0;
        }
        return !zzap0.zzh().isNaN() ? zzap0.zzh() : zzap0.zzi();
    }

    public static Map zzg(zzam zzam0) {
        Map map0 = new HashMap();
        for(Object object0: zzam0.zzb()) {
            String s = (String)object0;
            Object object1 = zzh.zzf(zzam0.zzf(s));
            if(object1 != null) {
                map0.put(s, object1);
            }
        }
        return map0;
    }

    public static void zzh(String s, int v, List list0) {
        if(list0.size() != v) {
            throw new IllegalArgumentException(String.format("%s operation requires %s parameters found %s", s, v, list0.size()));
        }
    }

    public static void zzi(String s, int v, List list0) {
        if(list0.size() < v) {
            throw new IllegalArgumentException(String.format("%s operation requires at least %s parameters found %s", s, v, list0.size()));
        }
    }

    public static void zzj(String s, int v, List list0) {
        if(list0.size() > v) {
            throw new IllegalArgumentException(String.format("%s operation requires at most %s parameters found %s", s, v, list0.size()));
        }
    }

    public static boolean zzk(zzap zzap0) {
        if(zzap0 == null) {
            return false;
        }
        Double double0 = zzap0.zzh();
        return !double0.isNaN() && ((double)double0) >= 0.0 && double0.equals(Math.floor(((double)double0)));
    }

    public static boolean zzl(zzap zzap0, zzap zzap1) {
        if(!zzap0.getClass().equals(zzap1.getClass())) {
            return false;
        }
        if(!(zzap0 instanceof zzau) && !(zzap0 instanceof zzan)) {
            if(zzap0 instanceof zzah) {
                return Double.isNaN(((double)zzap0.zzh())) || Double.isNaN(((double)zzap1.zzh())) ? false : zzap0.zzh().equals(zzap1.zzh());
            }
            if(zzap0 instanceof zzat) {
                return zzap0.zzi().equals(zzap1.zzi());
            }
            return zzap0 instanceof zzaf ? zzap0.zzg().equals(zzap1.zzg()) : zzap0 == zzap1;
        }
        return true;
    }
}

