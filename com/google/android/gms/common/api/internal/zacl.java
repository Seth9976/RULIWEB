package com.google.android.gms.common.api.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api.AnyClient;
import com.google.android.gms.tasks.TaskCompletionSource;

final class zacl extends UnregisterListenerMethod {
    final Builder zaa;

    zacl(Builder registrationMethods$Builder0, ListenerKey listenerHolder$ListenerKey0) {
        this.zaa = registrationMethods$Builder0;
        super(listenerHolder$ListenerKey0);
    }

    @Override  // com.google.android.gms.common.api.internal.UnregisterListenerMethod
    protected final void unregisterListener(AnyClient api$AnyClient0, TaskCompletionSource taskCompletionSource0) throws RemoteException {
        this.zaa.zab.accept(api$AnyClient0, taskCompletionSource0);
    }
}

