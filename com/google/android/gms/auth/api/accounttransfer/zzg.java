package com.google.android.gms.auth.api.accounttransfer;

import android.os.RemoteException;
import com.google.android.gms.internal.auth.zzaq;
import com.google.android.gms.internal.auth.zzau;

final class zzg extends zzl {
    final zzaq zza;

    zzg(AccountTransferClient accountTransferClient0, int v, zzaq zzaq0) {
        this.zza = zzaq0;
        super(1608, null);
    }

    @Override  // com.google.android.gms.auth.api.accounttransfer.zzl
    protected final void zza(zzau zzau0) throws RemoteException {
        zzau0.zzd(new zzf(this, this), this.zza);
    }
}

