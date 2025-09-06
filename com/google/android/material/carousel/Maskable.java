package com.google.android.material.carousel;

import android.graphics.RectF;

interface Maskable {
    RectF getMaskRectF();

    @Deprecated
    float getMaskXPercentage();

    void setMaskRectF(RectF arg1);

    @Deprecated
    void setMaskXPercentage(float arg1);

    void setOnMaskChangedListener(OnMaskChangedListener arg1);
}

