package com.google.android.gms.auth.api.identity;

import android.app.PendingIntent;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import java.util.List;

public final class zbn implements Parcelable.Creator {
    @Override  // android.os.Parcelable$Creator
    public final Object createFromParcel(Parcel parcel0) {
        int v = SafeParcelReader.validateObjectHeader(parcel0);
        PendingIntent pendingIntent0 = null;
        String s = null;
        String s1 = null;
        List list0 = null;
        String s2 = null;
        int v1 = 0;
        while(parcel0.dataPosition() < v) {
            int v2 = SafeParcelReader.readHeader(parcel0);
            switch(((char)v2)) {
                case 1: {
                    pendingIntent0 = (PendingIntent)SafeParcelReader.createParcelable(parcel0, v2, PendingIntent.CREATOR);
                    break;
                }
                case 2: {
                    s = SafeParcelReader.createString(parcel0, v2);
                    break;
                }
                case 3: {
                    s1 = SafeParcelReader.createString(parcel0, v2);
                    break;
                }
                case 4: {
                    list0 = SafeParcelReader.createStringList(parcel0, v2);
                    break;
                }
                case 5: {
                    s2 = SafeParcelReader.createString(parcel0, v2);
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
        return new SaveAccountLinkingTokenRequest(pendingIntent0, s, s1, list0, s2, v1);
    }

    @Override  // android.os.Parcelable$Creator
    public final Object[] newArray(int v) {
        return new SaveAccountLinkingTokenRequest[v];
    }
}

