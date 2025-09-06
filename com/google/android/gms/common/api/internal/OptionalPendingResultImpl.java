package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.api.OptionalPendingResult;
import com.google.android.gms.common.api.PendingResult.StatusListener;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.ResultTransform;
import com.google.android.gms.common.api.TransformedResult;
import java.util.concurrent.TimeUnit;

public final class OptionalPendingResultImpl extends OptionalPendingResult {
    private final BasePendingResult zaa;

    public OptionalPendingResultImpl(PendingResult pendingResult0) {
        this.zaa = (BasePendingResult)pendingResult0;
    }

    @Override  // com.google.android.gms.common.api.PendingResult
    public final void addStatusListener(StatusListener pendingResult$StatusListener0) {
        this.zaa.addStatusListener(pendingResult$StatusListener0);
    }

    @Override  // com.google.android.gms.common.api.PendingResult
    public final Result await() {
        return this.zaa.await();
    }

    @Override  // com.google.android.gms.common.api.PendingResult
    public final Result await(long v, TimeUnit timeUnit0) {
        return this.zaa.await(v, timeUnit0);
    }

    @Override  // com.google.android.gms.common.api.PendingResult
    public final void cancel() {
        this.zaa.cancel();
    }

    @Override  // com.google.android.gms.common.api.OptionalPendingResult
    public final Result get() {
        if(!this.zaa.isReady()) {
            throw new IllegalStateException("Result is not available. Check that isDone() returns true before calling get().");
        }
        return this.zaa.await(0L, TimeUnit.MILLISECONDS);
    }

    @Override  // com.google.android.gms.common.api.PendingResult
    public final boolean isCanceled() {
        return this.zaa.isCanceled();
    }

    @Override  // com.google.android.gms.common.api.OptionalPendingResult
    public final boolean isDone() {
        return this.zaa.isReady();
    }

    @Override  // com.google.android.gms.common.api.PendingResult
    public final void setResultCallback(ResultCallback resultCallback0) {
        this.zaa.setResultCallback(resultCallback0);
    }

    @Override  // com.google.android.gms.common.api.PendingResult
    public final void setResultCallback(ResultCallback resultCallback0, long v, TimeUnit timeUnit0) {
        this.zaa.setResultCallback(resultCallback0, v, timeUnit0);
    }

    @Override  // com.google.android.gms.common.api.PendingResult
    public final TransformedResult then(ResultTransform resultTransform0) {
        return this.zaa.then(resultTransform0);
    }
}

