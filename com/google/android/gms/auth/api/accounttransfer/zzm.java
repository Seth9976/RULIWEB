package com.google.android.gms.auth.api.accounttransfer;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.internal.auth.zzan;

final class zzm extends zzan {
    final zzn zza;

    zzm(zzn zzn0) {
        this.zza = zzn0;
        super();
    }

    @Override  // com.google.android.gms.internal.auth.zzan
    public final void zzd(Status status0) {
        this.zza.zzb.setException(new AccountTransferException(status0));
    }

    @Override  // com.google.android.gms.internal.auth.zzan
    public final void zze() {
        this.zza.zzb.setResult(null);
    }
}

