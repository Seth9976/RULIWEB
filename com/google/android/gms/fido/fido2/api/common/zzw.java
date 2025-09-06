package com.google.android.gms.fido.fido2.api.common;

import android.os.Parcel;
import android.os.Parcelable.Creator;

final class zzw implements Parcelable.Creator {
    @Override  // android.os.Parcelable$Creator
    public final Object createFromParcel(Parcel parcel0) {
        int v = parcel0.readInt();
        try {
            return ErrorCode.toErrorCode(v);
        }
        catch(UnsupportedErrorCodeException errorCode$UnsupportedErrorCodeException0) {
            throw new IllegalArgumentException(errorCode$UnsupportedErrorCodeException0);
        }
    }

    @Override  // android.os.Parcelable$Creator
    public final Object[] newArray(int v) {
        return new ErrorCode[v];
    }
}

