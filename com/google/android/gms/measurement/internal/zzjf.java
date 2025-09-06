package com.google.android.gms.measurement.internal;

import android.os.RemoteException;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.measurement.zzcf;

final class zzjf implements Runnable {
    final zzq zza;
    final zzcf zzb;
    final zzjz zzc;

    zzjf(zzjz zzjz0, zzq zzq0, zzcf zzcf0) {
        this.zzc = zzjz0;
        this.zza = zzq0;
        this.zzb = zzcf0;
        super();
    }

    @Override
    public final void run() {
        String s = null;
        try {
            if(this.zzc.zzt.zzm().zzc().zzj(zzha.zzb)) {
                zzjz zzjz0 = this.zzc;
                zzej zzej0 = zzjz.zzh(zzjz0);
                if(zzej0 == null) {
                    zzjz0.zzt.zzaA().zzd().zza("Failed to get app instance id");
                }
                else {
                    Preconditions.checkNotNull(this.zza);
                    s = zzej0.zzd(this.zza);
                    if(s != null) {
                        this.zzc.zzt.zzq().zzO(s);
                        this.zzc.zzt.zzm().zze.zzb(s);
                    }
                    zzjz.zzp(this.zzc);
                }
            }
            else {
                this.zzc.zzt.zzaA().zzl().zza("Analytics storage consent denied; will not get app instance id");
                this.zzc.zzt.zzq().zzO(null);
                this.zzc.zzt.zzm().zze.zzb(null);
            }
        }
        catch(RemoteException remoteException0) {
            this.zzc.zzt.zzaA().zzd().zzb("Failed to get app instance id", remoteException0);
        }
        finally {
            this.zzc.zzt.zzv().zzW(this.zzb, s);
        }
    }
}

