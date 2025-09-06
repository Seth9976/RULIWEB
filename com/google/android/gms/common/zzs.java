package com.google.android.gms.common;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.zzz;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import javax.annotation.Nullable;

public final class zzs extends AbstractSafeParcelable {
    public static final Parcelable.Creator CREATOR;
    private final String zza;
    @Nullable
    private final zzj zzb;
    private final boolean zzc;
    private final boolean zzd;

    static {
        zzs.CREATOR = new zzt();
    }

    zzs(String s, @Nullable IBinder iBinder0, boolean z, boolean z1) {
        IObjectWrapper iObjectWrapper0;
        this.zza = s;
        zzj zzj0 = null;
        if(iBinder0 != null) {
            try {
                iObjectWrapper0 = zzz.zzg(iBinder0).zzd();
            }
            catch(RemoteException remoteException0) {
                Log.e("GoogleCertificatesQuery", "Could not unwrap certificate", remoteException0);
                this.zzb = zzj0;
                this.zzc = z;
                this.zzd = z1;
                return;
            }
            byte[] arr_b = iObjectWrapper0 == null ? null : ((byte[])ObjectWrapper.unwrap(iObjectWrapper0));
            if(arr_b == null) {
                Log.e("GoogleCertificatesQuery", "Could not unwrap certificate");
            }
            else {
                zzj0 = new zzk(arr_b);
            }
        }
        this.zzb = zzj0;
        this.zzc = z;
        this.zzd = z1;
    }

    zzs(String s, @Nullable zzj zzj0, boolean z, boolean z1) {
        this.zza = s;
        this.zzb = zzj0;
        this.zzc = z;
        this.zzd = z1;
    }

    @Override  // android.os.Parcelable
    public final void writeToParcel(Parcel parcel0, int v) {
        int v1 = SafeParcelWriter.beginObjectHeader(parcel0);
        SafeParcelWriter.writeString(parcel0, 1, this.zza, false);
        zzj zzj0 = this.zzb;
        if(zzj0 == null) {
            Log.w("GoogleCertificatesQuery", "certificate binder is null");
            zzj0 = null;
        }
        SafeParcelWriter.writeIBinder(parcel0, 2, zzj0, false);
        SafeParcelWriter.writeBoolean(parcel0, 3, this.zzc);
        SafeParcelWriter.writeBoolean(parcel0, 4, this.zzd);
        SafeParcelWriter.finishObjectHeader(parcel0, v1);
    }
}

