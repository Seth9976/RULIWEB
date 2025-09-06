package com.google.android.gms.common.api.internal;

import android.os.Bundle;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.internal.Preconditions;
import java.util.concurrent.atomic.AtomicReference;

final class zaaz implements ConnectionCallbacks {
    final AtomicReference zaa;
    final StatusPendingResult zab;
    final zabe zac;

    zaaz(zabe zabe0, AtomicReference atomicReference0, StatusPendingResult statusPendingResult0) {
        this.zac = zabe0;
        this.zaa = atomicReference0;
        this.zab = statusPendingResult0;
        super();
    }

    @Override  // com.google.android.gms.common.api.internal.ConnectionCallbacks
    public final void onConnected(Bundle bundle0) {
        GoogleApiClient googleApiClient0 = (GoogleApiClient)Preconditions.checkNotNull(((GoogleApiClient)this.zaa.get()));
        zabe.zah(this.zac, googleApiClient0, this.zab, true);
    }

    @Override  // com.google.android.gms.common.api.internal.ConnectionCallbacks
    public final void onConnectionSuspended(int v) {
    }
}

