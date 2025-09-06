package com.google.android.gms.common.api;

import com.google.android.gms.common.api.internal.BasePendingResult;

final class zag extends BasePendingResult {
    private final Result zae;

    public zag(GoogleApiClient googleApiClient0, Result result0) {
        super(googleApiClient0);
        this.zae = result0;
    }

    @Override  // com.google.android.gms.common.api.internal.BasePendingResult
    protected final Result createFailedResult(Status status0) {
        return this.zae;
    }
}

