package com.google.android.gms.fido.u2f.api.common;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.os.Parcelable;

@Deprecated
public enum ErrorCode implements Parcelable {
    OK(0),
    OTHER_ERROR(1),
    BAD_REQUEST(2),
    CONFIGURATION_UNSUPPORTED(3),
    DEVICE_INELIGIBLE(4),
    TIMEOUT(5);

    public static final Parcelable.Creator CREATOR;
    private static final String zza;
    private final int zzc;

    static {
        ErrorCode.zza = "ErrorCode";
        ErrorCode.CREATOR = new zzc();
    }

    private ErrorCode(int v1) {
        this.zzc = v1;
    }

    @Override  // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public int getCode() {
        return this.zzc;
    }

    public static ErrorCode toErrorCode(int v) {
        ErrorCode[] arr_errorCode = ErrorCode.values();
        for(int v1 = 0; v1 < arr_errorCode.length; ++v1) {
            ErrorCode errorCode0 = arr_errorCode[v1];
            if(v == errorCode0.zzc) {
                return errorCode0;
            }
        }
        return ErrorCode.OTHER_ERROR;
    }

    @Override  // android.os.Parcelable
    public void writeToParcel(Parcel parcel0, int v) {
        parcel0.writeInt(this.zzc);
    }
}

