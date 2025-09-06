package com.google.android.gms.fido.fido2.api.common;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.os.Parcelable;

public enum ResidentKeyRequirement implements Parcelable {
    public static class UnsupportedResidentKeyRequirementException extends Exception {
        public UnsupportedResidentKeyRequirementException(String s) {
            super(String.format("Resident key requirement %s not supported", s));
        }
    }

    RESIDENT_KEY_DISCOURAGED("discouraged"),
    RESIDENT_KEY_PREFERRED("preferred"),
    RESIDENT_KEY_REQUIRED("required");

    public static final Parcelable.Creator CREATOR;
    private final String zzb;

    static {
        ResidentKeyRequirement.CREATOR = new zzas();
    }

    private ResidentKeyRequirement(String s1) {
        this.zzb = s1;
    }

    @Override  // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public static ResidentKeyRequirement fromString(String s) throws UnsupportedResidentKeyRequirementException {
        ResidentKeyRequirement[] arr_residentKeyRequirement = ResidentKeyRequirement.values();
        for(int v = 0; v < arr_residentKeyRequirement.length; ++v) {
            ResidentKeyRequirement residentKeyRequirement0 = arr_residentKeyRequirement[v];
            if(s.equals(residentKeyRequirement0.zzb)) {
                return residentKeyRequirement0;
            }
        }
        throw new UnsupportedResidentKeyRequirementException(s);
    }

    @Override
    public String toString() {
        return this.zzb;
    }

    @Override  // android.os.Parcelable
    public void writeToParcel(Parcel parcel0, int v) {
        parcel0.writeString(this.zzb);
    }
}

