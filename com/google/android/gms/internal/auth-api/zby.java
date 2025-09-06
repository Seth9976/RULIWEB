package com.google.android.gms.internal.auth-api;

import android.os.RemoteException;
import com.google.android.gms.auth.api.identity.AuthorizationResult;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.ApiExceptionUtil;
import com.google.android.gms.tasks.TaskCompletionSource;

final class zby extends zbh {
    final TaskCompletionSource zba;

    zby(zbz zbz0, TaskCompletionSource taskCompletionSource0) {
        this.zba = taskCompletionSource0;
        super();
    }

    @Override  // com.google.android.gms.internal.auth-api.zbi
    public final void zbb(Status status0, AuthorizationResult authorizationResult0) throws RemoteException {
        if(status0.isSuccess()) {
            this.zba.setResult(authorizationResult0);
            return;
        }
        ApiException apiException0 = ApiExceptionUtil.fromStatus(status0);
        this.zba.setException(apiException0);
    }
}

