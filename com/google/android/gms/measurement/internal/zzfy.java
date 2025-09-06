package com.google.android.gms.measurement.internal;

import com.google.android.gms.common.internal.Preconditions;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

final class zzfy extends FutureTask implements Comparable {
    final boolean zza;
    final zzga zzb;
    private final long zzc;
    private final String zzd;

    zzfy(zzga zzga0, Runnable runnable0, boolean z, String s) {
        this.zzb = zzga0;
        super(runnable0, null);
        Preconditions.checkNotNull(s);
        long v = zzga.zzk().getAndIncrement();
        this.zzc = v;
        this.zzd = s;
        this.zza = z;
        if(v == 0x7FFFFFFFFFFFFFFFL) {
            zzga0.zzt.zzaA().zzd().zza("Tasks index overflow");
        }
    }

    zzfy(zzga zzga0, Callable callable0, boolean z, String s) {
        this.zzb = zzga0;
        super(callable0);
        Preconditions.checkNotNull("Task exception on worker thread");
        long v = zzga.zzk().getAndIncrement();
        this.zzc = v;
        this.zzd = "Task exception on worker thread";
        this.zza = z;
        if(v == 0x7FFFFFFFFFFFFFFFL) {
            zzga0.zzt.zzaA().zzd().zza("Tasks index overflow");
        }
    }

    @Override
    public final int compareTo(Object object0) {
        boolean z = this.zza;
        if(z != ((zzfy)object0).zza) {
            return z ? -1 : 1;
        }
        int v = Long.compare(this.zzc, ((zzfy)object0).zzc);
        if(v < 0) {
            return -1;
        }
        if(v > 0) {
            return 1;
        }
        this.zzb.zzt.zzaA().zzh().zzb("Two tasks share the same index. index", this.zzc);
        return 0;
    }

    @Override
    protected final void setException(Throwable throwable0) {
        this.zzb.zzt.zzaA().zzd().zzb(this.zzd, throwable0);
        if(throwable0 instanceof zzfw) {
            Thread.UncaughtExceptionHandler thread$UncaughtExceptionHandler0 = Thread.getDefaultUncaughtExceptionHandler();
            if(thread$UncaughtExceptionHandler0 != null) {
                thread$UncaughtExceptionHandler0.uncaughtException(Thread.currentThread(), throwable0);
            }
        }
        super.setException(throwable0);
    }
}

