package com.google.android.material.internal;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

class ViewGroupOverlayApi14 extends ViewOverlayApi14 implements ViewGroupOverlayImpl {
    ViewGroupOverlayApi14(Context context0, ViewGroup viewGroup0, View view0) {
        super(context0, viewGroup0, view0);
    }

    @Override  // com.google.android.material.internal.ViewGroupOverlayImpl
    public void add(View view0) {
        this.overlayViewGroup.add(view0);
    }

    static ViewGroupOverlayApi14 createFrom(ViewGroup viewGroup0) {
        return (ViewGroupOverlayApi14)ViewOverlayApi14.createFrom(viewGroup0);
    }

    @Override  // com.google.android.material.internal.ViewGroupOverlayImpl
    public void remove(View view0) {
        this.overlayViewGroup.remove(view0);
    }
}

