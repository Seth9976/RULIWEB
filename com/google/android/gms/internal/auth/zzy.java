package com.google.android.gms.internal.auth;

import android.os.Bundle;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.tasks.TaskCompletionSource;

final class zzy extends zzj {
    final TaskCompletionSource zza;

    zzy(zzab zzab0, TaskCompletionSource taskCompletionSource0) {
        this.zza = taskCompletionSource0;
        super();
    }

    @Override  // com.google.android.gms.internal.auth.zzk
    public final void zzb(Status status0, Bundle bundle0) {
        zzab.zzf(status0, bundle0, this.zza);
    }
}

