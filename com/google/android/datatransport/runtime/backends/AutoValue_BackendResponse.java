package com.google.android.datatransport.runtime.backends;

final class AutoValue_BackendResponse extends BackendResponse {
    private final long nextRequestWaitMillis;
    private final Status status;

    AutoValue_BackendResponse(Status backendResponse$Status0, long v) {
        if(backendResponse$Status0 == null) {
            throw new NullPointerException("Null status");
        }
        this.status = backendResponse$Status0;
        this.nextRequestWaitMillis = v;
    }

    @Override
    public boolean equals(Object object0) {
        if(object0 == this) {
            return true;
        }
        if(object0 instanceof BackendResponse) {
            Status backendResponse$Status0 = ((BackendResponse)object0).getStatus();
            if(this.status.equals(backendResponse$Status0)) {
                long v = ((BackendResponse)object0).getNextRequestWaitMillis();
                return this.nextRequestWaitMillis == v;
            }
        }
        return false;
    }

    @Override  // com.google.android.datatransport.runtime.backends.BackendResponse
    public long getNextRequestWaitMillis() {
        return this.nextRequestWaitMillis;
    }

    @Override  // com.google.android.datatransport.runtime.backends.BackendResponse
    public Status getStatus() {
        return this.status;
    }

    @Override
    public int hashCode() {
        return (this.status.hashCode() ^ 1000003) * 1000003 ^ ((int)(this.nextRequestWaitMillis ^ this.nextRequestWaitMillis >>> 0x20));
    }

    @Override
    public String toString() {
        return "BackendResponse{status=" + this.status + ", nextRequestWaitMillis=" + this.nextRequestWaitMillis + "}";
    }
}

