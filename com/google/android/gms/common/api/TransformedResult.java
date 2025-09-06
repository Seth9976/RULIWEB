package com.google.android.gms.common.api;

import com.google.errorprone.annotations.ResultIgnorabilityUnspecified;

public abstract class TransformedResult {
    public abstract void andFinally(ResultCallbacks arg1);

    @ResultIgnorabilityUnspecified
    public abstract TransformedResult then(ResultTransform arg1);
}

