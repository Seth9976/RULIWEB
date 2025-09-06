package com.google.android.gms.internal.auth;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public final class zzau extends zza implements IInterface {
    zzau(IBinder iBinder0) {
        super(iBinder0, "com.google.android.gms.auth.api.accounttransfer.internal.IAccountTransferService");
    }

    public final void zzd(zzat zzat0, zzaq zzaq0) throws RemoteException {
        Parcel parcel0 = this.zza();
        zzc.zzd(parcel0, zzat0);
        zzc.zzc(parcel0, zzaq0);
        this.zzc(7, parcel0);
    }

    public final void zze(zzat zzat0, zzbb zzbb0) throws RemoteException {
        Parcel parcel0 = this.zza();
        zzc.zzd(parcel0, zzat0);
        zzc.zzc(parcel0, zzbb0);
        this.zzc(8, parcel0);
    }

    public final void zzf(zzat zzat0, zzav zzav0) throws RemoteException {
        Parcel parcel0 = this.zza();
        zzc.zzd(parcel0, zzat0);
        zzc.zzc(parcel0, zzav0);
        this.zzc(9, parcel0);
    }

    public final void zzg(zzat zzat0, zzax zzax0) throws RemoteException {
        Parcel parcel0 = this.zza();
        zzc.zzd(parcel0, zzat0);
        zzc.zzc(parcel0, zzax0);
        this.zzc(6, parcel0);
    }

    public final void zzh(zzat zzat0, zzaz zzaz0) throws RemoteException {
        Parcel parcel0 = this.zza();
        zzc.zzd(parcel0, zzat0);
        zzc.zzc(parcel0, zzaz0);
        this.zzc(5, parcel0);
    }
}

