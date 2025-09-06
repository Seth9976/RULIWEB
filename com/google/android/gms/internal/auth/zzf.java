package com.google.android.gms.internal.auth;

import android.accounts.Account;
import android.os.Bundle;
import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.auth.AccountChangeEventsRequest;
import com.google.android.gms.auth.AccountChangeEventsResponse;
import javax.annotation.Nullable;

public interface zzf extends IInterface {
    Bundle zzd(String arg1, Bundle arg2) throws RemoteException;

    @Nullable
    Bundle zze(Account arg1, String arg2, Bundle arg3) throws RemoteException;

    @Nullable
    Bundle zzf(Account arg1) throws RemoteException;

    Bundle zzg(String arg1) throws RemoteException;

    AccountChangeEventsResponse zzh(AccountChangeEventsRequest arg1) throws RemoteException;
}

