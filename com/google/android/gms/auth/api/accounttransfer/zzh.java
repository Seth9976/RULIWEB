package com.google.android.gms.auth.api.accounttransfer;

import android.os.RemoteException;
import com.google.android.gms.internal.auth.zzau;
import com.google.android.gms.internal.auth.zzbb;

final class zzh extends zzn {
    final zzbb zza;

    zzh(AccountTransferClient accountTransferClient0, int v, zzbb zzbb0) {
        this.zza = zzbb0;
        super(1609);
    }

    @Override  // com.google.android.gms.auth.api.accounttransfer.zzl
    protected final void zza(zzau zzau0) throws RemoteException {
        zzau0.zze(this.zzc, this.zza);
    }
}

