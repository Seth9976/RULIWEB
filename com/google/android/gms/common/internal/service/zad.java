package com.google.android.gms.common.internal.service;

import android.os.RemoteException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.BaseImplementation.ResultHolder;

final class zad extends zaa {
    private final ResultHolder zaa;

    public zad(ResultHolder baseImplementation$ResultHolder0) {
        this.zaa = baseImplementation$ResultHolder0;
    }

    @Override  // com.google.android.gms.common.internal.service.zaa
    public final void zab(int v) throws RemoteException {
        Status status0 = new Status(v);
        this.zaa.setResult(status0);
    }
}

