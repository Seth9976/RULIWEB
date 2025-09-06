package com.google.android.material.drawable;

import android.graphics.drawable.Drawable;
import androidx.appcompat.graphics.drawable.DrawableWrapperCompat;

public class ScaledDrawableWrapper extends DrawableWrapperCompat {
    private final int height;
    private final int width;

    public ScaledDrawableWrapper(Drawable drawable0, int v, int v1) {
        super(drawable0);
        this.width = v;
        this.height = v1;
    }

    @Override  // androidx.appcompat.graphics.drawable.DrawableWrapperCompat
    public int getIntrinsicHeight() {
        return this.height;
    }

    @Override  // androidx.appcompat.graphics.drawable.DrawableWrapperCompat
    public int getIntrinsicWidth() {
        return this.width;
    }
}

