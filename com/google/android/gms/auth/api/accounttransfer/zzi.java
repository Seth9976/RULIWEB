package com.google.android.gms.auth.api.accounttransfer;

import android.os.RemoteException;
import com.google.android.gms.internal.auth.zzau;
import com.google.android.gms.internal.auth.zzav;

final class zzi extends zzn {
    final zzav zza;

    zzi(AccountTransferClient accountTransferClient0, int v, zzav zzav0) {
        this.zza = zzav0;
        super(1610);
    }

    @Override  // com.google.android.gms.auth.api.accounttransfer.zzl
    protected final void zza(zzau zzau0) throws RemoteException {
        zzau0.zzf(this.zzc, this.zza);
    }
}

