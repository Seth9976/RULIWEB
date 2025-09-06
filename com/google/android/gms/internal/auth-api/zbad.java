package com.google.android.gms.internal.auth-api;

import android.os.RemoteException;
import com.google.android.gms.auth.api.identity.SaveAccountLinkingTokenResult;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.ApiExceptionUtil;
import com.google.android.gms.tasks.TaskCompletionSource;

final class zbad extends zbr {
    final TaskCompletionSource zba;

    zbad(zbaf zbaf0, TaskCompletionSource taskCompletionSource0) {
        this.zba = taskCompletionSource0;
        super();
    }

    @Override  // com.google.android.gms.internal.auth-api.zbs
    public final void zbb(Status status0, SaveAccountLinkingTokenResult saveAccountLinkingTokenResult0) throws RemoteException {
        if(status0.isSuccess()) {
            this.zba.setResult(saveAccountLinkingTokenResult0);
            return;
        }
        ApiException apiException0 = ApiExceptionUtil.fromStatus(status0);
        this.zba.setException(apiException0);
    }
}

