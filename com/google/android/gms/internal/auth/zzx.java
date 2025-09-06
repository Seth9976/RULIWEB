package com.google.android.gms.internal.auth;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.IStatusCallback.Stub;
import com.google.android.gms.tasks.TaskCompletionSource;

final class zzx extends Stub {
    final TaskCompletionSource zza;

    zzx(zzab zzab0, TaskCompletionSource taskCompletionSource0) {
        this.zza = taskCompletionSource0;
        super();
    }

    @Override  // com.google.android.gms.common.api.internal.IStatusCallback
    public final void onResult(Status status0) {
        zzab.zzf(status0, null, this.zza);
    }
}

