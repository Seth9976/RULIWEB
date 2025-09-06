package com.google.android.gms.internal.auth-api;

import android.os.RemoteException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.IStatusCallback.Stub;
import com.google.android.gms.common.api.internal.TaskUtil;
import com.google.android.gms.tasks.TaskCompletionSource;

final class zbam extends Stub {
    final TaskCompletionSource zba;

    zbam(zbap zbap0, TaskCompletionSource taskCompletionSource0) {
        this.zba = taskCompletionSource0;
        super();
    }

    @Override  // com.google.android.gms.common.api.internal.IStatusCallback
    public final void onResult(Status status0) throws RemoteException {
        TaskUtil.setResultOrApiException(status0, this.zba);
    }
}

