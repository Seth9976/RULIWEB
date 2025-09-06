package com.google.android.gms.common.moduleinstall.internal;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.TaskUtil;
import com.google.android.gms.common.moduleinstall.ModuleInstallIntentResponse;
import com.google.android.gms.tasks.TaskCompletionSource;

final class zaw extends zaa {
    final TaskCompletionSource zaa;

    zaw(zay zay0, TaskCompletionSource taskCompletionSource0) {
        this.zaa = taskCompletionSource0;
        super();
    }

    @Override  // com.google.android.gms.common.moduleinstall.internal.zaa
    public final void zac(Status status0, ModuleInstallIntentResponse moduleInstallIntentResponse0) {
        TaskUtil.trySetResultOrApiException(status0, moduleInstallIntentResponse0, this.zaa);
    }
}

