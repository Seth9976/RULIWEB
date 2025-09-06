package com.google.android.gms.fido.common;

import android.os.Parcel;
import android.os.Parcelable.Creator;

final class zza implements Parcelable.Creator {
    @Override  // android.os.Parcelable$Creator
    public final Object createFromParcel(Parcel parcel0) {
        String s = parcel0.readString();
        try {
            return Transport.fromString(s);
        }
        catch(UnsupportedTransportException transport$UnsupportedTransportException0) {
            throw new RuntimeException(transport$UnsupportedTransportException0);
        }
    }

    @Override  // android.os.Parcelable$Creator
    public final Object[] newArray(int v) {
        return new Transport[v];
    }
}

