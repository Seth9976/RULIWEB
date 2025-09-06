package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.internal.Preconditions;

final class zam {
    private final int zaa;
    private final ConnectionResult zab;

    zam(ConnectionResult connectionResult0, int v) {
        Preconditions.checkNotNull(connectionResult0);
        this.zab = connectionResult0;
        this.zaa = v;
    }

    final int zaa() {
        return this.zaa;
    }

    final ConnectionResult zab() {
        return this.zab;
    }
}

