package com.google.android.gms.common.api;

import android.app.PendingIntent;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

public final class zzb implements Parcelable.Creator {
    @Override  // android.os.Parcelable$Creator
    public final Object createFromParcel(Parcel parcel0) {
        int v = SafeParcelReader.validateObjectHeader(parcel0);
        String s = null;
        PendingIntent pendingIntent0 = null;
        ConnectionResult connectionResult0 = null;
        int v1 = 0;
        while(parcel0.dataPosition() < v) {
            int v2 = SafeParcelReader.readHeader(parcel0);
            switch(((char)v2)) {
                case 1: {
                    v1 = SafeParcelReader.readInt(parcel0, v2);
                    break;
                }
                case 2: {
                    s = SafeParcelReader.createString(parcel0, v2);
                    break;
                }
                case 3: {
                    pendingIntent0 = (PendingIntent)SafeParcelReader.createParcelable(parcel0, v2, PendingIntent.CREATOR);
                    break;
                }
                case 4: {
                    connectionResult0 = (ConnectionResult)SafeParcelReader.createParcelable(parcel0, v2, ConnectionResult.CREATOR);
                    break;
                }
                default: {
                    SafeParcelReader.skipUnknownField(parcel0, v2);
                }
            }
        }
        SafeParcelReader.ensureAtEnd(parcel0, v);
        return new Status(v1, s, pendingIntent0, connectionResult0);
    }

    @Override  // android.os.Parcelable$Creator
    public final Object[] newArray(int v) {
        return new Status[v];
    }
}

