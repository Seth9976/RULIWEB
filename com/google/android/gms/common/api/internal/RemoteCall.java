package com.google.android.gms.common.api.internal;

import android.os.RemoteException;

public interface RemoteCall {
    void accept(Object arg1, Object arg2) throws RemoteException;
}

