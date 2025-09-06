package com.google.android.gms.internal.auth;

import android.util.Log;
import com.google.android.gms.common.server.response.FastSafeParcelableJsonResponse;
import java.io.UnsupportedEncodingException;

public abstract class zzbz extends FastSafeParcelableJsonResponse {
    @Override  // com.google.android.gms.common.server.response.FastSafeParcelableJsonResponse
    public final byte[] toByteArray() {
        try {
            return this.toString().getBytes("UTF-8");
        }
        catch(UnsupportedEncodingException unsupportedEncodingException0) {
            Log.e("AUTH", "Error serializing object.", unsupportedEncodingException0);
            return null;
        }
    }
}

