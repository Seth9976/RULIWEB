package com.google.android.gms.internal.auth-api;

import android.app.PendingIntent;
import android.os.RemoteException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.TaskUtil;
import com.google.android.gms.tasks.TaskCompletionSource;

final class zban extends zbp {
    final TaskCompletionSource zba;

    zban(zbap zbap0, TaskCompletionSource taskCompletionSource0) {
        this.zba = taskCompletionSource0;
        super();
    }

    @Override  // com.google.android.gms.internal.auth-api.zbq
    public final void zbb(Status status0, PendingIntent pendingIntent0) throws RemoteException {
        TaskUtil.setResultOrApiException(status0, pendingIntent0, this.zba);
    }
}

