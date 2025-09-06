package com.google.android.gms.common.moduleinstall.internal;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.TaskUtil;
import com.google.android.gms.common.moduleinstall.ModuleInstallResponse;
import com.google.android.gms.tasks.TaskCompletionSource;

final class zat extends zaa {
    final TaskCompletionSource zaa;

    zat(zay zay0, TaskCompletionSource taskCompletionSource0) {
        this.zaa = taskCompletionSource0;
        super();
    }

    @Override  // com.google.android.gms.common.moduleinstall.internal.zaa
    public final void zad(Status status0, ModuleInstallResponse moduleInstallResponse0) {
        TaskUtil.trySetResultOrApiException(status0, moduleInstallResponse0, this.zaa);
    }
}

