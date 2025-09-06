package com.google.android.material.shape;

import android.graphics.Outline;
import android.view.View;
import android.view.ViewOutlineProvider;

class ShapeableDelegateV22 extends ShapeableDelegate {
    private boolean canUseViewOutline;
    private float cornerRadius;

    ShapeableDelegateV22(View view0) {
        this.canUseViewOutline = false;
        this.cornerRadius = 0.0f;
        this.initMaskOutlineProvider(view0);
    }

    float getCornerRadius() {
        return this.cornerRadius;
    }

    private float getDefaultCornerRadius() {
        return this.shapeAppearanceModel == null || this.maskBounds == null ? 0.0f : this.shapeAppearanceModel.topRightCornerSize.getCornerSize(this.maskBounds);
    }

    private void initMaskOutlineProvider(View view0) {
        view0.setOutlineProvider(new ViewOutlineProvider() {
            @Override  // android.view.ViewOutlineProvider
            public void getOutline(View view0, Outline outline0) {
                if(ShapeableDelegateV22.this.shapeAppearanceModel != null && !ShapeableDelegateV22.this.maskBounds.isEmpty()) {
                    outline0.setRoundRect(((int)ShapeableDelegateV22.this.maskBounds.left), ((int)ShapeableDelegateV22.this.maskBounds.top), ((int)ShapeableDelegateV22.this.maskBounds.right), ((int)ShapeableDelegateV22.this.maskBounds.bottom), ShapeableDelegateV22.this.cornerRadius);
                }
            }
        });
    }

    @Override  // com.google.android.material.shape.ShapeableDelegate
    void invalidateClippingMethod(View view0) {
        this.cornerRadius = this.getDefaultCornerRadius();
        this.canUseViewOutline = this.isShapeRoundRect() || this.offsetZeroCornerEdgeBoundsIfPossible();
        view0.setClipToOutline(!this.shouldUseCompatClipping());
        if(this.shouldUseCompatClipping()) {
            view0.invalidate();
            return;
        }
        view0.invalidateOutline();
    }

    private boolean isShapeRoundRect() {
        return this.maskBounds.isEmpty() || this.shapeAppearanceModel == null ? false : this.shapeAppearanceModel.isRoundRect(this.maskBounds);
    }

    private boolean offsetZeroCornerEdgeBoundsIfPossible() {
        if(!this.maskBounds.isEmpty() && this.shapeAppearanceModel != null && this.offsetZeroCornerEdgeBoundsEnabled && !this.shapeAppearanceModel.isRoundRect(this.maskBounds) && ShapeableDelegateV22.shapeUsesAllRoundedCornerTreatments(this.shapeAppearanceModel)) {
            float f = this.shapeAppearanceModel.getTopLeftCornerSize().getCornerSize(this.maskBounds);
            float f1 = this.shapeAppearanceModel.getTopRightCornerSize().getCornerSize(this.maskBounds);
            float f2 = this.shapeAppearanceModel.getBottomLeftCornerSize().getCornerSize(this.maskBounds);
            float f3 = this.shapeAppearanceModel.getBottomRightCornerSize().getCornerSize(this.maskBounds);
            int v = Float.compare(f, 0.0f);
            if(v == 0 && f2 == 0.0f && f1 == f3) {
                this.maskBounds.set(this.maskBounds.left - f1, this.maskBounds.top, this.maskBounds.right, this.maskBounds.bottom);
                this.cornerRadius = f1;
                return true;
            }
            if(v == 0 && f1 == 0.0f && f2 == f3) {
                this.maskBounds.set(this.maskBounds.left, this.maskBounds.top - f2, this.maskBounds.right, this.maskBounds.bottom);
                this.cornerRadius = f2;
                return true;
            }
            if(f1 == 0.0f && f3 == 0.0f && f == f2) {
                this.maskBounds.set(this.maskBounds.left, this.maskBounds.top, this.maskBounds.right + f, this.maskBounds.bottom);
                this.cornerRadius = f;
                return true;
            }
            if(f2 == 0.0f && f3 == 0.0f && f == f1) {
                this.maskBounds.set(this.maskBounds.left, this.maskBounds.top, this.maskBounds.right, this.maskBounds.bottom + f);
                this.cornerRadius = f;
                return true;
            }
        }
        return false;
    }

    // 去混淆评级： 低(40)
    private static boolean shapeUsesAllRoundedCornerTreatments(ShapeAppearanceModel shapeAppearanceModel0) {
        return shapeAppearanceModel0.getTopLeftCorner() instanceof RoundedCornerTreatment && shapeAppearanceModel0.getTopRightCorner() instanceof RoundedCornerTreatment && shapeAppearanceModel0.getBottomLeftCorner() instanceof RoundedCornerTreatment && shapeAppearanceModel0.getBottomRightCorner() instanceof RoundedCornerTreatment;
    }

    // 去混淆评级： 低(20)
    @Override  // com.google.android.material.shape.ShapeableDelegate
    boolean shouldUseCompatClipping() {
        return !this.canUseViewOutline || this.forceCompatClippingEnabled;
    }
}

