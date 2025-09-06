package com.google.android.gms.common.api;

import com.google.android.gms.common.Feature;

public final class UnsupportedApiCallException extends UnsupportedOperationException {
    private final Feature zza;

    public UnsupportedApiCallException(Feature feature0) {
        this.zza = feature0;
    }

    @Override
    public String getMessage() {
        return "Missing " + this.zza;
    }
}

