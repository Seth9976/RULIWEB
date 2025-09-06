package com.google.android.gms.cloudmessaging;

import android.os.IBinder;
import android.os.RemoteException;

public final class zzj implements Runnable {
    public final zzm zza;
    public final IBinder zzb;

    public zzj(zzm zzm0, IBinder iBinder0) {
        this.zza = zzm0;
        this.zzb = iBinder0;
    }

    @Override
    public final void run() {
        zzm zzm0 = this.zza;
        IBinder iBinder0 = this.zzb;
        synchronized(zzm0) {
            if(iBinder0 == null) {
                zzm0.zza(0, "Null service connection");
                return;
            }
            try {
                zzm0.zzc = new zzn(iBinder0);
            }
            catch(RemoteException remoteException0) {
                zzm0.zza(0, remoteException0.getMessage());
                return;
            }
            zzm0.zza = 2;
            zzm0.zzc();
        }
    }
}

