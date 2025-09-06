package com.google.android.gms.fido.fido2;

import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.internal.fido.zzf;
import com.google.android.gms.tasks.TaskCompletionSource;
import java.util.List;

final class zzv extends zzf {
    final TaskCompletionSource zza;

    zzv(Fido2PrivilegedApiClient fido2PrivilegedApiClient0, TaskCompletionSource taskCompletionSource0) {
        this.zza = taskCompletionSource0;
        super();
    }

    @Override  // com.google.android.gms.internal.fido.zzg
    public final void zzb(List list0) {
        this.zza.setResult(list0);
    }

    @Override  // com.google.android.gms.internal.fido.zzg
    public final void zzc(Status status0) {
        ApiException apiException0 = new ApiException(status0);
        this.zza.trySetException(apiException0);
    }
}

