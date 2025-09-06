package com.google.android.gms.measurement.internal;

import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.internal.measurement.zzbm;
import com.google.android.gms.internal.measurement.zzbo;
import java.util.List;

public final class zzeh extends zzbm implements zzej {
    zzeh(IBinder iBinder0) {
        super(iBinder0, "com.google.android.gms.measurement.internal.IMeasurementService");
    }

    @Override  // com.google.android.gms.measurement.internal.zzej
    public final String zzd(zzq zzq0) throws RemoteException {
        Parcel parcel0 = this.zza();
        zzbo.zzd(parcel0, zzq0);
        Parcel parcel1 = this.zzb(11, parcel0);
        String s = parcel1.readString();
        parcel1.recycle();
        return s;
    }

    @Override  // com.google.android.gms.measurement.internal.zzej
    public final List zze(zzq zzq0, boolean z) throws RemoteException {
        Parcel parcel0 = this.zza();
        zzbo.zzd(parcel0, zzq0);
        parcel0.writeInt(((int)z));
        Parcel parcel1 = this.zzb(7, parcel0);
        List list0 = parcel1.createTypedArrayList(zzlk.CREATOR);
        parcel1.recycle();
        return list0;
    }

    @Override  // com.google.android.gms.measurement.internal.zzej
    public final List zzf(String s, String s1, zzq zzq0) throws RemoteException {
        Parcel parcel0 = this.zza();
        parcel0.writeString(s);
        parcel0.writeString(s1);
        zzbo.zzd(parcel0, zzq0);
        Parcel parcel1 = this.zzb(16, parcel0);
        List list0 = parcel1.createTypedArrayList(zzac.CREATOR);
        parcel1.recycle();
        return list0;
    }

    @Override  // com.google.android.gms.measurement.internal.zzej
    public final List zzg(String s, String s1, String s2) throws RemoteException {
        Parcel parcel0 = this.zza();
        parcel0.writeString(null);
        parcel0.writeString(s1);
        parcel0.writeString(s2);
        Parcel parcel1 = this.zzb(17, parcel0);
        List list0 = parcel1.createTypedArrayList(zzac.CREATOR);
        parcel1.recycle();
        return list0;
    }

    @Override  // com.google.android.gms.measurement.internal.zzej
    public final List zzh(String s, String s1, boolean z, zzq zzq0) throws RemoteException {
        Parcel parcel0 = this.zza();
        parcel0.writeString(s);
        parcel0.writeString(s1);
        parcel0.writeInt(((int)z));
        zzbo.zzd(parcel0, zzq0);
        Parcel parcel1 = this.zzb(14, parcel0);
        List list0 = parcel1.createTypedArrayList(zzlk.CREATOR);
        parcel1.recycle();
        return list0;
    }

    @Override  // com.google.android.gms.measurement.internal.zzej
    public final List zzi(String s, String s1, String s2, boolean z) throws RemoteException {
        Parcel parcel0 = this.zza();
        parcel0.writeString(null);
        parcel0.writeString(s1);
        parcel0.writeString(s2);
        parcel0.writeInt(((int)z));
        Parcel parcel1 = this.zzb(15, parcel0);
        List list0 = parcel1.createTypedArrayList(zzlk.CREATOR);
        parcel1.recycle();
        return list0;
    }

    @Override  // com.google.android.gms.measurement.internal.zzej
    public final void zzj(zzq zzq0) throws RemoteException {
        Parcel parcel0 = this.zza();
        zzbo.zzd(parcel0, zzq0);
        this.zzc(4, parcel0);
    }

    @Override  // com.google.android.gms.measurement.internal.zzej
    public final void zzk(zzau zzau0, zzq zzq0) throws RemoteException {
        Parcel parcel0 = this.zza();
        zzbo.zzd(parcel0, zzau0);
        zzbo.zzd(parcel0, zzq0);
        this.zzc(1, parcel0);
    }

    @Override  // com.google.android.gms.measurement.internal.zzej
    public final void zzl(zzau zzau0, String s, String s1) throws RemoteException {
        throw null;
    }

    @Override  // com.google.android.gms.measurement.internal.zzej
    public final void zzm(zzq zzq0) throws RemoteException {
        Parcel parcel0 = this.zza();
        zzbo.zzd(parcel0, zzq0);
        this.zzc(18, parcel0);
    }

    @Override  // com.google.android.gms.measurement.internal.zzej
    public final void zzn(zzac zzac0, zzq zzq0) throws RemoteException {
        Parcel parcel0 = this.zza();
        zzbo.zzd(parcel0, zzac0);
        zzbo.zzd(parcel0, zzq0);
        this.zzc(12, parcel0);
    }

    @Override  // com.google.android.gms.measurement.internal.zzej
    public final void zzo(zzac zzac0) throws RemoteException {
        throw null;
    }

    @Override  // com.google.android.gms.measurement.internal.zzej
    public final void zzp(zzq zzq0) throws RemoteException {
        Parcel parcel0 = this.zza();
        zzbo.zzd(parcel0, zzq0);
        this.zzc(20, parcel0);
    }

    @Override  // com.google.android.gms.measurement.internal.zzej
    public final void zzq(long v, String s, String s1, String s2) throws RemoteException {
        Parcel parcel0 = this.zza();
        parcel0.writeLong(v);
        parcel0.writeString(s);
        parcel0.writeString(s1);
        parcel0.writeString(s2);
        this.zzc(10, parcel0);
    }

    @Override  // com.google.android.gms.measurement.internal.zzej
    public final void zzr(Bundle bundle0, zzq zzq0) throws RemoteException {
        Parcel parcel0 = this.zza();
        zzbo.zzd(parcel0, bundle0);
        zzbo.zzd(parcel0, zzq0);
        this.zzc(19, parcel0);
    }

    @Override  // com.google.android.gms.measurement.internal.zzej
    public final void zzs(zzq zzq0) throws RemoteException {
        Parcel parcel0 = this.zza();
        zzbo.zzd(parcel0, zzq0);
        this.zzc(6, parcel0);
    }

    @Override  // com.google.android.gms.measurement.internal.zzej
    public final void zzt(zzlk zzlk0, zzq zzq0) throws RemoteException {
        Parcel parcel0 = this.zza();
        zzbo.zzd(parcel0, zzlk0);
        zzbo.zzd(parcel0, zzq0);
        this.zzc(2, parcel0);
    }

    @Override  // com.google.android.gms.measurement.internal.zzej
    public final byte[] zzu(zzau zzau0, String s) throws RemoteException {
        Parcel parcel0 = this.zza();
        zzbo.zzd(parcel0, zzau0);
        parcel0.writeString(s);
        Parcel parcel1 = this.zzb(9, parcel0);
        byte[] arr_b = parcel1.createByteArray();
        parcel1.recycle();
        return arr_b;
    }
}

