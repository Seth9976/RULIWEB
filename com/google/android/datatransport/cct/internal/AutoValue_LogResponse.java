package com.google.android.datatransport.cct.internal;

final class AutoValue_LogResponse extends LogResponse {
    private final long nextRequestWaitMillis;

    AutoValue_LogResponse(long v) {
        this.nextRequestWaitMillis = v;
    }

    @Override
    public boolean equals(Object object0) {
        if(object0 == this) {
            return true;
        }
        if(object0 instanceof LogResponse) {
            long v = ((LogResponse)object0).getNextRequestWaitMillis();
            return this.nextRequestWaitMillis == v;
        }
        return false;
    }

    @Override  // com.google.android.datatransport.cct.internal.LogResponse
    public long getNextRequestWaitMillis() {
        return this.nextRequestWaitMillis;
    }

    @Override
    public int hashCode() {
        return 1000003 ^ ((int)(this.nextRequestWaitMillis ^ this.nextRequestWaitMillis >>> 0x20));
    }

    @Override
    public String toString() {
        return "LogResponse{nextRequestWaitMillis=" + this.nextRequestWaitMillis + "}";
    }
}

