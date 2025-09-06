package com.google.android.material.shadow;

import android.graphics.drawable.Drawable;

public interface ShadowViewDelegate {
    float getRadius();

    boolean isCompatPaddingEnabled();

    void setBackgroundDrawable(Drawable arg1);

    void setShadowPadding(int arg1, int arg2, int arg3, int arg4);
}

