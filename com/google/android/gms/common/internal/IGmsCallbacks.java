package com.google.android.gms.common.internal;

import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;

public interface IGmsCallbacks extends IInterface {
    void onPostInitComplete(int arg1, IBinder arg2, Bundle arg3) throws RemoteException;

    void zzb(int arg1, Bundle arg2) throws RemoteException;

    void zzc(int arg1, IBinder arg2, zzk arg3) throws RemoteException;
}

