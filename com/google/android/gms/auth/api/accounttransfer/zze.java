package com.google.android.gms.auth.api.accounttransfer;

import android.os.RemoteException;
import com.google.android.gms.internal.auth.zzau;
import com.google.android.gms.internal.auth.zzax;

final class zze extends zzl {
    final zzax zza;

    zze(AccountTransferClient accountTransferClient0, int v, zzax zzax0) {
        this.zza = zzax0;
        super(1607, null);
    }

    @Override  // com.google.android.gms.auth.api.accounttransfer.zzl
    protected final void zza(zzau zzau0) throws RemoteException {
        zzau0.zzg(new zzd(this, this), this.zza);
    }
}

