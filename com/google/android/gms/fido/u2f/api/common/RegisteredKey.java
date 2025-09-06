package com.google.android.gms.fido.u2f.api.common;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.util.Base64;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import java.util.Iterator;
import org.json.JSONException;
import org.json.JSONObject;

@Deprecated
public class RegisteredKey extends AbstractSafeParcelable {
    public static final Parcelable.Creator CREATOR;
    String zza;
    private final KeyHandle zzb;
    private final String zzc;

    static {
        RegisteredKey.CREATOR = new zzj();
    }

    public RegisteredKey(KeyHandle keyHandle0) {
        this(keyHandle0, null, null);
    }

    public RegisteredKey(KeyHandle keyHandle0, String s, String s1) {
        this.zzb = (KeyHandle)Preconditions.checkNotNull(keyHandle0);
        this.zza = s;
        this.zzc = s1;
    }

    @Override
    public boolean equals(Object object0) {
        if(this == object0) {
            return true;
        }
        if(!(object0 instanceof RegisteredKey)) {
            return false;
        }
        String s = this.zza;
        if(s == null) {
            if(((RegisteredKey)object0).zza != null) {
                return false;
            }
        }
        else if(!s.equals(((RegisteredKey)object0).zza)) {
            return false;
        }
        if(!this.zzb.equals(((RegisteredKey)object0).zzb)) {
            return false;
        }
        return this.zzc == null ? ((RegisteredKey)object0).zzc == null : this.zzc.equals(((RegisteredKey)object0).zzc);
    }

    public String getAppId() {
        return this.zzc;
    }

    public String getChallengeValue() {
        return this.zza;
    }

    public KeyHandle getKeyHandle() {
        return this.zzb;
    }

    @Override
    public int hashCode() {
        int v = 0;
        int v1 = this.zza == null ? 0 : this.zza.hashCode();
        int v2 = this.zzb.hashCode();
        String s = this.zzc;
        if(s != null) {
            v = s.hashCode();
        }
        return ((v1 + 0x1F) * 0x1F + v2) * 0x1F + v;
    }

    public static RegisteredKey parseFromJson(JSONObject jSONObject0) throws JSONException {
        String s = null;
        String s1 = jSONObject0.has("challenge") ? jSONObject0.getString("challenge") : null;
        KeyHandle keyHandle0 = KeyHandle.parseFromJson(jSONObject0);
        if(jSONObject0.has("appId")) {
            s = jSONObject0.getString("appId");
        }
        return new RegisteredKey(keyHandle0, s1, s);
    }

    public JSONObject toJson() {
        JSONObject jSONObject0 = new JSONObject();
        try {
            String s = this.zza;
            if(s != null) {
                jSONObject0.put("challenge", s);
            }
            JSONObject jSONObject1 = this.zzb.zza();
            Iterator iterator0 = jSONObject1.keys();
            while(iterator0.hasNext()) {
                Object object0 = iterator0.next();
                jSONObject0.put(((String)object0), jSONObject1.get(((String)object0)));
            }
            String s1 = this.zzc;
            if(s1 != null) {
                jSONObject0.put("appId", s1);
            }
            return jSONObject0;
        }
        catch(JSONException jSONException0) {
            throw new RuntimeException(jSONException0);
        }
    }

    @Override
    public String toString() {
        try {
            JSONObject jSONObject0 = new JSONObject();
            jSONObject0.put("keyHandle", Base64.encodeToString(this.zzb.getBytes(), 11));
            if(this.zzb.getProtocolVersion() != ProtocolVersion.UNKNOWN) {
                jSONObject0.put("version", this.zzb.getProtocolVersion().toString());
            }
            if(this.zzb.getTransports() != null) {
                jSONObject0.put("transports", this.zzb.getTransports().toString());
            }
            String s = this.zza;
            if(s != null) {
                jSONObject0.put("challenge", s);
            }
            String s1 = this.zzc;
            if(s1 != null) {
                jSONObject0.put("appId", s1);
            }
            return jSONObject0.toString();
        }
        catch(JSONException jSONException0) {
            throw new RuntimeException(jSONException0);
        }
    }

    @Override  // android.os.Parcelable
    public void writeToParcel(Parcel parcel0, int v) {
        int v1 = SafeParcelWriter.beginObjectHeader(parcel0);
        SafeParcelWriter.writeParcelable(parcel0, 2, this.getKeyHandle(), v, false);
        SafeParcelWriter.writeString(parcel0, 3, this.getChallengeValue(), false);
        SafeParcelWriter.writeString(parcel0, 4, this.getAppId(), false);
        SafeParcelWriter.finishObjectHeader(parcel0, v1);
    }
}

