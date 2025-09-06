package com.google.android.gms.measurement.internal;

import android.os.RemoteException;
import android.text.TextUtils;
import com.google.android.gms.common.internal.Preconditions;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

final class zzjq implements Runnable {
    final AtomicReference zza;
    final String zzb;
    final String zzc;
    final zzq zzd;
    final zzjz zze;

    zzjq(zzjz zzjz0, AtomicReference atomicReference0, String s, String s1, String s2, zzq zzq0) {
        this.zze = zzjz0;
        this.zza = atomicReference0;
        this.zzb = s1;
        this.zzc = s2;
        this.zzd = zzq0;
        super();
    }

    @Override
    public final void run() {
        AtomicReference atomicReference0 = this.zza;
        __monitor_enter(atomicReference0);
        try {
            try {
                zzjz zzjz0 = this.zze;
                zzej zzej0 = zzjz.zzh(zzjz0);
                if(zzej0 != null) {
                    if(TextUtils.isEmpty(null)) {
                        Preconditions.checkNotNull(this.zzd);
                        List list0 = zzej0.zzf(this.zzb, this.zzc, this.zzd);
                        this.zza.set(list0);
                    }
                    else {
                        List list1 = zzej0.zzg(null, this.zzb, this.zzc);
                        this.zza.set(list1);
                    }
                    zzjz.zzp(this.zze);
                    goto label_20;
                }
                zzjz0.zzt.zzaA().zzd().zzd("(legacy) Failed to get conditional properties; not connected to service", null, this.zzb, this.zzc);
                this.zza.set(Collections.EMPTY_LIST);
                goto label_26;
            }
            catch(RemoteException remoteException0) {
                this.zze.zzt.zzaA().zzd().zzd("(legacy) Failed to get conditional properties; remote exception", null, this.zzb, remoteException0);
                this.zza.set(Collections.EMPTY_LIST);
            }
        }
        catch(Throwable throwable0) {
            goto label_24;
        }
        try {
        label_20:
            this.zza.notify();
        }
        catch(Throwable throwable1) {
            __monitor_exit(atomicReference0);
            throw throwable1;
        }
        __monitor_exit(atomicReference0);
        return;
        try {
        label_24:
            this.zza.notify();
            throw throwable0;
        label_26:
            this.zza.notify();
        }
        catch(Throwable throwable1) {
            __monitor_exit(atomicReference0);
            throw throwable1;
        }
        __monitor_exit(atomicReference0);
    }
}

