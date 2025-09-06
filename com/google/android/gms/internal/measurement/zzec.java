package com.google.android.gms.internal.measurement;

import android.app.Activity;
import android.os.RemoteException;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.dynamic.ObjectWrapper;

final class zzec extends zzdu {
    final Activity zza;
    final zzbz zzb;
    final zzee zzc;

    zzec(zzee zzee0, Activity activity0, zzbz zzbz0) {
        this.zzc = zzee0;
        this.zza = activity0;
        this.zzb = zzbz0;
        super(zzee0.zza, true);
    }

    @Override  // com.google.android.gms.internal.measurement.zzdu
    final void zza() throws RemoteException {
        ((zzcc)Preconditions.checkNotNull(zzef.zze(this.zzc.zza))).onActivitySaveInstanceState(ObjectWrapper.wrap(this.zza), this.zzb, this.zzi);
    }
}

