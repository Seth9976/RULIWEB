package com.google.android.gms.fido.common;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.util.Log;
import com.google.android.gms.common.internal.ReflectedParcelable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;

public enum Transport implements ReflectedParcelable {
    public static class UnsupportedTransportException extends Exception {
        public UnsupportedTransportException(String s) {
            super(s);
        }
    }

    BLUETOOTH_CLASSIC("bt"),
    BLUETOOTH_LOW_ENERGY("ble"),
    NFC("nfc"),
    USB("usb"),
    INTERNAL("internal"),
    HYBRID("cable");

    public static final Parcelable.Creator CREATOR;
    private final String zzb;

    static {
        Transport.CREATOR = new zza();
    }

    private Transport(String s1) {
        this.zzb = s1;
    }

    @Override  // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public static Transport fromString(String s) throws UnsupportedTransportException {
        Transport[] arr_transport = Transport.values();
        for(int v = 0; v < arr_transport.length; ++v) {
            Transport transport0 = arr_transport[v];
            if(s.equals(transport0.zzb)) {
                return transport0;
            }
        }
        if(!s.equals("hybrid")) {
            throw new UnsupportedTransportException(String.format("Transport %s not supported", s));
        }
        return Transport.HYBRID;
    }

    public static List parseTransports(JSONArray jSONArray0) throws JSONException {
        if(jSONArray0 == null) {
            return null;
        }
        HashSet hashSet0 = new HashSet(jSONArray0.length());
        for(int v = 0; v < jSONArray0.length(); ++v) {
            String s = jSONArray0.getString(v);
            if(s != null && !s.isEmpty()) {
                try {
                    hashSet0.add(Transport.fromString(s));
                }
                catch(UnsupportedTransportException unused_ex) {
                    Log.w("Transport", "Ignoring unrecognized transport " + s);
                }
            }
        }
        return new ArrayList(hashSet0);
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

