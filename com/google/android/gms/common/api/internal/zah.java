package com.google.android.gms.common.api.internal;

import android.os.RemoteException;
import com.google.android.gms.common.Feature;
import com.google.android.gms.tasks.TaskCompletionSource;

public final class zah extends zad {
    public final ListenerKey zab;

    public zah(ListenerKey listenerHolder$ListenerKey0, TaskCompletionSource taskCompletionSource0) {
        super(4, taskCompletionSource0);
        this.zab = listenerHolder$ListenerKey0;
    }

    @Override  // com.google.android.gms.common.api.internal.zac
    public final boolean zaa(zabq zabq0) {
        zaci zaci0 = (zaci)zabq0.zah().get(this.zab);
        return zaci0 != null && zaci0.zaa.zab();
    }

    @Override  // com.google.android.gms.common.api.internal.zac
    public final Feature[] zab(zabq zabq0) {
        zaci zaci0 = (zaci)zabq0.zah().get(this.zab);
        return zaci0 == null ? null : zaci0.zaa.getRequiredFeatures();
    }

    @Override  // com.google.android.gms.common.api.internal.zad
    public final void zac(zabq zabq0) throws RemoteException {
        zaci zaci0 = (zaci)zabq0.zah().remove(this.zab);
        if(zaci0 != null) {
            zaci0.zab.unregisterListener(zabq0.zaf(), this.zaa);
            zaci0.zaa.clearListener();
            return;
        }
        this.zaa.trySetResult(Boolean.FALSE);
    }

    @Override  // com.google.android.gms.common.api.internal.zad
    public final void zag(zaad zaad0, boolean z) {
    }
}

