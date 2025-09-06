package com.google.android.gms.common.internal;

import android.content.Intent;
import androidx.fragment.app.Fragment;

final class zae extends zag {
    final Intent zaa;
    final Fragment zab;
    final int zac;

    zae(Intent intent0, Fragment fragment0, int v) {
        this.zaa = intent0;
        this.zab = fragment0;
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

