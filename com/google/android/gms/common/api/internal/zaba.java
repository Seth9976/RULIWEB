package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.api.Status;

final class zaba implements OnConnectionFailedListener {
    final StatusPendingResult zaa;

    zaba(zabe zabe0, StatusPendingResult statusPendingResult0) {
        this.zaa = statusPendingResult0;
        super();
    }

    @Override  // com.google.android.gms.common.api.internal.OnConnectionFailedListener
    public final void onConnectionFailed(ConnectionResult connectionResult0) {
        Status status0 = new Status(8);
        this.zaa.setResult(status0);
    }
}

