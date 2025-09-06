package com.google.android.gms.fido.u2f.api.common;

import java.util.Locale;
import org.json.JSONException;
import org.json.JSONObject;

@Deprecated
public class Error {
    public static final String JSON_ERROR_CODE = "errorCode";
    public static final String JSON_ERROR_MESSAGE = "errorMessage";
    private final ErrorCode zza;
    private final String zzb;

    public Error(ErrorCode errorCode0) {
        this.zza = errorCode0;
        this.zzb = null;
    }

    public Error(ErrorCode errorCode0, String s) {
        this.zza = errorCode0;
        this.zzb = s;
    }

    public ErrorCode getErrorCode() {
        return this.zza;
    }

    public String getErrorMessage() {
        return this.zzb;
    }

    public JSONObject toJsonObject() {
        JSONObject jSONObject0 = new JSONObject();
        try {
            jSONObject0.put("errorCode", this.zza.getCode());
            String s = this.zzb;
            if(s != null) {
                jSONObject0.put("errorMessage", s);
            }
            return jSONObject0;
        }
        catch(JSONException jSONException0) {
            throw new RuntimeException(jSONException0);
        }
    }

    @Override
    public String toString() {
        return this.zzb == null ? String.format(Locale.ENGLISH, "{errorCode: %d}", this.zza.getCode()) : String.format(Locale.ENGLISH, "{errorCode: %d, errorMessage: %s}", this.zza.getCode(), this.zzb);
    }
}

