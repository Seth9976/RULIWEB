package com.google.android.gms.auth.api;

import com.google.android.gms.auth.api.proxy.ProxyApi;
import com.google.android.gms.auth.api.signin.GoogleSignInApi;
import com.google.android.gms.auth.api.signin.internal.zbd;
import com.google.android.gms.common.api.Api.AbstractClientBuilder;
import com.google.android.gms.common.api.Api.ClientKey;
import com.google.android.gms.common.api.Api;

public final class Auth {
    public static final Api GOOGLE_SIGN_IN_API;
    public static final GoogleSignInApi GoogleSignInApi;
    @Deprecated
    public static final Api PROXY_API;
    @Deprecated
    public static final ProxyApi ProxyApi;
    public static final ClientKey zba;
    public static final ClientKey zbb;
    private static final AbstractClientBuilder zbc;
    private static final AbstractClientBuilder zbd;

    static {
        ClientKey api$ClientKey0 = new ClientKey();
        Auth.zba = api$ClientKey0;
        ClientKey api$ClientKey1 = new ClientKey();
        Auth.zbb = api$ClientKey1;
        zba zba0 = new zba();
        Auth.zbc = zba0;
        zbb zbb0 = new zbb();
        Auth.zbd = zbb0;
        Auth.PROXY_API = AuthProxy.API;
        new Api("Auth.CREDENTIALS_API", zba0, api$ClientKey0);
        Auth.GOOGLE_SIGN_IN_API = new Api("Auth.GOOGLE_SIGN_IN_API", zbb0, api$ClientKey1);
        Auth.ProxyApi = AuthProxy.ProxyApi;
        Auth.GoogleSignInApi = new zbd();
    }
}

