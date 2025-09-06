package com.google.android.material.progressindicator;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.util.Property;
import androidx.vectordrawable.graphics.drawable.Animatable2Compat.AnimationCallback;
import androidx.vectordrawable.graphics.drawable.Animatable2Compat;
import com.google.android.material.animation.AnimationUtils;
import java.util.ArrayList;
import java.util.List;

abstract class DrawableWithAnimatedVisibilityChange extends Drawable implements Animatable2Compat {
    private static final boolean DEFAULT_DRAWABLE_RESTART = false;
    private static final int GROW_DURATION = 500;
    private static final Property GROW_FRACTION;
    private List animationCallbacks;
    AnimatorDurationScaleProvider animatorDurationScaleProvider;
    final BaseProgressIndicatorSpec baseSpec;
    final Context context;
    private float growFraction;
    private ValueAnimator hideAnimator;
    private boolean ignoreCallbacks;
    private AnimationCallback internalAnimationCallback;
    private float mockGrowFraction;
    private boolean mockHideAnimationRunning;
    private boolean mockShowAnimationRunning;
    final Paint paint;
    private ValueAnimator showAnimator;
    private int totalAlpha;

    static {
        DrawableWithAnimatedVisibilityChange.GROW_FRACTION = new Property(Float.class, "growFraction") {
            public Float get(DrawableWithAnimatedVisibilityChange drawableWithAnimatedVisibilityChange0) {
                return drawableWithAnimatedVisibilityChange0.getGrowFraction();
            }

            @Override  // android.util.Property
            public Object get(Object object0) {
                return this.get(((DrawableWithAnimatedVisibilityChange)object0));
            }

            public void set(DrawableWithAnimatedVisibilityChange drawableWithAnimatedVisibilityChange0, Float float0) {
                drawableWithAnimatedVisibilityChange0.setGrowFraction(((float)float0));
            }

            @Override  // android.util.Property
            public void set(Object object0, Object object1) {
                this.set(((DrawableWithAnimatedVisibilityChange)object0), ((Float)object1));
            }
        };
    }

    DrawableWithAnimatedVisibilityChange(Context context0, BaseProgressIndicatorSpec baseProgressIndicatorSpec0) {
        this.paint = new Paint();
        this.context = context0;
        this.baseSpec = baseProgressIndicatorSpec0;
        this.animatorDurationScaleProvider = new AnimatorDurationScaleProvider();
        this.setAlpha(0xFF);
    }

    private void cancelAnimatorsWithoutCallbacks(ValueAnimator[] arr_valueAnimator) {
        boolean z = this.ignoreCallbacks;
        this.ignoreCallbacks = true;
        for(int v = 0; v < arr_valueAnimator.length; ++v) {
            arr_valueAnimator[v].cancel();
        }
        this.ignoreCallbacks = z;
    }

    @Override  // androidx.vectordrawable.graphics.drawable.Animatable2Compat
    public void clearAnimationCallbacks() {
        this.animationCallbacks.clear();
        this.animationCallbacks = null;
    }

    private void dispatchAnimationEnd() {
        AnimationCallback animatable2Compat$AnimationCallback0 = this.internalAnimationCallback;
        if(animatable2Compat$AnimationCallback0 != null) {
            animatable2Compat$AnimationCallback0.onAnimationEnd(this);
        }
        List list0 = this.animationCallbacks;
        if(list0 != null && !this.ignoreCallbacks) {
            for(Object object0: list0) {
                ((AnimationCallback)object0).onAnimationEnd(this);
            }
        }
    }

    private void dispatchAnimationStart() {
        AnimationCallback animatable2Compat$AnimationCallback0 = this.internalAnimationCallback;
        if(animatable2Compat$AnimationCallback0 != null) {
            animatable2Compat$AnimationCallback0.onAnimationStart(this);
        }
        List list0 = this.animationCallbacks;
        if(list0 != null && !this.ignoreCallbacks) {
            for(Object object0: list0) {
                ((AnimationCallback)object0).onAnimationStart(this);
            }
        }
    }

    private void endAnimatorsWithoutCallbacks(ValueAnimator[] arr_valueAnimator) {
        boolean z = this.ignoreCallbacks;
        this.ignoreCallbacks = true;
        for(int v = 0; v < arr_valueAnimator.length; ++v) {
            arr_valueAnimator[v].end();
        }
        this.ignoreCallbacks = z;
    }

    @Override  // android.graphics.drawable.Drawable
    public int getAlpha() {
        return this.totalAlpha;
    }

    float getGrowFraction() {
        if(!this.baseSpec.isShowAnimationEnabled() && !this.baseSpec.isHideAnimationEnabled()) {
            return 1.0f;
        }
        return this.mockHideAnimationRunning || this.mockShowAnimationRunning ? this.mockGrowFraction : this.growFraction;
    }

    ValueAnimator getHideAnimator() {
        return this.hideAnimator;
    }

    @Override  // android.graphics.drawable.Drawable
    public int getOpacity() {
        return -3;
    }

    public boolean hideNow() {
        return this.setVisible(false, false, false);
    }

    // 去混淆评级： 低(30)
    public boolean isHiding() {
        return this.hideAnimator != null && this.hideAnimator.isRunning() || this.mockHideAnimationRunning;
    }

    // 去混淆评级： 低(20)
    @Override  // android.graphics.drawable.Animatable
    public boolean isRunning() {
        return this.isShowing() || this.isHiding();
    }

    // 去混淆评级： 低(30)
    public boolean isShowing() {
        return this.showAnimator != null && this.showAnimator.isRunning() || this.mockShowAnimationRunning;
    }

    private void maybeInitializeAnimators() {
        if(this.showAnimator == null) {
            ObjectAnimator objectAnimator0 = ObjectAnimator.ofFloat(this, DrawableWithAnimatedVisibilityChange.GROW_FRACTION, new float[]{0.0f, 1.0f});
            this.showAnimator = objectAnimator0;
            objectAnimator0.setDuration(500L);
            this.showAnimator.setInterpolator(AnimationUtils.FAST_OUT_SLOW_IN_INTERPOLATOR);
            this.setShowAnimator(this.showAnimator);
        }
        if(this.hideAnimator == null) {
            ObjectAnimator objectAnimator1 = ObjectAnimator.ofFloat(this, DrawableWithAnimatedVisibilityChange.GROW_FRACTION, new float[]{1.0f, 0.0f});
            this.hideAnimator = objectAnimator1;
            objectAnimator1.setDuration(500L);
            this.hideAnimator.setInterpolator(AnimationUtils.FAST_OUT_SLOW_IN_INTERPOLATOR);
            this.setHideAnimator(this.hideAnimator);
        }
    }

    @Override  // androidx.vectordrawable.graphics.drawable.Animatable2Compat
    public void registerAnimationCallback(AnimationCallback animatable2Compat$AnimationCallback0) {
        if(this.animationCallbacks == null) {
            this.animationCallbacks = new ArrayList();
        }
        if(!this.animationCallbacks.contains(animatable2Compat$AnimationCallback0)) {
            this.animationCallbacks.add(animatable2Compat$AnimationCallback0);
        }
    }

    @Override  // android.graphics.drawable.Drawable
    public void setAlpha(int v) {
        this.totalAlpha = v;
        this.invalidateSelf();
    }

    @Override  // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter0) {
        this.paint.setColorFilter(colorFilter0);
        this.invalidateSelf();
    }

    void setGrowFraction(float f) {
        if(this.growFraction != f) {
            this.growFraction = f;
            this.invalidateSelf();
        }
    }

    private void setHideAnimator(ValueAnimator valueAnimator0) {
        if(this.hideAnimator != null && this.hideAnimator.isRunning()) {
            throw new IllegalArgumentException("Cannot set hideAnimator while the current hideAnimator is running.");
        }
        this.hideAnimator = valueAnimator0;
        valueAnimator0.addListener(new AnimatorListenerAdapter() {
            @Override  // android.animation.AnimatorListenerAdapter
            public void onAnimationEnd(Animator animator0) {
                super.onAnimationEnd(animator0);
                DrawableWithAnimatedVisibilityChange.this.super.setVisible(false, false);
                DrawableWithAnimatedVisibilityChange.this.dispatchAnimationEnd();
            }
        });
    }

    void setInternalAnimationCallback(AnimationCallback animatable2Compat$AnimationCallback0) {
        this.internalAnimationCallback = animatable2Compat$AnimationCallback0;
    }

    void setMockHideAnimationRunning(boolean z, float f) {
        this.mockHideAnimationRunning = z;
        this.mockGrowFraction = f;
    }

    void setMockShowAnimationRunning(boolean z, float f) {
        this.mockShowAnimationRunning = z;
        this.mockGrowFraction = f;
    }

    private void setShowAnimator(ValueAnimator valueAnimator0) {
        if(this.showAnimator != null && this.showAnimator.isRunning()) {
            throw new IllegalArgumentException("Cannot set showAnimator while the current showAnimator is running.");
        }
        this.showAnimator = valueAnimator0;
        valueAnimator0.addListener(new AnimatorListenerAdapter() {
            @Override  // android.animation.AnimatorListenerAdapter
            public void onAnimationStart(Animator animator0) {
                super.onAnimationStart(animator0);
                DrawableWithAnimatedVisibilityChange.this.dispatchAnimationStart();
            }
        });
    }

    @Override  // android.graphics.drawable.Drawable
    public boolean setVisible(boolean z, boolean z1) {
        return this.setVisible(z, z1, true);
    }

    public boolean setVisible(boolean z, boolean z1, boolean z2) {
        return !z2 || this.animatorDurationScaleProvider.getSystemAnimatorDurationScale(this.context.getContentResolver()) <= 0.0f ? this.setVisibleInternal(z, z1, false) : this.setVisibleInternal(z, z1, true);
    }

    boolean setVisibleInternal(boolean z, boolean z1, boolean z2) {
        this.maybeInitializeAnimators();
        if(!this.isVisible() && !z) {
            return false;
        }
        ValueAnimator valueAnimator0 = z ? this.showAnimator : this.hideAnimator;
        ValueAnimator valueAnimator1 = z ? this.hideAnimator : this.showAnimator;
        if(!z2) {
            if(valueAnimator1.isRunning()) {
                this.cancelAnimatorsWithoutCallbacks(new ValueAnimator[]{valueAnimator1});
            }
            if(valueAnimator0.isRunning()) {
                valueAnimator0.end();
                return super.setVisible(z, false);
            }
            this.endAnimatorsWithoutCallbacks(new ValueAnimator[]{valueAnimator0});
            return super.setVisible(z, false);
        }
        if(valueAnimator0.isRunning()) {
            return false;
        }
        boolean z3 = !z || super.setVisible(true, false);
        if(!(z ? this.baseSpec.isShowAnimationEnabled() : this.baseSpec.isHideAnimationEnabled())) {
            this.endAnimatorsWithoutCallbacks(new ValueAnimator[]{valueAnimator0});
            return z3;
        }
        if(!z1 && valueAnimator0.isPaused()) {
            valueAnimator0.resume();
            return z3;
        }
        valueAnimator0.start();
        return z3;
    }

    @Override  // android.graphics.drawable.Animatable
    public void start() {
        this.setVisibleInternal(true, true, false);
    }

    @Override  // android.graphics.drawable.Animatable
    public void stop() {
        this.setVisibleInternal(false, true, false);
    }

    @Override  // androidx.vectordrawable.graphics.drawable.Animatable2Compat
    public boolean unregisterAnimationCallback(AnimationCallback animatable2Compat$AnimationCallback0) {
        if(this.animationCallbacks != null && this.animationCallbacks.contains(animatable2Compat$AnimationCallback0)) {
            this.animationCallbacks.remove(animatable2Compat$AnimationCallback0);
            if(this.animationCallbacks.isEmpty()) {
                this.animationCallbacks = null;
            }
            return true;
        }
        return false;
    }
}

