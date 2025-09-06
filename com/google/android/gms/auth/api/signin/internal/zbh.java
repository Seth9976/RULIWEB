package com.google.android.gms.auth.api.signin.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.Status;

final class zbh extends zba {
    final zbi zba;

    zbh(zbi zbi0) {
        this.zba = zbi0;
        super();
    }

    @Override  // com.google.android.gms.auth.api.signin.internal.zba
    public final void zbc(Status status0) throws RemoteException {
        this.zba.setResult(status0);
    }
}

