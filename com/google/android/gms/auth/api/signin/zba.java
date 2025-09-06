package com.google.android.gms.auth.api.signin;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.internal.PendingResultUtil.ResultConverter;

final class zba implements ResultConverter {
    private zba() {
        throw null;
    }

    zba(zbb zbb0) {
    }

    @Override  // com.google.android.gms.common.internal.PendingResultUtil$ResultConverter
    public final Object convert(Result result0) {
        return ((GoogleSignInResult)result0).getSignInAccount();
    }
}

