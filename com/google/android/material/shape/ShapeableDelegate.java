package com.google.android.material.shape;

import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.RectF;
import android.os.Build.VERSION;
import android.view.View;
import com.google.android.material.canvas.CanvasCompat.CanvasOperation;

public abstract class ShapeableDelegate {
    boolean forceCompatClippingEnabled;
    RectF maskBounds;
    boolean offsetZeroCornerEdgeBoundsEnabled;
    ShapeAppearanceModel shapeAppearanceModel;
    final Path shapePath;

    public ShapeableDelegate() {
        this.forceCompatClippingEnabled = false;
        this.offsetZeroCornerEdgeBoundsEnabled = false;
        this.maskBounds = new RectF();
        this.shapePath = new Path();
    }

    public static ShapeableDelegate create(View view0) {
        if(Build.VERSION.SDK_INT >= 33) {
            return new ShapeableDelegateV33(view0);
        }
        return Build.VERSION.SDK_INT >= 22 ? new ShapeableDelegateV22(view0) : new ShapeableDelegateV14();
    }

    abstract void invalidateClippingMethod(View arg1);

    public boolean isForceCompatClippingEnabled() {
        return this.forceCompatClippingEnabled;
    }

    private boolean isMaskBoundsValid() {
        return this.maskBounds.left <= this.maskBounds.right && this.maskBounds.top <= this.maskBounds.bottom;
    }

    public void maybeClip(Canvas canvas0, CanvasOperation canvasCompat$CanvasOperation0) {
        if(this.shouldUseCompatClipping() && !this.shapePath.isEmpty()) {
            canvas0.save();
            canvas0.clipPath(this.shapePath);
            canvasCompat$CanvasOperation0.run(canvas0);
            canvas0.restore();
            return;
        }
        canvasCompat$CanvasOperation0.run(canvas0);
    }

    public void onMaskChanged(View view0, RectF rectF0) {
        this.maskBounds = rectF0;
        this.updateShapePath();
        this.invalidateClippingMethod(view0);
    }

    public void onShapeAppearanceChanged(View view0, ShapeAppearanceModel shapeAppearanceModel0) {
        this.shapeAppearanceModel = shapeAppearanceModel0;
        this.updateShapePath();
        this.invalidateClippingMethod(view0);
    }

    public void setForceCompatClippingEnabled(View view0, boolean z) {
        if(z != this.forceCompatClippingEnabled) {
            this.forceCompatClippingEnabled = z;
            this.invalidateClippingMethod(view0);
        }
    }

    public void setOffsetZeroCornerEdgeBoundsEnabled(View view0, boolean z) {
        this.offsetZeroCornerEdgeBoundsEnabled = z;
        this.invalidateClippingMethod(view0);
    }

    abstract boolean shouldUseCompatClipping();

    private void updateShapePath() {
        if(this.isMaskBoundsValid() && this.shapeAppearanceModel != null) {
            ShapeAppearancePathProvider.getInstance().calculatePath(this.shapeAppearanceModel, 1.0f, this.maskBounds, this.shapePath);
        }
    }
}

