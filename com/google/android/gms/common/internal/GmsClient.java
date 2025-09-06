package com.google.android.gms.common.internal;

import android.accounts.Account;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import com.google.android.gms.common.Feature;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.Api.Client;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.api.Scope;
import java.util.Collections;
import java.util.Set;
import java.util.concurrent.Executor;

public abstract class GmsClient extends BaseGmsClient implements Client, zaj {
    private static volatile Executor zaa;
    private final ClientSettings zab;
    private final Set zac;
    private final Account zad;

    protected GmsClient(Context context0, Handler handler0, int v, ClientSettings clientSettings0) {
        super(context0, handler0, GmsClientSupervisor.getInstance(context0), GoogleApiAvailability.getInstance(), v, null, null);
        this.zab = (ClientSettings)Preconditions.checkNotNull(clientSettings0);
        this.zad = clientSettings0.getAccount();
        this.zac = this.zaa(clientSettings0.getAllRequestedScopes());
    }

    protected GmsClient(Context context0, Looper looper0, int v, ClientSettings clientSettings0) {
        this(context0, looper0, GmsClientSupervisor.getInstance(context0), GoogleApiAvailability.getInstance(), v, clientSettings0, null, null);
    }

    @Deprecated
    protected GmsClient(Context context0, Looper looper0, int v, ClientSettings clientSettings0, ConnectionCallbacks googleApiClient$ConnectionCallbacks0, OnConnectionFailedListener googleApiClient$OnConnectionFailedListener0) {
        this(context0, looper0, v, clientSettings0, googleApiClient$ConnectionCallbacks0, googleApiClient$OnConnectionFailedListener0);
    }

    protected GmsClient(Context context0, Looper looper0, int v, ClientSettings clientSettings0, com.google.android.gms.common.api.internal.ConnectionCallbacks connectionCallbacks0, com.google.android.gms.common.api.internal.OnConnectionFailedListener onConnectionFailedListener0) {
        this(context0, looper0, GmsClientSupervisor.getInstance(context0), GoogleApiAvailability.getInstance(), v, clientSettings0, ((com.google.android.gms.common.api.internal.ConnectionCallbacks)Preconditions.checkNotNull(connectionCallbacks0)), ((com.google.android.gms.common.api.internal.OnConnectionFailedListener)Preconditions.checkNotNull(onConnectionFailedListener0)));
    }

    protected GmsClient(Context context0, Looper looper0, GmsClientSupervisor gmsClientSupervisor0, GoogleApiAvailability googleApiAvailability0, int v, ClientSettings clientSettings0, com.google.android.gms.common.api.internal.ConnectionCallbacks connectionCallbacks0, com.google.android.gms.common.api.internal.OnConnectionFailedListener onConnectionFailedListener0) {
        zai zai0 = null;
        zah zah0 = connectionCallbacks0 == null ? null : new zah(connectionCallbacks0);
        if(onConnectionFailedListener0 != null) {
            zai0 = new zai(onConnectionFailedListener0);
        }
        super(context0, looper0, gmsClientSupervisor0, googleApiAvailability0, v, zah0, zai0, clientSettings0.zac());
        this.zab = clientSettings0;
        this.zad = clientSettings0.getAccount();
        this.zac = this.zaa(clientSettings0.getAllRequestedScopes());
    }

    @Override  // com.google.android.gms.common.internal.BaseGmsClient
    public final Account getAccount() {
        return this.zad;
    }

    @Override  // com.google.android.gms.common.internal.BaseGmsClient
    protected Executor getBindServiceExecutor() {
        return null;
    }

    protected final ClientSettings getClientSettings() {
        return this.zab;
    }

    @Override  // com.google.android.gms.common.api.Api$Client
    public Feature[] getRequiredFeatures() {
        return new Feature[0];
    }

    @Override  // com.google.android.gms.common.internal.BaseGmsClient
    protected final Set getScopes() {
        return this.zac;
    }

    // 去混淆评级： 低(20)
    @Override  // com.google.android.gms.common.api.Api$Client
    public Set getScopesForConnectionlessNonSignIn() {
        return this.requiresSignIn() ? this.zac : Collections.EMPTY_SET;
    }

    protected Set validateScopes(Set set0) [...] // Inlined contents

    private final Set zaa(Set set0) {
        for(Object object0: set0) {
            if(!set0.contains(((Scope)object0))) {
                throw new IllegalStateException("Expanding scopes is not permitted, use implied scopes instead");
            }
            if(false) {
                break;
            }
        }
        return set0;
    }
}

