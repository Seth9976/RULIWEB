package com.google.android.gms.dynamic;

import android.os.Bundle;

final class zac implements zah {
    final Bundle zaa;
    final DeferredLifecycleHelper zab;

    zac(DeferredLifecycleHelper deferredLifecycleHelper0, Bundle bundle0) {
        this.zab = deferredLifecycleHelper0;
        this.zaa = bundle0;
        super();
    }

    @Override  // com.google.android.gms.dynamic.zah
    public final int zaa() {
        return 1;
    }

    @Override  // com.google.android.gms.dynamic.zah
    public final void zab(LifecycleDelegate lifecycleDelegate0) {
        this.zab.zaa.onCreate(this.zaa);
    }
}

