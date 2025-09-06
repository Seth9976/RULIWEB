package com.google.android.gms.common.internal;

import android.os.Bundle;
import com.google.android.gms.common.api.internal.ConnectionCallbacks;

final class zah implements BaseConnectionCallbacks {
    final ConnectionCallbacks zaa;

    zah(ConnectionCallbacks connectionCallbacks0) {
        this.zaa = connectionCallbacks0;
        super();
    }

    @Override  // com.google.android.gms.common.internal.BaseGmsClient$BaseConnectionCallbacks
    public final void onConnected(Bundle bundle0) {
        this.zaa.onConnected(bundle0);
    }

    @Override  // com.google.android.gms.common.internal.BaseGmsClient$BaseConnectionCallbacks
    public final void onConnectionSuspended(int v) {
        this.zaa.onConnectionSuspended(v);
    }
}

