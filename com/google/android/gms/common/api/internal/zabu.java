package com.google.android.gms.common.api.internal;

import android.util.Log;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Api.Client;
import com.google.android.gms.common.internal.BaseGmsClient.ConnectionProgressReportCallbacks;
import com.google.android.gms.common.internal.IAccountAccessor;
import java.util.Set;

final class zabu implements zacs, ConnectionProgressReportCallbacks {
    final GoogleApiManager zaa;
    private final Client zab;
    private final ApiKey zac;
    private IAccountAccessor zad;
    private Set zae;
    private boolean zaf;

    public zabu(GoogleApiManager googleApiManager0, Client api$Client0, ApiKey apiKey0) {
        this.zaa = googleApiManager0;
        super();
        this.zad = null;
        this.zae = null;
        this.zaf = false;
        this.zab = api$Client0;
        this.zac = apiKey0;
    }

    @Override  // com.google.android.gms.common.internal.BaseGmsClient$ConnectionProgressReportCallbacks
    public final void onReportServiceBinding(ConnectionResult connectionResult0) {
        zabt zabt0 = new zabt(this, connectionResult0);
        this.zaa.zar.post(zabt0);
    }

    static Client zaa(zabu zabu0) {
        return zabu0.zab;
    }

    static ApiKey zab(zabu zabu0) {
        return zabu0.zac;
    }

    static void zac(zabu zabu0, boolean z) {
        zabu0.zaf = true;
    }

    static void zad(zabu zabu0) {
        zabu0.zah();
    }

    @Override  // com.google.android.gms.common.api.internal.zacs
    public final void zae(ConnectionResult connectionResult0) {
        zabq zabq0 = (zabq)this.zaa.zan.get(this.zac);
        if(zabq0 != null) {
            zabq0.zas(connectionResult0);
        }
    }

    @Override  // com.google.android.gms.common.api.internal.zacs
    public final void zaf(IAccountAccessor iAccountAccessor0, Set set0) {
        if(iAccountAccessor0 != null && set0 != null) {
            this.zad = iAccountAccessor0;
            this.zae = set0;
            this.zah();
            return;
        }
        Log.wtf("GoogleApiManager", "Received null response from onSignInSuccess", new Exception());
        this.zae(new ConnectionResult(4));
    }

    @Override  // com.google.android.gms.common.api.internal.zacs
    public final void zag(int v) {
        zabq zabq0 = (zabq)this.zaa.zan.get(this.zac);
        if(zabq0 != null) {
            if(zabq0.zaj) {
                zabq0.zas(new ConnectionResult(17));
                return;
            }
            zabq0.onConnectionSuspended(v);
        }
    }

    private final void zah() {
        if(this.zaf) {
            IAccountAccessor iAccountAccessor0 = this.zad;
            if(iAccountAccessor0 != null) {
                this.zab.getRemoteService(iAccountAccessor0, this.zae);
            }
        }
    }
}

