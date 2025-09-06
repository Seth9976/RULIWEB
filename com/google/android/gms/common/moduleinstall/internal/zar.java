package com.google.android.gms.common.moduleinstall.internal;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.TaskUtil;
import com.google.android.gms.common.moduleinstall.ModuleAvailabilityResponse;
import com.google.android.gms.tasks.TaskCompletionSource;

final class zar extends zaa {
    final TaskCompletionSource zaa;

    zar(zay zay0, TaskCompletionSource taskCompletionSource0) {
        this.zaa = taskCompletionSource0;
        super();
    }

    @Override  // com.google.android.gms.common.moduleinstall.internal.zaa
    public final void zae(Status status0, ModuleAvailabilityResponse moduleAvailabilityResponse0) {
        TaskUtil.trySetResultOrApiException(status0, moduleAvailabilityResponse0, this.zaa);
    }
}

