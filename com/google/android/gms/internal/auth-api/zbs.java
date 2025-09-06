package com.google.android.gms.internal.auth-api;

import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.auth.api.identity.SaveAccountLinkingTokenResult;
import com.google.android.gms.common.api.Status;

public interface zbs extends IInterface {
    void zbb(Status arg1, SaveAccountLinkingTokenResult arg2) throws RemoteException;
}

