package com.google.android.gms.fido.u2f;

import android.app.PendingIntent;
import android.os.RemoteException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.TaskUtil;
import com.google.android.gms.internal.fido.zzt;
import com.google.android.gms.internal.fido.zzu;
import com.google.android.gms.tasks.TaskCompletionSource;

final class zzc extends zzu {
    final TaskCompletionSource zza;

    zzc(U2fApiClient u2fApiClient0, TaskCompletionSource taskCompletionSource0) {
        this.zza = taskCompletionSource0;
        super();
    }

    @Override  // com.google.android.gms.internal.fido.zzv
    public final void zzb(Status status0, PendingIntent pendingIntent0) throws RemoteException {
        TaskUtil.setResultOrApiException(status0, new zzt(pendingIntent0), this.zza);
    }
}

