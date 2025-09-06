package com.google.android.gms.measurement.internal;

import android.os.RemoteException;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.measurement.zzcf;
import java.util.ArrayList;

final class zzjr implements Runnable {
    final String zza;
    final String zzb;
    final zzq zzc;
    final zzcf zzd;
    final zzjz zze;

    zzjr(zzjz zzjz0, String s, String s1, zzq zzq0, zzcf zzcf0) {
        this.zze = zzjz0;
        this.zza = s;
        this.zzb = s1;
        this.zzc = zzq0;
        this.zzd = zzcf0;
        super();
    }

    @Override
    public final void run() {
        ArrayList arrayList0 = new ArrayList();
        try {
            zzjz zzjz0 = this.zze;
            zzej zzej0 = zzjz.zzh(zzjz0);
            if(zzej0 == null) {
                zzjz0.zzt.zzaA().zzd().zzc("Failed to get conditional properties; not connected to service", this.zza, this.zzb);
            }
            else {
                Preconditions.checkNotNull(this.zzc);
                arrayList0 = zzlp.zzH(zzej0.zzf(this.zza, this.zzb, this.zzc));
                zzjz.zzp(this.zze);
            }
        }
        catch(RemoteException remoteException0) {
            this.zze.zzt.zzaA().zzd().zzd("Failed to get conditional properties; remote exception", this.zza, this.zzb, remoteException0);
        }
        finally {
            this.zze.zzt.zzv().zzR(this.zzd, arrayList0);
        }
    }
}

