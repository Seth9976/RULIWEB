package com.google.android.gms.internal.auth-api;

import android.app.PendingIntent;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.TaskUtil;
import com.google.android.gms.tasks.TaskCompletionSource;

final class zbao extends zbn {
    final TaskCompletionSource zba;

    zbao(zbap zbap0, TaskCompletionSource taskCompletionSource0) {
        this.zba = taskCompletionSource0;
        super();
    }

    @Override  // com.google.android.gms.internal.auth-api.zbo
    public final void zbb(Status status0, PendingIntent pendingIntent0) {
        TaskUtil.setResultOrApiException(status0, pendingIntent0, this.zba);
    }
}

