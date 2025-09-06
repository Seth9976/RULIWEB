package com.google.android.material.progressindicator;

import android.content.Context;
import android.util.AttributeSet;
import com.google.android.material.R.attr;
import com.google.android.material.R.style;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class CircularProgressIndicator extends BaseProgressIndicator {
    @Retention(RetentionPolicy.SOURCE)
    public @interface IndicatorDirection {
    }

    public static final int DEF_STYLE_RES = 0;
    public static final int INDICATOR_DIRECTION_CLOCKWISE = 0;
    public static final int INDICATOR_DIRECTION_COUNTERCLOCKWISE = 1;

    static {
        CircularProgressIndicator.DEF_STYLE_RES = style.Widget_MaterialComponents_CircularProgressIndicator;
    }

    public CircularProgressIndicator(Context context0) {
        this(context0, null);
    }

    public CircularProgressIndicator(Context context0, AttributeSet attributeSet0) {
        this(context0, attributeSet0, attr.circularProgressIndicatorStyle);
    }

    public CircularProgressIndicator(Context context0, AttributeSet attributeSet0, int v) {
        super(context0, attributeSet0, v, CircularProgressIndicator.DEF_STYLE_RES);
        this.initializeDrawables();
    }

    @Override  // com.google.android.material.progressindicator.BaseProgressIndicator
    BaseProgressIndicatorSpec createSpec(Context context0, AttributeSet attributeSet0) {
        return this.createSpec(context0, attributeSet0);
    }

    CircularProgressIndicatorSpec createSpec(Context context0, AttributeSet attributeSet0) {
        return new CircularProgressIndicatorSpec(context0, attributeSet0);
    }

    public int getIndicatorDirection() {
        return ((CircularProgressIndicatorSpec)this.spec).indicatorDirection;
    }

    public int getIndicatorInset() {
        return ((CircularProgressIndicatorSpec)this.spec).indicatorInset;
    }

    public int getIndicatorSize() {
        return ((CircularProgressIndicatorSpec)this.spec).indicatorSize;
    }

    private void initializeDrawables() {
        CircularDrawingDelegate circularDrawingDelegate0 = new CircularDrawingDelegate(((CircularProgressIndicatorSpec)this.spec));
        this.setIndeterminateDrawable(IndeterminateDrawable.createCircularDrawable(this.getContext(), ((CircularProgressIndicatorSpec)this.spec), circularDrawingDelegate0));
        this.setProgressDrawable(DeterminateDrawable.createCircularDrawable(this.getContext(), ((CircularProgressIndicatorSpec)this.spec), circularDrawingDelegate0));
    }

    public void setIndicatorDirection(int v) {
        ((CircularProgressIndicatorSpec)this.spec).indicatorDirection = v;
        this.invalidate();
    }

    public void setIndicatorInset(int v) {
        if(((CircularProgressIndicatorSpec)this.spec).indicatorInset != v) {
            ((CircularProgressIndicatorSpec)this.spec).indicatorInset = v;
            this.invalidate();
        }
    }

    public void setIndicatorSize(int v) {
        int v1 = Math.max(v, this.getTrackThickness() * 2);
        if(((CircularProgressIndicatorSpec)this.spec).indicatorSize != v1) {
            ((CircularProgressIndicatorSpec)this.spec).indicatorSize = v1;
            ((CircularProgressIndicatorSpec)this.spec).validateSpec();
            this.requestLayout();
            this.invalidate();
        }
    }

    @Override  // com.google.android.material.progressindicator.BaseProgressIndicator
    public void setTrackThickness(int v) {
        super.setTrackThickness(v);
        ((CircularProgressIndicatorSpec)this.spec).validateSpec();
    }
}

