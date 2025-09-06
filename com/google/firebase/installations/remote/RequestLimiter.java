package com.google.firebase.installations.remote;

import com.google.firebase.installations.Utils;
import java.util.concurrent.TimeUnit;

class RequestLimiter {
    private static final long MAXIMUM_BACKOFF_DURATION_FOR_CONFIGURATION_ERRORS;
    private static final long MAXIMUM_BACKOFF_DURATION_FOR_SERVER_ERRORS;
    private int attemptCount;
    private long nextRequestTime;
    private final Utils utils;

    static {
        RequestLimiter.MAXIMUM_BACKOFF_DURATION_FOR_CONFIGURATION_ERRORS = TimeUnit.HOURS.toMillis(24L);
        RequestLimiter.MAXIMUM_BACKOFF_DURATION_FOR_SERVER_ERRORS = TimeUnit.MINUTES.toMillis(30L);
    }

    RequestLimiter() {
        this.utils = Utils.getInstance();
    }

    RequestLimiter(Utils utils0) {
        this.utils = utils0;
    }

    private long getBackoffDuration(int v) {
        synchronized(this) {
            if(!RequestLimiter.isRetryableError(v)) {
                return RequestLimiter.MAXIMUM_BACKOFF_DURATION_FOR_CONFIGURATION_ERRORS;
            }
        }
        return (long)Math.min(Math.pow(2.0, this.attemptCount) + 615.0, RequestLimiter.MAXIMUM_BACKOFF_DURATION_FOR_SERVER_ERRORS);
    }

    public boolean isRequestAllowed() {
        synchronized(this) {
            return this.attemptCount == 0 || this.utils.currentTimeInMillis() > this.nextRequestTime;
        }
    }

    private static boolean isRetryableError(int v) {
        return v == 429 || v >= 500 && v < 600;
    }

    private static boolean isSuccessfulOrRequiresNewFidCreation(int v) {
        return v >= 200 && v < 300 || (v == 401 || v == 404);
    }

    private void resetBackoffStrategy() {
        synchronized(this) {
            this.attemptCount = 0;
        }
    }

    public void setNextRequestTime(int v) {
        synchronized(this) {
            if(RequestLimiter.isSuccessfulOrRequiresNewFidCreation(v)) {
                this.resetBackoffStrategy();
                return;
            }
            ++this.attemptCount;
            long v2 = this.getBackoffDuration(v);
            this.nextRequestTime = this.utils.currentTimeInMillis() + v2;
        }
    }
}

