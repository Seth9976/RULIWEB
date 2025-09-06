package com.google.android.gms.auth.api.identity;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import java.util.List;

public final class zbj implements Parcelable.Creator {
    @Override  // android.os.Parcelable$Creator
    public final Object createFromParcel(Parcel parcel0) {
        int v = SafeParcelReader.validateObjectHeader(parcel0);
        String s = null;
        String s1 = null;
        String s2 = null;
        List list0 = null;
        boolean z = false;
        boolean z1 = false;
        boolean z2 = false;
        while(parcel0.dataPosition() < v) {
            int v1 = SafeParcelReader.readHeader(parcel0);
            switch(((char)v1)) {
                case 1: {
                    z = SafeParcelReader.readBoolean(parcel0, v1);
                    break;
                }
                case 2: {
                    s = SafeParcelReader.createString(parcel0, v1);
                    break;
                }
                case 3: {
                    s1 = SafeParcelReader.createString(parcel0, v1);
                    break;
                }
                case 4: {
                    z1 = SafeParcelReader.readBoolean(parcel0, v1);
                    break;
                }
                case 5: {
                    s2 = SafeParcelReader.createString(parcel0, v1);
                    break;
                }
                case 6: {
                    list0 = SafeParcelReader.createStringList(parcel0, v1);
                    break;
                }
                case 7: {
                    z2 = SafeParcelReader.readBoolean(parcel0, v1);
                    break;
                }
                default: {
                    SafeParcelReader.skipUnknownField(parcel0, v1);
                }
            }
        }
        SafeParcelReader.ensureAtEnd(parcel0, v);
        return new GoogleIdTokenRequestOptions(z, s, s1, z1, s2, list0, z2);
    }

    @Override  // android.os.Parcelable$Creator
    public final Object[] newArray(int v) {
        return new GoogleIdTokenRequestOptions[v];
    }
}

