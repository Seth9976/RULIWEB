package com.google.android.gms.internal.measurement;

import android.os.RemoteException;

abstract class zzdu implements Runnable {
    final long zzh;
    final long zzi;
    final boolean zzj;
    final zzef zzk;

    zzdu(zzef zzef0, boolean z) {
        this.zzk = zzef0;
        super();
        this.zzh = zzef0.zza.currentTimeMillis();
        this.zzi = zzef0.zza.elapsedRealtime();
        this.zzj = z;
    }

    @Override
    public final void run() {
        if(zzef.zzQ(this.zzk)) {
            this.zzb();
            return;
        }
        try {
            this.zza();
        }
        catch(Exception exception0) {
            zzef.zzt(this.zzk, exception0, false, this.zzj);
            this.zzb();
        }
    }

    abstract void zza() throws RemoteException;

    protected void zzb() {
    }
}

