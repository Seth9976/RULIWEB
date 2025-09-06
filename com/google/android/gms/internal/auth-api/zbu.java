package com.google.android.gms.internal.auth-api;

import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.auth.api.identity.SavePasswordResult;
import com.google.android.gms.common.api.Status;

public interface zbu extends IInterface {
    void zbb(Status arg1, SavePasswordResult arg2) throws RemoteException;
}

