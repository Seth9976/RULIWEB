package com.google.android.gms.auth.api.signin.internal;

import android.content.Context;
import android.os.RemoteException;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.api.Status;

final class zbf extends zba {
    final Context zba;
    final GoogleSignInOptions zbb;
    final zbg zbc;

    zbf(zbg zbg0, Context context0, GoogleSignInOptions googleSignInOptions0) {
        this.zba = context0;
        this.zbb = googleSignInOptions0;
        this.zbc = zbg0;
        super();
    }

    @Override  // com.google.android.gms.auth.api.signin.internal.zba
    public final void zbd(GoogleSignInAccount googleSignInAccount0, Status status0) throws RemoteException {
        if(googleSignInAccount0 != null) {
            zbn.zbc(this.zba).zbe(this.zbb, googleSignInAccount0);
        }
        GoogleSignInResult googleSignInResult0 = new GoogleSignInResult(googleSignInAccount0, status0);
        this.zbc.setResult(googleSignInResult0);
    }
}

