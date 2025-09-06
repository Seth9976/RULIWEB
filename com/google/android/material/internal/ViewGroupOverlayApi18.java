package com.google.android.material.internal;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroupOverlay;

class ViewGroupOverlayApi18 implements ViewGroupOverlayImpl {
    private final ViewGroupOverlay viewGroupOverlay;

    ViewGroupOverlayApi18(ViewGroup viewGroup0) {
        this.viewGroupOverlay = viewGroup0.getOverlay();
    }

    @Override  // com.google.android.material.internal.ViewOverlayImpl
    public void add(Drawable drawable0) {
        this.viewGroupOverlay.add(drawable0);
    }

    @Override  // com.google.android.material.internal.ViewGroupOverlayImpl
    public void add(View view0) {
        this.viewGroupOverlay.add(view0);
    }

    @Override  // com.google.android.material.internal.ViewOverlayImpl
    public void remove(Drawable drawable0) {
        this.viewGroupOverlay.remove(drawable0);
    }

    @Override  // com.google.android.material.internal.ViewGroupOverlayImpl
    public void remove(View view0) {
        this.viewGroupOverlay.remove(view0);
    }
}

