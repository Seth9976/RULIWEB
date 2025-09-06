package com.google.android.gms.auth.api.signin;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

public final class zbc implements Parcelable.Creator {
    @Override  // android.os.Parcelable$Creator
    public final Object createFromParcel(Parcel parcel0) {
        int v = SafeParcelReader.validateObjectHeader(parcel0);
        String s = "";
        GoogleSignInAccount googleSignInAccount0 = null;
        String s1 = "";
        while(parcel0.dataPosition() < v) {
            int v1 = SafeParcelReader.readHeader(parcel0);
            switch(((char)v1)) {
                case 4: {
                    s = SafeParcelReader.createString(parcel0, v1);
                    break;
                }
                case 7: {
                    googleSignInAccount0 = (GoogleSignInAccount)SafeParcelReader.createParcelable(parcel0, v1, GoogleSignInAccount.CREATOR);
                    break;
                }
                case 8: {
                    s1 = SafeParcelReader.createString(parcel0, v1);
                    break;
                }
                default: {
                    SafeParcelReader.skipUnknownField(parcel0, v1);
                }
            }
        }
        SafeParcelReader.ensureAtEnd(parcel0, v);
        return new SignInAccount(s, googleSignInAccount0, s1);
    }

    @Override  // android.os.Parcelable$Creator
    public final Object[] newArray(int v) {
        return new SignInAccount[v];
    }
}

