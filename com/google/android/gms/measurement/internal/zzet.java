package com.google.android.gms.measurement.internal;

import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.measurement.zzpe;
import org.checkerframework.checker.nullness.qual.EnsuresNonNull;

public final class zzet extends zzgx {
    private char zza;
    private long zzb;
    private String zzc;
    private final zzer zzd;
    private final zzer zze;
    private final zzer zzf;
    private final zzer zzg;
    private final zzer zzh;
    private final zzer zzi;
    private final zzer zzj;
    private final zzer zzk;
    private final zzer zzl;

    zzet(zzgd zzgd0) {
        super(zzgd0);
        this.zza = '\u0000';
        this.zzb = -1L;
        this.zzd = new zzer(this, 6, false, false);
        this.zze = new zzer(this, 6, true, false);
        this.zzf = new zzer(this, 6, false, true);
        this.zzg = new zzer(this, 5, false, false);
        this.zzh = new zzer(this, 5, true, false);
        this.zzi = new zzer(this, 5, false, true);
        this.zzj = new zzer(this, 4, false, false);
        this.zzk = new zzer(this, 3, false, false);
        this.zzl = new zzer(this, 2, false, false);
    }

    static char zza(zzet zzet0) {
        return zzet0.zza;
    }

    static long zzb(zzet zzet0) {
        return zzet0.zzb;
    }

    public final zzer zzc() {
        return this.zzk;
    }

    public final zzer zzd() {
        return this.zzd;
    }

    public final zzer zze() {
        return this.zzf;
    }

    @Override  // com.google.android.gms.measurement.internal.zzgx
    protected final boolean zzf() {
        return false;
    }

    public final zzer zzh() {
        return this.zze;
    }

    public final zzer zzi() {
        return this.zzj;
    }

    public final zzer zzj() {
        return this.zzl;
    }

    public final zzer zzk() {
        return this.zzg;
    }

    public final zzer zzl() {
        return this.zzi;
    }

    public final zzer zzm() {
        return this.zzh;
    }

    protected static Object zzn(String s) {
        return s == null ? null : new zzes(s);
    }

    static String zzo(boolean z, String s, Object object0, Object object1, Object object2) {
        String s1 = zzet.zzp(z, object0);
        String s2 = zzet.zzp(z, object1);
        String s3 = zzet.zzp(z, object2);
        StringBuilder stringBuilder0 = new StringBuilder();
        String s4 = "";
        if(s == null) {
            s = "";
        }
        if(!TextUtils.isEmpty(s)) {
            stringBuilder0.append(s);
            s4 = ": ";
        }
        String s5 = ", ";
        if(!TextUtils.isEmpty(s1)) {
            stringBuilder0.append(s4);
            stringBuilder0.append(s1);
            s4 = ", ";
        }
        if(TextUtils.isEmpty(s2)) {
            s5 = s4;
        }
        else {
            stringBuilder0.append(s4);
            stringBuilder0.append(s2);
        }
        if(!TextUtils.isEmpty(s3)) {
            stringBuilder0.append(s5);
            stringBuilder0.append(s3);
        }
        return stringBuilder0.toString();
    }

    static String zzp(boolean z, Object object0) {
        String s = "";
        if(object0 == null) {
            return "";
        }
        if(object0 instanceof Integer) {
            object0 = (long)(((int)(((Integer)object0))));
        }
        if(object0 instanceof Long) {
            if(!z) {
                return object0.toString();
            }
            if(Math.abs(((long)(((Long)object0)))) < 100L) {
                return object0.toString();
            }
            String s1 = String.valueOf(Math.abs(((long)(((Long)object0)))));
            long v1 = Math.round(Math.pow(10.0, s1.length() - 1));
            long v2 = Math.round(Math.pow(10.0, s1.length()) - 1.0);
            StringBuilder stringBuilder0 = new StringBuilder();
            if(object0.toString().charAt(0) == 45) {
                s = "-";
            }
            stringBuilder0.append(s);
            stringBuilder0.append(v1);
            stringBuilder0.append("...");
            stringBuilder0.append(s);
            stringBuilder0.append(v2);
            return stringBuilder0.toString();
        }
        if(object0 instanceof Boolean) {
            return object0.toString();
        }
        if(object0 instanceof Throwable) {
            StringBuilder stringBuilder1 = new StringBuilder((z ? ((Throwable)object0).getClass().getName() : ((Throwable)object0).toString()));
            String s2 = zzet.zzq(zzgd.class.getCanonicalName());
            StackTraceElement[] arr_stackTraceElement = ((Throwable)object0).getStackTrace();
            for(int v = 0; v < arr_stackTraceElement.length; ++v) {
                StackTraceElement stackTraceElement0 = arr_stackTraceElement[v];
                if(!stackTraceElement0.isNativeMethod()) {
                    String s3 = stackTraceElement0.getClassName();
                    if(s3 != null && zzet.zzq(s3).equals(s2)) {
                        stringBuilder1.append(": ");
                        stringBuilder1.append(stackTraceElement0);
                        return stringBuilder1.toString();
                    }
                }
            }
            return stringBuilder1.toString();
        }
        if(object0 instanceof zzes) {
            return ((zzes)object0).zza;
        }
        return z ? "-" : object0.toString();
    }

    static String zzq(String s) {
        if(TextUtils.isEmpty(s)) {
            return "";
        }
        int v = s.lastIndexOf(46);
        if(v == -1) {
            zzpe.zzc();
            return ((Boolean)zzeg.zzay.zza(null)).booleanValue() ? "" : s;
        }
        return s.substring(0, v);
    }

    @EnsuresNonNull({"logTagDoNotUseDirectly"})
    protected final String zzr() {
        synchronized(this) {
            if(this.zzc == null) {
                this.zzc = this.zzt.zzy() == null ? this.zzt.zzf().zzn() : this.zzt.zzy();
            }
            Preconditions.checkNotNull(this.zzc);
            return this.zzc;
        }
    }

    static void zzs(zzet zzet0, long v) {
        zzet0.zzb = 79000L;
    }

    static void zzt(zzet zzet0, char c) {
        zzet0.zza = c;
    }

    protected final void zzu(int v, boolean z, boolean z1, String s, Object object0, Object object1, Object object2) {
        if(!z && Log.isLoggable(this.zzr(), v)) {
            String s1 = zzet.zzo(false, s, object0, object1, object2);
            Log.println(v, this.zzr(), s1);
        }
        if(!z1 && v >= 5) {
            Preconditions.checkNotNull(s);
            zzga zzga0 = this.zzt.zzo();
            if(zzga0 == null) {
                Log.println(6, this.zzr(), "Scheduler not set. Not logging error/warn");
                return;
            }
            if(!zzga0.zzy()) {
                Log.println(6, this.zzr(), "Scheduler not initialized. Not logging error/warn");
                return;
            }
            zzga0.zzp(new zzeq(this, (v < 9 ? v : 8), s, object0, object1, object2));
        }
    }
}

