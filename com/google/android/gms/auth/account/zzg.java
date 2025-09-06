package com.google.android.gms.auth.account;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.internal.PendingResultUtil.ResultConverter;

final class zzg implements ResultConverter {
    zzg(WorkAccountClient workAccountClient0) {
    }

    @Override  // com.google.android.gms.common.internal.PendingResultUtil$ResultConverter
    public final Object convert(Result result0) {
        return ((AddAccountResult)result0).getAccount();
    }
}

