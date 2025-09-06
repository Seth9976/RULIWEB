package com.google.android.gms.stats;

import android.content.Context;
import android.os.PowerManager.WakeLock;
import android.os.PowerManager;
import android.os.WorkSource;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.common.util.DefaultClock;
import com.google.android.gms.common.util.WorkSourceUtil;
import com.google.android.gms.internal.stats.zzb;
import com.google.android.gms.internal.stats.zzi;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class WakeLock {
    zzb zza;
    private static final long zzb;
    private static volatile ScheduledExecutorService zzc;
    private static final Object zzd;
    private static volatile zzd zze;
    private final Object zzf;
    private final PowerManager.WakeLock zzg;
    private int zzh;
    private Future zzi;
    private long zzj;
    private final Set zzk;
    private boolean zzl;
    private int zzm;
    private Clock zzn;
    private WorkSource zzo;
    private final String zzp;
    private final String zzq;
    private final Context zzr;
    private final Map zzs;
    private AtomicInteger zzt;
    private final ScheduledExecutorService zzu;

    static {
        WakeLock.zzb = TimeUnit.DAYS.toMillis(366L);
        WakeLock.zzc = null;
        WakeLock.zzd = new Object();
        WakeLock.zze = new com.google.android.gms.stats.zzb();
    }

    public WakeLock(Context context0, int v, String s) {
        this.zzf = new Object();
        this.zzh = 0;
        this.zzk = new HashSet();
        this.zzl = true;
        this.zzn = DefaultClock.getInstance();
        this.zzs = new HashMap();
        this.zzt = new AtomicInteger(0);
        Preconditions.checkNotNull(context0, "WakeLock: context must not be null");
        Preconditions.checkNotEmpty(s, "WakeLock: wakeLockName must not be empty");
        this.zzr = context0.getApplicationContext();
        this.zzq = s;
        this.zza = null;
        String s1 = String.valueOf(s);
        this.zzp = s1.length() == 0 ? new String("*gcore*:") : "*gcore*:" + s1;
        PowerManager powerManager0 = (PowerManager)context0.getSystemService("power");
        if(powerManager0 != null) {
            PowerManager.WakeLock powerManager$WakeLock0 = powerManager0.newWakeLock(v, s);
            this.zzg = powerManager$WakeLock0;
            if(WorkSourceUtil.hasWorkSourcePermission(context0)) {
                WorkSource workSource0 = WorkSourceUtil.fromPackage(context0, "com.ruliweb.www.ruliapp");
                this.zzo = workSource0;
                if(workSource0 != null) {
                    WakeLock.zze(powerManager$WakeLock0, workSource0);
                }
            }
            ScheduledExecutorService scheduledExecutorService0 = WakeLock.zzc;
            if(scheduledExecutorService0 == null) {
                synchronized(WakeLock.zzd) {
                    scheduledExecutorService0 = WakeLock.zzc;
                    if(scheduledExecutorService0 == null) {
                        scheduledExecutorService0 = Executors.unconfigurableScheduledExecutorService(Executors.newScheduledThreadPool(1));
                        WakeLock.zzc = scheduledExecutorService0;
                    }
                }
            }
            this.zzu = scheduledExecutorService0;
            return;
        }
        new StringBuilder(29).append("expected a non-null reference", 0, 29);
        throw new zzi("expected a non-null reference");
    }

    public void acquire(long v) {
        this.zzt.incrementAndGet();
        long v1 = 0x7FFFFFFFFFFFFFFFL;
        long v2 = v <= 0L ? Math.max(Math.min(0x7FFFFFFFFFFFFFFFL, WakeLock.zzb), 1L) : Math.min(v, Math.max(Math.min(0x7FFFFFFFFFFFFFFFL, WakeLock.zzb), 1L));
        synchronized(this.zzf) {
            if(!this.isHeld()) {
                this.zza = zzb.zza(false, null);
                this.zzg.acquire();
                this.zzn.elapsedRealtime();
            }
            ++this.zzh;
            ++this.zzm;
            this.zzb(null);
            zzc zzc0 = (zzc)this.zzs.get(null);
            if(zzc0 == null) {
                zzc0 = new zzc(null);
                this.zzs.put(null, zzc0);
            }
            ++zzc0.zza;
            long v4 = this.zzn.elapsedRealtime();
            if(0x7FFFFFFFFFFFFFFFL - v4 > v2) {
                v1 = v4 + v2;
            }
            if(v1 > this.zzj) {
                this.zzj = v1;
                Future future0 = this.zzi;
                if(future0 != null) {
                    future0.cancel(false);
                }
                zza zza0 = () -> synchronized(this.zzf) {
                    if(!this.isHeld()) {
                        return;
                    }
                    Log.e("WakeLock", this.zzp + " ** IS FORCE-RELEASED ON TIMEOUT **");
                    this.zzc();
                    if(!this.isHeld()) {
                        return;
                    }
                    this.zzh = 1;
                    this.zzd(0);
                };
                this.zzi = this.zzu.schedule(zza0, v2, TimeUnit.MILLISECONDS);
            }
        }
    }

    public boolean isHeld() {
        synchronized(this.zzf) {
        }
        return this.zzh > 0;
    }

    public void release() {
        if(this.zzt.decrementAndGet() < 0) {
            Log.e("WakeLock", this.zzp + " release without a matched acquire!");
        }
        synchronized(this.zzf) {
            this.zzb(null);
            if(this.zzs.containsKey(null)) {
                zzc zzc0 = (zzc)this.zzs.get(null);
                if(zzc0 != null) {
                    int v1 = zzc0.zza - 1;
                    zzc0.zza = v1;
                    if(v1 == 0) {
                        this.zzs.remove(null);
                    }
                }
            }
            else {
                Log.w("WakeLock", this.zzp + " counter does not exist");
            }
            this.zzd(0);
        }
    }

    public void setReferenceCounted(boolean z) {
        synchronized(this.zzf) {
            this.zzl = z;
        }
    }

    // 检测为 Lambda 实现
    public static void zza(WakeLock wakeLock0) [...]

    private final String zzb(String s) {
        if(this.zzl) {
            TextUtils.isEmpty(null);
        }
        return null;
    }

    private final void zzc() {
        if(!this.zzk.isEmpty()) {
            ArrayList arrayList0 = new ArrayList(this.zzk);
            this.zzk.clear();
            if(arrayList0.size() > 0) {
                zze zze0 = (zze)arrayList0.get(0);
                throw null;
            }
        }
    }

    private final void zzd(int v) {
        synchronized(this.zzf) {
            if(!this.isHeld()) {
                return;
            }
            if(this.zzl) {
                int v2 = this.zzh - 1;
                this.zzh = v2;
                if(v2 > 0) {
                    return;
                }
            }
            else {
                this.zzh = 0;
            }
            this.zzc();
            for(Object object1: this.zzs.values()) {
                ((zzc)object1).zza = 0;
            }
            this.zzs.clear();
            Future future0 = this.zzi;
            if(future0 != null) {
                future0.cancel(false);
                this.zzi = null;
                this.zzj = 0L;
            }
            this.zzm = 0;
            if(this.zzg.isHeld()) {
                try {
                    try {
                        this.zzg.release();
                    }
                    catch(RuntimeException runtimeException0) {
                        if(!runtimeException0.getClass().equals(RuntimeException.class)) {
                            throw runtimeException0;
                        }
                        Log.e("WakeLock", this.zzp + " failed to release!", runtimeException0);
                        if(this.zza != null) {
                            this.zza = null;
                        }
                        return;
                    }
                }
                catch(Throwable throwable0) {
                    if(this.zza != null) {
                        this.zza = null;
                    }
                    throw throwable0;
                }
                if(this.zza != null) {
                    this.zza = null;
                }
            }
            else {
                Log.e("WakeLock", this.zzp + " should be held!");
            }
        }
    }

    private static void zze(PowerManager.WakeLock powerManager$WakeLock0, WorkSource workSource0) {
        try {
            powerManager$WakeLock0.setWorkSource(workSource0);
        }
        catch(IllegalArgumentException | ArrayIndexOutOfBoundsException exception0) {
            Log.wtf("WakeLock", ((RuntimeException)exception0).toString());
        }
    }
}

