package com.google.android.gms.internal.measurement;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.regex.Pattern;
import jeb.synthetic.TWR;

public final class zzha {
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
        zzha.zza = Uri.parse("content://com.google.android.gsf.gservices");
        zzha.zzb = Uri.parse("content://com.google.android.gsf.gservices/prefix");
        zzha.zzc = Pattern.compile("^(1|true|t|on|yes|y)$", 2);
        zzha.zzd = Pattern.compile("^(0|false|f|off|no|n)$", 2);
        zzha.zzl = new AtomicBoolean();
        zzha.zzf = new HashMap(16, 1.0f);
        zzha.zzg = new HashMap(16, 1.0f);
        zzha.zzh = new HashMap(16, 1.0f);
        zzha.zzi = new HashMap(16, 1.0f);
        zzha.zzk = new String[0];
    }

    public static String zza(ContentResolver contentResolver0, String s, String s1) {
        Object object0;
        String s4;
        String s2 = null;
        synchronized(zzha.class) {
            if(zzha.zze == null) {
                zzha.zzl.set(false);
                zzha.zze = new HashMap(16, 1.0f);
                zzha.zzm = new Object();
                zzha.zzj = false;
                zzgz zzgz0 = new zzgz(null);
                contentResolver0.registerContentObserver(zzha.zza, true, zzgz0);
            }
            else if(zzha.zzl.getAndSet(false)) {
                zzha.zze.clear();
                zzha.zzf.clear();
                zzha.zzg.clear();
                zzha.zzh.clear();
                zzha.zzi.clear();
                zzha.zzm = new Object();
                zzha.zzj = false;
            }
            object0 = zzha.zzm;
            if(zzha.zze.containsKey(s)) {
                String s3 = (String)zzha.zze.get(s);
                if(s3 != null) {
                    s2 = s3;
                }
                return s2;
            }
        }
        Cursor cursor0 = contentResolver0.query(zzha.zza, null, null, new String[]{s}, null);
        if(cursor0 == null) {
            return null;
        }
        try {
            if(!cursor0.moveToFirst()) {
                zzha.zzc(object0, s, null);
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
        zzha.zzc(object0, s, s4);
        return s4 == null ? null : s4;
    }

    static AtomicBoolean zzb() {
        return zzha.zzl;
    }

    private static void zzc(Object object0, String s, String s1) {
        synchronized(zzha.class) {
            if(object0 == zzha.zzm) {
                zzha.zze.put(s, s1);
            }
        }
    }
}

