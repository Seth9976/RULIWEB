package com.google.android.gms.common.internal;

import android.content.ComponentName;
import android.content.Context;
import android.content.ServiceConnection;
import android.os.HandlerThread;
import com.google.errorprone.annotations.ResultIgnorabilityUnspecified;
import java.util.concurrent.Executor;

public abstract class GmsClientSupervisor {
    static HandlerThread zza = null;
    private static final Object zzb = null;
    private static zzs zzc = null;
    private static Executor zzd = null;
    private static boolean zze = false;

    static {
        GmsClientSupervisor.zzb = new Object();
    }

    public boolean bindService(ComponentName componentName0, ServiceConnection serviceConnection0, String s) {
        return this.zzc(new zzo(componentName0, 0x1081), serviceConnection0, s, null);
    }

    public boolean bindService(ComponentName componentName0, ServiceConnection serviceConnection0, String s, Executor executor0) {
        return this.zzc(new zzo(componentName0, 0x1081), serviceConnection0, s, executor0);
    }

    @ResultIgnorabilityUnspecified
    public boolean bindService(String s, ServiceConnection serviceConnection0, String s1) {
        return this.zzc(new zzo(s, 0x1081, false), serviceConnection0, s1, null);
    }

    public static int getDefaultBindFlags() [...] // Inlined contents

    public static GmsClientSupervisor getInstance(Context context0) {
        synchronized(GmsClientSupervisor.zzb) {
            if(GmsClientSupervisor.zzc == null) {
                GmsClientSupervisor.zzc = new zzs(context0.getApplicationContext(), (GmsClientSupervisor.zze ? GmsClientSupervisor.getOrStartHandlerThread().getLooper() : context0.getMainLooper()), GmsClientSupervisor.zzd);
            }
            return GmsClientSupervisor.zzc;
        }
    }

    public static HandlerThread getOrStartHandlerThread() {
        synchronized(GmsClientSupervisor.zzb) {
            HandlerThread handlerThread0 = GmsClientSupervisor.zza;
            if(handlerThread0 != null) {
                return handlerThread0;
            }
            HandlerThread handlerThread1 = new HandlerThread("GoogleApiHandler", 9);
            GmsClientSupervisor.zza = handlerThread1;
            handlerThread1.start();
            return GmsClientSupervisor.zza;
        }
    }

    public static HandlerThread getOrStartHandlerThread(int v) {
        synchronized(GmsClientSupervisor.zzb) {
            HandlerThread handlerThread0 = GmsClientSupervisor.zza;
            if(handlerThread0 != null) {
                return handlerThread0;
            }
            HandlerThread handlerThread1 = new HandlerThread("GoogleApiHandler", v);
            GmsClientSupervisor.zza = handlerThread1;
            handlerThread1.start();
            return GmsClientSupervisor.zza;
        }
    }

    public static void setDefaultBindExecutor(Executor executor0) {
        synchronized(GmsClientSupervisor.zzb) {
            zzs zzs0 = GmsClientSupervisor.zzc;
            if(zzs0 != null) {
                zzs0.zzi(executor0);
            }
            GmsClientSupervisor.zzd = executor0;
        }
    }

    public static void setUseHandlerThreadForCallbacks() {
        synchronized(GmsClientSupervisor.zzb) {
            zzs zzs0 = GmsClientSupervisor.zzc;
            if(zzs0 != null && !GmsClientSupervisor.zze) {
                zzs0.zzj(GmsClientSupervisor.getOrStartHandlerThread().getLooper());
            }
            GmsClientSupervisor.zze = true;
        }
    }

    public void unbindService(ComponentName componentName0, ServiceConnection serviceConnection0, String s) {
        this.zza(new zzo(componentName0, 0x1081), serviceConnection0, s);
    }

    public void unbindService(String s, ServiceConnection serviceConnection0, String s1) {
        this.zza(new zzo(s, 0x1081, false), serviceConnection0, s1);
    }

    protected abstract void zza(zzo arg1, ServiceConnection arg2, String arg3);

    public final void zzb(String s, String s1, int v, ServiceConnection serviceConnection0, String s2, boolean z) {
        this.zza(new zzo(s, s1, 0x1081, z), serviceConnection0, s2);
    }

    protected abstract boolean zzc(zzo arg1, ServiceConnection arg2, String arg3, Executor arg4);
}

