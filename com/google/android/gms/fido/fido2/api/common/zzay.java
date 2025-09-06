package com.google.android.gms.fido.fido2.api.common;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.os.Parcelable;

public enum zzay implements Parcelable {
    USER_VERIFICATION_REQUIRED("required"),
    USER_VERIFICATION_PREFERRED("preferred"),
    USER_VERIFICATION_DISCOURAGED("discouraged");

    public static final Parcelable.Creator CREATOR;
    private final String zze;

    static {
        zzay.CREATOR = new zzaw();
    }

    private zzay(String s1) {
        this.zze = s1;
    }

    @Override  // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    @Override
    public final String toString() {
        return this.zze;
    }

    @Override  // android.os.Parcelable
    public final void writeToParcel(Parcel parcel0, int v) {
        parcel0.writeString(this.zze);
    }

    public static zzay zza(String s) throws zzax {
        zzay[] arr_zzay = zzay.values();
        for(int v = 0; v < arr_zzay.length; ++v) {
            zzay zzay0 = arr_zzay[v];
            if(s.equals(zzay0.zze)) {
                return zzay0;
            }
        }
        throw new zzax(s);
    }
}

