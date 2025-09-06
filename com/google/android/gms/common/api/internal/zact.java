package com.google.android.gms.common.api.internal;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Api.AbstractClientBuilder;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.internal.ClientSettings;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.zav;
import com.google.android.gms.signin.internal.zac;
import com.google.android.gms.signin.internal.zak;
import com.google.android.gms.signin.zad;
import com.google.android.gms.signin.zae;
import java.util.Set;

public final class zact extends zac implements ConnectionCallbacks, OnConnectionFailedListener {
    private static final AbstractClientBuilder zaa;
    private final Context zab;
    private final Handler zac;
    private final AbstractClientBuilder zad;
    private final Set zae;
    private final ClientSettings zaf;
    private zae zag;
    private zacs zah;

    static {
        zact.zaa = zad.zac;
    }

    public zact(Context context0, Handler handler0, ClientSettings clientSettings0) {
        this.zab = context0;
        this.zac = handler0;
        this.zaf = (ClientSettings)Preconditions.checkNotNull(clientSettings0, "ClientSettings must not be null");
        this.zae = clientSettings0.getRequiredScopes();
        this.zad = zact.zaa;
    }

    @Override  // com.google.android.gms.common.api.internal.ConnectionCallbacks
    public final void onConnected(Bundle bundle0) {
        this.zag.zad(this);
    }

    @Override  // com.google.android.gms.common.api.internal.OnConnectionFailedListener
    public final void onConnectionFailed(ConnectionResult connectionResult0) {
        this.zah.zae(connectionResult0);
    }

    @Override  // com.google.android.gms.common.api.internal.ConnectionCallbacks
    public final void onConnectionSuspended(int v) {
        this.zah.zag(v);
    }

    @Override  // com.google.android.gms.signin.internal.zac
    public final void zab(zak zak0) {
        zacr zacr0 = new zacr(this, zak0);
        this.zac.post(zacr0);
    }

    static zacs zac(zact zact0) {
        return zact0.zah;
    }

    static void zad(zact zact0, zak zak0) {
        ConnectionResult connectionResult0 = zak0.zaa();
        if(connectionResult0.isSuccess()) {
            zav zav0 = (zav)Preconditions.checkNotNull(zak0.zab());
            ConnectionResult connectionResult1 = zav0.zaa();
            if(!connectionResult1.isSuccess()) {
                Log.wtf("SignInCoordinator", "Sign-in succeeded with resolve account failure: " + connectionResult1, new Exception());
                zact0.zah.zae(connectionResult1);
                zact0.zag.disconnect();
                return;
            }
            zact0.zah.zaf(zav0.zab(), zact0.zae);
        }
        else {
            zact0.zah.zae(connectionResult0);
        }
        zact0.zag.disconnect();
    }

    public final void zae(zacs zacs0) {
        zae zae0 = this.zag;
        if(zae0 != null) {
            zae0.disconnect();
        }
        this.zaf.zae(System.identityHashCode(this));
        Looper looper0 = this.zac.getLooper();
        this.zag = this.zad.buildClient(this.zab, looper0, this.zaf, this.zaf.zaa(), this, this);
        this.zah = zacs0;
        if(this.zae != null && !this.zae.isEmpty()) {
            this.zag.zab();
            return;
        }
        zacq zacq0 = new zacq(this);
        this.zac.post(zacq0);
    }

    public final void zaf() {
        zae zae0 = this.zag;
        if(zae0 != null) {
            zae0.disconnect();
        }
    }
}

