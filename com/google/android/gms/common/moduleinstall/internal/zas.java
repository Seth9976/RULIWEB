package com.google.android.gms.common.moduleinstall.internal;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.TaskUtil;
import com.google.android.gms.tasks.TaskCompletionSource;

final class zas extends zaa {
    final TaskCompletionSource zaa;

    zas(zay zay0, TaskCompletionSource taskCompletionSource0) {
        this.zaa = taskCompletionSource0;
        super();
    }

    @Override  // com.google.android.gms.common.moduleinstall.internal.zaa
    public final void zab(Status status0) {
        TaskUtil.trySetResultOrApiException(status0, null, this.zaa);
    }
}

