package com.google.android.gms.auth.account;

import android.accounts.Account;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.internal.auth.zza;

public final class zzc extends zza implements zze {
    zzc(IBinder iBinder0) {
        super(iBinder0, "com.google.android.gms.auth.account.IWorkAccountService");
    }

    @Override  // com.google.android.gms.auth.account.zze
    public final void zzd(zzb zzb0, String s) throws RemoteException {
        Parcel parcel0 = this.zza();
        com.google.android.gms.internal.auth.zzc.zzd(parcel0, zzb0);
        parcel0.writeString(s);
        this.zzc(2, parcel0);
    }

    @Override  // com.google.android.gms.auth.account.zze
    public final void zze(zzb zzb0, Account account0) throws RemoteException {
        Parcel parcel0 = this.zza();
        com.google.android.gms.internal.auth.zzc.zzd(parcel0, zzb0);
        com.google.android.gms.internal.auth.zzc.zzc(parcel0, account0);
        this.zzc(3, parcel0);
    }

    @Override  // com.google.android.gms.auth.account.zze
    public final void zzf(boolean z) throws RemoteException {
        Parcel parcel0 = this.zza();
        parcel0.writeInt(((int)z));
        this.zzc(1, parcel0);
    }
}

