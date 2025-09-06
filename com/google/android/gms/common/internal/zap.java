package com.google.android.gms.common.internal;

import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.PendingResult.StatusListener;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.tasks.TaskCompletionSource;
import java.util.concurrent.TimeUnit;

final class zap implements StatusListener {
    final PendingResult zaa;
    final TaskCompletionSource zab;
    final ResultConverter zac;
    final zas zad;

    zap(PendingResult pendingResult0, TaskCompletionSource taskCompletionSource0, ResultConverter pendingResultUtil$ResultConverter0, zas zas0) {
        this.zaa = pendingResult0;
        this.zab = taskCompletionSource0;
        this.zac = pendingResultUtil$ResultConverter0;
        this.zad = zas0;
        super();
    }

    @Override  // com.google.android.gms.common.api.PendingResult$StatusListener
    public final void onComplete(Status status0) {
        if(status0.isSuccess()) {
            Result result0 = this.zaa.await(0L, TimeUnit.MILLISECONDS);
            Object object0 = this.zac.convert(result0);
            this.zab.setResult(object0);
            return;
        }
        ApiException apiException0 = ApiExceptionUtil.fromStatus(status0);
        this.zab.setException(apiException0);
    }
}

