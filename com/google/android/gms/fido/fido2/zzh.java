package com.google.android.gms.fido.fido2;

import android.app.PendingIntent;
import android.os.RemoteException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.TaskUtil;
import com.google.android.gms.internal.fido.zzi;
import com.google.android.gms.internal.fido.zzq;
import com.google.android.gms.tasks.TaskCompletionSource;

final class zzh extends zzq {
    final TaskCompletionSource zza;

    zzh(Fido2ApiClient fido2ApiClient0, TaskCompletionSource taskCompletionSource0) {
        this.zza = taskCompletionSource0;
        super();
    }

    @Override  // com.google.android.gms.internal.fido.zzr
    public final void zzb(Status status0, PendingIntent pendingIntent0) throws RemoteException {
        TaskUtil.setResultOrApiException(status0, new zzi(pendingIntent0), this.zza);
    }
}

