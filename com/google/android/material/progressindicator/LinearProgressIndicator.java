package com.google.android.material.progressindicator;

import android.content.Context;
import android.util.AttributeSet;
import androidx.core.view.ViewCompat;
import com.google.android.material.R.attr;
import com.google.android.material.R.style;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class LinearProgressIndicator extends BaseProgressIndicator {
    @Retention(RetentionPolicy.SOURCE)
    public @interface IndeterminateAnimationType {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface IndicatorDirection {
    }

    public static final int DEF_STYLE_RES = 0;
    public static final int INDETERMINATE_ANIMATION_TYPE_CONTIGUOUS = 0;
    public static final int INDETERMINATE_ANIMATION_TYPE_DISJOINT = 1;
    public static final int INDICATOR_DIRECTION_END_TO_START = 3;
    public static final int INDICATOR_DIRECTION_LEFT_TO_RIGHT = 0;
    public static final int INDICATOR_DIRECTION_RIGHT_TO_LEFT = 1;
    public static final int INDICATOR_DIRECTION_START_TO_END = 2;

    static {
        LinearProgressIndicator.DEF_STYLE_RES = style.Widget_MaterialComponents_LinearProgressIndicator;
    }

    public LinearProgressIndicator(Context context0) {
        this(context0, null);
    }

    public LinearProgressIndicator(Context context0, AttributeSet attributeSet0) {
        this(context0, attributeSet0, attr.linearProgressIndicatorStyle);
    }

    public LinearProgressIndicator(Context context0, AttributeSet attributeSet0, int v) {
        super(context0, attributeSet0, v, LinearProgressIndicator.DEF_STYLE_RES);
        this.initializeDrawables();
    }

    @Override  // com.google.android.material.progressindicator.BaseProgressIndicator
    BaseProgressIndicatorSpec createSpec(Context context0, AttributeSet attributeSet0) {
        return this.createSpec(context0, attributeSet0);
    }

    LinearProgressIndicatorSpec createSpec(Context context0, AttributeSet attributeSet0) {
        return new LinearProgressIndicatorSpec(context0, attributeSet0);
    }

    public int getIndeterminateAnimationType() {
        return ((LinearProgressIndicatorSpec)this.spec).indeterminateAnimationType;
    }

    public int getIndicatorDirection() {
        return ((LinearProgressIndicatorSpec)this.spec).indicatorDirection;
    }

    public int getTrackStopIndicatorSize() {
        return ((LinearProgressIndicatorSpec)this.spec).trackStopIndicatorSize;
    }

    private void initializeDrawables() {
        LinearDrawingDelegate linearDrawingDelegate0 = new LinearDrawingDelegate(((LinearProgressIndicatorSpec)this.spec));
        this.setIndeterminateDrawable(IndeterminateDrawable.createLinearDrawable(this.getContext(), ((LinearProgressIndicatorSpec)this.spec), linearDrawingDelegate0));
        this.setProgressDrawable(DeterminateDrawable.createLinearDrawable(this.getContext(), ((LinearProgressIndicatorSpec)this.spec), linearDrawingDelegate0));
    }

    @Override  // android.view.View
    protected void onLayout(boolean z, int v, int v1, int v2, int v3) {
        super.onLayout(z, v, v1, v2, v3);
        LinearProgressIndicatorSpec linearProgressIndicatorSpec0 = (LinearProgressIndicatorSpec)this.spec;
        linearProgressIndicatorSpec0.drawHorizontallyInverse = ((LinearProgressIndicatorSpec)this.spec).indicatorDirection == 1 || ViewCompat.getLayoutDirection(this) == 1 && ((LinearProgressIndicatorSpec)this.spec).indicatorDirection == 2 || ViewCompat.getLayoutDirection(this) == 0 && ((LinearProgressIndicatorSpec)this.spec).indicatorDirection == 3;
    }

    @Override  // android.widget.ProgressBar
    protected void onSizeChanged(int v, int v1, int v2, int v3) {
        int v4 = v - (this.getPaddingLeft() + this.getPaddingRight());
        int v5 = v1 - (this.getPaddingTop() + this.getPaddingBottom());
        IndeterminateDrawable indeterminateDrawable0 = this.getIndeterminateDrawable();
        if(indeterminateDrawable0 != null) {
            indeterminateDrawable0.setBounds(0, 0, v4, v5);
        }
        DeterminateDrawable determinateDrawable0 = this.getProgressDrawable();
        if(determinateDrawable0 != null) {
            determinateDrawable0.setBounds(0, 0, v4, v5);
        }
    }

    public void setIndeterminateAnimationType(int v) {
        if(((LinearProgressIndicatorSpec)this.spec).indeterminateAnimationType == v) {
            return;
        }
        if(this.visibleToUser() && this.isIndeterminate()) {
            throw new IllegalStateException("Cannot change indeterminate animation type while the progress indicator is show in indeterminate mode.");
        }
        ((LinearProgressIndicatorSpec)this.spec).indeterminateAnimationType = v;
        ((LinearProgressIndicatorSpec)this.spec).validateSpec();
        if(v == 0) {
            this.getIndeterminateDrawable().setAnimatorDelegate(new LinearIndeterminateContiguousAnimatorDelegate(((LinearProgressIndicatorSpec)this.spec)));
        }
        else {
            this.getIndeterminateDrawable().setAnimatorDelegate(new LinearIndeterminateDisjointAnimatorDelegate(this.getContext(), ((LinearProgressIndicatorSpec)this.spec)));
        }
        this.invalidate();
    }

    @Override  // com.google.android.material.progressindicator.BaseProgressIndicator
    public void setIndicatorColor(int[] arr_v) {
        super.setIndicatorColor(arr_v);
        ((LinearProgressIndicatorSpec)this.spec).validateSpec();
    }

    public void setIndicatorDirection(int v) {
        ((LinearProgressIndicatorSpec)this.spec).indicatorDirection = v;
        LinearProgressIndicatorSpec linearProgressIndicatorSpec0 = (LinearProgressIndicatorSpec)this.spec;
        linearProgressIndicatorSpec0.drawHorizontallyInverse = v == 1 || ViewCompat.getLayoutDirection(this) == 1 && ((LinearProgressIndicatorSpec)this.spec).indicatorDirection == 2 || ViewCompat.getLayoutDirection(this) == 0 && v == 3;
        this.invalidate();
    }

    @Override  // com.google.android.material.progressindicator.BaseProgressIndicator
    public void setProgressCompat(int v, boolean z) {
        if(this.spec != null && ((LinearProgressIndicatorSpec)this.spec).indeterminateAnimationType == 0 && this.isIndeterminate()) {
            return;
        }
        super.setProgressCompat(v, z);
    }

    @Override  // com.google.android.material.progressindicator.BaseProgressIndicator
    public void setTrackCornerRadius(int v) {
        super.setTrackCornerRadius(v);
        ((LinearProgressIndicatorSpec)this.spec).validateSpec();
        this.invalidate();
    }

    public void setTrackStopIndicatorSize(int v) {
        if(((LinearProgressIndicatorSpec)this.spec).trackStopIndicatorSize != v) {
            ((LinearProgressIndicatorSpec)this.spec).trackStopIndicatorSize = Math.min(v, ((LinearProgressIndicatorSpec)this.spec).trackThickness);
            ((LinearProgressIndicatorSpec)this.spec).validateSpec();
            this.invalidate();
        }
    }
}

