package com.google.android.gms.fido.u2f.api.common;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.util.Base64;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import java.util.Arrays;
import org.json.JSONException;
import org.json.JSONObject;

@Deprecated
public class RegisterRequest extends AbstractSafeParcelable {
    public static final Parcelable.Creator CREATOR = null;
    public static final int U2F_V1_CHALLENGE_BYTE_LENGTH = 65;
    private final int zza;
    private final ProtocolVersion zzb;
    private final byte[] zzc;
    private final String zzd;

    static {
        RegisterRequest.CREATOR = new zzg();
    }

    RegisterRequest(int v, String s, byte[] arr_b, String s1) {
        this.zza = v;
        try {
            this.zzb = ProtocolVersion.fromString(s);
            this.zzc = arr_b;
            this.zzd = s1;
        }
        catch(UnsupportedProtocolException protocolVersion$UnsupportedProtocolException0) {
            throw new IllegalArgumentException(protocolVersion$UnsupportedProtocolException0);
        }
    }

    public RegisterRequest(ProtocolVersion protocolVersion0, byte[] arr_b, String s) {
        boolean z = true;
        this.zza = 1;
        this.zzb = (ProtocolVersion)Preconditions.checkNotNull(protocolVersion0);
        this.zzc = (byte[])Preconditions.checkNotNull(arr_b);
        if(protocolVersion0 == ProtocolVersion.V1) {
            if(arr_b.length != 65) {
                z = false;
            }
            Preconditions.checkArgument(z, "invalid challengeValue length for V1");
        }
        this.zzd = s;
    }

    @Override
    public boolean equals(Object object0) {
        if(this == object0) {
            return true;
        }
        if(!(object0 instanceof RegisterRequest)) {
            return false;
        }
        if(!Arrays.equals(this.zzc, ((RegisterRequest)object0).zzc)) {
            return false;
        }
        if(this.zzb != ((RegisterRequest)object0).zzb) {
            return false;
        }
        return this.zzd == null ? ((RegisterRequest)object0).zzd == null : this.zzd.equals(((RegisterRequest)object0).zzd);
    }

    public String getAppId() {
        return this.zzd;
    }

    public byte[] getChallengeValue() {
        return this.zzc;
    }

    public ProtocolVersion getProtocolVersion() {
        return this.zzb;
    }

    public int getVersionCode() {
        return this.zza;
    }

    @Override
    public int hashCode() {
        int v = Arrays.hashCode(this.zzc);
        int v1 = this.zzb.hashCode();
        return this.zzd == null ? ((v + 0x1F) * 0x1F + v1) * 0x1F : ((v + 0x1F) * 0x1F + v1) * 0x1F + this.zzd.hashCode();
    }

    public static RegisterRequest parseFromJson(JSONObject jSONObject0) throws JSONException {
        byte[] arr_b;
        ProtocolVersion protocolVersion0;
        String s = null;
        String s1 = jSONObject0.has("version") ? jSONObject0.getString("version") : null;
        try {
            protocolVersion0 = ProtocolVersion.fromString(s1);
        }
        catch(UnsupportedProtocolException protocolVersion$UnsupportedProtocolException0) {
            throw new JSONException(protocolVersion$UnsupportedProtocolException0.toString());
        }
        String s2 = jSONObject0.getString("challenge");
        try {
            arr_b = Base64.decode(s2, 8);
        }
        catch(IllegalArgumentException illegalArgumentException0) {
            throw new JSONException(illegalArgumentException0.toString());
        }
        if(jSONObject0.has("appId")) {
            s = jSONObject0.getString("appId");
        }
        try {
            return new RegisterRequest(protocolVersion0, arr_b, s);
        }
        catch(IllegalArgumentException illegalArgumentException1) {
            throw new JSONException(illegalArgumentException1.getMessage());
        }
    }

    public JSONObject toJson() {
        JSONObject jSONObject0 = new JSONObject();
        try {
            jSONObject0.put("version", this.zzb.toString());
            jSONObject0.put("challenge", Base64.encodeToString(this.zzc, 11));
            String s = this.zzd;
            if(s != null) {
                jSONObject0.put("appId", s);
            }
            return jSONObject0;
        }
        catch(JSONException jSONException0) {
            throw new RuntimeException(jSONException0);
        }
    }

    @Override  // android.os.Parcelable
    public void writeToParcel(Parcel parcel0, int v) {
        int v1 = SafeParcelWriter.beginObjectHeader(parcel0);
        SafeParcelWriter.writeInt(parcel0, 1, this.getVersionCode());
        SafeParcelWriter.writeString(parcel0, 2, this.zzb.toString(), false);
        SafeParcelWriter.writeByteArray(parcel0, 3, this.getChallengeValue(), false);
        SafeParcelWriter.writeString(parcel0, 4, this.getAppId(), false);
        SafeParcelWriter.finishObjectHeader(parcel0, v1);
    }
}

