package com.google.android.gms.internal.auth-api;

import android.os.BadParcelableException;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.os.Parcelable;

public final class zbc {
    static {
    }

    public static Parcelable zba(Parcel parcel0, Parcelable.Creator parcelable$Creator0) {
        return parcel0.readInt() == 0 ? null : ((Parcelable)parcelable$Creator0.createFromParcel(parcel0));
    }

    public static void zbb(Parcel parcel0) {
        int v = parcel0.dataAvail();
        if(v > 0) {
            throw new BadParcelableException("Parcel data not fully consumed, unread size: " + v);
        }
    }

    public static void zbc(Parcel parcel0, Parcelable parcelable0) {
        if(parcelable0 == null) {
            parcel0.writeInt(0);
            return;
        }
        parcel0.writeInt(1);
        parcelable0.writeToParcel(parcel0, 0);
    }

    public static void zbd(Parcel parcel0, IInterface iInterface0) {
        parcel0.writeStrongBinder(iInterface0.asBinder());
    }
}

