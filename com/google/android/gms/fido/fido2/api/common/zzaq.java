package com.google.android.gms.fido.fido2.api.common;

import android.os.Parcel;
import android.os.Parcelable.Creator;

final class zzaq implements Parcelable.Creator {
    @Override  // android.os.Parcelable$Creator
    public final Object createFromParcel(Parcel parcel0) {
        String s = parcel0.readString();
        try {
            return PublicKeyCredentialType.fromString(s);
        }
        catch(UnsupportedPublicKeyCredTypeException publicKeyCredentialType$UnsupportedPublicKeyCredTypeException0) {
            throw new RuntimeException(publicKeyCredentialType$UnsupportedPublicKeyCredTypeException0);
        }
    }

    @Override  // android.os.Parcelable$Creator
    public final Object[] newArray(int v) {
        return new PublicKeyCredentialType[v];
    }
}

