package com.google.android.gms.common.api.internal;

import android.os.IBinder;
import android.os.RemoteException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.internal.base.zaa;

public final class zaby extends zaa implements IStatusCallback {
    zaby(IBinder iBinder0) {
        super(iBinder0, "com.google.android.gms.common.api.internal.IStatusCallback");
    }

    @Override  // com.google.android.gms.common.api.internal.IStatusCallback
    public final void onResult(Status status0) throws RemoteException {
        throw null;
    }
}

