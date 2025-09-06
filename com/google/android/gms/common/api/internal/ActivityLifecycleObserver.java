package com.google.android.gms.common.api.internal;

import android.app.Activity;

public abstract class ActivityLifecycleObserver {
    public static final ActivityLifecycleObserver of(Activity activity0) {
        return new zab(zaa.zaa(activity0));
    }

    public abstract ActivityLifecycleObserver onStopCallOnce(Runnable arg1);
}

