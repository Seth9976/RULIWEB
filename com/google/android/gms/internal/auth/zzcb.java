package com.google.android.gms.internal.auth;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.regex.Pattern;
import jeb.synthetic.TWR;

public final class zzcb {
    public static final Uri zza;
    public static final Uri zzb;
    public static final Pattern zzc;
    public static final Pattern zzd;
    static HashMap zze;
    static final HashMap zzf;
    static final HashMap zzg;
    static final HashMap zzh;
    static final HashMap zzi;
    static boolean zzj;
    static final String[] zzk;
    private static final AtomicBoolean zzl;
    private static Object zzm;

    static {
        zzcb.zza = Uri.parse("content://com.google.android.gsf.gservices");
        zzcb.zzb = Uri.parse("content://com.google.android.gsf.gservices/prefix");
        zzcb.zzc = Pattern.compile("^(1|true|t|on|yes|y)$", 2);
        zzcb.zzd = Pattern.compile("^(0|false|f|off|no|n)$", 2);
        zzcb.zzl = new AtomicBoolean();
        zzcb.zzf = new HashMap(16, 1.0f);
        zzcb.zzg = new HashMap(16, 1.0f);
        zzcb.zzh = new HashMap(16, 1.0f);
        zzcb.zzi = new HashMap(16, 1.0f);
        zzcb.zzk = new String[0];
    }

    public static String zza(ContentResolver contentResolver0, String s, String s1) {
        Object object0;
        String s4;
        String s2 = null;
        synchronized(zzcb.class) {
            if(zzcb.zze == null) {
                zzcb.zzl.set(false);
                zzcb.zze = new HashMap(16, 1.0f);
                zzcb.zzm = new Object();
                zzcb.zzj = false;
                zzca zzca0 = new zzca(null);
                contentResolver0.registerContentObserver(zzcb.zza, true, zzca0);
            }
            else if(zzcb.zzl.getAndSet(false)) {
                zzcb.zze.clear();
                zzcb.zzf.clear();
                zzcb.zzg.clear();
                zzcb.zzh.clear();
                zzcb.zzi.clear();
                zzcb.zzm = new Object();
                zzcb.zzj = false;
            }
            object0 = zzcb.zzm;
            if(zzcb.zze.containsKey(s)) {
                String s3 = (String)zzcb.zze.get(s);
                if(s3 != null) {
                    s2 = s3;
                }
                return s2;
            }
        }
        Cursor cursor0 = contentResolver0.query(zzcb.zza, null, null, new String[]{s}, null);
        if(cursor0 == null) {
            return null;
        }
        try {
            if(!cursor0.moveToFirst()) {
                zzcb.zzc(object0, s, null);
                goto label_35;
            }
            goto label_37;
        }
        catch(Throwable throwable0) {
            goto label_40;
        }
    label_35:
        cursor0.close();
        return null;
        try {
        label_37:
            s4 = cursor0.getString(1);
        }
        catch(Throwable throwable0) {
        label_40:
            TWR.safeClose$NT(cursor0, throwable0);
            throw throwable0;
        }
        cursor0.close();
        if(s4 != null && s4.equals(null)) {
            s4 = null;
        }
        zzcb.zzc(object0, s, s4);
        return s4 == null ? null : s4;
    }

    static AtomicBoolean zzb() {
        return zzcb.zzl;
    }

    private static void zzc(Object object0, String s, String s1) {
        synchronized(zzcb.class) {
            if(object0 == zzcb.zzm) {
                zzcb.zze.put(s, s1);
            }
        }
    }
}

