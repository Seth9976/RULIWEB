package com.google.android.gms.internal.base;

import android.os.BadParcelableException;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.os.Parcelable;

public final class zac {
    private static final ClassLoader zaa;

    static {
        zac.zaa = zac.class.getClassLoader();
    }

    public static Parcelable zaa(Parcel parcel0, Parcelable.Creator parcelable$Creator0) {
        return parcel0.readInt() == 0 ? null : ((Parcelable)parcelable$Creator0.createFromParcel(parcel0));
    }

    public static void zab(Parcel parcel0) {
        int v = parcel0.dataAvail();
        if(v > 0) {
            throw new BadParcelableException("Parcel data not fully consumed, unread size: " + v);
        }
    }

    public static void zac(Parcel parcel0, Parcelable parcelable0) {
        if(parcelable0 == null) {
            parcel0.writeInt(0);
            return;
        }
        parcel0.writeInt(1);
        parcelable0.writeToParcel(parcel0, 0);
    }

    public static void zad(Parcel parcel0, IInterface iInterface0) {
        if(iInterface0 == null) {
            parcel0.writeStrongBinder(null);
            return;
        }
        parcel0.writeStrongBinder(iInterface0.asBinder());
    }
}

