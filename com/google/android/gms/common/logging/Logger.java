package com.google.android.gms.common.logging;

import android.util.Log;
import com.google.android.gms.common.internal.GmsLogger;
import java.util.Locale;

public class Logger {
    private final String zza;
    private final String zzb;
    private final GmsLogger zzc;
    private final int zzd;

    public Logger(String s, String[] arr_s) {
        String s1;
        if(arr_s.length == 0) {
            s1 = "";
        }
        else {
            StringBuilder stringBuilder0 = new StringBuilder();
            stringBuilder0.append('[');
            for(int v = 0; v < arr_s.length; ++v) {
                String s2 = arr_s[v];
                if(stringBuilder0.length() > 1) {
                    stringBuilder0.append(",");
                }
                stringBuilder0.append(s2);
            }
            stringBuilder0.append("] ");
            s1 = stringBuilder0.toString();
        }
        super();
        this.zzb = s1;
        this.zza = s;
        this.zzc = new GmsLogger(s);
        int v1;
        for(v1 = 2; v1 <= 7 && !Log.isLoggable(this.zza, v1); ++v1) {
        }
        this.zzd = v1;
    }

    public void d(String s, Object[] arr_object) {
        if(this.isLoggable(3)) {
            String s1 = this.format(s, arr_object);
            Log.d(this.zza, s1);
        }
    }

    public void e(String s, Throwable throwable0, Object[] arr_object) {
        String s1 = this.format(s, arr_object);
        Log.e(this.zza, s1, throwable0);
    }

    public void e(String s, Object[] arr_object) {
        String s1 = this.format(s, arr_object);
        Log.e(this.zza, s1);
    }

    protected String format(String s, Object[] arr_object) {
        if(arr_object != null && arr_object.length > 0) {
            s = String.format(Locale.US, s, arr_object);
        }
        return this.zzb + s;
    }

    public String getTag() {
        return this.zza;
    }

    public void i(String s, Object[] arr_object) {
        String s1 = this.format(s, arr_object);
        Log.i(this.zza, s1);
    }

    public boolean isLoggable(int v) {
        return this.zzd <= v;
    }

    public void v(String s, Throwable throwable0, Object[] arr_object) {
        if(this.isLoggable(2)) {
            String s1 = this.format(s, arr_object);
            Log.v(this.zza, s1, throwable0);
        }
    }

    public void v(String s, Object[] arr_object) {
        if(this.isLoggable(2)) {
            String s1 = this.format(s, arr_object);
            Log.v(this.zza, s1);
        }
    }

    public void w(String s, Object[] arr_object) {
        String s1 = this.format(s, arr_object);
        Log.w(this.zza, s1);
    }

    public void wtf(String s, Throwable throwable0, Object[] arr_object) {
        String s1 = this.format(s, arr_object);
        Log.wtf(this.zza, s1, throwable0);
    }

    public void wtf(Throwable throwable0) {
        Log.wtf(this.zza, throwable0);
    }
}

