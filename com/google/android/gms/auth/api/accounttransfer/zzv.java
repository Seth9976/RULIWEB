package com.google.android.gms.auth.api.accounttransfer;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader.ParseException;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import java.util.HashSet;

public final class zzv implements Parcelable.Creator {
    @Override  // android.os.Parcelable$Creator
    public final Object createFromParcel(Parcel parcel0) {
        int v = SafeParcelReader.validateObjectHeader(parcel0);
        HashSet hashSet0 = new HashSet();
        int v1 = 0;
        zzw zzw0 = null;
        String s = null;
        String s1 = null;
        String s2 = null;
        while(parcel0.dataPosition() < v) {
            int v2 = SafeParcelReader.readHeader(parcel0);
            switch(((char)v2)) {
                case 1: {
                    v1 = SafeParcelReader.readInt(parcel0, v2);
                    hashSet0.add(1);
                    break;
                }
                case 2: {
                    zzw0 = (zzw)SafeParcelReader.createParcelable(parcel0, v2, zzw.CREATOR);
                    hashSet0.add(2);
                    break;
                }
                case 3: {
                    s = SafeParcelReader.createString(parcel0, v2);
                    hashSet0.add(3);
                    break;
                }
                case 4: {
                    s1 = SafeParcelReader.createString(parcel0, v2);
                    hashSet0.add(4);
                    break;
                }
                case 5: {
                    s2 = SafeParcelReader.createString(parcel0, v2);
                    hashSet0.add(5);
                    break;
                }
                default: {
                    SafeParcelReader.skipUnknownField(parcel0, v2);
                }
            }
        }
        if(parcel0.dataPosition() != v) {
            throw new ParseException("Overread allowed size end=" + v, parcel0);
        }
        return new zzu(hashSet0, v1, zzw0, s, s1, s2);
    }

    @Override  // android.os.Parcelable$Creator
    public final Object[] newArray(int v) {
        return new zzu[v];
    }
}

