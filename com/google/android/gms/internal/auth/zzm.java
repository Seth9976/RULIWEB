package com.google.android.gms.internal.auth;

import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.auth.AccountChangeEventsResponse;
import com.google.android.gms.common.api.Status;

public interface zzm extends IInterface {
    void zzb(Status arg1, AccountChangeEventsResponse arg2) throws RemoteException;
}

