package com.google.android.gms.fido.fido2.api.common;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

public final class zzj implements Parcelable.Creator {
    @Override  // android.os.Parcelable$Creator
    public final Object createFromParcel(Parcel parcel0) {
        int v = SafeParcelReader.validateObjectHeader(parcel0);
        byte[] arr_b = null;
        byte[] arr_b1 = null;
        byte[] arr_b2 = null;
        byte[] arr_b3 = null;
        byte[] arr_b4 = null;
        while(parcel0.dataPosition() < v) {
            int v1 = SafeParcelReader.readHeader(parcel0);
            switch(((char)v1)) {
                case 2: {
                    arr_b = SafeParcelReader.createByteArray(parcel0, v1);
                    break;
                }
                case 3: {
                    arr_b1 = SafeParcelReader.createByteArray(parcel0, v1);
                    break;
                }
                case 4: {
                    arr_b2 = SafeParcelReader.createByteArray(parcel0, v1);
                    break;
                }
                case 5: {
                    arr_b3 = SafeParcelReader.createByteArray(parcel0, v1);
                    break;
                }
                case 6: {
                    arr_b4 = SafeParcelReader.createByteArray(parcel0, v1);
                    break;
                }
                default: {
                    SafeParcelReader.skipUnknownField(parcel0, v1);
                }
            }
        }
        SafeParcelReader.ensureAtEnd(parcel0, v);
        return new AuthenticatorAssertionResponse(arr_b, arr_b1, arr_b2, arr_b3, arr_b4);
    }

    @Override  // android.os.Parcelable$Creator
    public final Object[] newArray(int v) {
        return new AuthenticatorAssertionResponse[v];
    }
}

