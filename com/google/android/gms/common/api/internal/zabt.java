package com.google.android.gms.common.api.internal;

import android.util.Log;
import com.google.android.gms.common.ConnectionResult;

final class zabt implements Runnable {
    final ConnectionResult zaa;
    final zabu zab;

    zabt(zabu zabu0, ConnectionResult connectionResult0) {
        this.zab = zabu0;
        this.zaa = connectionResult0;
        super();
    }

    @Override
    public final void run() {
        zabq zabq0 = (zabq)this.zab.zaa.zan.get(zabu.zab(this.zab));
        if(zabq0 == null) {
            return;
        }
        if(this.zaa.isSuccess()) {
            zabu.zac(this.zab, true);
            if(zabu.zaa(this.zab).requiresSignIn()) {
                zabu.zad(this.zab);
                return;
            }
            try {
                zabu.zaa(this.zab).getRemoteService(null, zabu.zaa(this.zab).getScopesForConnectionlessNonSignIn());
            }
            catch(SecurityException securityException0) {
                Log.e("GoogleApiManager", "Failed to get service from broker. ", securityException0);
                zabu.zaa(this.zab).disconnect("Failed to get service from broker.");
                zabq0.zar(new ConnectionResult(10), null);
            }
            return;
        }
        zabq0.zar(this.zaa, null);
    }
}

