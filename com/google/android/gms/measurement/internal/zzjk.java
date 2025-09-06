package com.google.android.gms.measurement.internal;

import android.os.RemoteException;
import com.google.android.gms.internal.measurement.zzcf;

final class zzjk implements Runnable {
    final zzau zza;
    final String zzb;
    final zzcf zzc;
    final zzjz zzd;

    zzjk(zzjz zzjz0, zzau zzau0, String s, zzcf zzcf0) {
        this.zzd = zzjz0;
        this.zza = zzau0;
        this.zzb = s;
        this.zzc = zzcf0;
        super();
    }

    @Override
    public final void run() {
        byte[] arr_b = null;
        try {
            zzjz zzjz0 = this.zzd;
            zzej zzej0 = zzjz.zzh(zzjz0);
            if(zzej0 == null) {
                zzjz0.zzt.zzaA().zzd().zza("Discarding data. Failed to send event to service to bundle");
            }
            else {
                arr_b = zzej0.zzu(this.zza, this.zzb);
                zzjz.zzp(this.zzd);
            }
        }
        catch(RemoteException remoteException0) {
            this.zzd.zzt.zzaA().zzd().zzb("Failed to send event to the service to bundle", remoteException0);
        }
        finally {
            this.zzd.zzt.zzv().zzT(this.zzc, arr_b);
        }
    }
}

