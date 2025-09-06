package com.google.android.material.internal;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.drawable.Drawable;

public class FadeThroughDrawable extends Drawable {
    private final float[] alphas;
    private final Drawable fadeInDrawable;
    private final Drawable fadeOutDrawable;
    private float progress;

    public FadeThroughDrawable(Drawable drawable0, Drawable drawable1) {
        this.fadeOutDrawable = drawable0.getConstantState().newDrawable().mutate();
        Drawable drawable2 = drawable1.getConstantState().newDrawable().mutate();
        this.fadeInDrawable = drawable2;
        drawable2.setAlpha(0);
        this.alphas = new float[2];
    }

    @Override  // android.graphics.drawable.Drawable
    public void draw(Canvas canvas0) {
        this.fadeOutDrawable.draw(canvas0);
        this.fadeInDrawable.draw(canvas0);
    }

    @Override  // android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        return Math.max(this.fadeOutDrawable.getIntrinsicHeight(), this.fadeInDrawable.getIntrinsicHeight());
    }

    @Override  // android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        return Math.max(this.fadeOutDrawable.getIntrinsicWidth(), this.fadeInDrawable.getIntrinsicWidth());
    }

    @Override  // android.graphics.drawable.Drawable
    public int getMinimumHeight() {
        return Math.max(this.fadeOutDrawable.getMinimumHeight(), this.fadeInDrawable.getMinimumHeight());
    }

    @Override  // android.graphics.drawable.Drawable
    public int getMinimumWidth() {
        return Math.max(this.fadeOutDrawable.getMinimumWidth(), this.fadeInDrawable.getMinimumWidth());
    }

    @Override  // android.graphics.drawable.Drawable
    public int getOpacity() {
        return -3;
    }

    // 去混淆评级： 低(20)
    @Override  // android.graphics.drawable.Drawable
    public boolean isStateful() {
        return this.fadeOutDrawable.isStateful() || this.fadeInDrawable.isStateful();
    }

    @Override  // android.graphics.drawable.Drawable
    public void setAlpha(int v) {
        if(this.progress <= 0.5f) {
            this.fadeOutDrawable.setAlpha(v);
            this.fadeInDrawable.setAlpha(0);
        }
        else {
            this.fadeOutDrawable.setAlpha(0);
            this.fadeInDrawable.setAlpha(v);
        }
        this.invalidateSelf();
    }

    @Override  // android.graphics.drawable.Drawable
    public void setBounds(int v, int v1, int v2, int v3) {
        super.setBounds(v, v1, v2, v3);
        this.fadeOutDrawable.setBounds(v, v1, v2, v3);
        this.fadeInDrawable.setBounds(v, v1, v2, v3);
    }

    @Override  // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter0) {
        this.fadeOutDrawable.setColorFilter(colorFilter0);
        this.fadeInDrawable.setColorFilter(colorFilter0);
        this.invalidateSelf();
    }

    public void setProgress(float f) {
        if(this.progress != f) {
            this.progress = f;
            FadeThroughUtils.calculateFadeOutAndInAlphas(f, this.alphas);
            this.fadeOutDrawable.setAlpha(((int)(this.alphas[0] * 255.0f)));
            this.fadeInDrawable.setAlpha(((int)(this.alphas[1] * 255.0f)));
            this.invalidateSelf();
        }
    }

    // 去混淆评级： 低(20)
    @Override  // android.graphics.drawable.Drawable
    public boolean setState(int[] arr_v) {
        return this.fadeOutDrawable.setState(arr_v) || this.fadeInDrawable.setState(arr_v);
    }
}

