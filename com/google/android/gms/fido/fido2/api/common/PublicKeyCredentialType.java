package com.google.android.gms.fido.fido2.api.common;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.os.Parcelable;

public enum PublicKeyCredentialType implements Parcelable {
    public static class UnsupportedPublicKeyCredTypeException extends Exception {
        public UnsupportedPublicKeyCredTypeException(String s) {
            super(s);
        }
    }

    PUBLIC_KEY("public-key");

    public static final Parcelable.Creator CREATOR;
    private final String zzb;

    static {
        PublicKeyCredentialType.CREATOR = new zzaq();
    }

    private PublicKeyCredentialType(String s1) {
        this.zzb = "public-key";
    }

    @Override  // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public static PublicKeyCredentialType fromString(String s) throws UnsupportedPublicKeyCredTypeException {
        PublicKeyCredentialType[] arr_publicKeyCredentialType = PublicKeyCredentialType.values();
        for(int v = 0; v < arr_publicKeyCredentialType.length; ++v) {
            PublicKeyCredentialType publicKeyCredentialType0 = arr_publicKeyCredentialType[v];
            if(s.equals(publicKeyCredentialType0.zzb)) {
                return publicKeyCredentialType0;
            }
        }
        throw new UnsupportedPublicKeyCredTypeException(String.format("PublicKeyCredentialType %s not supported", s));
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

