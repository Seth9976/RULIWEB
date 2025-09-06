package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.api.Api.Client;
import java.util.ArrayList;

final class zaap extends zaav {
    final zaaw zaa;
    private final ArrayList zac;

    public zaap(zaaw zaaw0, ArrayList arrayList0) {
        this.zaa = zaaw0;
        super(zaaw0, null);
        this.zac = arrayList0;
    }

    @Override  // com.google.android.gms.common.api.internal.zaav
    public final void zaa() {
        zaaw.zak(this.zaa).zag.zad = zaaw.zao(this.zaa);
        ArrayList arrayList0 = this.zac;
        int v = arrayList0.size();
        for(int v1 = 0; v1 < v; ++v1) {
            ((Client)arrayList0.get(v1)).getRemoteService(zaaw.zam(this.zaa), zaaw.zak(this.zaa).zag.zad);
        }
    }
}

