package com.google.android.gms.fido.u2f.api.common;

import android.os.Parcel;
import android.os.Parcelable.Creator;

final class zzf implements Parcelable.Creator {
    @Override  // android.os.Parcelable$Creator
    public final Object createFromParcel(Parcel parcel0) {
        String s = parcel0.readString();
        try {
            return ProtocolVersion.fromString(s);
        }
        catch(UnsupportedProtocolException protocolVersion$UnsupportedProtocolException0) {
            throw new RuntimeException(protocolVersion$UnsupportedProtocolException0);
        }
    }

    @Override  // android.os.Parcelable$Creator
    public final Object[] newArray(int v) {
        return new ProtocolVersion[v];
    }
}

