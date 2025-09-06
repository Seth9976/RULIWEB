package com.google.android.gms.internal.auth-api-phone;

import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.IStatusCallback.Stub;
import com.google.android.gms.common.api.internal.TaskUtil;
import com.google.android.gms.common.internal.ApiExceptionUtil;
import com.google.android.gms.tasks.TaskCompletionSource;

final class zzu extends Stub {
    final TaskCompletionSource zza;

    zzu(zzv zzv0, TaskCompletionSource taskCompletionSource0) {
        this.zza = taskCompletionSource0;
        super();
    }

    @Override  // com.google.android.gms.common.api.internal.IStatusCallback
    public final void onResult(Status status0) {
        if(status0.getStatusCode() == 6) {
            ApiException apiException0 = ApiExceptionUtil.fromStatus(status0);
            this.zza.trySetException(apiException0);
            return;
        }
        TaskUtil.setResultOrApiException(status0, this.zza);
    }
}

