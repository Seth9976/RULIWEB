package com.google.android.gms.measurement.internal;

import android.os.RemoteException;
import com.google.android.gms.common.internal.Preconditions;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

final class zzjb implements Runnable {
    final AtomicReference zza;
    final zzq zzb;
    final boolean zzc;
    final zzjz zzd;

    zzjb(zzjz zzjz0, AtomicReference atomicReference0, zzq zzq0, boolean z) {
        this.zzd = zzjz0;
        this.zza = atomicReference0;
        this.zzb = zzq0;
        this.zzc = z;
        super();
    }

    @Override
    public final void run() {
        zzej zzej0;
        AtomicReference atomicReference0 = this.zza;
        __monitor_enter(atomicReference0);
        try {
            zzjz zzjz0 = this.zzd;
            zzej0 = zzjz.zzh(zzjz0);
            if(zzej0 == null) {
                zzjz0.zzt.zzaA().zzd().zza("Failed to get all user properties; not connected to service");
                goto label_6;
            }
            goto label_9;
        }
        catch(RemoteException remoteException0) {
            goto label_15;
        }
        catch(Throwable throwable0) {
            goto label_18;
        }
        try {
        label_6:
            this.zza.notify();
        }
        catch(Throwable throwable1) {
            __monitor_exit(atomicReference0);
            throw throwable1;
        }
        __monitor_exit(atomicReference0);
        return;
        try {
            try {
            label_9:
                Preconditions.checkNotNull(this.zzb);
                List list0 = zzej0.zze(this.zzb, this.zzc);
                this.zza.set(list0);
                zzjz.zzp(this.zzd);
            }
            catch(RemoteException remoteException0) {
            label_15:
                this.zzd.zzt.zzaA().zzd().zzb("Failed to get all user properties; remote exception", remoteException0);
            }
            goto label_20;
        }
        catch(Throwable throwable0) {
            try {
            label_18:
                this.zza.notify();
                throw throwable0;
            label_20:
                this.zza.notify();
                goto label_25;
            }
            catch(Throwable throwable1) {
            }
        }
        __monitor_exit(atomicReference0);
        throw throwable1;
    label_25:
        __monitor_exit(atomicReference0);
    }
}

