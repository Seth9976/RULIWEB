package com.google.android.gms.auth.api.identity;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

public final class zbi implements Parcelable.Creator {
    @Override  // android.os.Parcelable$Creator
    public final Object createFromParcel(Parcel parcel0) {
        int v = SafeParcelReader.validateObjectHeader(parcel0);
        String s = null;
        String s1 = null;
        String s2 = null;
        String s3 = null;
        boolean z = false;
        int v1 = 0;
        while(parcel0.dataPosition() < v) {
            int v2 = SafeParcelReader.readHeader(parcel0);
            switch(((char)v2)) {
                case 1: {
                    s = SafeParcelReader.createString(parcel0, v2);
                    break;
                }
                case 2: {
                    s1 = SafeParcelReader.createString(parcel0, v2);
                    break;
                }
                case 3: {
                    s2 = SafeParcelReader.createString(parcel0, v2);
                    break;
                }
                case 4: {
                    s3 = SafeParcelReader.createString(parcel0, v2);
                    break;
                }
                case 5: {
                    z = SafeParcelReader.readBoolean(parcel0, v2);
                    break;
                }
                case 6: {
                    v1 = SafeParcelReader.readInt(parcel0, v2);
                    break;
                }
                default: {
                    SafeParcelReader.skipUnknownField(parcel0, v2);
                }
            }
        }
        SafeParcelReader.ensureAtEnd(parcel0, v);
        return new GetSignInIntentRequest(s, s1, s2, s3, z, v1);
    }

    @Override  // android.os.Parcelable$Creator
    public final Object[] newArray(int v) {
        return new GetSignInIntentRequest[v];
    }
}

