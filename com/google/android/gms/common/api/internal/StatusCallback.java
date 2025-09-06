package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.api.Status;

public class StatusCallback extends Stub {
    private final ResultHolder resultHolder;

    public StatusCallback(ResultHolder baseImplementation$ResultHolder0) {
        this.resultHolder = baseImplementation$ResultHolder0;
    }

    @Override  // com.google.android.gms.common.api.internal.IStatusCallback
    public void onResult(Status status0) {
        this.resultHolder.setResult(status0);
    }
}

