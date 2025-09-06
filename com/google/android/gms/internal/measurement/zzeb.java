package com.google.android.gms.internal.measurement;

import android.app.Activity;
import android.os.RemoteException;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.dynamic.ObjectWrapper;

final class zzeb extends zzdu {
    final Activity zza;
    final zzee zzb;

    zzeb(zzee zzee0, Activity activity0) {
        this.zzb = zzee0;
        this.zza = activity0;
        super(zzee0.zza, true);
    }

    @Override  // com.google.android.gms.internal.measurement.zzdu
    final void zza() throws RemoteException {
        ((zzcc)Preconditions.checkNotNull(zzef.zze(this.zzb.zza))).onActivityStopped(ObjectWrapper.wrap(this.zza), this.zzi);
    }
}

