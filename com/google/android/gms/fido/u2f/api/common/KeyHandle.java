package com.google.android.gms.fido.u2f.api.common;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.util.Base64;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.util.Base64Utils;
import com.google.android.gms.fido.common.Transport;
import java.util.Arrays;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@Deprecated
public class KeyHandle extends AbstractSafeParcelable {
    public static final Parcelable.Creator CREATOR;
    private final int zza;
    private final byte[] zzb;
    private final ProtocolVersion zzc;
    private final List zzd;

    static {
        KeyHandle.CREATOR = new zze();
    }

    KeyHandle(int v, byte[] arr_b, String s, List list0) {
        this.zza = v;
        this.zzb = arr_b;
        try {
            this.zzc = ProtocolVersion.fromString(s);
            this.zzd = list0;
        }
        catch(UnsupportedProtocolException protocolVersion$UnsupportedProtocolException0) {
            throw new IllegalArgumentException(protocolVersion$UnsupportedProtocolException0);
        }
    }

    public KeyHandle(byte[] arr_b, ProtocolVersion protocolVersion0, List list0) {
        this.zza = 1;
        this.zzb = arr_b;
        this.zzc = protocolVersion0;
        this.zzd = list0;
    }

    @Override
    public boolean equals(Object object0) {
        if(this == object0) {
            return true;
        }
        if(!(object0 instanceof KeyHandle)) {
            return false;
        }
        if(!Arrays.equals(this.zzb, ((KeyHandle)object0).zzb)) {
            return false;
        }
        if(!this.zzc.equals(((KeyHandle)object0).zzc)) {
            return false;
        }
        List list0 = this.zzd;
        if(list0 == null && ((KeyHandle)object0).zzd == null) {
            return true;
        }
        if(list0 != null) {
            List list1 = ((KeyHandle)object0).zzd;
            return list1 != null && list0.containsAll(list1) && ((KeyHandle)object0).zzd.containsAll(this.zzd);
        }
        return false;
    }

    public byte[] getBytes() {
        return this.zzb;
    }

    public ProtocolVersion getProtocolVersion() {
        return this.zzc;
    }

    public List getTransports() {
        return this.zzd;
    }

    public int getVersionCode() {
        return this.zza;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(new Object[]{Arrays.hashCode(this.zzb), this.zzc, this.zzd});
    }

    public static KeyHandle parseFromJson(JSONObject jSONObject0) throws JSONException {
        byte[] arr_b;
        ProtocolVersion protocolVersion0;
        List list0 = null;
        String s = jSONObject0.has("version") ? jSONObject0.getString("version") : null;
        try {
            protocolVersion0 = ProtocolVersion.fromString(s);
        }
        catch(UnsupportedProtocolException protocolVersion$UnsupportedProtocolException0) {
            throw new JSONException(protocolVersion$UnsupportedProtocolException0.toString());
        }
        String s1 = jSONObject0.getString("keyHandle");
        try {
            arr_b = Base64.decode(s1, 8);
        }
        catch(IllegalArgumentException illegalArgumentException0) {
            throw new JSONException(illegalArgumentException0.toString());
        }
        if(jSONObject0.has("transports")) {
            list0 = Transport.parseTransports(jSONObject0.getJSONArray("transports"));
        }
        return new KeyHandle(arr_b, protocolVersion0, list0);
    }

    public JSONObject toJson() {
        return this.zza();
    }

    @Override
    public String toString() {
        return this.zzd == null ? String.format("{keyHandle: %s, version: %s, transports: %s}", Base64Utils.encode(this.zzb), this.zzc, "null") : String.format("{keyHandle: %s, version: %s, transports: %s}", Base64Utils.encode(this.zzb), this.zzc, this.zzd.toString());
    }

    @Override  // android.os.Parcelable
    public void writeToParcel(Parcel parcel0, int v) {
        int v1 = SafeParcelWriter.beginObjectHeader(parcel0);
        SafeParcelWriter.writeInt(parcel0, 1, this.getVersionCode());
        SafeParcelWriter.writeByteArray(parcel0, 2, this.getBytes(), false);
        SafeParcelWriter.writeString(parcel0, 3, this.zzc.toString(), false);
        SafeParcelWriter.writeTypedList(parcel0, 4, this.getTransports(), false);
        SafeParcelWriter.finishObjectHeader(parcel0, v1);
    }

    public final JSONObject zza() {
        JSONObject jSONObject0 = new JSONObject();
        try {
            byte[] arr_b = this.zzb;
            if(arr_b != null) {
                jSONObject0.put("keyHandle", Base64.encodeToString(arr_b, 11));
            }
            ProtocolVersion protocolVersion0 = this.zzc;
            if(protocolVersion0 != null) {
                jSONObject0.put("version", protocolVersion0.toString());
            }
            if(this.zzd != null) {
                JSONArray jSONArray0 = new JSONArray();
                for(Object object0: this.zzd) {
                    jSONArray0.put(((Transport)object0).toString());
                }
                jSONObject0.put("transports", jSONArray0);
            }
            return jSONObject0;
        }
        catch(JSONException jSONException0) {
            throw new RuntimeException(jSONException0);
        }
    }
}

