package com.google.android.gms.common.api;

import com.google.android.gms.common.internal.Preconditions;
import com.google.errorprone.annotations.ResultIgnorabilityUnspecified;
import java.util.concurrent.TimeUnit;

public final class BatchResult implements Result {
    private final Status zaa;
    private final PendingResult[] zab;

    BatchResult(Status status0, PendingResult[] arr_pendingResult) {
        this.zaa = status0;
        this.zab = arr_pendingResult;
    }

    @Override  // com.google.android.gms.common.api.Result
    public Status getStatus() {
        return this.zaa;
    }

    @ResultIgnorabilityUnspecified
    public Result take(BatchResultToken batchResultToken0) {
        Preconditions.checkArgument(batchResultToken0.mId < this.zab.length, "The result token does not belong to this batch");
        return this.zab[batchResultToken0.mId].await(0L, TimeUnit.MILLISECONDS);
    }
}

