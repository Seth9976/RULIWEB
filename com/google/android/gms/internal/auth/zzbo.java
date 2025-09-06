package com.google.android.gms.internal.auth;

import android.app.Activity;
import android.content.Context;
import com.google.android.gms.auth.api.AuthProxy;
import com.google.android.gms.auth.api.AuthProxyOptions;
import com.google.android.gms.auth.api.proxy.ProxyClient;
import com.google.android.gms.auth.api.proxy.ProxyRequest;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApi.Settings;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.api.internal.TaskApiCall;
import com.google.android.gms.tasks.Task;

public final class zzbo extends GoogleApi implements ProxyClient {
    public zzbo(Activity activity0, AuthProxyOptions authProxyOptions0) {
        Api api0 = AuthProxy.API;
        if(authProxyOptions0 == null) {
            authProxyOptions0 = AuthProxyOptions.zza;
        }
        super(activity0, api0, authProxyOptions0, Settings.DEFAULT_SETTINGS);
    }

    public zzbo(Context context0, AuthProxyOptions authProxyOptions0) {
        Api api0 = AuthProxy.API;
        if(authProxyOptions0 == null) {
            authProxyOptions0 = AuthProxyOptions.zza;
        }
        super(context0, api0, authProxyOptions0, Settings.DEFAULT_SETTINGS);
    }

    @Override  // com.google.android.gms.auth.api.proxy.ProxyClient
    public final Task getSpatulaHeader() {
        return this.doRead(TaskApiCall.builder().run(new zzbk(this)).setMethodKey(0x5F0).build());
    }

    @Override  // com.google.android.gms.auth.api.proxy.ProxyClient
    public final Task performProxyRequest(ProxyRequest proxyRequest0) {
        return this.doWrite(TaskApiCall.builder().run(new zzbl(this, proxyRequest0)).setMethodKey(0x5EE).build());
    }
}

