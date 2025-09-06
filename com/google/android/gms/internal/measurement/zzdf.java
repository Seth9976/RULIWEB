package com.google.android.gms.internal.measurement;

import android.os.RemoteException;
import com.google.android.gms.common.internal.Preconditions;

final class zzdf extends zzdu {
    final String zza;
    final String zzb;
    final boolean zzc;
    final zzbz zzd;
    final zzef zze;

    zzdf(zzef zzef0, String s, String s1, boolean z, zzbz zzbz0) {
        this.zze = zzef0;
        this.zza = s;
        this.zzb = s1;
        this.zzc = z;
        this.zzd = zzbz0;
        super(zzef0, true);
    }

    @Override  // com.google.android.gms.internal.measurement.zzdu
    final void zza() throws RemoteException {
        ((zzcc)Preconditions.checkNotNull(zzef.zze(this.zze))).getUserProperties(this.zza, this.zzb, this.zzc, this.zzd);
    }

    @Override  // com.google.android.gms.internal.measurement.zzdu
    protected final void zzb() {
        this.zzd.zze(null);
    }
}

