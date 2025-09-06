package com.google.android.material.shape;

import android.graphics.Outline;
import android.view.View;
import android.view.ViewOutlineProvider;
import kotlin.io.path.LinkFollowing..ExternalSyntheticApiModelOutline0;

class ShapeableDelegateV33 extends ShapeableDelegate {
    ShapeableDelegateV33(View view0) {
        this.initMaskOutlineProvider(view0);
    }

    private void initMaskOutlineProvider(View view0) {
        view0.setOutlineProvider(new ViewOutlineProvider() {
            @Override  // android.view.ViewOutlineProvider
            public void getOutline(View view0, Outline outline0) {
                if(!ShapeableDelegateV33.this.shapePath.isEmpty()) {
                    LinkFollowing..ExternalSyntheticApiModelOutline0.m(outline0, ShapeableDelegateV33.this.shapePath);
                }
            }
        });
    }

    @Override  // com.google.android.material.shape.ShapeableDelegate
    void invalidateClippingMethod(View view0) {
        view0.setClipToOutline(!this.shouldUseCompatClipping());
        if(this.shouldUseCompatClipping()) {
            view0.invalidate();
            return;
        }
        view0.invalidateOutline();
    }

    @Override  // com.google.android.material.shape.ShapeableDelegate
    boolean shouldUseCompatClipping() {
        return this.forceCompatClippingEnabled;
    }
}

