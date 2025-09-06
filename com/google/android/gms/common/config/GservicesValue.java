package com.google.android.gms.common.config;

import android.os.Binder;
import android.os.StrictMode.ThreadPolicy;
import android.os.StrictMode;
import android.util.Log;
import com.google.errorprone.annotations.ResultIgnorabilityUnspecified;

public abstract class GservicesValue {
    protected final String zza;
    protected final Object zzb;
    private static final Object zzc;
    private Object zzd;

    static {
        GservicesValue.zzc = new Object();
    }

    protected GservicesValue(String s, Object object0) {
        this.zzd = null;
        this.zza = s;
        this.zzb = object0;
    }

    @ResultIgnorabilityUnspecified
    public final Object get() {
        Object object2;
        Object object1;
        Object object0 = this.zzd;
        if(object0 != null) {
            return object0;
        }
        StrictMode.ThreadPolicy strictMode$ThreadPolicy0 = StrictMode.allowThreadDiskReads();
        try {
            object1 = this.zza(this.zza);
        }
        catch(SecurityException unused_ex) {
            long v1 = Binder.clearCallingIdentity();
            try {
                object2 = this.zza(this.zza);
            }
            catch(Throwable throwable0) {
                Binder.restoreCallingIdentity(v1);
                throw throwable0;
            }
            Binder.restoreCallingIdentity(v1);
            object1 = object2;
        }
        finally {
            StrictMode.setThreadPolicy(strictMode$ThreadPolicy0);
        }
        return object1;
    }

    @Deprecated
    public final Object getBinderSafe() {
        return this.get();
    }

    @ResultIgnorabilityUnspecified
    public static boolean isInitialized() {
        synchronized(GservicesValue.zzc) {
        }
        return false;
    }

    public void override(Object object0) {
        Log.w("GservicesValue", "GservicesValue.override(): test should probably call initForTests() first");
        this.zzd = object0;
        synchronized(GservicesValue.zzc) {
        }
    }

    public void resetOverride() {
        this.zzd = null;
    }

    public static GservicesValue value(String s, Float float0) {
        return new zzd(s, float0);
    }

    public static GservicesValue value(String s, Integer integer0) {
        return new zzc(s, integer0);
    }

    public static GservicesValue value(String s, Long long0) {
        return new zzb(s, long0);
    }

    public static GservicesValue value(String s, String s1) {
        return new zze(s, s1);
    }

    public static GservicesValue value(String s, boolean z) {
        return new zza(s, Boolean.valueOf(z));
    }

    protected abstract Object zza(String arg1);
}

