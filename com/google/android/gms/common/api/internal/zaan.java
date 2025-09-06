package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.internal.BaseGmsClient.ConnectionProgressReportCallbacks;

final class zaan extends zabg {
    final ConnectionProgressReportCallbacks zaa;

    zaan(zaao zaao0, zabf zabf0, ConnectionProgressReportCallbacks baseGmsClient$ConnectionProgressReportCallbacks0) {
        this.zaa = baseGmsClient$ConnectionProgressReportCallbacks0;
        super(zabf0);
    }

    @Override  // com.google.android.gms.common.api.internal.zabg
    public final void zaa() {
        ConnectionResult connectionResult0 = new ConnectionResult(16, null);
        this.zaa.onReportServiceBinding(connectionResult0);
    }
}

