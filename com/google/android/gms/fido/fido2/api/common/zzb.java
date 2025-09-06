package com.google.android.gms.fido.fido2.api.common;

import android.os.Parcel;
import android.os.Parcelable.Creator;

final class zzb implements Parcelable.Creator {
    @Override  // android.os.Parcelable$Creator
    public final Object createFromParcel(Parcel parcel0) {
        String s = parcel0.readString();
        try {
            return AttestationConveyancePreference.fromString(s);
        }
        catch(UnsupportedAttestationConveyancePreferenceException attestationConveyancePreference$UnsupportedAttestationConveyancePreferenceException0) {
            throw new RuntimeException(attestationConveyancePreference$UnsupportedAttestationConveyancePreferenceException0);
        }
    }

    @Override  // android.os.Parcelable$Creator
    public final Object[] newArray(int v) {
        return new AttestationConveyancePreference[v];
    }
}

