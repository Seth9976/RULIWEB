package com.google.android.gms.auth.api.signin;

import android.accounts.Account;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.auth.api.signin.internal.GoogleSignInOptionsExtensionParcelable;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import java.util.ArrayList;

public final class zae implements Parcelable.Creator {
    @Override  // android.os.Parcelable$Creator
    public final Object createFromParcel(Parcel parcel0) {
        int v = SafeParcelReader.validateObjectHeader(parcel0);
        ArrayList arrayList0 = null;
        Account account0 = null;
        String s = null;
        String s1 = null;
        ArrayList arrayList1 = null;
        String s2 = null;
        int v1 = 0;
        boolean z = false;
        boolean z1 = false;
        boolean z2 = false;
        while(parcel0.dataPosition() < v) {
            int v2 = SafeParcelReader.readHeader(parcel0);
            switch(((char)v2)) {
                case 1: {
                    v1 = SafeParcelReader.readInt(parcel0, v2);
                    break;
                }
                case 2: {
                    arrayList0 = SafeParcelReader.createTypedList(parcel0, v2, Scope.CREATOR);
                    break;
                }
                case 3: {
                    account0 = (Account)SafeParcelReader.createParcelable(parcel0, v2, Account.CREATOR);
                    break;
                }
                case 4: {
                    z = SafeParcelReader.readBoolean(parcel0, v2);
                    break;
                }
                case 5: {
                    z1 = SafeParcelReader.readBoolean(parcel0, v2);
                    break;
                }
                case 6: {
                    z2 = SafeParcelReader.readBoolean(parcel0, v2);
                    break;
                }
                case 7: {
                    s = SafeParcelReader.createString(parcel0, v2);
                    break;
                }
                case 8: {
                    s1 = SafeParcelReader.createString(parcel0, v2);
                    break;
                }
                case 9: {
                    arrayList1 = SafeParcelReader.createTypedList(parcel0, v2, GoogleSignInOptionsExtensionParcelable.CREATOR);
                    break;
                }
                case 10: {
                    s2 = SafeParcelReader.createString(parcel0, v2);
                    break;
                }
                default: {
                    SafeParcelReader.skipUnknownField(parcel0, v2);
                }
            }
        }
        SafeParcelReader.ensureAtEnd(parcel0, v);
        return new GoogleSignInOptions(v1, arrayList0, account0, z, z1, z2, s, s1, arrayList1, s2);
    }

    @Override  // android.os.Parcelable$Creator
    public final Object[] newArray(int v) {
        return new GoogleSignInOptions[v];
    }
}

