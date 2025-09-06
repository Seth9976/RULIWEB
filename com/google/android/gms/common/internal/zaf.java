package com.google.android.gms.common.internal;

import android.content.Intent;
import com.google.android.gms.common.api.internal.LifecycleFragment;

final class zaf extends zag {
    final Intent zaa;
    final LifecycleFragment zab;

    zaf(Intent intent0, LifecycleFragment lifecycleFragment0, int v) {
        this.zaa = intent0;
        this.zab = lifecycleFragment0;
        super();
    }

    @Override  // com.google.android.gms.common.internal.zag
    public final void zaa() {
        Intent intent0 = this.zaa;
        if(intent0 != null) {
            this.zab.startActivityForResult(intent0, 2);
        }
    }
}

