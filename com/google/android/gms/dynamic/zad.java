package com.google.android.gms.dynamic;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

final class zad implements zah {
    final FrameLayout zaa;
    final LayoutInflater zab;
    final ViewGroup zac;
    final Bundle zad;
    final DeferredLifecycleHelper zae;

    zad(DeferredLifecycleHelper deferredLifecycleHelper0, FrameLayout frameLayout0, LayoutInflater layoutInflater0, ViewGroup viewGroup0, Bundle bundle0) {
        this.zae = deferredLifecycleHelper0;
        this.zaa = frameLayout0;
        this.zab = layoutInflater0;
        this.zac = viewGroup0;
        this.zad = bundle0;
        super();
    }

    @Override  // com.google.android.gms.dynamic.zah
    public final int zaa() {
        return 2;
    }

    @Override  // com.google.android.gms.dynamic.zah
    public final void zab(LifecycleDelegate lifecycleDelegate0) {
        this.zaa.removeAllViews();
        View view0 = this.zae.zaa.onCreateView(this.zab, this.zac, this.zad);
        this.zaa.addView(view0);
    }
}

