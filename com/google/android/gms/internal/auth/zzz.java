package com.google.android.gms.internal.auth;

import com.google.android.gms.auth.AccountChangeEventsResponse;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.tasks.TaskCompletionSource;

final class zzz extends zzl {
    final TaskCompletionSource zza;

    zzz(zzab zzab0, TaskCompletionSource taskCompletionSource0) {
        this.zza = taskCompletionSource0;
        super();
    }

    @Override  // com.google.android.gms.internal.auth.zzm
    public final void zzb(Status status0, AccountChangeEventsResponse accountChangeEventsResponse0) {
        zzab.zzf(status0, accountChangeEventsResponse0, this.zza);
    }
}

