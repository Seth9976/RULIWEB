package com.google.android.gms.common.api.internal;

import android.os.RemoteException;
import com.google.android.gms.common.Feature;
import com.google.android.gms.tasks.TaskCompletionSource;

public final class zaf extends zad {
    public final zaci zab;

    public zaf(zaci zaci0, TaskCompletionSource taskCompletionSource0) {
        super(3, taskCompletionSource0);
        this.zab = zaci0;
    }

    @Override  // com.google.android.gms.common.api.internal.zac
    public final boolean zaa(zabq zabq0) {
        return this.zab.zaa.zab();
    }

    @Override  // com.google.android.gms.common.api.internal.zac
    public final Feature[] zab(zabq zabq0) {
        return this.zab.zaa.getRequiredFeatures();
    }

    @Override  // com.google.android.gms.common.api.internal.zad
    public final void zac(zabq zabq0) throws RemoteException {
        this.zab.zaa.registerListener(zabq0.zaf(), this.zaa);
        ListenerKey listenerHolder$ListenerKey0 = this.zab.zaa.getListenerKey();
        if(listenerHolder$ListenerKey0 != null) {
            zabq0.zah().put(listenerHolder$ListenerKey0, this.zab);
        }
    }

    @Override  // com.google.android.gms.common.api.internal.zad
    public final void zag(zaad zaad0, boolean z) {
    }
}

