package com.google.android.gms.dynamite;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper.Stub;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.internal.common.zza;
import com.google.android.gms.internal.common.zzc;

public final class zzq extends zza implements IInterface {
    zzq(IBinder iBinder0) {
        super(iBinder0, "com.google.android.gms.dynamite.IDynamiteLoader");
    }

    public final int zze() throws RemoteException {
        Parcel parcel0 = this.zzB(6, this.zza());
        int v = parcel0.readInt();
        parcel0.recycle();
        return v;
    }

    public final int zzf(IObjectWrapper iObjectWrapper0, String s, boolean z) throws RemoteException {
        Parcel parcel0 = this.zza();
        zzc.zze(parcel0, iObjectWrapper0);
        parcel0.writeString(s);
        parcel0.writeInt(((int)z));
        Parcel parcel1 = this.zzB(3, parcel0);
        int v = parcel1.readInt();
        parcel1.recycle();
        return v;
    }

    public final int zzg(IObjectWrapper iObjectWrapper0, String s, boolean z) throws RemoteException {
        Parcel parcel0 = this.zza();
        zzc.zze(parcel0, iObjectWrapper0);
        parcel0.writeString(s);
        parcel0.writeInt(((int)z));
        Parcel parcel1 = this.zzB(5, parcel0);
        int v = parcel1.readInt();
        parcel1.recycle();
        return v;
    }

    public final IObjectWrapper zzh(IObjectWrapper iObjectWrapper0, String s, int v) throws RemoteException {
        Parcel parcel0 = this.zza();
        zzc.zze(parcel0, iObjectWrapper0);
        parcel0.writeString(s);
        parcel0.writeInt(v);
        Parcel parcel1 = this.zzB(2, parcel0);
        IObjectWrapper iObjectWrapper1 = Stub.asInterface(parcel1.readStrongBinder());
        parcel1.recycle();
        return iObjectWrapper1;
    }

    public final IObjectWrapper zzi(IObjectWrapper iObjectWrapper0, String s, int v, IObjectWrapper iObjectWrapper1) throws RemoteException {
        Parcel parcel0 = this.zza();
        zzc.zze(parcel0, iObjectWrapper0);
        parcel0.writeString(s);
        parcel0.writeInt(v);
        zzc.zze(parcel0, iObjectWrapper1);
        Parcel parcel1 = this.zzB(8, parcel0);
        IObjectWrapper iObjectWrapper2 = Stub.asInterface(parcel1.readStrongBinder());
        parcel1.recycle();
        return iObjectWrapper2;
    }

    public final IObjectWrapper zzj(IObjectWrapper iObjectWrapper0, String s, int v) throws RemoteException {
        Parcel parcel0 = this.zza();
        zzc.zze(parcel0, iObjectWrapper0);
        parcel0.writeString(s);
        parcel0.writeInt(v);
        Parcel parcel1 = this.zzB(4, parcel0);
        IObjectWrapper iObjectWrapper1 = Stub.asInterface(parcel1.readStrongBinder());
        parcel1.recycle();
        return iObjectWrapper1;
    }

    public final IObjectWrapper zzk(IObjectWrapper iObjectWrapper0, String s, boolean z, long v) throws RemoteException {
        Parcel parcel0 = this.zza();
        zzc.zze(parcel0, iObjectWrapper0);
        parcel0.writeString(s);
        parcel0.writeInt(((int)z));
        parcel0.writeLong(v);
        Parcel parcel1 = this.zzB(7, parcel0);
        IObjectWrapper iObjectWrapper1 = Stub.asInterface(parcel1.readStrongBinder());
        parcel1.recycle();
        return iObjectWrapper1;
    }
}

