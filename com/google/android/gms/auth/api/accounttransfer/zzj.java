package com.google.android.gms.auth.api.accounttransfer;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.internal.auth.zzan;

class zzj extends zzan {
    final zzl zzb;

    public zzj(zzl zzl0) {
        this.zzb = zzl0;
    }

    @Override  // com.google.android.gms.internal.auth.zzan
    public final void zzd(Status status0) {
        this.zzb.zzb.setException(new AccountTransferException(status0));
    }
}

