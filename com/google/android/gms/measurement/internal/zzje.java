package com.google.android.gms.measurement.internal;

import android.os.RemoteException;
import com.google.android.gms.common.internal.Preconditions;
import java.util.concurrent.atomic.AtomicReference;

final class zzje implements Runnable {
    final AtomicReference zza;
    final zzq zzb;
    final zzjz zzc;

    zzje(zzjz zzjz0, AtomicReference atomicReference0, zzq zzq0) {
        this.zzc = zzjz0;
        this.zza = atomicReference0;
        this.zzb = zzq0;
        super();
    }

    @Override
    public final void run() {
        AtomicReference atomicReference0 = this.zza;
        __monitor_enter(atomicReference0);
        try {
            try {
                if(this.zzc.zzt.zzm().zzc().zzj(zzha.zzb)) {
                    zzjz zzjz0 = this.zzc;
                    zzej zzej0 = zzjz.zzh(zzjz0);
                    if(zzej0 != null) {
                        Preconditions.checkNotNull(this.zzb);
                        String s = zzej0.zzd(this.zzb);
                        this.zza.set(s);
                        String s1 = (String)this.zza.get();
                        if(s1 != null) {
                            this.zzc.zzt.zzq().zzO(s1);
                            this.zzc.zzt.zzm().zze.zzb(s1);
                        }
                        zzjz.zzp(this.zzc);
                        goto label_24;
                    }
                    zzjz0.zzt.zzaA().zzd().zza("Failed to get app instance id");
                }
                else {
                    this.zzc.zzt.zzaA().zzl().zza("Analytics storage consent denied; will not get app instance id");
                    this.zzc.zzt.zzq().zzO(null);
                    this.zzc.zzt.zzm().zze.zzb(null);
                    this.zza.set(null);
                }
                goto label_30;
            }
            catch(RemoteException remoteException0) {
                this.zzc.zzt.zzaA().zzd().zzb("Failed to get app instance id", remoteException0);
            }
        }
        catch(Throwable throwable0) {
            goto label_28;
        }
        try {
        label_24:
            this.zza.notify();
        }
        catch(Throwable throwable1) {
            __monitor_exit(atomicReference0);
            throw throwable1;
        }
        __monitor_exit(atomicReference0);
        return;
        try {
        label_28:
            this.zza.notify();
            throw throwable0;
        label_30:
            this.zza.notify();
        }
        catch(Throwable throwable1) {
            __monitor_exit(atomicReference0);
            throw throwable1;
        }
        __monitor_exit(atomicReference0);
    }
}

