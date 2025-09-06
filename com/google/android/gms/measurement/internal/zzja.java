package com.google.android.gms.measurement.internal;

import android.os.Bundle;
import android.os.RemoteException;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.measurement.zzcf;
import java.util.List;

final class zzja implements Runnable {
    final String zza;
    final String zzb;
    final zzq zzc;
    final boolean zzd;
    final zzcf zze;
    final zzjz zzf;

    zzja(zzjz zzjz0, String s, String s1, zzq zzq0, boolean z, zzcf zzcf0) {
        this.zzf = zzjz0;
        this.zza = s;
        this.zzb = s1;
        this.zzc = zzq0;
        this.zzd = z;
        this.zze = zzcf0;
        super();
    }

    @Override
    public final void run() {
        RemoteException remoteException1;
        Bundle bundle1;
        zzej zzej0;
        Bundle bundle0 = new Bundle();
        try {
            zzjz zzjz0 = this.zzf;
            zzej0 = zzjz.zzh(zzjz0);
            if(zzej0 == null) {
                zzjz0.zzt.zzaA().zzd().zzc("Failed to get user properties; not connected to service", this.zza, this.zzb);
                goto label_5;
            }
            goto label_7;
        }
        catch(RemoteException remoteException0) {
            bundle1 = bundle0;
            remoteException1 = remoteException0;
            goto label_36;
        }
        catch(Throwable throwable0) {
            this.zzf.zzt.zzv().zzS(this.zze, bundle0);
            throw throwable0;
        }
    label_5:
        this.zzf.zzt.zzv().zzS(this.zze, bundle0);
        return;
        try {
        label_7:
            Preconditions.checkNotNull(this.zzc);
            List list0 = zzej0.zzh(this.zza, this.zzb, this.zzd, this.zzc);
            bundle1 = new Bundle();
            if(list0 != null) {
                for(Object object0: list0) {
                    zzlk zzlk0 = (zzlk)object0;
                    String s = zzlk0.zze;
                    if(s == null) {
                        Long long0 = zzlk0.zzd;
                        if(long0 == null) {
                            Double double0 = zzlk0.zzg;
                            if(double0 == null) {
                                continue;
                            }
                            bundle1.putDouble(zzlk0.zzb, ((double)double0));
                        }
                        else {
                            bundle1.putLong(zzlk0.zzb, ((long)long0));
                        }
                    }
                    else {
                        bundle1.putString(zzlk0.zzb, s);
                    }
                }
            }
        }
        catch(RemoteException remoteException0) {
            bundle1 = bundle0;
            remoteException1 = remoteException0;
            goto label_36;
        }
        catch(Throwable throwable0) {
            this.zzf.zzt.zzv().zzS(this.zze, bundle0);
            throw throwable0;
        }
        try {
            zzjz.zzp(this.zzf);
            goto label_44;
        }
        catch(RemoteException remoteException1) {
            try {
            label_36:
                this.zzf.zzt.zzaA().zzd().zzc("Failed to get user properties; remote exception", this.zza, remoteException1);
            }
            catch(Throwable throwable1) {
                throwable0 = throwable1;
                bundle0 = bundle1;
                this.zzf.zzt.zzv().zzS(this.zze, bundle0);
                throw throwable0;
            }
            this.zzf.zzt.zzv().zzS(this.zze, bundle1);
            return;
        }
        catch(Throwable throwable1) {
            throwable0 = throwable1;
            bundle0 = bundle1;
        }
        this.zzf.zzt.zzv().zzS(this.zze, bundle0);
        throw throwable0;
    label_44:
        this.zzf.zzt.zzv().zzS(this.zze, bundle1);
    }
}

