package com.google.android.material.progressindicator;

import androidx.vectordrawable.graphics.drawable.Animatable2Compat.AnimationCallback;
import java.util.ArrayList;
import java.util.List;

abstract class IndeterminateAnimatorDelegate {
    protected final List activeIndicators;
    protected IndeterminateDrawable drawable;

    protected IndeterminateAnimatorDelegate(int v) {
        this.activeIndicators = new ArrayList();
        for(int v1 = 0; v1 < v; ++v1) {
            ActiveIndicator drawingDelegate$ActiveIndicator0 = new ActiveIndicator();
            this.activeIndicators.add(drawingDelegate$ActiveIndicator0);
        }
    }

    abstract void cancelAnimatorImmediately();

    protected float getFractionInRange(int v, int v1, int v2) {
        return ((float)(v - v1)) / ((float)v2);
    }

    public abstract void invalidateSpecValues();

    public abstract void registerAnimatorsCompleteCallback(AnimationCallback arg1);

    protected void registerDrawable(IndeterminateDrawable indeterminateDrawable0) {
        this.drawable = indeterminateDrawable0;
    }

    abstract void requestCancelAnimatorAfterCurrentCycle();

    abstract void resetPropertiesForNewStart();

    abstract void setAnimationFraction(float arg1);

    abstract void startAnimator();

    public abstract void unregisterAnimatorsCompleteCallback();
}

