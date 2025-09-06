package com.google.android.gms.auth.api;

import android.app.Activity;
import android.content.Context;
import com.google.android.gms.auth.api.proxy.ProxyApi;
import com.google.android.gms.auth.api.proxy.ProxyClient;
import com.google.android.gms.common.api.Api.AbstractClientBuilder;
import com.google.android.gms.common.api.Api.ClientKey;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.internal.auth.zzbo;
import com.google.android.gms.internal.auth.zzbt;

public final class AuthProxy {
    public static final Api API;
    public static final ProxyApi ProxyApi;
    public static final ClientKey zza;
    private static final AbstractClientBuilder zzb;

    static {
        ClientKey api$ClientKey0 = new ClientKey();
        AuthProxy.zza = api$ClientKey0;
        zza zza0 = new zza();
        AuthProxy.zzb = zza0;
        AuthProxy.API = new Api("Auth.PROXY_API", zza0, api$ClientKey0);
        AuthProxy.ProxyApi = new zzbt();
    }

    public static ProxyClient getClient(Activity activity0, AuthProxyOptions authProxyOptions0) {
        return new zzbo(activity0, authProxyOptions0);
    }

    public static ProxyClient getClient(Context context0, AuthProxyOptions authProxyOptions0) {
        return new zzbo(context0, authProxyOptions0);
    }
}

