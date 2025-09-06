package com.google.android.gms.internal.measurement;

import android.os.RemoteException;
import com.google.android.gms.common.internal.Preconditions;

final class zzdp extends zzdu {
    final zzdv zza;
    final zzef zzb;

    zzdp(zzef zzef0, zzdv zzdv0) {
        this.zzb = zzef0;
        this.zza = zzdv0;
        super(zzef0, true);
    }

    @Override  // com.google.android.gms.internal.measurement.zzdu
    final void zza() throws RemoteException {
        ((zzcc)Preconditions.checkNotNull(zzef.zze(this.zzb))).setEventInterceptor(this.zza);
    }
}

