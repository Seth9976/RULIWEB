package com.google.android.gms.internal.auth;

import android.accounts.Account;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.auth.AccountChangeEventsRequest;
import com.google.android.gms.auth.AccountChangeEventsResponse;

public final class zzd extends zza implements zzf {
    zzd(IBinder iBinder0) {
        super(iBinder0, "com.google.android.auth.IAuthManagerService");
    }

    @Override  // com.google.android.gms.internal.auth.zzf
    public final Bundle zzd(String s, Bundle bundle0) throws RemoteException {
        Parcel parcel0 = this.zza();
        parcel0.writeString(s);
        zzc.zzc(parcel0, bundle0);
        Parcel parcel1 = this.zzb(2, parcel0);
        Bundle bundle1 = (Bundle)zzc.zza(parcel1, Bundle.CREATOR);
        parcel1.recycle();
        return bundle1;
    }

    @Override  // com.google.android.gms.internal.auth.zzf
    public final Bundle zze(Account account0, String s, Bundle bundle0) throws RemoteException {
        Parcel parcel0 = this.zza();
        zzc.zzc(parcel0, account0);
        parcel0.writeString(s);
        zzc.zzc(parcel0, bundle0);
        Parcel parcel1 = this.zzb(5, parcel0);
        Bundle bundle1 = (Bundle)zzc.zza(parcel1, Bundle.CREATOR);
        parcel1.recycle();
        return bundle1;
    }

    @Override  // com.google.android.gms.internal.auth.zzf
    public final Bundle zzf(Account account0) throws RemoteException {
        Parcel parcel0 = this.zza();
        zzc.zzc(parcel0, account0);
        Parcel parcel1 = this.zzb(7, parcel0);
        Bundle bundle0 = (Bundle)zzc.zza(parcel1, Bundle.CREATOR);
        parcel1.recycle();
        return bundle0;
    }

    @Override  // com.google.android.gms.internal.auth.zzf
    public final Bundle zzg(String s) throws RemoteException {
        Parcel parcel0 = this.zza();
        parcel0.writeString(s);
        Parcel parcel1 = this.zzb(8, parcel0);
        Bundle bundle0 = (Bundle)zzc.zza(parcel1, Bundle.CREATOR);
        parcel1.recycle();
        return bundle0;
    }

    @Override  // com.google.android.gms.internal.auth.zzf
    public final AccountChangeEventsResponse zzh(AccountChangeEventsRequest accountChangeEventsRequest0) throws RemoteException {
        Parcel parcel0 = this.zza();
        zzc.zzc(parcel0, accountChangeEventsRequest0);
        Parcel parcel1 = this.zzb(3, parcel0);
        AccountChangeEventsResponse accountChangeEventsResponse0 = (AccountChangeEventsResponse)zzc.zza(parcel1, AccountChangeEventsResponse.CREATOR);
        parcel1.recycle();
        return accountChangeEventsResponse0;
    }
}

