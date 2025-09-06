package com.google.android.gms.fido.fido2;

import android.os.RemoteException;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.internal.fido.zzd;
import com.google.android.gms.tasks.TaskCompletionSource;

final class zzj extends zzd {
    final TaskCompletionSource zza;

    zzj(Fido2ApiClient fido2ApiClient0, TaskCompletionSource taskCompletionSource0) {
        this.zza = taskCompletionSource0;
        super();
    }

    @Override  // com.google.android.gms.internal.fido.zze
    public final void zzb(boolean z) throws RemoteException {
        this.zza.setResult(Boolean.valueOf(z));
    }

    @Override  // com.google.android.gms.internal.fido.zze
    public final void zzc(Status status0) throws RemoteException {
        ApiException apiException0 = new ApiException(status0);
        this.zza.trySetException(apiException0);
    }
}

