package com.google.android.gms.auth.api;

import android.content.Context;
import android.os.Looper;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.internal.zbe;
import com.google.android.gms.common.api.Api.AbstractClientBuilder;
import com.google.android.gms.common.api.Api.Client;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.internal.ClientSettings;
import java.util.Collections;
import java.util.List;

final class zbb extends AbstractClientBuilder {
    @Override  // com.google.android.gms.common.api.Api$AbstractClientBuilder
    public final Client buildClient(Context context0, Looper looper0, ClientSettings clientSettings0, Object object0, ConnectionCallbacks googleApiClient$ConnectionCallbacks0, OnConnectionFailedListener googleApiClient$OnConnectionFailedListener0) {
        return new zbe(context0, looper0, clientSettings0, ((GoogleSignInOptions)object0), googleApiClient$ConnectionCallbacks0, googleApiClient$OnConnectionFailedListener0);
    }

    @Override  // com.google.android.gms.common.api.Api$BaseClientBuilder
    public final List getImpliedScopes(Object object0) {
        return ((GoogleSignInOptions)object0) == null ? Collections.EMPTY_LIST : ((GoogleSignInOptions)object0).getScopes();
    }
}

