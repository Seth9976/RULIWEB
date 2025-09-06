package com.google.android.gms.common.internal;

import android.accounts.Account;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

public final class zau implements Parcelable.Creator {
    @Override  // android.os.Parcelable$Creator
    public final Object createFromParcel(Parcel parcel0) {
        int v = SafeParcelReader.validateObjectHeader(parcel0);
        Account account0 = null;
        GoogleSignInAccount googleSignInAccount0 = null;
        int v1 = 0;
        int v2 = 0;
        while(parcel0.dataPosition() < v) {
            int v3 = SafeParcelReader.readHeader(parcel0);
            switch(((char)v3)) {
                case 1: {
                    v1 = SafeParcelReader.readInt(parcel0, v3);
                    break;
                }
                case 2: {
                    account0 = (Account)SafeParcelReader.createParcelable(parcel0, v3, Account.CREATOR);
                    break;
                }
                case 3: {
                    v2 = SafeParcelReader.readInt(parcel0, v3);
                    break;
                }
                case 4: {
                    googleSignInAccount0 = (GoogleSignInAccount)SafeParcelReader.createParcelable(parcel0, v3, GoogleSignInAccount.CREATOR);
                    break;
                }
                default: {
                    SafeParcelReader.skipUnknownField(parcel0, v3);
                }
            }
        }
        SafeParcelReader.ensureAtEnd(parcel0, v);
        return new zat(v1, account0, v2, googleSignInAccount0);
    }

    @Override  // android.os.Parcelable$Creator
    public final Object[] newArray(int v) {
        return new zat[v];
    }
}

