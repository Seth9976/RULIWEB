package com.google.android.gms.common.api;

import com.google.android.gms.common.api.internal.zacp;

public abstract class ResultTransform {
    public final PendingResult createFailedResult(Status status0) {
        return new zacp(status0);
    }

    public Status onFailure(Status status0) [...] // Inlined contents

    public abstract PendingResult onSuccess(Result arg1);
}

