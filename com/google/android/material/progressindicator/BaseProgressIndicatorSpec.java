package com.google.android.material.progressindicator;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import com.google.android.material.R.attr;
import com.google.android.material.R.dimen;
import com.google.android.material.R.styleable;
import com.google.android.material.color.MaterialColors;
import com.google.android.material.internal.ThemeEnforcement;
import com.google.android.material.resources.MaterialResources;

public abstract class BaseProgressIndicatorSpec {
    public int hideAnimationBehavior;
    public int[] indicatorColors;
    public int indicatorTrackGapSize;
    public int showAnimationBehavior;
    public int trackColor;
    public int trackCornerRadius;
    public int trackThickness;

    protected BaseProgressIndicatorSpec(Context context0, AttributeSet attributeSet0, int v, int v1) {
        this.indicatorColors = new int[0];
        int v2 = context0.getResources().getDimensionPixelSize(dimen.mtrl_progress_track_thickness);
        TypedArray typedArray0 = ThemeEnforcement.obtainStyledAttributes(context0, attributeSet0, styleable.BaseProgressIndicator, v, v1, new int[0]);
        this.trackThickness = MaterialResources.getDimensionPixelSize(context0, typedArray0, styleable.BaseProgressIndicator_trackThickness, v2);
        this.trackCornerRadius = Math.min(MaterialResources.getDimensionPixelSize(context0, typedArray0, styleable.BaseProgressIndicator_trackCornerRadius, 0), this.trackThickness / 2);
        this.showAnimationBehavior = typedArray0.getInt(styleable.BaseProgressIndicator_showAnimationBehavior, 0);
        this.hideAnimationBehavior = typedArray0.getInt(styleable.BaseProgressIndicator_hideAnimationBehavior, 0);
        this.indicatorTrackGapSize = typedArray0.getDimensionPixelSize(styleable.BaseProgressIndicator_indicatorTrackGapSize, 0);
        this.loadIndicatorColors(context0, typedArray0);
        this.loadTrackColor(context0, typedArray0);
        typedArray0.recycle();
    }

    public boolean isHideAnimationEnabled() {
        return this.hideAnimationBehavior != 0;
    }

    public boolean isShowAnimationEnabled() {
        return this.showAnimationBehavior != 0;
    }

    private void loadIndicatorColors(Context context0, TypedArray typedArray0) {
        if(!typedArray0.hasValue(styleable.BaseProgressIndicator_indicatorColor)) {
            this.indicatorColors = new int[]{MaterialColors.getColor(context0, attr.colorPrimary, -1)};
            return;
        }
        if(typedArray0.peekValue(styleable.BaseProgressIndicator_indicatorColor).type != 1) {
            this.indicatorColors = new int[]{typedArray0.getColor(styleable.BaseProgressIndicator_indicatorColor, -1)};
            return;
        }
        int[] arr_v = context0.getResources().getIntArray(typedArray0.getResourceId(styleable.BaseProgressIndicator_indicatorColor, -1));
        this.indicatorColors = arr_v;
        if(arr_v.length == 0) {
            throw new IllegalArgumentException("indicatorColors cannot be empty when indicatorColor is not used.");
        }
    }

    private void loadTrackColor(Context context0, TypedArray typedArray0) {
        if(typedArray0.hasValue(styleable.BaseProgressIndicator_trackColor)) {
            this.trackColor = typedArray0.getColor(styleable.BaseProgressIndicator_trackColor, -1);
            return;
        }
        this.trackColor = this.indicatorColors[0];
        TypedArray typedArray1 = context0.getTheme().obtainStyledAttributes(new int[]{0x1010033});
        float f = typedArray1.getFloat(0, 0.2f);
        typedArray1.recycle();
        this.trackColor = MaterialColors.compositeARGBWithAlpha(this.trackColor, ((int)(f * 255.0f)));
    }

    void validateSpec() {
        if(this.indicatorTrackGapSize < 0) {
            throw new IllegalArgumentException("indicatorTrackGapSize must be >= 0.");
        }
    }
}

