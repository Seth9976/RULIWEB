package com.google.android.gms.common.api.internal;

import android.os.Bundle;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.internal.Preconditions;

public final class zat implements ConnectionCallbacks, OnConnectionFailedListener {
    public final Api zaa;
    private final boolean zab;
    private zau zac;

    public zat(Api api0, boolean z) {
        this.zaa = api0;
        this.zab = z;
    }

    @Override  // com.google.android.gms.common.api.internal.ConnectionCallbacks
    public final void onConnected(Bundle bundle0) {
        this.zab().onConnected(bundle0);
    }

    @Override  // com.google.android.gms.common.api.internal.OnConnectionFailedListener
    public final void onConnectionFailed(ConnectionResult connectionResult0) {
        this.zab().zaa(connectionResult0, this.zaa, this.zab);
    }

    @Override  // com.google.android.gms.common.api.internal.ConnectionCallbacks
    public final void onConnectionSuspended(int v) {
        this.zab().onConnectionSuspended(v);
    }

    public final void zaa(zau zau0) {
        this.zac = zau0;
    }

    private final zau zab() {
        Preconditions.checkNotNull(this.zac, "Callbacks must be attached to a ClientConnectionHelper instance before connecting the client.");
        return this.zac;
    }
}

