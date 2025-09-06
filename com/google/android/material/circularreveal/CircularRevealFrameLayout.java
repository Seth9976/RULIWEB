package com.google.android.material.circularreveal;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.FrameLayout;

public class CircularRevealFrameLayout extends FrameLayout implements CircularRevealWidget {
    private final CircularRevealHelper helper;

    public CircularRevealFrameLayout(Context context0) {
        this(context0, null);
    }

    public CircularRevealFrameLayout(Context context0, AttributeSet attributeSet0) {
        super(context0, attributeSet0);
        this.helper = new CircularRevealHelper(this);
    }

    @Override  // com.google.android.material.circularreveal.CircularRevealHelper$Delegate
    public void actualDraw(Canvas canvas0) {
        super.draw(canvas0);
    }

    @Override  // com.google.android.material.circularreveal.CircularRevealHelper$Delegate
    public boolean actualIsOpaque() {
        return super.isOpaque();
    }

    @Override  // com.google.android.material.circularreveal.CircularRevealWidget
    public void buildCircularRevealCache() {
        this.helper.buildCircularRevealCache();
    }

    @Override  // com.google.android.material.circularreveal.CircularRevealWidget
    public void destroyCircularRevealCache() {
        this.helper.destroyCircularRevealCache();
    }

    @Override  // com.google.android.material.circularreveal.CircularRevealWidget, android.view.View
    public void draw(Canvas canvas0) {
        CircularRevealHelper circularRevealHelper0 = this.helper;
        if(circularRevealHelper0 != null) {
            circularRevealHelper0.draw(canvas0);
            return;
        }
        super.draw(canvas0);
    }

    @Override  // com.google.android.material.circularreveal.CircularRevealWidget
    public Drawable getCircularRevealOverlayDrawable() {
        return this.helper.getCircularRevealOverlayDrawable();
    }

    @Override  // com.google.android.material.circularreveal.CircularRevealWidget
    public int getCircularRevealScrimColor() {
        return this.helper.getCircularRevealScrimColor();
    }

    @Override  // com.google.android.material.circularreveal.CircularRevealWidget
    public RevealInfo getRevealInfo() {
        return this.helper.getRevealInfo();
    }

    @Override  // com.google.android.material.circularreveal.CircularRevealWidget, android.view.View
    public boolean isOpaque() {
        return this.helper == null ? super.isOpaque() : this.helper.isOpaque();
    }

    @Override  // com.google.android.material.circularreveal.CircularRevealWidget
    public void setCircularRevealOverlayDrawable(Drawable drawable0) {
        this.helper.setCircularRevealOverlayDrawable(drawable0);
    }

    @Override  // com.google.android.material.circularreveal.CircularRevealWidget
    public void setCircularRevealScrimColor(int v) {
        this.helper.setCircularRevealScrimColor(v);
    }

    @Override  // com.google.android.material.circularreveal.CircularRevealWidget
    public void setRevealInfo(RevealInfo circularRevealWidget$RevealInfo0) {
        this.helper.setRevealInfo(circularRevealWidget$RevealInfo0);
    }
}

