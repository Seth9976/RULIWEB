package com.google.android.gms.measurement.internal;

import android.os.RemoteException;

final class zzjh implements Runnable {
    final zzir zza;
    final zzjz zzb;

    zzjh(zzjz zzjz0, zzir zzir0) {
        this.zzb = zzjz0;
        this.zza = zzir0;
        super();
    }

    @Override
    public final void run() {
        zzjz zzjz0 = this.zzb;
        zzej zzej0 = zzjz.zzh(zzjz0);
        if(zzej0 == null) {
            zzjz0.zzt.zzaA().zzd().zza("Failed to send current screen to service");
            return;
        }
        try {
            zzir zzir0 = this.zza;
            if(zzir0 == null) {
                zzej0.zzq(0L, null, null, "com.ruliweb.www.ruliapp");
            }
            else {
                zzej0.zzq(zzir0.zzc, zzir0.zza, zzir0.zzb, "com.ruliweb.www.ruliapp");
            }
            zzjz.zzp(this.zzb);
        }
        catch(RemoteException remoteException0) {
            this.zzb.zzt.zzaA().zzd().zzb("Failed to send current screen to the service", remoteException0);
        }
    }
}

