package com.google.android.gms.auth.api.proxy;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Result;

public interface ProxyApi {
    public interface ProxyResult extends Result {
        ProxyResponse getResponse();
    }

    public interface SpatulaHeaderResult extends Result {
        String getSpatulaHeader();
    }

    @Deprecated
    PendingResult getSpatulaHeader(GoogleApiClient arg1);

    @Deprecated
    PendingResult performProxyRequest(GoogleApiClient arg1, ProxyRequest arg2);
}

