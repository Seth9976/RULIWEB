package com.google.android.gms.common.internal;

import android.os.IBinder;
import android.os.RemoteException;
import com.google.android.gms.internal.common.zza;

public final class zzx extends zza implements ICancelToken {
    zzx(IBinder iBinder0) {
        super(iBinder0, "com.google.android.gms.common.internal.ICancelToken");
    }

    @Override  // com.google.android.gms.common.internal.ICancelToken
    public final void cancel() throws RemoteException {
        this.zzD(2, this.zza());
    }
}

