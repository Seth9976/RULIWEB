package com.google.android.gms.common.internal;

import android.app.Activity;
import android.content.Intent;

final class zad extends zag {
    final Intent zaa;
    final Activity zab;
    final int zac;

    zad(Intent intent0, Activity activity0, int v) {
        this.zaa = intent0;
        this.zab = activity0;
        this.zac = v;
        super();
    }

    @Override  // com.google.android.gms.common.internal.zag
    public final void zaa() {
        Intent intent0 = this.zaa;
        if(intent0 != null) {
            this.zab.startActivityForResult(intent0, this.zac);
        }
    }
}

