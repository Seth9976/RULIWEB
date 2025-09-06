package com.google.android.gms.fido.fido2.api.common;

import android.os.Parcel;
import android.os.Parcelable.Creator;

final class zzas implements Parcelable.Creator {
    @Override  // android.os.Parcelable$Creator
    public final Object createFromParcel(Parcel parcel0) {
        String s = parcel0.readString();
        try {
            if(s == null) {
                s = "";
            }
            return ResidentKeyRequirement.fromString(s);
        }
        catch(UnsupportedResidentKeyRequirementException residentKeyRequirement$UnsupportedResidentKeyRequirementException0) {
            throw new RuntimeException(residentKeyRequirement$UnsupportedResidentKeyRequirementException0);
        }
    }

    @Override  // android.os.Parcelable$Creator
    public final Object[] newArray(int v) {
        return new ResidentKeyRequirement[v];
    }
}

