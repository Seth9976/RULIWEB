package com.google.android.gms.internal.measurement;

import android.os.RemoteException;
import com.google.android.gms.common.internal.Preconditions;

final class zzcs extends zzdu {
    final Boolean zza;
    final zzef zzb;

    zzcs(zzef zzef0, Boolean boolean0) {
        this.zzb = zzef0;
        this.zza = boolean0;
        super(zzef0, true);
    }

    @Override  // com.google.android.gms.internal.measurement.zzdu
    final void zza() throws RemoteException {
        if(this.zza != null) {
            ((zzcc)Preconditions.checkNotNull(zzef.zze(this.zzb))).setMeasurementEnabled(this.zza.booleanValue(), this.zzh);
            return;
        }
        ((zzcc)Preconditions.checkNotNull(zzef.zze(this.zzb))).clearMeasurementEnabled(this.zzh);
    }
}

