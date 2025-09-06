package com.google.android.gms.auth.api.proxy;

import com.google.android.gms.common.api.HasApiKey;
import com.google.android.gms.tasks.Task;

public interface ProxyClient extends HasApiKey {
    Task getSpatulaHeader();

    Task performProxyRequest(ProxyRequest arg1);
}

