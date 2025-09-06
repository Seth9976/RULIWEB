package com.google.android.gms.internal.measurement;

import android.os.RemoteException;
import com.google.android.gms.common.internal.Preconditions;

final class zzcz extends zzdu {
    final String zza;
    final zzef zzb;

    zzcz(zzef zzef0, String s) {
        this.zzb = zzef0;
        this.zza = s;
        super(zzef0, true);
    }

    @Override  // com.google.android.gms.internal.measurement.zzdu
    final void zza() throws RemoteException {
        ((zzcc)Preconditions.checkNotNull(zzef.zze(this.zzb))).endAdUnitExposure(this.zza, this.zzi);
    }
}

