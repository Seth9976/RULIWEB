package com.google.android.material.carousel;

import android.content.Context;
import android.view.View;

public abstract class CarouselStrategy {
    private float smallSizeMax;
    private float smallSizeMin;

    static int[] doubleCounts(int[] arr_v) {
        int[] arr_v1 = new int[arr_v.length];
        for(int v = 0; v < arr_v.length; ++v) {
            arr_v1[v] = arr_v[v] * 2;
        }
        return arr_v1;
    }

    static float getChildMaskPercentage(float f, float f1, float f2) [...] // Inlined contents

    public float getSmallItemSizeMax() {
        return this.smallSizeMax;
    }

    public float getSmallItemSizeMin() {
        return this.smallSizeMin;
    }

    void initialize(Context context0) {
        this.smallSizeMin = this.smallSizeMin > 0.0f ? this.smallSizeMin : CarouselStrategyHelper.getSmallSizeMin(context0);
        this.smallSizeMax = this.smallSizeMax > 0.0f ? this.smallSizeMax : CarouselStrategyHelper.getSmallSizeMax(context0);
    }

    boolean isContained() {
        return true;
    }

    abstract KeylineState onFirstChildMeasuredWithMargins(Carousel arg1, View arg2);

    public void setSmallItemSizeMax(float f) {
        this.smallSizeMax = f;
    }

    public void setSmallItemSizeMin(float f) {
        this.smallSizeMin = f;
    }

    boolean shouldRefreshKeylineState(Carousel carousel0, int v) {
        return false;
    }
}

