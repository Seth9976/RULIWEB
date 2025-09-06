package com.google.android.material.progressindicator;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.util.Property;
import android.view.animation.Interpolator;
import androidx.core.math.MathUtils;
import androidx.vectordrawable.graphics.drawable.Animatable2Compat.AnimationCallback;
import androidx.vectordrawable.graphics.drawable.AnimationUtilsCompat;
import com.google.android.material.R.anim;

final class LinearIndeterminateDisjointAnimatorDelegate extends IndeterminateAnimatorDelegate {
    private static final Property ANIMATION_FRACTION = null;
    private static final int[] DELAY_TO_MOVE_SEGMENT_ENDS = null;
    private static final int[] DURATION_TO_MOVE_SEGMENT_ENDS = null;
    private static final int TOTAL_DURATION_IN_MS = 1800;
    private float animationFraction;
    private ObjectAnimator animator;
    AnimationCallback animatorCompleteCallback;
    private final BaseProgressIndicatorSpec baseSpec;
    private ObjectAnimator completeEndAnimator;
    private boolean dirtyColors;
    private int indicatorColorIndex;
    private final Interpolator[] interpolatorArray;

    static {
        LinearIndeterminateDisjointAnimatorDelegate.DURATION_TO_MOVE_SEGMENT_ENDS = new int[]{533, 567, 850, 750};
        LinearIndeterminateDisjointAnimatorDelegate.DELAY_TO_MOVE_SEGMENT_ENDS = new int[]{0x4F3, 1000, 333, 0};
        LinearIndeterminateDisjointAnimatorDelegate.ANIMATION_FRACTION = new Property(Float.class, "animationFraction") {
            public Float get(LinearIndeterminateDisjointAnimatorDelegate linearIndeterminateDisjointAnimatorDelegate0) {
                return linearIndeterminateDisjointAnimatorDelegate0.getAnimationFraction();
            }

            @Override  // android.util.Property
            public Object get(Object object0) {
                return this.get(((LinearIndeterminateDisjointAnimatorDelegate)object0));
            }

            public void set(LinearIndeterminateDisjointAnimatorDelegate linearIndeterminateDisjointAnimatorDelegate0, Float float0) {
                linearIndeterminateDisjointAnimatorDelegate0.setAnimationFraction(((float)float0));
            }

            @Override  // android.util.Property
            public void set(Object object0, Object object1) {
                this.set(((LinearIndeterminateDisjointAnimatorDelegate)object0), ((Float)object1));
            }
        };
    }

    public LinearIndeterminateDisjointAnimatorDelegate(Context context0, LinearProgressIndicatorSpec linearProgressIndicatorSpec0) {
        super(2);
        this.indicatorColorIndex = 0;
        this.animatorCompleteCallback = null;
        this.baseSpec = linearProgressIndicatorSpec0;
        this.interpolatorArray = new Interpolator[]{AnimationUtilsCompat.loadInterpolator(context0, anim.linear_indeterminate_line1_head_interpolator), AnimationUtilsCompat.loadInterpolator(context0, anim.linear_indeterminate_line1_tail_interpolator), AnimationUtilsCompat.loadInterpolator(context0, anim.linear_indeterminate_line2_head_interpolator), AnimationUtilsCompat.loadInterpolator(context0, anim.linear_indeterminate_line2_tail_interpolator)};
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
            ObjectAnimator objectAnimator0 = ObjectAnimator.ofFloat(this, LinearIndeterminateDisjointAnimatorDelegate.ANIMATION_FRACTION, new float[]{0.0f, 1.0f});
            this.animator = objectAnimator0;
            objectAnimator0.setDuration(1800L);
            this.animator.setInterpolator(null);
            this.animator.setRepeatCount(-1);
            this.animator.addListener(new AnimatorListenerAdapter() {
                @Override  // android.animation.AnimatorListenerAdapter
                public void onAnimationRepeat(Animator animator0) {
                    super.onAnimationRepeat(animator0);
                    LinearIndeterminateDisjointAnimatorDelegate.this.indicatorColorIndex = (LinearIndeterminateDisjointAnimatorDelegate.this.indicatorColorIndex + 1) % LinearIndeterminateDisjointAnimatorDelegate.this.baseSpec.indicatorColors.length;
                    LinearIndeterminateDisjointAnimatorDelegate.this.dirtyColors = true;
                }
            });
        }
        if(this.completeEndAnimator == null) {
            ObjectAnimator objectAnimator1 = ObjectAnimator.ofFloat(this, LinearIndeterminateDisjointAnimatorDelegate.ANIMATION_FRACTION, new float[]{1.0f});
            this.completeEndAnimator = objectAnimator1;
            objectAnimator1.setDuration(1800L);
            this.completeEndAnimator.setInterpolator(null);
            this.completeEndAnimator.addListener(new AnimatorListenerAdapter() {
                @Override  // android.animation.AnimatorListenerAdapter
                public void onAnimationEnd(Animator animator0) {
                    super.onAnimationEnd(animator0);
                    LinearIndeterminateDisjointAnimatorDelegate.this.cancelAnimatorImmediately();
                    if(LinearIndeterminateDisjointAnimatorDelegate.this.animatorCompleteCallback != null) {
                        LinearIndeterminateDisjointAnimatorDelegate.this.animatorCompleteCallback.onAnimationEnd(LinearIndeterminateDisjointAnimatorDelegate.this.drawable);
                    }
                }
            });
        }
    }

    private void maybeUpdateSegmentColors() {
        if(this.dirtyColors) {
            for(Object object0: this.activeIndicators) {
                ((ActiveIndicator)object0).color = this.baseSpec.indicatorColors[this.indicatorColorIndex];
            }
            this.dirtyColors = false;
        }
    }

    @Override  // com.google.android.material.progressindicator.IndeterminateAnimatorDelegate
    public void registerAnimatorsCompleteCallback(AnimationCallback animatable2Compat$AnimationCallback0) {
        this.animatorCompleteCallback = animatable2Compat$AnimationCallback0;
    }

    @Override  // com.google.android.material.progressindicator.IndeterminateAnimatorDelegate
    public void requestCancelAnimatorAfterCurrentCycle() {
        if(this.completeEndAnimator != null && !this.completeEndAnimator.isRunning()) {
            this.cancelAnimatorImmediately();
            if(this.drawable.isVisible()) {
                this.completeEndAnimator.setFloatValues(new float[]{this.animationFraction, 1.0f});
                this.completeEndAnimator.setDuration(((long)((1.0f - this.animationFraction) * 1800.0f)));
                this.completeEndAnimator.start();
            }
        }
    }

    @Override  // com.google.android.material.progressindicator.IndeterminateAnimatorDelegate
    void resetPropertiesForNewStart() {
        this.indicatorColorIndex = 0;
        for(Object object0: this.activeIndicators) {
            ((ActiveIndicator)object0).color = this.baseSpec.indicatorColors[0];
        }
    }

    @Override  // com.google.android.material.progressindicator.IndeterminateAnimatorDelegate
    void setAnimationFraction(float f) {
        this.animationFraction = f;
        this.updateSegmentPositions(((int)(f * 1800.0f)));
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
        this.animatorCompleteCallback = null;
    }

    private void updateSegmentPositions(int v) {
        for(int v1 = 0; v1 < this.activeIndicators.size(); ++v1) {
            ActiveIndicator drawingDelegate$ActiveIndicator0 = (ActiveIndicator)this.activeIndicators.get(v1);
            float f = this.getFractionInRange(v, LinearIndeterminateDisjointAnimatorDelegate.DELAY_TO_MOVE_SEGMENT_ENDS[v1 * 2], LinearIndeterminateDisjointAnimatorDelegate.DURATION_TO_MOVE_SEGMENT_ENDS[v1 * 2]);
            drawingDelegate$ActiveIndicator0.startFraction = MathUtils.clamp(this.interpolatorArray[v1 * 2].getInterpolation(f), 0.0f, 1.0f);
            int v2 = v1 * 2 + 1;
            float f1 = this.getFractionInRange(v, LinearIndeterminateDisjointAnimatorDelegate.DELAY_TO_MOVE_SEGMENT_ENDS[v2], LinearIndeterminateDisjointAnimatorDelegate.DURATION_TO_MOVE_SEGMENT_ENDS[v2]);
            drawingDelegate$ActiveIndicator0.endFraction = MathUtils.clamp(this.interpolatorArray[v2].getInterpolation(f1), 0.0f, 1.0f);
        }
    }
}

