package com.google.android.gms.auth.api.signin.internal;

import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.common.api.Status;

public interface zbr extends IInterface {
    void zbb(Status arg1) throws RemoteException;

    void zbc(Status arg1) throws RemoteException;

    void zbd(GoogleSignInAccount arg1, Status arg2) throws RemoteException;
}

