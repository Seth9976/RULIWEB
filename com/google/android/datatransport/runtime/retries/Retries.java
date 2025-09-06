package com.google.android.datatransport.runtime.retries;

public final class Retries {
    public static Object retry(int v, Object object0, Function function0, RetryStrategy retryStrategy0) throws Throwable {
        Object object1;
        if(v < 1) {
            return function0.apply(object0);
        }
        do {
            object1 = function0.apply(object0);
            object0 = retryStrategy0.shouldRetry(object0, object1);
            if(object0 == null) {
                break;
            }
            --v;
        }
        while(v >= 1);
        return object1;
    }
}

