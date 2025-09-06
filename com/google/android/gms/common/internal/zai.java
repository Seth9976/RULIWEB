package com.google.android.gms.common.internal;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.internal.OnConnectionFailedListener;

final class zai implements BaseOnConnectionFailedListener {
    final OnConnectionFailedListener zaa;

    zai(OnConnectionFailedListener onConnectionFailedListener0) {
        this.zaa = onConnectionFailedListener0;
        super();
    }

    @Override  // com.google.android.gms.common.internal.BaseGmsClient$BaseOnConnectionFailedListener
    public final void onConnectionFailed(ConnectionResult connectionResult0) {
        this.zaa.onConnectionFailed(connectionResult0);
    }
}

