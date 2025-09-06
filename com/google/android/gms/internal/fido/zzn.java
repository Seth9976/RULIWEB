package com.google.android.gms.internal.fido;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.fido.fido2.api.common.BrowserPublicKeyCredentialCreationOptions;
import com.google.android.gms.fido.fido2.api.common.BrowserPublicKeyCredentialRequestOptions;

public final class zzn extends zza implements IInterface {
    zzn(IBinder iBinder0) {
        super(iBinder0, "com.google.android.gms.fido.fido2.internal.privileged.IFido2PrivilegedService");
    }

    public final void zzc(zzg zzg0, String s) throws RemoteException {
        Parcel parcel0 = this.zza();
        parcel0.writeStrongBinder(((IBinder)zzg0));
        parcel0.writeString(s);
        this.zzb(4, parcel0);
    }

    public final void zzd(zzm zzm0, BrowserPublicKeyCredentialCreationOptions browserPublicKeyCredentialCreationOptions0) throws RemoteException {
        Parcel parcel0 = this.zza();
        parcel0.writeStrongBinder(((IBinder)zzm0));
        zzc.zzd(parcel0, browserPublicKeyCredentialCreationOptions0);
        this.zzb(1, parcel0);
    }

    public final void zze(zzm zzm0, BrowserPublicKeyCredentialRequestOptions browserPublicKeyCredentialRequestOptions0) throws RemoteException {
        Parcel parcel0 = this.zza();
        parcel0.writeStrongBinder(((IBinder)zzm0));
        zzc.zzd(parcel0, browserPublicKeyCredentialRequestOptions0);
        this.zzb(2, parcel0);
    }

    public final void zzf(zze zze0) throws RemoteException {
        Parcel parcel0 = this.zza();
        parcel0.writeStrongBinder(((IBinder)zze0));
        this.zzb(3, parcel0);
    }
}

