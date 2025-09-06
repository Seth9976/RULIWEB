package com.google.android.gms.internal.auth-api-phone;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.TaskUtil;
import com.google.android.gms.tasks.TaskCompletionSource;

final class zzp extends zzd {
    final TaskCompletionSource zza;

    zzp(zzr zzr0, TaskCompletionSource taskCompletionSource0) {
        this.zza = taskCompletionSource0;
        super();
    }

    @Override  // com.google.android.gms.internal.auth-api-phone.zze
    public final void zzb(Status status0, int v) {
        TaskUtil.setResultOrApiException(status0, v, this.zza);
    }
}

