package com.google.android.material.progressindicator;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.util.Property;
import androidx.interpolator.view.animation.FastOutSlowInInterpolator;
import androidx.vectordrawable.graphics.drawable.Animatable2Compat.AnimationCallback;

final class LinearIndeterminateContiguousAnimatorDelegate extends IndeterminateAnimatorDelegate {
    private static final Property ANIMATION_FRACTION = null;
    private static final int DURATION_PER_CYCLE_IN_MS = 333;
    private static final int TOTAL_DURATION_IN_MS = 667;
    private float animationFraction;
    private ObjectAnimator animator;
    private final BaseProgressIndicatorSpec baseSpec;
    private boolean dirtyColors;
    private FastOutSlowInInterpolator interpolator;
    private int newIndicatorColorIndex;

    static {
        LinearIndeterminateContiguousAnimatorDelegate.ANIMATION_FRACTION = new Property(Float.class, "animationFraction") {
            public Float get(LinearIndeterminateContiguousAnimatorDelegate linearIndeterminateContiguousAnimatorDelegate0) {
                return linearIndeterminateContiguousAnimatorDelegate0.getAnimationFraction();
            }

            @Override  // android.util.Property
            public Object get(Object object0) {
                return this.get(((LinearIndeterminateContiguousAnimatorDelegate)object0));
            }

            public void set(LinearIndeterminateContiguousAnimatorDelegate linearIndeterminateContiguousAnimatorDelegate0, Float float0) {
                linearIndeterminateContiguousAnimatorDelegate0.setAnimationFraction(((float)float0));
            }

            @Override  // android.util.Property
            public void set(Object object0, Object object1) {
                this.set(((LinearIndeterminateContiguousAnimatorDelegate)object0), ((Float)object1));
            }
        };
    }

    public LinearIndeterminateContiguousAnimatorDelegate(LinearProgressIndicatorSpec linearProgressIndicatorSpec0) {
        super(3);
        this.newIndicatorColorIndex = 1;
        this.baseSpec = linearProgressIndicatorSpec0;
        this.interpolator = new FastOutSlowInInterpolator();
    }

    @Override  // com.google.android.material.progressindicator.IndeterminateAnimatorDelegate
    public void cancelAnimatorImmediately() {
        ObjectAnimator objectAnimator0 = this.animator;
        if(objectAnimator0 != null) {
            objectAnimator0.cancel();
        }
    }

    private float getAnimationFraction() {
        return this.animationFraction;
    }

    @Override  // com.google.android.material.progressindicator.IndeterminateAnimatorDelegate
    public void invalidateSpecValues() {
        this.resetPropertiesForNewStart();
    }

    private void maybeInitializeAnimators() {
        if(this.animator == null) {
            ObjectAnimator objectAnimator0 = ObjectAnimator.ofFloat(this, LinearIndeterminateContiguousAnimatorDelegate.ANIMATION_FRACTION, new float[]{0.0f, 1.0f});
            this.animator = objectAnimator0;
            objectAnimator0.setDuration(333L);
            this.animator.setInterpolator(null);
            this.animator.setRepeatCount(-1);
            this.animator.addListener(new AnimatorListenerAdapter() {
                @Override  // android.animation.AnimatorListenerAdapter
                public void onAnimationRepeat(Animator animator0) {
                    super.onAnimationRepeat(animator0);
                    LinearIndeterminateContiguousAnimatorDelegate.this.newIndicatorColorIndex = (LinearIndeterminateContiguousAnimatorDelegate.this.newIndicatorColorIndex + 1) % LinearIndeterminateContiguousAnimatorDelegate.this.baseSpec.indicatorColors.length;
                    LinearIndeterminateContiguousAnimatorDelegate.this.dirtyColors = true;
                }
            });
        }
    }

    private void maybeUpdateSegmentColors() {
        if(this.dirtyColors && ((ActiveIndicator)this.activeIndicators.get(1)).endFraction < 1.0f) {
            ActiveIndicator drawingDelegate$ActiveIndicator0 = (ActiveIndicator)this.activeIndicators.get(2);
            drawingDelegate$ActiveIndicator0.color = ((ActiveIndicator)this.activeIndicators.get(1)).color;
            ActiveIndicator drawingDelegate$ActiveIndicator1 = (ActiveIndicator)this.activeIndicators.get(1);
            drawingDelegate$ActiveIndicator1.color = ((ActiveIndicator)this.activeIndicators.get(0)).color;
            ActiveIndicator drawingDelegate$ActiveIndicator2 = (ActiveIndicator)this.activeIndicators.get(0);
            drawingDelegate$ActiveIndicator2.color = this.baseSpec.indicatorColors[this.newIndicatorColorIndex];
            this.dirtyColors = false;
        }
    }

    @Override  // com.google.android.material.progressindicator.IndeterminateAnimatorDelegate
    public void registerAnimatorsCompleteCallback(AnimationCallback animatable2Compat$AnimationCallback0) {
    }

    @Override  // com.google.android.material.progressindicator.IndeterminateAnimatorDelegate
    public void requestCancelAnimatorAfterCurrentCycle() {
    }

    @Override  // com.google.android.material.progressindicator.IndeterminateAnimatorDelegate
    void resetPropertiesForNewStart() {
        this.dirtyColors = true;
        this.newIndicatorColorIndex = 1;
        for(Object object0: this.activeIndicators) {
            ((ActiveIndicator)object0).color = this.baseSpec.indicatorColors[0];
            ((ActiveIndicator)object0).gapSize = this.baseSpec.indicatorTrackGapSize / 2;
        }
    }

    @Override  // com.google.android.material.progressindicator.IndeterminateAnimatorDelegate
    void setAnimationFraction(float f) {
        this.animationFraction = f;
        this.updateSegmentPositions(((int)(f * 333.0f)));
        this.maybeUpdateSegmentColors();
        this.drawable.invalidateSelf();
    }

    @Override  // com.google.android.material.progressindicator.IndeterminateAnimatorDelegate
    public void startAnimator() {
        this.maybeInitializeAnimators();
        this.resetPropertiesForNewStart();
        this.animator.start();
    }

    @Override  // com.google.android.material.progressindicator.IndeterminateAnimatorDelegate
    public void unregisterAnimatorsCompleteCallback() {
    }

    private void updateSegmentPositions(int v) {
        ((ActiveIndicator)this.activeIndicators.get(0)).startFraction = 0.0f;
        float f = this.getFractionInRange(v, 0, 667);
        ActiveIndicator drawingDelegate$ActiveIndicator0 = (ActiveIndicator)this.activeIndicators.get(0);
        ActiveIndicator drawingDelegate$ActiveIndicator1 = (ActiveIndicator)this.activeIndicators.get(1);
        float f1 = this.interpolator.getInterpolation(f);
        drawingDelegate$ActiveIndicator1.startFraction = f1;
        drawingDelegate$ActiveIndicator0.endFraction = f1;
        ActiveIndicator drawingDelegate$ActiveIndicator2 = (ActiveIndicator)this.activeIndicators.get(1);
        ActiveIndicator drawingDelegate$ActiveIndicator3 = (ActiveIndicator)this.activeIndicators.get(2);
        float f2 = this.interpolator.getInterpolation(f + 0.49925f);
        drawingDelegate$ActiveIndicator3.startFraction = f2;
        drawingDelegate$ActiveIndicator2.endFraction = f2;
        ((ActiveIndicator)this.activeIndicators.get(2)).endFraction = 1.0f;
    }
}

