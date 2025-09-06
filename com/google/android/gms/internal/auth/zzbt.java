package com.google.android.gms.internal.auth;

import com.google.android.gms.auth.api.proxy.ProxyApi;
import com.google.android.gms.auth.api.proxy.ProxyRequest;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.internal.Preconditions;

public final class zzbt implements ProxyApi {
    @Override  // com.google.android.gms.auth.api.proxy.ProxyApi
    public final PendingResult getSpatulaHeader(GoogleApiClient googleApiClient0) {
        Preconditions.checkNotNull(googleApiClient0);
        return googleApiClient0.execute(new zzbs(this, googleApiClient0));
    }

    @Override  // com.google.android.gms.auth.api.proxy.ProxyApi
    public final PendingResult performProxyRequest(GoogleApiClient googleApiClient0, ProxyRequest proxyRequest0) {
        Preconditions.checkNotNull(googleApiClient0);
        Preconditions.checkNotNull(proxyRequest0);
        return googleApiClient0.execute(new zzbq(this, googleApiClient0, proxyRequest0));
    }
}

