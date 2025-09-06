package com.google.android.gms.common.api.internal;

import android.app.Dialog;

final class zan extends zabw {
    final Dialog zaa;
    final zao zab;

    zan(zao zao0, Dialog dialog0) {
        this.zab = zao0;
        this.zaa = dialog0;
        super();
    }

    @Override  // com.google.android.gms.common.api.internal.zabw
    public final void zaa() {
        this.zab.zaa.zad();
        if(this.zaa.isShowing()) {
            this.zaa.dismiss();
        }
    }
}

