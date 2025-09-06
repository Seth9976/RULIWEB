package com.google.android.gms.fido.fido2.api.common;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.os.Parcelable;

public enum AttestationConveyancePreference implements Parcelable {
    public static class UnsupportedAttestationConveyancePreferenceException extends Exception {
        public UnsupportedAttestationConveyancePreferenceException(String s) {
            super(String.format("Attestation conveyance preference %s not supported", s));
        }
    }

    NONE("none"),
    INDIRECT("indirect"),
    DIRECT("direct");

    public static final Parcelable.Creator CREATOR;
    private final String zzb;

    static {
        AttestationConveyancePreference.CREATOR = new zzb();
    }

    private AttestationConveyancePreference(String s1) {
        this.zzb = s1;
    }

    @Override  // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public static AttestationConveyancePreference fromString(String s) throws UnsupportedAttestationConveyancePreferenceException {
        AttestationConveyancePreference[] arr_attestationConveyancePreference = AttestationConveyancePreference.values();
        for(int v = 0; v < arr_attestationConveyancePreference.length; ++v) {
            AttestationConveyancePreference attestationConveyancePreference0 = arr_attestationConveyancePreference[v];
            if(s.equals(attestationConveyancePreference0.zzb)) {
                return attestationConveyancePreference0;
            }
        }
        throw new UnsupportedAttestationConveyancePreferenceException(s);
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

