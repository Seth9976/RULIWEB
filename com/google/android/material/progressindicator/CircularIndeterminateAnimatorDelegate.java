package com.google.android.material.progressindicator;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.util.Property;
import androidx.interpolator.view.animation.FastOutSlowInInterpolator;
import androidx.vectordrawable.graphics.drawable.Animatable2Compat.AnimationCallback;
import com.google.android.material.animation.ArgbEvaluatorCompat;

final class CircularIndeterminateAnimatorDelegate extends IndeterminateAnimatorDelegate {
    private static final Property ANIMATION_FRACTION = null;
    private static final Property COMPLETE_END_FRACTION = null;
    private static final int CONSTANT_ROTATION_DEGREES = 0x5F0;
    private static final int[] DELAY_TO_COLLAPSE_IN_MS = null;
    private static final int[] DELAY_TO_EXPAND_IN_MS = null;
    private static final int[] DELAY_TO_FADE_IN_MS = null;
    private static final int DURATION_TO_COLLAPSE_IN_MS = 667;
    private static final int DURATION_TO_COMPLETE_END_IN_MS = 333;
    private static final int DURATION_TO_EXPAND_IN_MS = 667;
    private static final int DURATION_TO_FADE_IN_MS = 333;
    private static final int EXTRA_DEGREES_PER_CYCLE = 0xFA;
    private static final int TAIL_DEGREES_OFFSET = -20;
    private static final int TOTAL_CYCLES = 4;
    private static final int TOTAL_DURATION_IN_MS = 5400;
    private float animationFraction;
    private ObjectAnimator animator;
    AnimationCallback animatorCompleteCallback;
    private final BaseProgressIndicatorSpec baseSpec;
    private ObjectAnimator completeEndAnimator;
    private float completeEndFraction;
    private int indicatorColorIndexOffset;
    private final FastOutSlowInInterpolator interpolator;

    static {
        CircularIndeterminateAnimatorDelegate.DELAY_TO_EXPAND_IN_MS = new int[]{0, 1350, 2700, 4050};
        CircularIndeterminateAnimatorDelegate.DELAY_TO_COLLAPSE_IN_MS = new int[]{667, 2017, 0xD27, 4717};
        CircularIndeterminateAnimatorDelegate.DELAY_TO_FADE_IN_MS = new int[]{1000, 2350, 3700, 5050};
        CircularIndeterminateAnimatorDelegate.ANIMATION_FRACTION = new Property(Float.class, "animationFraction") {
            public Float get(CircularIndeterminateAnimatorDelegate circularIndeterminateAnimatorDelegate0) {
                return circularIndeterminateAnimatorDelegate0.getAnimationFraction();
            }

            @Override  // android.util.Property
            public Object get(Object object0) {
                return this.get(((CircularIndeterminateAnimatorDelegate)object0));
            }

            public void set(CircularIndeterminateAnimatorDelegate circularIndeterminateAnimatorDelegate0, Float float0) {
                circularIndeterminateAnimatorDelegate0.setAnimationFraction(((float)float0));
            }

            @Override  // android.util.Property
            public void set(Object object0, Object object1) {
                this.set(((CircularIndeterminateAnimatorDelegate)object0), ((Float)object1));
            }
        };
        CircularIndeterminateAnimatorDelegate.COMPLETE_END_FRACTION = new Property(Float.class, "completeEndFraction") {
            public Float get(CircularIndeterminateAnimatorDelegate circularIndeterminateAnimatorDelegate0) {
                return circularIndeterminateAnimatorDelegate0.getCompleteEndFraction();
            }

            @Override  // android.util.Property
            public Object get(Object object0) {
                return this.get(((CircularIndeterminateAnimatorDelegate)object0));
            }

            public void set(CircularIndeterminateAnimatorDelegate circularIndeterminateAnimatorDelegate0, Float float0) {
                circularIndeterminateAnimatorDelegate0.setCompleteEndFraction(((float)float0));
            }

            @Override  // android.util.Property
            public void set(Object object0, Object object1) {
                this.set(((CircularIndeterminateAnimatorDelegate)object0), ((Float)object1));
            }
        };
    }

    public CircularIndeterminateAnimatorDelegate(CircularProgressIndicatorSpec circularProgressIndicatorSpec0) {
        super(1);
        this.indicatorColorIndexOffset = 0;
        this.animatorCompleteCallback = null;
        this.baseSpec = circularProgressIndicatorSpec0;
        this.interpolator = new FastOutSlowInInterpolator();
    }

    @Override  // com.google.android.material.progressindicator.IndeterminateAnimatorDelegate
    void cancelAnimatorImmediately() {
        ObjectAnimator objectAnimator0 = this.animator;
        if(objectAnimator0 != null) {
            objectAnimator0.cancel();
        }
    }

    private float getAnimationFraction() {
        return this.animationFraction;
    }

    private float getCompleteEndFraction() {
        return this.completeEndFraction;
    }

    @Override  // com.google.android.material.progressindicator.IndeterminateAnimatorDelegate
    public void invalidateSpecValues() {
        this.resetPropertiesForNewStart();
    }

    private void maybeInitializeAnimators() {
        if(this.animator == null) {
            ObjectAnimator objectAnimator0 = ObjectAnimator.ofFloat(this, CircularIndeterminateAnimatorDelegate.ANIMATION_FRACTION, new float[]{0.0f, 1.0f});
            this.animator = objectAnimator0;
            objectAnimator0.setDuration(5400L);
            this.animator.setInterpolator(null);
            this.animator.setRepeatCount(-1);
            this.animator.addListener(new AnimatorListenerAdapter() {
                @Override  // android.animation.AnimatorListenerAdapter
                public void onAnimationRepeat(Animator animator0) {
                    super.onAnimationRepeat(animator0);
                    CircularIndeterminateAnimatorDelegate.this.indicatorColorIndexOffset = (CircularIndeterminateAnimatorDelegate.this.indicatorColorIndexOffset + 4) % CircularIndeterminateAnimatorDelegate.this.baseSpec.indicatorColors.length;
                }
            });
        }
        if(this.completeEndAnimator == null) {
            ObjectAnimator objectAnimator1 = ObjectAnimator.ofFloat(this, CircularIndeterminateAnimatorDelegate.COMPLETE_END_FRACTION, new float[]{0.0f, 1.0f});
            this.completeEndAnimator = objectAnimator1;
            objectAnimator1.setDuration(333L);
            this.completeEndAnimator.setInterpolator(this.interpolator);
            this.completeEndAnimator.addListener(new AnimatorListenerAdapter() {
                @Override  // android.animation.AnimatorListenerAdapter
                public void onAnimationEnd(Animator animator0) {
                    super.onAnimationEnd(animator0);
                    CircularIndeterminateAnimatorDelegate.this.cancelAnimatorImmediately();
                    if(CircularIndeterminateAnimatorDelegate.this.animatorCompleteCallback != null) {
                        CircularIndeterminateAnimatorDelegate.this.animatorCompleteCallback.onAnimationEnd(CircularIndeterminateAnimatorDelegate.this.drawable);
                    }
                }
            });
        }
    }

    private void maybeUpdateSegmentColors(int v) {
        for(int v1 = 0; v1 < 4; ++v1) {
            float f = this.getFractionInRange(v, CircularIndeterminateAnimatorDelegate.DELAY_TO_FADE_IN_MS[v1], 333);
            if(f >= 0.0f && f <= 1.0f) {
                int v2 = (v1 + this.indicatorColorIndexOffset) % this.baseSpec.indicatorColors.length;
                int v3 = (v2 + 1) % this.baseSpec.indicatorColors.length;
                int v4 = this.baseSpec.indicatorColors[v2];
                int v5 = this.baseSpec.indicatorColors[v3];
                ((ActiveIndicator)this.activeIndicators.get(0)).color = (int)ArgbEvaluatorCompat.getInstance().evaluate(this.interpolator.getInterpolation(f), v4, v5);
                return;
            }
        }
    }

    @Override  // com.google.android.material.progressindicator.IndeterminateAnimatorDelegate
    public void registerAnimatorsCompleteCallback(AnimationCallback animatable2Compat$AnimationCallback0) {
        this.animatorCompleteCallback = animatable2Compat$AnimationCallback0;
    }

    @Override  // com.google.android.material.progressindicator.IndeterminateAnimatorDelegate
    void requestCancelAnimatorAfterCurrentCycle() {
        if(this.completeEndAnimator != null && !this.completeEndAnimator.isRunning()) {
            if(this.drawable.isVisible()) {
                this.completeEndAnimator.start();
                return;
            }
            this.cancelAnimatorImmediately();
        }
    }

    @Override  // com.google.android.material.progressindicator.IndeterminateAnimatorDelegate
    void resetPropertiesForNewStart() {
        this.indicatorColorIndexOffset = 0;
        ActiveIndicator drawingDelegate$ActiveIndicator0 = (ActiveIndicator)this.activeIndicators.get(0);
        drawingDelegate$ActiveIndicator0.color = this.baseSpec.indicatorColors[0];
        this.completeEndFraction = 0.0f;
    }

    @Override  // com.google.android.material.progressindicator.IndeterminateAnimatorDelegate
    void setAnimationFraction(float f) {
        this.animationFraction = f;
        this.updateSegmentPositions(((int)(f * 5400.0f)));
        this.maybeUpdateSegmentColors(((int)(f * 5400.0f)));
        this.drawable.invalidateSelf();
    }

    private void setCompleteEndFraction(float f) {
        this.completeEndFraction = f;
    }

    @Override  // com.google.android.material.progressindicator.IndeterminateAnimatorDelegate
    void startAnimator() {
        this.maybeInitializeAnimators();
        this.resetPropertiesForNewStart();
        this.animator.start();
    }

    @Override  // com.google.android.material.progressindicator.IndeterminateAnimatorDelegate
    public void unregisterAnimatorsCompleteCallback() {
        this.animatorCompleteCallback = null;
    }

    private void updateSegmentPositions(int v) {
        ActiveIndicator drawingDelegate$ActiveIndicator0 = (ActiveIndicator)this.activeIndicators.get(0);
        drawingDelegate$ActiveIndicator0.startFraction = this.animationFraction * 1520.0f - 20.0f;
        drawingDelegate$ActiveIndicator0.endFraction = this.animationFraction * 1520.0f;
        for(int v1 = 0; v1 < 4; ++v1) {
            float f = this.getFractionInRange(v, CircularIndeterminateAnimatorDelegate.DELAY_TO_EXPAND_IN_MS[v1], 667);
            drawingDelegate$ActiveIndicator0.endFraction += this.interpolator.getInterpolation(f) * 250.0f;
            float f1 = this.getFractionInRange(v, CircularIndeterminateAnimatorDelegate.DELAY_TO_COLLAPSE_IN_MS[v1], 667);
            drawingDelegate$ActiveIndicator0.startFraction += this.interpolator.getInterpolation(f1) * 250.0f;
        }
        drawingDelegate$ActiveIndicator0.startFraction /= 360.0f;
        drawingDelegate$ActiveIndicator0.endFraction /= 360.0f;
    }
}

