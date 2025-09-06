package com.google.android.gms.fido.u2f.api.common;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import java.util.List;

public final class zzk implements Parcelable.Creator {
    @Override  // android.os.Parcelable$Creator
    public final Object createFromParcel(Parcel parcel0) {
        int v = SafeParcelReader.validateObjectHeader(parcel0);
        Integer integer0 = null;
        Double double0 = null;
        Uri uri0 = null;
        byte[] arr_b = null;
        List list0 = null;
        ChannelIdValue channelIdValue0 = null;
        String s = null;
        while(parcel0.dataPosition() < v) {
            int v1 = SafeParcelReader.readHeader(parcel0);
            switch(((char)v1)) {
                case 2: {
                    integer0 = SafeParcelReader.readIntegerObject(parcel0, v1);
                    break;
                }
                case 3: {
                    double0 = SafeParcelReader.readDoubleObject(parcel0, v1);
                    break;
                }
                case 4: {
                    uri0 = (Uri)SafeParcelReader.createParcelable(parcel0, v1, Uri.CREATOR);
                    break;
                }
                case 5: {
                    arr_b = SafeParcelReader.createByteArray(parcel0, v1);
                    break;
                }
                case 6: {
                    list0 = SafeParcelReader.createTypedList(parcel0, v1, RegisteredKey.CREATOR);
                    break;
                }
                case 7: {
                    channelIdValue0 = (ChannelIdValue)SafeParcelReader.createParcelable(parcel0, v1, ChannelIdValue.CREATOR);
                    break;
                }
                case 8: {
                    s = SafeParcelReader.createString(parcel0, v1);
                    break;
                }
                default: {
                    SafeParcelReader.skipUnknownField(parcel0, v1);
                }
            }
        }
        SafeParcelReader.ensureAtEnd(parcel0, v);
        return new SignRequestParams(integer0, double0, uri0, arr_b, list0, channelIdValue0, s);
    }

    @Override  // android.os.Parcelable$Creator
    public final Object[] newArray(int v) {
        return new SignRequestParams[v];
    }
}

