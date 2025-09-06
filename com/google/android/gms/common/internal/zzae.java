package com.google.android.gms.common.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.common.zzo;
import com.google.android.gms.common.zzq;
import com.google.android.gms.common.zzs;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.internal.common.zza;
import com.google.android.gms.internal.common.zzc;

public final class zzae extends zza implements zzag {
    zzae(IBinder iBinder0) {
        super(iBinder0, "com.google.android.gms.common.internal.IGoogleCertificatesApi");
    }

    @Override  // com.google.android.gms.common.internal.zzag
    public final zzq zze(zzo zzo0) throws RemoteException {
        Parcel parcel0 = this.zza();
        zzc.zzc(parcel0, zzo0);
        Parcel parcel1 = this.zzB(6, parcel0);
        zzq zzq0 = (zzq)zzc.zza(parcel1, zzq.CREATOR);
        parcel1.recycle();
        return zzq0;
    }

    @Override  // com.google.android.gms.common.internal.zzag
    public final zzq zzf(zzo zzo0) throws RemoteException {
        Parcel parcel0 = this.zza();
        zzc.zzc(parcel0, zzo0);
        Parcel parcel1 = this.zzB(8, parcel0);
        zzq zzq0 = (zzq)zzc.zza(parcel1, zzq.CREATOR);
        parcel1.recycle();
        return zzq0;
    }

    @Override  // com.google.android.gms.common.internal.zzag
    public final boolean zzg() throws RemoteException {
        Parcel parcel0 = this.zzB(9, this.zza());
        boolean z = zzc.zzf(parcel0);
        parcel0.recycle();
        return z;
    }

    @Override  // com.google.android.gms.common.internal.zzag
    public final boolean zzh(zzs zzs0, IObjectWrapper iObjectWrapper0) throws RemoteException {
        Parcel parcel0 = this.zza();
        zzc.zzc(parcel0, zzs0);
        zzc.zze(parcel0, iObjectWrapper0);
        Parcel parcel1 = this.zzB(5, parcel0);
        boolean z = zzc.zzf(parcel1);
        parcel1.recycle();
        return z;
    }

    @Override  // com.google.android.gms.common.internal.zzag
    public final boolean zzi() throws RemoteException {
        Parcel parcel0 = this.zzB(7, this.zza());
        boolean z = zzc.zzf(parcel0);
        parcel0.recycle();
        return z;
    }
}

