package com.google.android.gms.fido.u2f.api.common;

import android.os.Parcel;
import android.os.Parcelable.Creator;

final class zza implements Parcelable.Creator {
    @Override  // android.os.Parcelable$Creator
    public final Object createFromParcel(Parcel parcel0) {
        int v = parcel0.readInt();
        try {
            return ChannelIdValue.toChannelIdValueType(v);
        }
        catch(UnsupportedChannelIdValueTypeException channelIdValue$UnsupportedChannelIdValueTypeException0) {
            throw new RuntimeException(channelIdValue$UnsupportedChannelIdValueTypeException0);
        }
    }

    @Override  // android.os.Parcelable$Creator
    public final Object[] newArray(int v) {
        return new ChannelIdValueType[v];
    }
}

