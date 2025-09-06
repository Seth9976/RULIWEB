package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.api.PendingResult.StatusListener;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.ResultTransform;
import com.google.android.gms.common.api.TransformedResult;
import com.google.errorprone.annotations.ResultIgnorabilityUnspecified;
import java.util.concurrent.TimeUnit;

public abstract class PendingResultFacade extends PendingResult {
    @Override  // com.google.android.gms.common.api.PendingResult
    public final void addStatusListener(StatusListener pendingResult$StatusListener0) {
        throw null;
    }

    @Override  // com.google.android.gms.common.api.PendingResult
    public final Result await() {
        throw null;
    }

    @Override  // com.google.android.gms.common.api.PendingResult
    public final Result await(long v, TimeUnit timeUnit0) {
        throw null;
    }

    @Override  // com.google.android.gms.common.api.PendingResult
    public final void cancel() {
        throw null;
    }

    @Override  // com.google.android.gms.common.api.PendingResult
    public final boolean isCanceled() {
        throw null;
    }

    @Override  // com.google.android.gms.common.api.PendingResult
    public final void setResultCallback(ResultCallback resultCallback0) {
        throw null;
    }

    @Override  // com.google.android.gms.common.api.PendingResult
    public final void setResultCallback(ResultCallback resultCallback0, long v, TimeUnit timeUnit0) {
        throw null;
    }

    @Override  // com.google.android.gms.common.api.PendingResult
    @ResultIgnorabilityUnspecified
    public final TransformedResult then(ResultTransform resultTransform0) {
        throw null;
    }
}

