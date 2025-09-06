package com.google.android.gms.fido.fido2.api.common;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.os.Parcelable;
import java.util.Locale;

public enum ErrorCode implements Parcelable {
    public static class UnsupportedErrorCodeException extends Exception {
        public UnsupportedErrorCodeException(int v) {
            super(String.format(Locale.US, "Error code %d is not supported", v));
        }
    }

    NOT_SUPPORTED_ERR(9),
    INVALID_STATE_ERR(11),
    SECURITY_ERR(18),
    NETWORK_ERR(19),
    ABORT_ERR(20),
    TIMEOUT_ERR(23),
    ENCODING_ERR(27),
    UNKNOWN_ERR(28),
    CONSTRAINT_ERR(29),
    DATA_ERR(30),
    NOT_ALLOWED_ERR(35),
    ATTESTATION_NOT_PRIVATE_ERR(36);

    public static final Parcelable.Creator CREATOR;
    private final int zzb;

    static {
        ErrorCode.CREATOR = new zzw();
    }

    private ErrorCode(int v1) {
        this.zzb = v1;
    }

    @Override  // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public int getCode() {
        return this.zzb;
    }

    public static ErrorCode toErrorCode(int v) throws UnsupportedErrorCodeException {
        ErrorCode[] arr_errorCode = ErrorCode.values();
        for(int v1 = 0; v1 < arr_errorCode.length; ++v1) {
            ErrorCode errorCode0 = arr_errorCode[v1];
            if(v == errorCode0.zzb) {
                return errorCode0;
            }
        }
        throw new UnsupportedErrorCodeException(v);
    }

    @Override  // android.os.Parcelable
    public void writeToParcel(Parcel parcel0, int v) {
        parcel0.writeInt(this.zzb);
    }
}

