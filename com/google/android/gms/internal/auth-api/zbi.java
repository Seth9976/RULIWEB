package com.google.android.gms.internal.auth-api;

import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.auth.api.identity.AuthorizationResult;
import com.google.android.gms.common.api.Status;

public interface zbi extends IInterface {
    void zbb(Status arg1, AuthorizationResult arg2) throws RemoteException;
}

