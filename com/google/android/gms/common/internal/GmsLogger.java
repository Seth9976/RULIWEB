package com.google.android.gms.common.internal;

import android.util.Log;

public final class GmsLogger {
    private final String zza;
    private final String zzb;

    public GmsLogger(String s) {
        this(s, null);
    }

    public GmsLogger(String s, String s1) {
        Preconditions.checkNotNull(s, "log tag cannot be null");
        Object[] arr_object = new Object[2];
        boolean z = false;
        arr_object[0] = s;
        arr_object[1] = 23;
        if(s.length() <= 23) {
            z = true;
        }
        Preconditions.checkArgument(z, "tag \"%s\" is longer than the %d character maximum", arr_object);
        this.zza = s;
        if(s1 == null || s1.length() <= 0) {
            s1 = null;
        }
        this.zzb = s1;
    }

    public boolean canLog(int v) {
        return Log.isLoggable(this.zza, v);
    }

    public boolean canLogPii() {
        return false;
    }

    public void d(String s, String s1) {
        if(this.canLog(3)) {
            Log.d(s, this.zza(s1));
        }
    }

    public void d(String s, String s1, Throwable throwable0) {
        if(this.canLog(3)) {
            Log.d(s, this.zza(s1), throwable0);
        }
    }

    public void e(String s, String s1) {
        if(this.canLog(6)) {
            Log.e(s, this.zza(s1));
        }
    }

    public void e(String s, String s1, Throwable throwable0) {
        if(this.canLog(6)) {
            Log.e(s, this.zza(s1), throwable0);
        }
    }

    public void efmt(String s, String s1, Object[] arr_object) {
        if(this.canLog(6)) {
            Log.e(s, this.zzb(s1, arr_object));
        }
    }

    public void i(String s, String s1) {
        if(this.canLog(4)) {
            Log.i(s, this.zza(s1));
        }
    }

    public void i(String s, String s1, Throwable throwable0) {
        if(this.canLog(4)) {
            Log.i(s, this.zza(s1), throwable0);
        }
    }

    public void pii(String s, String s1) {
    }

    public void pii(String s, String s1, Throwable throwable0) {
    }

    public void v(String s, String s1) {
        if(this.canLog(2)) {
            Log.v(s, this.zza(s1));
        }
    }

    public void v(String s, String s1, Throwable throwable0) {
        if(this.canLog(2)) {
            Log.v(s, this.zza(s1), throwable0);
        }
    }

    public void w(String s, String s1) {
        if(this.canLog(5)) {
            Log.w(s, this.zza(s1));
        }
    }

    public void w(String s, String s1, Throwable throwable0) {
        if(this.canLog(5)) {
            Log.w(s, this.zza(s1), throwable0);
        }
    }

    public void wfmt(String s, String s1, Object[] arr_object) {
        if(this.canLog(5)) {
            String s2 = this.zzb(s1, arr_object);
            Log.w(this.zza, s2);
        }
    }

    public void wtf(String s, String s1, Throwable throwable0) {
        if(this.canLog(7)) {
            Log.e(s, this.zza(s1), throwable0);
            Log.wtf(s, this.zza(s1), throwable0);
        }
    }

    private final String zza(String s) {
        return this.zzb == null ? s : this.zzb + s;
    }

    private final String zzb(String s, Object[] arr_object) {
        String s1 = String.format(s, arr_object);
        return this.zzb == null ? s1 : this.zzb + s1;
    }
}

