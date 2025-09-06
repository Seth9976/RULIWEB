package com.google.android.gms.common.api.internal;

import android.os.Bundle;
import com.google.android.gms.common.ConnectionResult;

final class zaz implements zabz {
    final zaaa zaa;

    zaz(zaaa zaaa0, zay zay0) {
        this.zaa = zaaa0;
        super();
    }

    @Override  // com.google.android.gms.common.api.internal.zabz
    public final void zaa(ConnectionResult connectionResult0) {
        this.zaa.zam.lock();
        try {
            this.zaa.zak = connectionResult0;
            zaaa.zap(this.zaa);
        }
        finally {
            this.zaa.zam.unlock();
        }
    }

    @Override  // com.google.android.gms.common.api.internal.zabz
    public final void zab(Bundle bundle0) {
        this.zaa.zam.lock();
        try {
            this.zaa.zak = ConnectionResult.RESULT_SUCCESS;
            zaaa.zap(this.zaa);
        }
        finally {
            this.zaa.zam.unlock();
        }
    }

    @Override  // com.google.android.gms.common.api.internal.zabz
    public final void zac(int v, boolean z) {
        this.zaa.zam.lock();
        try {
            zaaa zaaa0 = this.zaa;
            if(zaaa0.zal) {
                zaaa0.zal = false;
                zaaa.zan(this.zaa, v, z);
            }
            else {
                zaaa0.zal = true;
                this.zaa.zad.onConnectionSuspended(v);
            }
        }
        finally {
            this.zaa.zam.unlock();
        }
    }
}

