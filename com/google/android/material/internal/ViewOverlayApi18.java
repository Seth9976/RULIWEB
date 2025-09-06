package com.google.android.material.internal;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewOverlay;

class ViewOverlayApi18 implements ViewOverlayImpl {
    private final ViewOverlay viewOverlay;

    ViewOverlayApi18(View view0) {
        this.viewOverlay = view0.getOverlay();
    }

    @Override  // com.google.android.material.internal.ViewOverlayImpl
    public void add(Drawable drawable0) {
        this.viewOverlay.add(drawable0);
    }

    @Override  // com.google.android.material.internal.ViewOverlayImpl
    public void remove(Drawable drawable0) {
        this.viewOverlay.remove(drawable0);
    }
}

