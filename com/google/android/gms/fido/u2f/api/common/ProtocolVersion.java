package com.google.android.gms.fido.u2f.api.common;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.os.Parcelable;
import java.io.UnsupportedEncodingException;

@Deprecated
public enum ProtocolVersion implements Parcelable {
    public static class UnsupportedProtocolException extends Exception {
        public UnsupportedProtocolException(String s) {
            super(String.format("Protocol version %s not supported", s));
        }
    }

    UNKNOWN("UNKNOWN"),
    V1("U2F_V1"),
    V2("U2F_V2");

    public static final Parcelable.Creator CREATOR;
    private final String zzb;

    static {
        ProtocolVersion.CREATOR = new zzf();
    }

    private ProtocolVersion(String s1) {
        this.zzb = s1;
    }

    @Override  // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public static ProtocolVersion fromBytes(byte[] arr_b) throws UnsupportedProtocolException {
        try {
            return ProtocolVersion.fromString(new String(arr_b, "UTF-8"));
        }
        catch(UnsupportedEncodingException unsupportedEncodingException0) {
            throw new RuntimeException(unsupportedEncodingException0);
        }
    }

    public static ProtocolVersion fromString(String s) throws UnsupportedProtocolException {
        if(s == null) {
            return ProtocolVersion.UNKNOWN;
        }
        ProtocolVersion[] arr_protocolVersion = ProtocolVersion.values();
        for(int v = 0; v < arr_protocolVersion.length; ++v) {
            ProtocolVersion protocolVersion0 = arr_protocolVersion[v];
            if(s.equals(protocolVersion0.zzb)) {
                return protocolVersion0;
            }
        }
        throw new UnsupportedProtocolException(s);
    }

    // 去混淆评级： 低(20)
    public boolean isCompatible(ProtocolVersion protocolVersion0) {
        return this.equals(ProtocolVersion.UNKNOWN) || protocolVersion0.equals(ProtocolVersion.UNKNOWN) ? true : this.equals(protocolVersion0);
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

