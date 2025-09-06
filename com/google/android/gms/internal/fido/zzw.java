package com.google.android.gms.internal.fido;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.fido.u2f.api.common.RegisterRequestParams;
import com.google.android.gms.fido.u2f.api.common.SignRequestParams;

public final class zzw extends zza implements IInterface {
    zzw(IBinder iBinder0) {
        super(iBinder0, "com.google.android.gms.fido.u2f.internal.regular.IU2fAppService");
    }

    public final void zzc(zzv zzv0, RegisterRequestParams registerRequestParams0) throws RemoteException {
        Parcel parcel0 = this.zza();
        parcel0.writeStrongBinder(((IBinder)zzv0));
        zzc.zzd(parcel0, registerRequestParams0);
        this.zzb(1, parcel0);
    }

    public final void zzd(zzv zzv0, SignRequestParams signRequestParams0) throws RemoteException {
        Parcel parcel0 = this.zza();
        parcel0.writeStrongBinder(((IBinder)zzv0));
        zzc.zzd(parcel0, signRequestParams0);
        this.zzb(2, parcel0);
    }
}

