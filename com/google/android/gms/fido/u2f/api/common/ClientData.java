package com.google.android.gms.fido.u2f.api.common;

import com.google.android.gms.common.internal.Preconditions;
import org.json.JSONException;
import org.json.JSONObject;

@Deprecated
public class ClientData {
    public static class Builder implements Cloneable {
        private String zza;
        private String zzb;
        private String zzc;
        private ChannelIdValue zzd;

        Builder() {
            this.zzd = ChannelIdValue.ABSENT;
        }

        Builder(String s, String s1, String s2, ChannelIdValue channelIdValue0) {
            this.zza = s;
            this.zzb = s1;
            this.zzc = s2;
            this.zzd = channelIdValue0;
        }

        public ClientData build() {
            return new ClientData(this.zza, this.zzb, this.zzc, this.zzd);
        }

        public Builder clone() {
            return new Builder(this.zza, this.zzb, this.zzc, this.zzd);
        }

        @Override
        public final Object clone() throws CloneNotSupportedException {
            return this.clone();
        }

        public static Builder newInstance() {
            return new Builder();
        }

        public Builder setChallenge(String s) {
            this.zzb = s;
            return this;
        }

        public Builder setChannelId(ChannelIdValue channelIdValue0) {
            this.zzd = channelIdValue0;
            return this;
        }

        public Builder setOrigin(String s) {
            this.zzc = s;
            return this;
        }

        public Builder setType(String s) {
            this.zza = s;
            return this;
        }
    }

    public static final String KEY_CHALLENGE = "challenge";
    public static final String KEY_CID_PUBKEY = "cid_pubkey";
    public static final String KEY_ORIGIN = "origin";
    public static final String KEY_TYPE = "typ";
    public static final String TYPE_FINISH_ENROLLMENT = "navigator.id.finishEnrollment";
    public static final String TYPE_GET_ASSERTION = "navigator.id.getAssertion";
    private final String zza;
    private final String zzb;
    private final String zzc;
    private final ChannelIdValue zzd;

    ClientData(String s, String s1, String s2, ChannelIdValue channelIdValue0) {
        this.zza = (String)Preconditions.checkNotNull(s);
        this.zzb = (String)Preconditions.checkNotNull(s1);
        this.zzc = (String)Preconditions.checkNotNull(s2);
        this.zzd = (ChannelIdValue)Preconditions.checkNotNull(channelIdValue0);
    }

    @Override
    public boolean equals(Object object0) {
        if(this == object0) {
            return true;
        }
        return object0 instanceof ClientData ? this.zza.equals(((ClientData)object0).zza) && this.zzb.equals(((ClientData)object0).zzb) && this.zzc.equals(((ClientData)object0).zzc) && this.zzd.equals(((ClientData)object0).zzd) : false;
    }

    @Override
    public int hashCode() {
        return (((this.zza.hashCode() + 0x1F) * 0x1F + this.zzb.hashCode()) * 0x1F + this.zzc.hashCode()) * 0x1F + this.zzd.hashCode();
    }

    public String toJsonString() {
        JSONObject jSONObject0 = new JSONObject();
        try {
            jSONObject0.put("typ", this.zza);
            jSONObject0.put("challenge", this.zzb);
            jSONObject0.put("origin", this.zzc);
            switch(this.zzd.getType().ordinal()) {
                case 1: {
                    jSONObject0.put("cid_pubkey", this.zzd.getStringValue());
                    return jSONObject0.toString();
                }
                case 2: {
                    jSONObject0.put("cid_pubkey", this.zzd.getObjectValue());
                    return jSONObject0.toString();
                }
                default: {
                    return jSONObject0.toString();
                }
            }
        }
        catch(JSONException jSONException0) {
            throw new RuntimeException(jSONException0);
        }
    }
}

