package com.google.android.gms.internal.auth-api-phone;

import android.os.BadParcelableException;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.os.Parcelable;

public final class zzc {
    private static final ClassLoader zza;

    static {
        zzc.zza = zzc.class.getClassLoader();
    }

    public static Parcelable zza(Parcel parcel0, Parcelable.Creator parcelable$Creator0) {
        return parcel0.readInt() == 0 ? null : ((Parcelable)parcelable$Creator0.createFromParcel(parcel0));
    }

    public static void zzb(Parcel parcel0) {
        int v = parcel0.dataAvail();
        if(v > 0) {
            throw new BadParcelableException("Parcel data not fully consumed, unread size: " + v);
        }
    }

    public static void zzc(Parcel parcel0, IInterface iInterface0) {
        parcel0.writeStrongBinder(iInterface0.asBinder());
    }
}

