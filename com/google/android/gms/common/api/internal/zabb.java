package com.google.android.gms.common.api.internal;

import com.google.android.gms.auth.api.signin.internal.Storage;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;

final class zabb implements ResultCallback {
    final StatusPendingResult zaa;
    final boolean zab;
    final GoogleApiClient zac;
    final zabe zad;

    zabb(zabe zabe0, StatusPendingResult statusPendingResult0, boolean z, GoogleApiClient googleApiClient0) {
        this.zad = zabe0;
        this.zaa = statusPendingResult0;
        this.zab = z;
        this.zac = googleApiClient0;
        super();
    }

    @Override  // com.google.android.gms.common.api.ResultCallback
    public final void onResult(Result result0) {
        Storage.getInstance(zabe.zae(this.zad)).zac();
        if(((Status)result0).isSuccess() && this.zad.isConnected()) {
            this.zad.disconnect();
            this.zad.connect();
        }
        this.zaa.setResult(((Status)result0));
        if(this.zab) {
            this.zac.disconnect();
        }
    }
}

