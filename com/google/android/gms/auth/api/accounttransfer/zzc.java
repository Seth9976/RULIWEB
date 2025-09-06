package com.google.android.gms.auth.api.accounttransfer;

import android.os.RemoteException;
import com.google.android.gms.internal.auth.zzau;
import com.google.android.gms.internal.auth.zzaz;

final class zzc extends zzn {
    final zzaz zza;

    zzc(AccountTransferClient accountTransferClient0, int v, zzaz zzaz0) {
        this.zza = zzaz0;
        super(1606);
    }

    @Override  // com.google.android.gms.auth.api.accounttransfer.zzl
    protected final void zza(zzau zzau0) throws RemoteException {
        zzau0.zzh(this.zzc, this.zza);
    }
}

