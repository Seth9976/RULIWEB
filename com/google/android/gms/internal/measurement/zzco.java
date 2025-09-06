package com.google.android.gms.internal.measurement;

import android.os.Bundle;
import android.os.RemoteException;
import com.google.android.gms.common.internal.Preconditions;

final class zzco extends zzdu {
    final String zza;
    final String zzb;
    final Bundle zzc;
    final zzef zzd;

    zzco(zzef zzef0, String s, String s1, Bundle bundle0) {
        this.zzd = zzef0;
        this.zza = s;
        this.zzb = s1;
        this.zzc = bundle0;
        super(zzef0, true);
    }

    @Override  // com.google.android.gms.internal.measurement.zzdu
    final void zza() throws RemoteException {
        ((zzcc)Preconditions.checkNotNull(zzef.zze(this.zzd))).clearConditionalUserProperty(this.zza, this.zzb, this.zzc);
    }
}

