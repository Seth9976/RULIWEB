package com.google.android.gms.common.api.internal;

import android.app.Activity;
import java.util.ArrayList;
import java.util.List;

final class zaa extends LifecycleCallback {
    private List zaa;

    private zaa(LifecycleFragment lifecycleFragment0) {
        super(lifecycleFragment0);
        this.zaa = new ArrayList();
        this.mLifecycleFragment.addCallback("LifecycleObserverOnStop", this);
    }

    @Override  // com.google.android.gms.common.api.internal.LifecycleCallback
    public final void onStop() {
        synchronized(this) {
            List list0 = this.zaa;
            this.zaa = new ArrayList();
        }
        for(Object object0: list0) {
            ((Runnable)object0).run();
        }
    }

    static zaa zaa(Activity activity0) {
        synchronized(activity0) {
            LifecycleFragment lifecycleFragment0 = zaa.getFragment(activity0);
            zaa zaa0 = (zaa)lifecycleFragment0.getCallbackOrNull("LifecycleObserverOnStop", zaa.class);
            if(zaa0 == null) {
                zaa0 = new zaa(lifecycleFragment0);
            }
            return zaa0;
        }
    }

    private final void zac(Runnable runnable0) {
        synchronized(this) {
            this.zaa.add(runnable0);
        }
    }
}

