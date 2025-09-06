package com.google.android.gms.common.api;

import android.os.Looper;
import com.google.android.gms.common.api.internal.BasePendingResult;

final class zaf extends BasePendingResult {
    private final Result zae;

    public zaf(Result result0) {
        super(Looper.getMainLooper());
        this.zae = result0;
    }

    @Override  // com.google.android.gms.common.api.internal.BasePendingResult
    protected final Result createFailedResult(Status status0) {
        if(status0.getStatusCode() != this.zae.getStatus().getStatusCode()) {
            throw new UnsupportedOperationException("Creating failed results is not supported");
        }
        return this.zae;
    }
}

