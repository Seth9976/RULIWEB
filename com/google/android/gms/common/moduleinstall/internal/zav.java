package com.google.android.gms.common.moduleinstall.internal;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.IStatusCallback.Stub;
import com.google.android.gms.common.api.internal.TaskUtil;
import com.google.android.gms.tasks.TaskCompletionSource;

final class zav extends Stub {
    final TaskCompletionSource zaa;

    zav(zay zay0, TaskCompletionSource taskCompletionSource0) {
        this.zaa = taskCompletionSource0;
        super();
    }

    @Override  // com.google.android.gms.common.api.internal.IStatusCallback
    public final void onResult(Status status0) {
        TaskUtil.trySetResultOrApiException(status0, Boolean.TRUE, this.zaa);
    }
}

