package com.google.android.material.shape;

import android.view.View;

class ShapeableDelegateV14 extends ShapeableDelegate {
    @Override  // com.google.android.material.shape.ShapeableDelegate
    void invalidateClippingMethod(View view0) {
        if(this.shapeAppearanceModel != null && !this.maskBounds.isEmpty()) {
            view0.invalidate();
        }
    }

    @Override  // com.google.android.material.shape.ShapeableDelegate
    boolean shouldUseCompatClipping() [...] // Inlined contents
}

