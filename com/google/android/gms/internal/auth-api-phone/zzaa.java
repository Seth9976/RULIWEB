package com.google.android.gms.internal.auth-api-phone;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.TaskUtil;
import com.google.android.gms.tasks.TaskCompletionSource;

final class zzaa extends zzi {
    final TaskCompletionSource zza;

    zzaa(zzab zzab0, TaskCompletionSource taskCompletionSource0) {
        this.zza = taskCompletionSource0;
        super();
    }

    @Override  // com.google.android.gms.internal.auth-api-phone.zzj
    public final void zzb(Status status0) {
        TaskUtil.setResultOrApiException(status0, this.zza);
    }
}

