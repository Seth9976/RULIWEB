package com.google.android.gms.measurement.internal;

import android.os.RemoteException;
import com.google.android.gms.internal.measurement.zzcf;
import com.google.android.gms.internal.measurement.zzqr;

final class zzhy implements Runnable {
    final zzcf zza;
    final zzik zzb;

    zzhy(zzik zzik0, zzcf zzcf0) {
        this.zzb = zzik0;
        this.zza = zzcf0;
        super();
    }

    @Override
    public final void run() {
        Long long0;
        zzkp zzkp0 = this.zzb.zzt.zzu();
        zzqr.zzc();
        if(!zzkp0.zzt.zzf().zzs(null, zzeg.zzau)) {
            zzkp0.zzt.zzaA().zzl().zza("getSessionId has been disabled.");
            long0 = null;
        }
        else if(!zzkp0.zzt.zzm().zzc().zzj(zzha.zzb)) {
            zzkp0.zzt.zzaA().zzl().zza("Analytics storage consent denied; will not get session id");
            long0 = null;
        }
        else if(zzkp0.zzt.zzm().zzk(zzkp0.zzt.zzax().currentTimeMillis()) || zzkp0.zzt.zzm().zzk.zza() == 0L) {
            long0 = null;
        }
        else {
            long0 = zzkp0.zzt.zzm().zzk.zza();
        }
        if(long0 != null) {
            this.zzb.zzt.zzv().zzV(this.zza, ((long)long0));
            return;
        }
        try {
            this.zza.zze(null);
        }
        catch(RemoteException remoteException0) {
            this.zzb.zzt.zzaA().zzd().zzb("getSessionId failed with exception", remoteException0);
        }
    }
}

