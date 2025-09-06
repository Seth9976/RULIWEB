package com.google.android.gms.internal.auth;

import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.auth.api.accounttransfer.DeviceMetaData;
import com.google.android.gms.auth.api.accounttransfer.zzo;
import com.google.android.gms.auth.api.accounttransfer.zzw;
import com.google.android.gms.common.api.Status;

public interface zzat extends IInterface {
    void zzb(byte[] arg1) throws RemoteException;

    void zzc(DeviceMetaData arg1) throws RemoteException;

    void zzd(Status arg1) throws RemoteException;

    void zze() throws RemoteException;

    void zzf(Status arg1, zzw arg2) throws RemoteException;

    void zzg(Status arg1, zzo arg2) throws RemoteException;

    void zzh(Status arg1) throws RemoteException;
}

