package com.google.android.gms.measurement.internal;

import com.google.android.gms.common.internal.Preconditions;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;

public final class zzga extends zzgx {
    private static final AtomicLong zza;
    private zzfz zzb;
    private zzfz zzc;
    private final PriorityBlockingQueue zzd;
    private final BlockingQueue zze;
    private final Thread.UncaughtExceptionHandler zzf;
    private final Thread.UncaughtExceptionHandler zzg;
    private final Object zzh;
    private final Semaphore zzi;
    private volatile boolean zzj;

    static {
        zzga.zza = new AtomicLong(0x8000000000000000L);
    }

    zzga(zzgd zzgd0) {
        super(zzgd0);
        this.zzh = new Object();
        this.zzi = new Semaphore(2);
        this.zzd = new PriorityBlockingQueue();
        this.zze = new LinkedBlockingQueue();
        this.zzf = new zzfx(this, "Thread death: Uncaught exception on worker thread");
        this.zzg = new zzfx(this, "Thread death: Uncaught exception on network thread");
    }

    static zzfz zza(zzga zzga0) {
        return zzga0.zzc;
    }

    @Override  // com.google.android.gms.measurement.internal.zzgw
    public final void zzaz() {
        if(Thread.currentThread() != this.zzc) {
            throw new IllegalStateException("Call expected from network thread");
        }
    }

    static zzfz zzb(zzga zzga0) {
        return zzga0.zzb;
    }

    static Object zzc(zzga zzga0) {
        return zzga0.zzh;
    }

    final Object zzd(AtomicReference atomicReference0, long v, String s, Runnable runnable0) {
        synchronized(atomicReference0) {
            this.zzt.zzaB().zzp(runnable0);
            try {
                atomicReference0.wait(v);
            }
            catch(InterruptedException unused_ex) {
                this.zzt.zzaA().zzk().zza("Interrupted waiting for " + s);
                return null;
            }
        }
        Object object0 = atomicReference0.get();
        if(object0 == null) {
            this.zzt.zzaA().zzk().zza("Timed out waiting for " + s);
        }
        return object0;
    }

    @Override  // com.google.android.gms.measurement.internal.zzgx
    protected final boolean zzf() {
        return false;
    }

    @Override  // com.google.android.gms.measurement.internal.zzgw
    public final void zzg() {
        if(Thread.currentThread() != this.zzb) {
            throw new IllegalStateException("Call expected from worker thread");
        }
    }

    public final Future zzh(Callable callable0) throws IllegalStateException {
        this.zzv();
        Preconditions.checkNotNull(callable0);
        Future future0 = new zzfy(this, callable0, false, "Task exception on worker thread");
        if(Thread.currentThread() == this.zzb) {
            if(!this.zzd.isEmpty()) {
                this.zzt.zzaA().zzk().zza("Callable skipped the worker queue.");
            }
            ((zzfy)future0).run();
            return future0;
        }
        this.zzt(((zzfy)future0));
        return future0;
    }

    public final Future zzi(Callable callable0) throws IllegalStateException {
        this.zzv();
        Preconditions.checkNotNull(callable0);
        Future future0 = new zzfy(this, callable0, true, "Task exception on worker thread");
        if(Thread.currentThread() == this.zzb) {
            ((zzfy)future0).run();
            return future0;
        }
        this.zzt(((zzfy)future0));
        return future0;
    }

    static Semaphore zzj(zzga zzga0) {
        return zzga0.zzi;
    }

    static AtomicLong zzk() {
        return zzga.zza;
    }

    static void zzl(zzga zzga0, zzfz zzfz0) {
        zzga0.zzc = null;
    }

    static void zzm(zzga zzga0, zzfz zzfz0) {
        zzga0.zzb = null;
    }

    public final void zzo(Runnable runnable0) throws IllegalStateException {
        this.zzv();
        Preconditions.checkNotNull(runnable0);
        zzfy zzfy0 = new zzfy(this, runnable0, false, "Task exception on network thread");
        synchronized(this.zzh) {
            this.zze.add(zzfy0);
            zzfz zzfz0 = this.zzc;
            if(zzfz0 == null) {
                zzfz zzfz1 = new zzfz(this, "Measurement Network", this.zze);
                this.zzc = zzfz1;
                zzfz1.setUncaughtExceptionHandler(this.zzg);
                this.zzc.start();
            }
            else {
                zzfz0.zza();
            }
        }
    }

    public final void zzp(Runnable runnable0) throws IllegalStateException {
        this.zzv();
        Preconditions.checkNotNull(runnable0);
        this.zzt(new zzfy(this, runnable0, false, "Task exception on worker thread"));
    }

    public final void zzq(Runnable runnable0) throws IllegalStateException {
        this.zzv();
        Preconditions.checkNotNull(runnable0);
        this.zzt(new zzfy(this, runnable0, true, "Task exception on worker thread"));
    }

    static boolean zzr(zzga zzga0) [...] // Inlined contents

    public final boolean zzs() {
        return Thread.currentThread() == this.zzb;
    }

    private final void zzt(zzfy zzfy0) {
        synchronized(this.zzh) {
            this.zzd.add(zzfy0);
            zzfz zzfz0 = this.zzb;
            if(zzfz0 == null) {
                zzfz zzfz1 = new zzfz(this, "Measurement Worker", this.zzd);
                this.zzb = zzfz1;
                zzfz1.setUncaughtExceptionHandler(this.zzf);
                this.zzb.start();
            }
            else {
                zzfz0.zza();
            }
        }
    }
}

