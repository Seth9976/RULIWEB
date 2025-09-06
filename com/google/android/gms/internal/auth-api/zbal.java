package com.google.android.gms.internal.auth-api;

import android.os.RemoteException;
import com.google.android.gms.auth.api.identity.BeginSignInResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.TaskUtil;
import com.google.android.gms.tasks.TaskCompletionSource;

final class zbal extends zbk {
    final TaskCompletionSource zba;

    zbal(zbap zbap0, TaskCompletionSource taskCompletionSource0) {
        this.zba = taskCompletionSource0;
        super();
    }

    @Override  // com.google.android.gms.internal.auth-api.zbl
    public final void zbb(Status status0, BeginSignInResult beginSignInResult0) throws RemoteException {
        TaskUtil.setResultOrApiException(status0, beginSignInResult0, this.zba);
    }
}

