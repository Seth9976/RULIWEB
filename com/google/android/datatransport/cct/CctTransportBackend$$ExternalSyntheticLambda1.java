package com.google.android.datatransport.cct;

import com.google.android.datatransport.runtime.retries.RetryStrategy;

public final class CctTransportBackend..ExternalSyntheticLambda1 implements RetryStrategy {
    @Override  // com.google.android.datatransport.runtime.retries.RetryStrategy
    public final Object shouldRetry(Object object0, Object object1) {
        return CctTransportBackend.lambda$send$0(((HttpRequest)object0), ((HttpResponse)object1));
    }
}

