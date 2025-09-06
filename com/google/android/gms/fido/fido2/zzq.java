package com.google.android.gms.fido.fido2;

import android.app.PendingIntent;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.TaskUtil;
import com.google.android.gms.internal.fido.zzl;
import com.google.android.gms.tasks.TaskCompletionSource;

final class zzq extends zzl {
    final TaskCompletionSource zza;

    zzq(Fido2PrivilegedApiClient fido2PrivilegedApiClient0, TaskCompletionSource taskCompletionSource0) {
        this.zza = taskCompletionSource0;
        super();
    }

    @Override  // com.google.android.gms.internal.fido.zzm
    public final void zzb(Status status0, PendingIntent pendingIntent0) {
        TaskUtil.setResultOrApiException(status0, pendingIntent0, this.zza);
    }
}

