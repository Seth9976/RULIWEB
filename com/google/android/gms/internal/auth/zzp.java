package com.google.android.gms.internal.auth;

import android.accounts.Account;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.auth.AccountChangeEventsRequest;
import com.google.android.gms.common.api.internal.IStatusCallback;

public final class zzp extends zza implements IInterface {
    zzp(IBinder iBinder0) {
        super(iBinder0, "com.google.android.gms.auth.account.data.IGoogleAuthService");
    }

    public final void zzd(IStatusCallback iStatusCallback0, zzbw zzbw0) throws RemoteException {
        Parcel parcel0 = this.zza();
        zzc.zzd(parcel0, iStatusCallback0);
        zzc.zzc(parcel0, zzbw0);
        this.zzc(2, parcel0);
    }

    public final void zze(zzm zzm0, AccountChangeEventsRequest accountChangeEventsRequest0) throws RemoteException {
        Parcel parcel0 = this.zza();
        zzc.zzd(parcel0, zzm0);
        zzc.zzc(parcel0, accountChangeEventsRequest0);
        this.zzc(4, parcel0);
    }

    public final void zzf(zzo zzo0, Account account0, String s, Bundle bundle0) throws RemoteException {
        Parcel parcel0 = this.zza();
        zzc.zzd(parcel0, zzo0);
        zzc.zzc(parcel0, account0);
        parcel0.writeString(s);
        zzc.zzc(parcel0, bundle0);
        this.zzc(1, parcel0);
    }

    public final void zzg(zzk zzk0, Account account0) throws RemoteException {
        Parcel parcel0 = this.zza();
        zzc.zzd(parcel0, zzk0);
        zzc.zzc(parcel0, account0);
        this.zzc(6, parcel0);
    }

    public final void zzh(zzk zzk0, String s) throws RemoteException {
        Parcel parcel0 = this.zza();
        zzc.zzd(parcel0, zzk0);
        parcel0.writeString(s);
        this.zzc(3, parcel0);
    }
}

