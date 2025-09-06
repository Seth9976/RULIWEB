package com.google.android.gms.internal.measurement;

import android.os.Bundle;
import android.os.RemoteException;
import com.google.android.gms.common.internal.Preconditions;

final class zzds extends zzdu {
    final Long zza;
    final String zzb;
    final String zzc;
    final Bundle zzd;
    final boolean zze;
    final boolean zzf;
    final zzef zzg;

    zzds(zzef zzef0, Long long0, String s, String s1, Bundle bundle0, boolean z, boolean z1) {
        this.zzg = zzef0;
        this.zza = long0;
        this.zzb = s;
        this.zzc = s1;
        this.zzd = bundle0;
        this.zze = z;
        this.zzf = z1;
        super(zzef0, true);
    }

    @Override  // com.google.android.gms.internal.measurement.zzdu
    final void zza() throws RemoteException {
        long v = this.zza == null ? this.zzh : ((long)this.zza);
        ((zzcc)Preconditions.checkNotNull(zzef.zze(this.zzg))).logEvent(this.zzb, this.zzc, this.zzd, this.zze, this.zzf, v);
    }
}

