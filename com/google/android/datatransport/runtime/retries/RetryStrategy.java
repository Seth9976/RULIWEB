package com.google.android.datatransport.runtime.retries;

public interface RetryStrategy {
    Object shouldRetry(Object arg1, Object arg2);
}

