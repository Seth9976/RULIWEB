package com.google.android.gms.auth.api.identity;

import android.app.PendingIntent;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import java.util.List;

public final class zbc implements Parcelable.Creator {
    @Override  // android.os.Parcelable$Creator
    public final Object createFromParcel(Parcel parcel0) {
        int v = SafeParcelReader.validateObjectHeader(parcel0);
        String s = null;
        String s1 = null;
        String s2 = null;
        List list0 = null;
        GoogleSignInAccount googleSignInAccount0 = null;
        PendingIntent pendingIntent0 = null;
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
                    s2 = SafeParcelReader.createString(parcel0, v1);
                    break;
                }
                case 4: {
                    list0 = SafeParcelReader.createStringList(parcel0, v1);
                    break;
                }
                case 5: {
                    googleSignInAccount0 = (GoogleSignInAccount)SafeParcelReader.createParcelable(parcel0, v1, GoogleSignInAccount.CREATOR);
                    break;
                }
                case 6: {
                    pendingIntent0 = (PendingIntent)SafeParcelReader.createParcelable(parcel0, v1, PendingIntent.CREATOR);
                    break;
                }
                default: {
                    SafeParcelReader.skipUnknownField(parcel0, v1);
                }
            }
        }
        SafeParcelReader.ensureAtEnd(parcel0, v);
        return new AuthorizationResult(s, s1, s2, list0, googleSignInAccount0, pendingIntent0);
    }

    @Override  // android.os.Parcelable$Creator
    public final Object[] newArray(int v) {
        return new AuthorizationResult[v];
    }
}

