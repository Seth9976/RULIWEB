package com.google.android.material.progressindicator;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import com.google.android.material.R.attr;
import com.google.android.material.R.styleable;
import com.google.android.material.internal.ThemeEnforcement;

public final class LinearProgressIndicatorSpec extends BaseProgressIndicatorSpec {
    boolean drawHorizontallyInverse;
    public int indeterminateAnimationType;
    public int indicatorDirection;
    public int trackStopIndicatorSize;

    public LinearProgressIndicatorSpec(Context context0, AttributeSet attributeSet0) {
        this(context0, attributeSet0, attr.linearProgressIndicatorStyle);
    }

    public LinearProgressIndicatorSpec(Context context0, AttributeSet attributeSet0, int v) {
        this(context0, attributeSet0, v, LinearProgressIndicator.DEF_STYLE_RES);
    }

    public LinearProgressIndicatorSpec(Context context0, AttributeSet attributeSet0, int v, int v1) {
        super(context0, attributeSet0, v, v1);
        boolean z = false;
        TypedArray typedArray0 = ThemeEnforcement.obtainStyledAttributes(context0, attributeSet0, styleable.LinearProgressIndicator, attr.linearProgressIndicatorStyle, LinearProgressIndicator.DEF_STYLE_RES, new int[0]);
        this.indeterminateAnimationType = typedArray0.getInt(styleable.LinearProgressIndicator_indeterminateAnimationType, 1);
        this.indicatorDirection = typedArray0.getInt(styleable.LinearProgressIndicator_indicatorDirectionLinear, 0);
        this.trackStopIndicatorSize = Math.min(typedArray0.getDimensionPixelSize(styleable.LinearProgressIndicator_trackStopIndicatorSize, 0), this.trackThickness);
        typedArray0.recycle();
        this.validateSpec();
        if(this.indicatorDirection == 1) {
            z = true;
        }
        this.drawHorizontallyInverse = z;
    }

    @Override  // com.google.android.material.progressindicator.BaseProgressIndicatorSpec
    void validateSpec() {
        super.validateSpec();
        if(this.trackStopIndicatorSize < 0) {
            throw new IllegalArgumentException("Stop indicator size must be >= 0.");
        }
        if(this.indeterminateAnimationType == 0) {
            if(this.trackCornerRadius > 0 && this.indicatorTrackGapSize == 0) {
                throw new IllegalArgumentException("Rounded corners without gap are not supported in contiguous indeterminate animation.");
            }
            if(this.indicatorColors.length < 3) {
                throw new IllegalArgumentException("Contiguous indeterminate animation must be used with 3 or more indicator colors.");
            }
        }
    }
}

