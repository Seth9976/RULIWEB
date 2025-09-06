package com.google.android.gms.measurement.internal;

import android.os.Bundle;
import android.os.RemoteException;
import com.google.android.gms.internal.measurement.zzci;

final class zzo implements zzhf {
    public final zzci zza;
    final AppMeasurementDynamiteService zzb;

    zzo(AppMeasurementDynamiteService appMeasurementDynamiteService0, zzci zzci0) {
        this.zzb = appMeasurementDynamiteService0;
        super();
        this.zza = zzci0;
    }

    @Override  // com.google.android.gms.measurement.internal.zzhf
    public final void interceptEvent(String s, String s1, Bundle bundle0, long v) {
        try {
            this.zza.zze(s, s1, bundle0, v);
        }
        catch(RemoteException remoteException0) {
            zzgd zzgd0 = this.zzb.zza;
            if(zzgd0 != null) {
                zzgd0.zzaA().zzk().zzb("Event interceptor threw exception", remoteException0);
            }
        }
    }
}

