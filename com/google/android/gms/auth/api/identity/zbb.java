package com.google.android.gms.auth.api.identity;

import android.accounts.Account;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import java.util.List;

public final class zbb implements Parcelable.Creator {
    @Override  // android.os.Parcelable$Creator
    public final Object createFromParcel(Parcel parcel0) {
        int v = SafeParcelReader.validateObjectHeader(parcel0);
        List list0 = null;
        String s = null;
        Account account0 = null;
        String s1 = null;
        String s2 = null;
        Bundle bundle0 = null;
        boolean z = false;
        boolean z1 = false;
        boolean z2 = false;
        boolean z3 = false;
        while(parcel0.dataPosition() < v) {
            int v1 = SafeParcelReader.readHeader(parcel0);
            switch(((char)v1)) {
                case 1: {
                    list0 = SafeParcelReader.createTypedList(parcel0, v1, Scope.CREATOR);
                    break;
                }
                case 2: {
                    s = SafeParcelReader.createString(parcel0, v1);
                    break;
                }
                case 3: {
                    z = SafeParcelReader.readBoolean(parcel0, v1);
                    break;
                }
                case 4: {
                    z1 = SafeParcelReader.readBoolean(parcel0, v1);
                    break;
                }
                case 5: {
                    account0 = (Account)SafeParcelReader.createParcelable(parcel0, v1, Account.CREATOR);
                    break;
                }
                case 6: {
                    s1 = SafeParcelReader.createString(parcel0, v1);
                    break;
                }
                case 7: {
                    s2 = SafeParcelReader.createString(parcel0, v1);
                    break;
                }
                case 8: {
                    z2 = SafeParcelReader.readBoolean(parcel0, v1);
                    break;
                }
                case 9: {
                    bundle0 = SafeParcelReader.createBundle(parcel0, v1);
                    break;
                }
                case 10: {
                    z3 = SafeParcelReader.readBoolean(parcel0, v1);
                    break;
                }
                default: {
                    SafeParcelReader.skipUnknownField(parcel0, v1);
                }
            }
        }
        SafeParcelReader.ensureAtEnd(parcel0, v);
        return new AuthorizationRequest(list0, s, z, z1, account0, s1, s2, z2, bundle0, z3);
    }

    @Override  // android.os.Parcelable$Creator
    public final Object[] newArray(int v) {
        return new AuthorizationRequest[v];
    }
}

