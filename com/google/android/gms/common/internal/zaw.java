package com.google.android.gms.common.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

public final class zaw implements Parcelable.Creator {
    @Override  // android.os.Parcelable$Creator
    public final Object createFromParcel(Parcel parcel0) {
        int v = SafeParcelReader.validateObjectHeader(parcel0);
        IBinder iBinder0 = null;
        ConnectionResult connectionResult0 = null;
        int v1 = 0;
        boolean z = false;
        boolean z1 = false;
        while(parcel0.dataPosition() < v) {
            int v2 = SafeParcelReader.readHeader(parcel0);
            switch(((char)v2)) {
                case 1: {
                    v1 = SafeParcelReader.readInt(parcel0, v2);
                    break;
                }
                case 2: {
                    iBinder0 = SafeParcelReader.readIBinder(parcel0, v2);
                    break;
                }
                case 3: {
                    connectionResult0 = (ConnectionResult)SafeParcelReader.createParcelable(parcel0, v2, ConnectionResult.CREATOR);
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
                default: {
                    SafeParcelReader.skipUnknownField(parcel0, v2);
                }
            }
        }
        SafeParcelReader.ensureAtEnd(parcel0, v);
        return new zav(v1, iBinder0, connectionResult0, z, z1);
    }

    @Override  // android.os.Parcelable$Creator
    public final Object[] newArray(int v) {
        return new zav[v];
    }
}

