package com.google.android.gms.common.api.internal;

import com.google.android.gms.signin.internal.zac;
import com.google.android.gms.signin.internal.zak;
import java.lang.ref.WeakReference;

final class zaar extends zac {
    private final WeakReference zaa;

    zaar(zaaw zaaw0) {
        this.zaa = new WeakReference(zaaw0);
    }

    @Override  // com.google.android.gms.signin.internal.zac
    public final void zab(zak zak0) {
        zaaw zaaw0 = (zaaw)this.zaa.get();
        if(zaaw0 == null) {
            return;
        }
        zaaw.zak(zaaw0).zal(new zaaq(this, zaaw0, zaaw0, zak0));
    }
}

