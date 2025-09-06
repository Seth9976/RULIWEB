package com.google.android.gms.internal.measurement;

import android.os.RemoteException;
import com.google.android.gms.common.internal.Preconditions;

final class zzdq extends zzdu {
    final zzdw zza;
    final zzef zzb;

    zzdq(zzef zzef0, zzdw zzdw0) {
        this.zzb = zzef0;
        this.zza = zzdw0;
        super(zzef0, true);
    }

    @Override  // com.google.android.gms.internal.measurement.zzdu
    final void zza() throws RemoteException {
        ((zzcc)Preconditions.checkNotNull(zzef.zze(this.zzb))).registerOnMeasurementEventListener(this.zza);
    }
}

