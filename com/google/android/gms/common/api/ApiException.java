package com.google.android.gms.common.api;

public class ApiException extends Exception {
    @Deprecated
    protected final Status mStatus;

    public ApiException(Status status0) {
        super(status0.getStatusCode() + ": " + (status0.getStatusMessage() == null ? "" : status0.getStatusMessage()));
        this.mStatus = status0;
    }

    public Status getStatus() {
        return this.mStatus;
    }

    public int getStatusCode() {
        return this.mStatus.getStatusCode();
    }

    @Deprecated
    public String getStatusMessage() {
        return this.mStatus.getStatusMessage();
    }
}

