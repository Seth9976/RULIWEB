package com.google.android.gms.common.api.internal;

import android.os.RemoteException;
import com.google.android.gms.common.Feature;
import com.google.android.gms.common.api.Api.AnyClient;
import com.google.android.gms.tasks.TaskCompletionSource;

final class zack extends RegisterListenerMethod {
    final Builder zaa;

    zack(Builder registrationMethods$Builder0, ListenerHolder listenerHolder0, Feature[] arr_feature, boolean z, int v) {
        this.zaa = registrationMethods$Builder0;
        super(listenerHolder0, arr_feature, z, v);
    }

    @Override  // com.google.android.gms.common.api.internal.RegisterListenerMethod
    protected final void registerListener(AnyClient api$AnyClient0, TaskCompletionSource taskCompletionSource0) throws RemoteException {
        this.zaa.zaa.accept(api$AnyClient0, taskCompletionSource0);
    }
}

