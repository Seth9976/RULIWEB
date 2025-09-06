package com.google.android.gms.common.api.internal;

import android.os.Bundle;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.internal.ClientSettings;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.signin.zae;

final class zaat implements ConnectionCallbacks, OnConnectionFailedListener {
    final zaaw zaa;

    zaat(zaaw zaaw0, zaas zaas0) {
        this.zaa = zaaw0;
        super();
    }

    @Override  // com.google.android.gms.common.api.internal.ConnectionCallbacks
    public final void onConnected(Bundle bundle0) {
        ClientSettings clientSettings0 = (ClientSettings)Preconditions.checkNotNull(zaaw.zal(this.zaa));
        ((zae)Preconditions.checkNotNull(zaaw.zan(this.zaa))).zad(new zaar(this.zaa));
    }

    @Override  // com.google.android.gms.common.api.internal.OnConnectionFailedListener
    public final void onConnectionFailed(ConnectionResult connectionResult0) {
        zaaw.zap(this.zaa).lock();
        try {
            if(zaaw.zay(this.zaa, connectionResult0)) {
                zaaw.zaq(this.zaa);
                zaaw.zau(this.zaa);
            }
            else {
                zaaw.zas(this.zaa, connectionResult0);
            }
        }
        finally {
            zaaw.zap(this.zaa).unlock();
        }
    }

    @Override  // com.google.android.gms.common.api.internal.ConnectionCallbacks
    public final void onConnectionSuspended(int v) {
    }
}

