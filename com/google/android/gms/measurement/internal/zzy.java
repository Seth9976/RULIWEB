package com.google.android.gms.measurement.internal;

import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.measurement.zzer;
import com.google.android.gms.internal.measurement.zzey;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

abstract class zzy {
    final String zzb;
    final int zzc;
    Boolean zzd;
    Boolean zze;
    Long zzf;
    Long zzg;

    zzy(String s, int v) {
        this.zzb = s;
        this.zzc = v;
    }

    abstract int zza();

    abstract boolean zzb();

    abstract boolean zzc();

    private static Boolean zzd(String s, int v, boolean z, String s1, List list0, String s2, zzet zzet0) {
        if(v == 7) {
            if(list0 == null || list0.isEmpty()) {
                return null;
            }
        }
        else if(s1 == null) {
            return null;
        }
        if(!z && v != 2) {
            s = s.toUpperCase(Locale.ENGLISH);
        }
        switch(v - 1) {
            case 1: {
                if(s2 == null) {
                    return null;
                }
                try {
                    return Boolean.valueOf(Pattern.compile(s2, (z ? 0 : 66)).matcher(s).matches());
                }
                catch(PatternSyntaxException unused_ex) {
                    if(zzet0 != null) {
                        zzet0.zzk().zzb("Invalid regular expression in REGEXP audience filter. expression", s2);
                    }
                    return null;
                }
            }
            case 2: {
                return Boolean.valueOf(s.startsWith(s1));
            }
            case 3: {
                return Boolean.valueOf(s.endsWith(s1));
            }
            case 4: {
                return Boolean.valueOf(s.contains(s1));
            }
            case 5: {
                return Boolean.valueOf(s.equals(s1));
            }
            case 6: {
                return list0 == null ? null : Boolean.valueOf(list0.contains(s));
            }
            default: {
                return null;
            }
        }
    }

    // 去混淆评级： 低(31)
    static Boolean zze(BigDecimal bigDecimal0, zzer zzer0, double f) {
        Preconditions.checkNotNull(zzer0);
        if(zzer0.zzg()) {
            switch(zzer0.zzm()) {
                case 1: {
                    break;
                }
                case 5: {
                    if(!zzer0.zzk() || !zzer0.zzj()) {
                        return null;
                    }
                    return zzer0.zzm() == 5 ? null : null;
                }
                default: {
                    if(!zzer0.zzh()) {
                        return null;
                    }
                    return zzer0.zzm() == 5 ? null : null;
                }
            }
        }
        return null;
    }

    static Boolean zzf(String s, zzey zzey0, zzet zzet0) {
        Preconditions.checkNotNull(zzey0);
        if(s == null) {
            return null;
        }
        if(zzey0.zzi()) {
            switch(zzey0.zzj()) {
                case 1: {
                    break;
                }
                case 7: {
                    if(zzey0.zza() == 0) {
                        return null;
                    }
                label_9:
                    int v = zzey0.zzj();
                    boolean z = zzey0.zzf();
                    if(zzey0.zza() == 0) {
                        return v == 2 ? zzy.zzd(s, 2, z, "", null, "", zzet0) : zzy.zzd(s, v, z, "", null, null, zzet0);
                    }
                    List list0 = zzey0.zze();
                    if(!z) {
                        ArrayList arrayList0 = new ArrayList(list0.size());
                        for(Object object0: list0) {
                            arrayList0.add(((String)object0).toUpperCase(Locale.ENGLISH));
                        }
                        list0 = Collections.unmodifiableList(arrayList0);
                    }
                    return v == 2 ? zzy.zzd(s, 2, z, "", list0, "", zzet0) : zzy.zzd(s, v, z, "", list0, null, zzet0);
                }
                default: {
                    if(!zzey0.zzh()) {
                        return null;
                    }
                    goto label_9;
                }
            }
        }
        return null;
    }

    static Boolean zzg(double f, zzer zzer0) {
        try {
            return zzy.zze(new BigDecimal(f), zzer0, Math.ulp(f));
        }
        catch(NumberFormatException unused_ex) {
            return null;
        }
    }

    static Boolean zzh(long v, zzer zzer0) {
        try {
            return zzy.zze(new BigDecimal(v), zzer0, 0.0);
        }
        catch(NumberFormatException unused_ex) {
            return null;
        }
    }

    static Boolean zzi(String s, zzer zzer0) {
        if(!zzlj.zzy(s)) {
            return null;
        }
        try {
            return zzy.zze(new BigDecimal(s), zzer0, 0.0);
        }
        catch(NumberFormatException unused_ex) {
            return null;
        }
    }

    static Boolean zzj(Boolean boolean0, boolean z) {
        if(boolean0 == null) {
            return null;
        }
        return boolean0.booleanValue() == z ? false : true;
    }
}

