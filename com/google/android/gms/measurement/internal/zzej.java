package com.google.android.gms.measurement.internal;

import android.os.Bundle;
import android.os.IInterface;
import android.os.RemoteException;
import java.util.List;

public interface zzej extends IInterface {
    String zzd(zzq arg1) throws RemoteException;

    List zze(zzq arg1, boolean arg2) throws RemoteException;

    List zzf(String arg1, String arg2, zzq arg3) throws RemoteException;

    List zzg(String arg1, String arg2, String arg3) throws RemoteException;

    List zzh(String arg1, String arg2, boolean arg3, zzq arg4) throws RemoteException;

    List zzi(String arg1, String arg2, String arg3, boolean arg4) throws RemoteException;

    void zzj(zzq arg1) throws RemoteException;

    void zzk(zzau arg1, zzq arg2) throws RemoteException;

    void zzl(zzau arg1, String arg2, String arg3) throws RemoteException;

    void zzm(zzq arg1) throws RemoteException;

    void zzn(zzac arg1, zzq arg2) throws RemoteException;

    void zzo(zzac arg1) throws RemoteException;

    void zzp(zzq arg1) throws RemoteException;

    void zzq(long arg1, String arg2, String arg3, String arg4) throws RemoteException;

    void zzr(Bundle arg1, zzq arg2) throws RemoteException;

    void zzs(zzq arg1) throws RemoteException;

    void zzt(zzlk arg1, zzq arg2) throws RemoteException;

    byte[] zzu(zzau arg1, String arg2) throws RemoteException;
}

