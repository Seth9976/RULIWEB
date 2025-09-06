package com.google.firebase.analytics.connector.internal;

import android.os.Bundle;
import com.google.android.gms.internal.measurement.zzix;
import com.google.android.gms.internal.measurement.zzja;
import com.google.android.gms.internal.measurement.zzjb;
import com.google.android.gms.measurement.internal.zzhe;

public final class zzc {
    public static final int zza;
    private static final zzjb zzb;
    private static final zzja zzc;
    private static final zzja zzd;
    private static final zzja zze;
    private static final zzja zzf;
    private static final zzja zzg;

    static {
        zzc.zzb = zzjb.zzi("_in", "_xa", "_xu", "_aq", "_aa", "_ai", new String[]{"_ac", "campaign_details", "_ug", "_iapx", "_exp_set", "_exp_clear", "_exp_activate", "_exp_timeout", "_exp_expire"});
        zzc.zzc = zzja.zzj("_e", "_f", "_iap", "_s", "_au", "_ui", "_cd");
        zzc.zzd = zzja.zzi("auto", "app", "am");
        zzc.zze = zzja.zzh("_r", "_dbg");
        zzix zzix0 = new zzix();
        zzix0.zza(zzhe.zza);
        zzix0.zza(zzhe.zzb);
        zzc.zzf = zzix0.zzb();
        zzc.zzg = zzja.zzh("^_ltv_[A-Z]{3}$", "^_cc[1-5]{1}$");
    }

    public static boolean zza(String s, String s1, Bundle bundle0) {
        if(!"_cmp".equals(s1)) {
            return true;
        }
        if(!zzc.zzd(s)) {
            return false;
        }
        if(bundle0 == null) {
            return false;
        }
        zzja zzja0 = zzc.zze;
        int v = zzja0.size();
        int v1 = 0;
        while(v1 < v) {
            boolean z = bundle0.containsKey(((String)zzja0.get(v1)));
            ++v1;
            if(z) {
                return false;
            }
            if(false) {
                break;
            }
        }
        switch(s) {
            case "fcm": {
                bundle0.putString("_cis", "fcm_integration");
                return true;
            }
            case "fdl": {
                bundle0.putString("_cis", "fdl_integration");
                return true;
            }
            case "fiam": {
                bundle0.putString("_cis", "fiam_integration");
                return true;
            }
            default: {
                return false;
            }
        }
    }

    public static boolean zzb(String s, Bundle bundle0) {
        if(zzc.zzc.contains(s)) {
            return false;
        }
        if(bundle0 != null) {
            zzja zzja0 = zzc.zze;
            int v = zzja0.size();
            int v1 = 0;
            while(v1 < v) {
                boolean z = bundle0.containsKey(((String)zzja0.get(v1)));
                ++v1;
                if(z) {
                    return false;
                }
                if(false) {
                    break;
                }
            }
        }
        return true;
    }

    public static boolean zzc(String s) {
        return !zzc.zzb.contains(s);
    }

    public static boolean zzd(String s) {
        return !zzc.zzd.contains(s);
    }

    public static boolean zze(String s, String s1) {
        if(!"_ce1".equals(s1) && !"_ce2".equals(s1)) {
            if("_ln".equals(s1)) {
                return s.equals("fcm") || s.equals("fiam");
            }
            if(zzc.zzf.contains(s1)) {
                return false;
            }
            zzja zzja0 = zzc.zzg;
            int v = zzja0.size();
            int v1 = 0;
            while(v1 < v) {
                boolean z = s1.matches(((String)zzja0.get(v1)));
                ++v1;
                if(z) {
                    return false;
                }
                if(false) {
                    break;
                }
            }
            return true;
        }
        return s.equals("fcm") || s.equals("frc");
    }
}

