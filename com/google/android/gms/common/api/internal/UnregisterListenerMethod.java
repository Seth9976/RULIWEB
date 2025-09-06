package com.google.android.gms.common.api.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api.AnyClient;
import com.google.android.gms.tasks.TaskCompletionSource;

public abstract class UnregisterListenerMethod {
    private final ListenerKey zaa;

    protected UnregisterListenerMethod(ListenerKey listenerHolder$ListenerKey0) {
        this.zaa = listenerHolder$ListenerKey0;
    }

    public ListenerKey getListenerKey() {
        return this.zaa;
    }

    protected abstract void unregisterListener(AnyClient arg1, TaskCompletionSource arg2) throws RemoteException;
}

