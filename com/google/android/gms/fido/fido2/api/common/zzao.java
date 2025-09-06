package com.google.android.gms.fido.fido2.api.common;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import java.util.List;

public final class zzao implements Parcelable.Creator {
    @Override  // android.os.Parcelable$Creator
    public final Object createFromParcel(Parcel parcel0) {
        int v = SafeParcelReader.validateObjectHeader(parcel0);
        byte[] arr_b = null;
        Double double0 = null;
        String s = null;
        List list0 = null;
        Integer integer0 = null;
        TokenBinding tokenBinding0 = null;
        String s1 = null;
        AuthenticationExtensions authenticationExtensions0 = null;
        Long long0 = null;
        while(parcel0.dataPosition() < v) {
            int v1 = SafeParcelReader.readHeader(parcel0);
            switch(((char)v1)) {
                case 2: {
                    arr_b = SafeParcelReader.createByteArray(parcel0, v1);
                    break;
                }
                case 3: {
                    double0 = SafeParcelReader.readDoubleObject(parcel0, v1);
                    break;
                }
                case 4: {
                    s = SafeParcelReader.createString(parcel0, v1);
                    break;
                }
                case 5: {
                    list0 = SafeParcelReader.createTypedList(parcel0, v1, PublicKeyCredentialDescriptor.CREATOR);
                    break;
                }
                case 6: {
                    integer0 = SafeParcelReader.readIntegerObject(parcel0, v1);
                    break;
                }
                case 7: {
                    tokenBinding0 = (TokenBinding)SafeParcelReader.createParcelable(parcel0, v1, TokenBinding.CREATOR);
                    break;
                }
                case 8: {
                    s1 = SafeParcelReader.createString(parcel0, v1);
                    break;
                }
                case 9: {
                    authenticationExtensions0 = (AuthenticationExtensions)SafeParcelReader.createParcelable(parcel0, v1, AuthenticationExtensions.CREATOR);
                    break;
                }
                case 10: {
                    long0 = SafeParcelReader.readLongObject(parcel0, v1);
                    break;
                }
                default: {
                    SafeParcelReader.skipUnknownField(parcel0, v1);
                }
            }
        }
        SafeParcelReader.ensureAtEnd(parcel0, v);
        return new PublicKeyCredentialRequestOptions(arr_b, double0, s, list0, integer0, tokenBinding0, s1, authenticationExtensions0, long0);
    }

    @Override  // android.os.Parcelable$Creator
    public final Object[] newArray(int v) {
        return new PublicKeyCredentialRequestOptions[v];
    }
}

