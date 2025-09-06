package com.google.android.gms.common.api.internal;

import java.lang.ref.WeakReference;

public final class zab extends ActivityLifecycleObserver {
    private final WeakReference zaa;

    zab(zaa zaa0) {
        this.zaa = new WeakReference(zaa0);
    }

    @Override  // com.google.android.gms.common.api.internal.ActivityLifecycleObserver
    public final ActivityLifecycleObserver onStopCallOnce(Runnable runnable0) {
        zaa zaa0 = (zaa)this.zaa.get();
        if(zaa0 == null) {
            throw new IllegalStateException("The target activity has already been GC\'d");
        }
        zaa0.zac(runnable0);
        return this;
    }
}

