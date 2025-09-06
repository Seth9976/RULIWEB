package com.google.android.gms.common.api;

import android.os.Looper;
import com.google.android.gms.common.api.internal.BasePendingResult;
import com.google.android.gms.common.api.internal.OptionalPendingResultImpl;
import com.google.android.gms.common.api.internal.StatusPendingResult;
import com.google.android.gms.common.internal.Preconditions;

public final class PendingResults {
    public static PendingResult canceledPendingResult() {
        PendingResult pendingResult0 = new StatusPendingResult(Looper.getMainLooper());
        ((BasePendingResult)pendingResult0).cancel();
        return pendingResult0;
    }

    public static PendingResult canceledPendingResult(Result result0) {
        Preconditions.checkNotNull(result0, "Result must not be null");
        Preconditions.checkArgument(result0.getStatus().getStatusCode() == 16, "Status code must be CommonStatusCodes.CANCELED");
        PendingResult pendingResult0 = new zaf(result0);
        ((BasePendingResult)pendingResult0).cancel();
        return pendingResult0;
    }

    public static PendingResult immediateFailedResult(Result result0, GoogleApiClient googleApiClient0) {
        Preconditions.checkNotNull(result0, "Result must not be null");
        Preconditions.checkArgument(!result0.getStatus().isSuccess(), "Status code must not be SUCCESS");
        PendingResult pendingResult0 = new zag(googleApiClient0, result0);
        ((BasePendingResult)pendingResult0).setResult(result0);
        return pendingResult0;
    }

    public static OptionalPendingResult immediatePendingResult(Result result0) {
        Preconditions.checkNotNull(result0, "Result must not be null");
        zah zah0 = new zah(null);
        zah0.setResult(result0);
        return new OptionalPendingResultImpl(zah0);
    }

    public static OptionalPendingResult immediatePendingResult(Result result0, GoogleApiClient googleApiClient0) {
        Preconditions.checkNotNull(result0, "Result must not be null");
        zah zah0 = new zah(googleApiClient0);
        zah0.setResult(result0);
        return new OptionalPendingResultImpl(zah0);
    }

    public static PendingResult immediatePendingResult(Status status0) {
        Preconditions.checkNotNull(status0, "Result must not be null");
        PendingResult pendingResult0 = new StatusPendingResult(Looper.getMainLooper());
        ((BasePendingResult)pendingResult0).setResult(status0);
        return pendingResult0;
    }

    public static PendingResult immediatePendingResult(Status status0, GoogleApiClient googleApiClient0) {
        Preconditions.checkNotNull(status0, "Result must not be null");
        PendingResult pendingResult0 = new StatusPendingResult(googleApiClient0);
        ((BasePendingResult)pendingResult0).setResult(status0);
        return pendingResult0;
    }
}

