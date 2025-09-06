package com.google.android.gms.common.moduleinstall.internal;

import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.moduleinstall.ModuleInstallResponse;
import com.google.android.gms.tasks.SuccessContinuation;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import java.util.concurrent.atomic.AtomicReference;

public final class zak implements SuccessContinuation {
    public final AtomicReference zaa;

    public zak(AtomicReference atomicReference0) {
        this.zaa = atomicReference0;
    }

    @Override  // com.google.android.gms.tasks.SuccessContinuation
    public final Task then(Object object0) {
        Void void0 = (Void)object0;
        return this.zaa.get() == null ? Tasks.forException(new ApiException(Status.RESULT_INTERNAL_ERROR)) : Tasks.forResult(((ModuleInstallResponse)this.zaa.get()));
    }
}

