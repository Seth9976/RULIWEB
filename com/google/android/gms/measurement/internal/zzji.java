package com.google.android.gms.measurement.internal;

import android.os.Bundle;
import android.os.RemoteException;
import com.google.android.gms.common.internal.Preconditions;

final class zzji implements Runnable {
    final zzq zza;
    final Bundle zzb;
    final zzjz zzc;

    zzji(zzjz zzjz0, zzq zzq0, Bundle bundle0) {
        this.zzc = zzjz0;
        this.zza = zzq0;
        this.zzb = bundle0;
        super();
    }

    @Override
    public final void run() {
        zzjz zzjz0 = this.zzc;
        zzej zzej0 = zzjz.zzh(zzjz0);
        if(zzej0 == null) {
            zzjz0.zzt.zzaA().zzd().zza("Failed to send default event parameters to service");
            return;
        }
        try {
            Preconditions.checkNotNull(this.zza);
            zzej0.zzr(this.zzb, this.zza);
        }
        catch(RemoteException remoteException0) {
            this.zzc.zzt.zzaA().zzd().zzb("Failed to send default event parameters to service", remoteException0);
        }
    }
}

