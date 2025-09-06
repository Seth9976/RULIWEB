package com.google.android.gms.fido.fido2.api.common;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

public final class zzy implements Parcelable.Creator {
    @Override  // android.os.Parcelable$Creator
    public final Object createFromParcel(Parcel parcel0) {
        int v = SafeParcelReader.validateObjectHeader(parcel0);
        String s = null;
        String s1 = null;
        byte[] arr_b = null;
        byte[] arr_b1 = null;
        boolean z = false;
        boolean z1 = false;
        while(parcel0.dataPosition() < v) {
            int v1 = SafeParcelReader.readHeader(parcel0);
            switch(((char)v1)) {
                case 1: {
                    s = SafeParcelReader.createString(parcel0, v1);
                    break;
                }
                case 2: {
                    s1 = SafeParcelReader.createString(parcel0, v1);
                    break;
                }
                case 3: {
                    arr_b = SafeParcelReader.createByteArray(parcel0, v1);
                    break;
                }
                case 4: {
                    arr_b1 = SafeParcelReader.createByteArray(parcel0, v1);
                    break;
                }
                case 5: {
                    z = SafeParcelReader.readBoolean(parcel0, v1);
                    break;
                }
                case 6: {
                    z1 = SafeParcelReader.readBoolean(parcel0, v1);
                    break;
                }
                default: {
                    SafeParcelReader.skipUnknownField(parcel0, v1);
                }
            }
        }
        SafeParcelReader.ensureAtEnd(parcel0, v);
        return new FidoCredentialDetails(s, s1, arr_b, arr_b1, z, z1);
    }

    @Override  // android.os.Parcelable$Creator
    public final Object[] newArray(int v) {
        return new FidoCredentialDetails[v];
    }
}

