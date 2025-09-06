package com.google.android.gms.auth.api.signin.internal;

import android.content.Context;
import android.os.RemoteException;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.api.Api.AnyClient;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;

final class zbg extends zbl {
    final Context zba;
    final GoogleSignInOptions zbb;

    zbg(GoogleApiClient googleApiClient0, Context context0, GoogleSignInOptions googleSignInOptions0) {
        this.zba = context0;
        this.zbb = googleSignInOptions0;
        super(googleApiClient0);
    }

    @Override  // com.google.android.gms.common.api.internal.BasePendingResult
    protected final Result createFailedResult(Status status0) {
        return new GoogleSignInResult(null, status0);
    }

    @Override  // com.google.android.gms.common.api.internal.BaseImplementation$ApiMethodImpl
    protected final void doExecute(AnyClient api$AnyClient0) throws RemoteException {
        ((zbs)((zbe)api$AnyClient0).getService()).zbe(new zbf(this, this.zba, this.zbb), this.zbb);
    }
}

