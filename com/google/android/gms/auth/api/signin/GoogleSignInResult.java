package com.google.android.gms.auth.api.signin;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;

@Deprecated
public class GoogleSignInResult implements Result {
    private final Status zba;
    private final GoogleSignInAccount zbb;

    public GoogleSignInResult(GoogleSignInAccount googleSignInAccount0, Status status0) {
        this.zbb = googleSignInAccount0;
        this.zba = status0;
    }

    public GoogleSignInAccount getSignInAccount() {
        return this.zbb;
    }

    @Override  // com.google.android.gms.common.api.Result
    public Status getStatus() {
        return this.zba;
    }

    public boolean isSuccess() {
        return this.zba.isSuccess();
    }
}

