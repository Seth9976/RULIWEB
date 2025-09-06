package com.google.android.gms.fido.fido2.api.common;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.internal.fido.zzal;
import java.util.Arrays;
import org.json.JSONException;
import org.json.JSONObject;

public class TokenBinding extends AbstractSafeParcelable {
    public static enum TokenBindingStatus implements Parcelable {
        PRESENT("present"),
        SUPPORTED("supported"),
        NOT_SUPPORTED("not-supported");

        public static final Parcelable.Creator CREATOR;
        private final String zzb;

        static {
            TokenBindingStatus.CREATOR = new zzat();
        }

        private TokenBindingStatus(String s1) {
            this.zzb = s1;
        }

        @Override  // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        public static TokenBindingStatus fromString(String s) throws UnsupportedTokenBindingStatusException {
            TokenBindingStatus[] arr_tokenBinding$TokenBindingStatus = TokenBindingStatus.values();
            for(int v = 0; v < arr_tokenBinding$TokenBindingStatus.length; ++v) {
                TokenBindingStatus tokenBinding$TokenBindingStatus0 = arr_tokenBinding$TokenBindingStatus[v];
                if(s.equals(tokenBinding$TokenBindingStatus0.zzb)) {
                    return tokenBinding$TokenBindingStatus0;
                }
            }
            throw new UnsupportedTokenBindingStatusException(s);
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

    public static class UnsupportedTokenBindingStatusException extends Exception {
        public UnsupportedTokenBindingStatusException(String s) {
            super(String.format("TokenBindingStatus %s not supported", s));
        }
    }

    public static final Parcelable.Creator CREATOR;
    public static final TokenBinding NOT_SUPPORTED;
    public static final TokenBinding SUPPORTED;
    private final TokenBindingStatus zza;
    private final String zzb;

    static {
        TokenBinding.CREATOR = new zzau();
        TokenBinding.SUPPORTED = new TokenBinding("supported", null);
        TokenBinding.NOT_SUPPORTED = new TokenBinding("not-supported", null);
    }

    public TokenBinding(String s) {
        this("present", ((String)Preconditions.checkNotNull(s)));
    }

    TokenBinding(String s, String s1) {
        Preconditions.checkNotNull(s);
        try {
            this.zza = TokenBindingStatus.fromString(s);
            this.zzb = s1;
        }
        catch(UnsupportedTokenBindingStatusException tokenBinding$UnsupportedTokenBindingStatusException0) {
            throw new IllegalArgumentException(tokenBinding$UnsupportedTokenBindingStatusException0);
        }
    }

    // 去混淆评级： 低(30)
    @Override
    public boolean equals(Object object0) {
        return object0 instanceof TokenBinding ? zzal.zza(this.zza, ((TokenBinding)object0).zza) && zzal.zza(this.zzb, ((TokenBinding)object0).zzb) : false;
    }

    public String getTokenBindingId() {
        return this.zzb;
    }

    public String getTokenBindingStatusAsString() {
        return this.zza.toString();
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(new Object[]{this.zza, this.zzb});
    }

    public JSONObject toJsonObject() throws JSONException {
        try {
            return new JSONObject().put("status", this.zza).put("id", this.zzb);
        }
        catch(JSONException jSONException0) {
            throw new RuntimeException(jSONException0);
        }
    }

    @Override  // android.os.Parcelable
    public void writeToParcel(Parcel parcel0, int v) {
        int v1 = SafeParcelWriter.beginObjectHeader(parcel0);
        SafeParcelWriter.writeString(parcel0, 2, this.getTokenBindingStatusAsString(), false);
        SafeParcelWriter.writeString(parcel0, 3, this.getTokenBindingId(), false);
        SafeParcelWriter.finishObjectHeader(parcel0, v1);
    }
}

