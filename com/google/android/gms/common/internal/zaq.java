package com.google.android.gms.common.internal;

import com.google.android.gms.common.api.Response;
import com.google.android.gms.common.api.Result;

final class zaq implements ResultConverter {
    final Response zaa;

    zaq(Response response0) {
        this.zaa = response0;
        super();
    }

    @Override  // com.google.android.gms.common.internal.PendingResultUtil$ResultConverter
    public final Object convert(Result result0) {
        this.zaa.setResult(result0);
        return this.zaa;
    }
}

