package com.google.android.datatransport.runtime.backends;

public abstract class BackendResponse {
    public static enum Status {
        OK,
        TRANSIENT_ERROR,
        FATAL_ERROR,
        INVALID_PAYLOAD;

    }

    public static BackendResponse fatalError() {
        return new AutoValue_BackendResponse(Status.FATAL_ERROR, -1L);
    }

    public abstract long getNextRequestWaitMillis();

    public abstract Status getStatus();

    public static BackendResponse invalidPayload() {
        return new AutoValue_BackendResponse(Status.INVALID_PAYLOAD, -1L);
    }

    public static BackendResponse ok(long v) {
        return new AutoValue_BackendResponse(Status.OK, v);
    }

    public static BackendResponse transientError() {
        return new AutoValue_BackendResponse(Status.TRANSIENT_ERROR, -1L);
    }
}

