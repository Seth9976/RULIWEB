package androidx.core.view.insets;

import android.graphics.drawable.ColorDrawable;

public class ColorProtection extends Protection {
    private int mColor;
    private final ColorDrawable mDrawable;
    private boolean mHasColor;

    public ColorProtection(int v) {
        super(v);
        this.mDrawable = new ColorDrawable();
        this.mColor = 0;
    }

    public ColorProtection(int v, int v1) {
        this(v);
        this.setColor(v1);
    }

    @Override  // androidx.core.view.insets.Protection
    void dispatchColorHint(int v) {
        if(!this.mHasColor) {
            this.setColorInner(v);
        }
    }

    public int getColor() {
        return this.mColor;
    }

    @Override  // androidx.core.view.insets.Protection
    boolean occupiesCorners() {
        return true;
    }

    public void setColor(int v) {
        this.mHasColor = true;
        this.setColorInner(v);
    }

    private void setColorInner(int v) {
        if(this.mColor != v) {
            this.mColor = v;
            this.mDrawable.setColor(v);
            this.setDrawable(this.mDrawable);
        }
    }
}

