package com.google.android.material.internal;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Path.Direction;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.widget.FrameLayout;

public class ClippableRoundedCornerLayout extends FrameLayout {
    private float cornerRadius;
    private Path path;

    public ClippableRoundedCornerLayout(Context context0) {
        super(context0);
    }

    public ClippableRoundedCornerLayout(Context context0, AttributeSet attributeSet0) {
        super(context0, attributeSet0);
    }

    public ClippableRoundedCornerLayout(Context context0, AttributeSet attributeSet0, int v) {
        super(context0, attributeSet0, v);
    }

    @Override  // android.view.ViewGroup
    protected void dispatchDraw(Canvas canvas0) {
        if(this.path == null) {
            super.dispatchDraw(canvas0);
            return;
        }
        int v = canvas0.save();
        canvas0.clipPath(this.path);
        super.dispatchDraw(canvas0);
        canvas0.restoreToCount(v);
    }

    public float getCornerRadius() {
        return this.cornerRadius;
    }

    public void resetClipBoundsAndCornerRadius() {
        this.path = null;
        this.cornerRadius = 0.0f;
        this.invalidate();
    }

    public void updateClipBoundsAndCornerRadius(float f, float f1, float f2, float f3, float f4) {
        this.updateClipBoundsAndCornerRadius(new RectF(f, f1, f2, f3), f4);
    }

    public void updateClipBoundsAndCornerRadius(Rect rect0, float f) {
        this.updateClipBoundsAndCornerRadius(((float)rect0.left), ((float)rect0.top), ((float)rect0.right), ((float)rect0.bottom), f);
    }

    public void updateClipBoundsAndCornerRadius(RectF rectF0, float f) {
        if(this.path == null) {
            this.path = new Path();
        }
        this.cornerRadius = f;
        this.path.reset();
        this.path.addRoundRect(rectF0, f, f, Path.Direction.CW);
        this.path.close();
        this.invalidate();
    }

    public void updateCornerRadius(float f) {
        this.updateClipBoundsAndCornerRadius(((float)this.getLeft()), ((float)this.getTop()), ((float)this.getRight()), ((float)this.getBottom()), f);
    }
}

