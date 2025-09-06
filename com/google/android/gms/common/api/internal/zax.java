package com.google.android.gms.common.api.internal;

import android.os.Bundle;
import com.google.android.gms.common.ConnectionResult;

final class zax implements zabz {
    final zaaa zaa;

    zax(zaaa zaaa0, zaw zaw0) {
        this.zaa = zaaa0;
        super();
    }

    @Override  // com.google.android.gms.common.api.internal.zabz
    public final void zaa(ConnectionResult connectionResult0) {
        this.zaa.zam.lock();
        try {
            this.zaa.zaj = connectionResult0;
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
            zaaa.zao(this.zaa, bundle0);
            this.zaa.zaj = ConnectionResult.RESULT_SUCCESS;
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
            if(this.zaa.zal || this.zaa.zak == null || !this.zaa.zak.isSuccess()) {
                this.zaa.zal = false;
                zaaa.zan(this.zaa, v, z);
            }
            else {
                this.zaa.zal = true;
                this.zaa.zae.onConnectionSuspended(v);
            }
        }
        finally {
            this.zaa.zam.unlock();
        }
    }
}

