package com.google.android.gms.dynamic;

import android.app.Activity;
import android.os.Bundle;

final class zab implements zah {
    final Activity zaa;
    final Bundle zab;
    final Bundle zac;
    final DeferredLifecycleHelper zad;

    zab(DeferredLifecycleHelper deferredLifecycleHelper0, Activity activity0, Bundle bundle0, Bundle bundle1) {
        this.zad = deferredLifecycleHelper0;
        this.zaa = activity0;
        this.zab = bundle0;
        this.zac = bundle1;
        super();
    }

    @Override  // com.google.android.gms.dynamic.zah
    public final int zaa() {
        return 0;
    }

    @Override  // com.google.android.gms.dynamic.zah
    public final void zab(LifecycleDelegate lifecycleDelegate0) {
        this.zad.zaa.onInflate(this.zaa, this.zab, this.zac);
    }
}

