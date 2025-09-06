package com.google.android.gms.common.api.internal;

import java.lang.ref.WeakReference;

final class zabd extends zabw {
    private final WeakReference zaa;

    zabd(zabe zabe0) {
        this.zaa = new WeakReference(zabe0);
    }

    @Override  // com.google.android.gms.common.api.internal.zabw
    public final void zaa() {
        zabe zabe0 = (zabe)this.zaa.get();
        if(zabe0 == null) {
            return;
        }
        zabe.zai(zabe0);
    }
}

