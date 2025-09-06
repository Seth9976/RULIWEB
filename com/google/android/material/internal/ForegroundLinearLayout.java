package com.google.android.material.internal;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.Gravity;
import androidx.appcompat.widget.LinearLayoutCompat;
import com.google.android.material.R.styleable;

public class ForegroundLinearLayout extends LinearLayoutCompat {
    private Drawable foreground;
    boolean foregroundBoundsChanged;
    private int foregroundGravity;
    protected boolean mForegroundInPadding;
    private final Rect overlayBounds;
    private final Rect selfBounds;

    public ForegroundLinearLayout(Context context0) {
        this(context0, null);
    }

    public ForegroundLinearLayout(Context context0, AttributeSet attributeSet0) {
        this(context0, attributeSet0, 0);
    }

    public ForegroundLinearLayout(Context context0, AttributeSet attributeSet0, int v) {
        super(context0, attributeSet0, v);
        this.selfBounds = new Rect();
        this.overlayBounds = new Rect();
        this.foregroundGravity = 0x77;
        this.mForegroundInPadding = true;
        this.foregroundBoundsChanged = false;
        TypedArray typedArray0 = ThemeEnforcement.obtainStyledAttributes(context0, attributeSet0, styleable.ForegroundLinearLayout, v, 0, new int[0]);
        this.foregroundGravity = typedArray0.getInt(styleable.ForegroundLinearLayout_android_foregroundGravity, this.foregroundGravity);
        Drawable drawable0 = typedArray0.getDrawable(styleable.ForegroundLinearLayout_android_foreground);
        if(drawable0 != null) {
            this.setForeground(drawable0);
        }
        this.mForegroundInPadding = typedArray0.getBoolean(styleable.ForegroundLinearLayout_foregroundInsidePadding, true);
        typedArray0.recycle();
    }

    @Override  // android.view.View
    public void draw(Canvas canvas0) {
        super.draw(canvas0);
        Drawable drawable0 = this.foreground;
        if(drawable0 != null) {
            if(this.foregroundBoundsChanged) {
                this.foregroundBoundsChanged = false;
                Rect rect0 = this.selfBounds;
                Rect rect1 = this.overlayBounds;
                int v = this.getRight() - this.getLeft();
                int v1 = this.getBottom() - this.getTop();
                if(this.mForegroundInPadding) {
                    rect0.set(0, 0, v, v1);
                }
                else {
                    rect0.set(this.getPaddingLeft(), this.getPaddingTop(), v - this.getPaddingRight(), v1 - this.getPaddingBottom());
                }
                Gravity.apply(this.foregroundGravity, drawable0.getIntrinsicWidth(), drawable0.getIntrinsicHeight(), rect0, rect1);
                drawable0.setBounds(rect1);
            }
            drawable0.draw(canvas0);
        }
    }

    @Override  // android.view.View
    public void drawableHotspotChanged(float f, float f1) {
        super.drawableHotspotChanged(f, f1);
        Drawable drawable0 = this.foreground;
        if(drawable0 != null) {
            drawable0.setHotspot(f, f1);
        }
    }

    @Override  // android.view.ViewGroup
    protected void drawableStateChanged() {
        super.drawableStateChanged();
        if(this.foreground != null && this.foreground.isStateful()) {
            this.foreground.setState(this.getDrawableState());
        }
    }

    @Override  // android.view.View
    public Drawable getForeground() {
        return this.foreground;
    }

    @Override  // android.view.View
    public int getForegroundGravity() {
        return this.foregroundGravity;
    }

    @Override  // android.view.ViewGroup
    public void jumpDrawablesToCurrentState() {
        super.jumpDrawablesToCurrentState();
        Drawable drawable0 = this.foreground;
        if(drawable0 != null) {
            drawable0.jumpToCurrentState();
        }
    }

    @Override  // androidx.appcompat.widget.LinearLayoutCompat
    protected void onLayout(boolean z, int v, int v1, int v2, int v3) {
        super.onLayout(z, v, v1, v2, v3);
        this.foregroundBoundsChanged |= z;
    }

    @Override  // android.view.View
    protected void onSizeChanged(int v, int v1, int v2, int v3) {
        super.onSizeChanged(v, v1, v2, v3);
        this.foregroundBoundsChanged = true;
    }

    @Override  // android.view.View
    public void setForeground(Drawable drawable0) {
        Drawable drawable1 = this.foreground;
        if(drawable1 != drawable0) {
            if(drawable1 != null) {
                drawable1.setCallback(null);
                this.unscheduleDrawable(this.foreground);
            }
            this.foreground = drawable0;
            this.foregroundBoundsChanged = true;
            if(drawable0 == null) {
                this.setWillNotDraw(true);
            }
            else {
                this.setWillNotDraw(false);
                drawable0.setCallback(this);
                if(drawable0.isStateful()) {
                    drawable0.setState(this.getDrawableState());
                }
                if(this.foregroundGravity == 0x77) {
                    drawable0.getPadding(new Rect());
                }
            }
            this.requestLayout();
            this.invalidate();
        }
    }

    @Override  // android.view.View
    public void setForegroundGravity(int v) {
        if(this.foregroundGravity != v) {
            if((0x800007 & v) == 0) {
                v |= 0x800003;
            }
            if((v & 0x70) == 0) {
                v |= 0x30;
            }
            this.foregroundGravity = v;
            if(v == 0x77 && this.foreground != null) {
                Rect rect0 = new Rect();
                this.foreground.getPadding(rect0);
            }
            this.requestLayout();
        }
    }

    @Override  // android.view.View
    protected boolean verifyDrawable(Drawable drawable0) {
        return super.verifyDrawable(drawable0) || drawable0 == this.foreground;
    }
}

