package com.google.android.gms.common.api.internal;

import android.os.RemoteException;
import com.google.android.gms.common.Feature;
import com.google.android.gms.common.api.Api.AnyClient;
import com.google.android.gms.tasks.TaskCompletionSource;

final class zacv extends TaskApiCall {
    final Builder zaa;

    zacv(Builder taskApiCall$Builder0, Feature[] arr_feature, boolean z, int v) {
        this.zaa = taskApiCall$Builder0;
        super(arr_feature, z, v);
    }

    @Override  // com.google.android.gms.common.api.internal.TaskApiCall
    protected final void doExecute(AnyClient api$AnyClient0, TaskCompletionSource taskCompletionSource0) throws RemoteException {
        this.zaa.zaa.accept(api$AnyClient0, taskCompletionSource0);
    }
}

