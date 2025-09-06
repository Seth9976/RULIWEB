package com.google.android.gms.measurement.internal;

import android.os.RemoteException;
import com.google.android.gms.common.internal.Preconditions;

final class zzjn implements Runnable {
    final zzq zza;
    final zzjz zzb;

    zzjn(zzjz zzjz0, zzq zzq0) {
        this.zzb = zzjz0;
        this.zza = zzq0;
        super();
    }

    @Override
    public final void run() {
        zzjz zzjz0 = this.zzb;
        zzej zzej0 = zzjz.zzh(zzjz0);
        if(zzej0 == null) {
            zzjz0.zzt.zzaA().zzd().zza("Failed to send consent settings to service");
            return;
        }
        try {
            Preconditions.checkNotNull(this.zza);
            zzej0.zzp(this.zza);
            zzjz.zzp(this.zzb);
        }
        catch(RemoteException remoteException0) {
            this.zzb.zzt.zzaA().zzd().zzb("Failed to send consent settings to the service", remoteException0);
        }
    }
}

