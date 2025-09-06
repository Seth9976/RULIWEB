package com.google.android.material.progressindicator;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import com.google.android.material.R.attr;
import com.google.android.material.R.dimen;
import com.google.android.material.R.styleable;
import com.google.android.material.internal.ThemeEnforcement;
import com.google.android.material.resources.MaterialResources;

public final class CircularProgressIndicatorSpec extends BaseProgressIndicatorSpec {
    public int indicatorDirection;
    public int indicatorInset;
    public int indicatorSize;

    public CircularProgressIndicatorSpec(Context context0, AttributeSet attributeSet0) {
        this(context0, attributeSet0, attr.circularProgressIndicatorStyle);
    }

    public CircularProgressIndicatorSpec(Context context0, AttributeSet attributeSet0, int v) {
        this(context0, attributeSet0, v, CircularProgressIndicator.DEF_STYLE_RES);
    }

    public CircularProgressIndicatorSpec(Context context0, AttributeSet attributeSet0, int v, int v1) {
        super(context0, attributeSet0, v, v1);
        int v2 = context0.getResources().getDimensionPixelSize(dimen.mtrl_progress_circular_size_medium);
        int v3 = context0.getResources().getDimensionPixelSize(dimen.mtrl_progress_circular_inset_medium);
        TypedArray typedArray0 = ThemeEnforcement.obtainStyledAttributes(context0, attributeSet0, styleable.CircularProgressIndicator, v, v1, new int[0]);
        this.indicatorSize = Math.max(MaterialResources.getDimensionPixelSize(context0, typedArray0, styleable.CircularProgressIndicator_indicatorSize, v2), this.trackThickness * 2);
        this.indicatorInset = MaterialResources.getDimensionPixelSize(context0, typedArray0, styleable.CircularProgressIndicator_indicatorInset, v3);
        this.indicatorDirection = typedArray0.getInt(styleable.CircularProgressIndicator_indicatorDirectionCircular, 0);
        typedArray0.recycle();
        this.validateSpec();
    }

    int getIndicatorTrackGapSizeDegree() {
        return this.indicatorTrackGapSize == 0 ? 0 : ((int)Math.round(360.0 / (((double)(this.indicatorSize - this.indicatorInset * 2 - this.trackThickness)) * 3.141593 / ((double)(this.indicatorTrackGapSize + this.trackCornerRadius)))));
    }
}

