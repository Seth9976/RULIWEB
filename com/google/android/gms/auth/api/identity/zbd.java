package com.google.android.gms.auth.api.identity;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

public final class zbd implements Parcelable.Creator {
    @Override  // android.os.Parcelable$Creator
    public final Object createFromParcel(Parcel parcel0) {
        int v = SafeParcelReader.validateObjectHeader(parcel0);
        PasswordRequestOptions beginSignInRequest$PasswordRequestOptions0 = null;
        GoogleIdTokenRequestOptions beginSignInRequest$GoogleIdTokenRequestOptions0 = null;
        String s = null;
        PasskeysRequestOptions beginSignInRequest$PasskeysRequestOptions0 = null;
        PasskeyJsonRequestOptions beginSignInRequest$PasskeyJsonRequestOptions0 = null;
        boolean z = false;
        int v1 = 0;
        boolean z1 = false;
        while(parcel0.dataPosition() < v) {
            int v2 = SafeParcelReader.readHeader(parcel0);
            switch(((char)v2)) {
                case 1: {
                    beginSignInRequest$PasswordRequestOptions0 = (PasswordRequestOptions)SafeParcelReader.createParcelable(parcel0, v2, PasswordRequestOptions.CREATOR);
                    break;
                }
                case 2: {
                    beginSignInRequest$GoogleIdTokenRequestOptions0 = (GoogleIdTokenRequestOptions)SafeParcelReader.createParcelable(parcel0, v2, GoogleIdTokenRequestOptions.CREATOR);
                    break;
                }
                case 3: {
                    s = SafeParcelReader.createString(parcel0, v2);
                    break;
                }
                case 4: {
                    z = SafeParcelReader.readBoolean(parcel0, v2);
                    break;
                }
                case 5: {
                    v1 = SafeParcelReader.readInt(parcel0, v2);
                    break;
                }
                case 6: {
                    beginSignInRequest$PasskeysRequestOptions0 = (PasskeysRequestOptions)SafeParcelReader.createParcelable(parcel0, v2, PasskeysRequestOptions.CREATOR);
                    break;
                }
                case 7: {
                    beginSignInRequest$PasskeyJsonRequestOptions0 = (PasskeyJsonRequestOptions)SafeParcelReader.createParcelable(parcel0, v2, PasskeyJsonRequestOptions.CREATOR);
                    break;
                }
                case 8: {
                    z1 = SafeParcelReader.readBoolean(parcel0, v2);
                    break;
                }
                default: {
                    SafeParcelReader.skipUnknownField(parcel0, v2);
                }
            }
        }
        SafeParcelReader.ensureAtEnd(parcel0, v);
        return new BeginSignInRequest(beginSignInRequest$PasswordRequestOptions0, beginSignInRequest$GoogleIdTokenRequestOptions0, s, z, v1, beginSignInRequest$PasskeysRequestOptions0, beginSignInRequest$PasskeyJsonRequestOptions0, z1);
    }

    @Override  // android.os.Parcelable$Creator
    public final Object[] newArray(int v) {
        return new BeginSignInRequest[v];
    }
}

