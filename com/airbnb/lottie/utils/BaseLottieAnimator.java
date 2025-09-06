package com.airbnb.lottie.utils;

import android.animation.Animator.AnimatorListener;
import android.animation.Animator;
import android.animation.TimeInterpolator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.animation.ValueAnimator;
import android.os.Build.VERSION;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

public abstract class BaseLottieAnimator extends ValueAnimator {
    private final Set listeners;
    private final Set updateListeners;

    public BaseLottieAnimator() {
        this.updateListeners = new CopyOnWriteArraySet();
        this.listeners = new CopyOnWriteArraySet();
    }

    @Override  // android.animation.Animator
    public void addListener(Animator.AnimatorListener animator$AnimatorListener0) {
        this.listeners.add(animator$AnimatorListener0);
    }

    @Override  // android.animation.ValueAnimator
    public void addUpdateListener(ValueAnimator.AnimatorUpdateListener valueAnimator$AnimatorUpdateListener0) {
        this.updateListeners.add(valueAnimator$AnimatorUpdateListener0);
    }

    @Override  // android.animation.ValueAnimator
    public long getStartDelay() {
        throw new UnsupportedOperationException("LottieAnimator does not support getStartDelay.");
    }

    void notifyCancel() {
        for(Object object0: this.listeners) {
            ((Animator.AnimatorListener)object0).onAnimationCancel(this);
        }
    }

    void notifyEnd(boolean z) {
        for(Object object0: this.listeners) {
            Animator.AnimatorListener animator$AnimatorListener0 = (Animator.AnimatorListener)object0;
            if(Build.VERSION.SDK_INT >= 26) {
                animator$AnimatorListener0.onAnimationEnd(this, z);
            }
            else {
                animator$AnimatorListener0.onAnimationEnd(this);
            }
        }
    }

    void notifyRepeat() {
        for(Object object0: this.listeners) {
            ((Animator.AnimatorListener)object0).onAnimationRepeat(this);
        }
    }

    void notifyStart(boolean z) {
        for(Object object0: this.listeners) {
            Animator.AnimatorListener animator$AnimatorListener0 = (Animator.AnimatorListener)object0;
            if(Build.VERSION.SDK_INT >= 26) {
                animator$AnimatorListener0.onAnimationStart(this, z);
            }
            else {
                animator$AnimatorListener0.onAnimationStart(this);
            }
        }
    }

    void notifyUpdate() {
        for(Object object0: this.updateListeners) {
            ((ValueAnimator.AnimatorUpdateListener)object0).onAnimationUpdate(this);
        }
    }

    @Override  // android.animation.Animator
    public void removeAllListeners() {
        this.listeners.clear();
    }

    @Override  // android.animation.ValueAnimator
    public void removeAllUpdateListeners() {
        this.updateListeners.clear();
    }

    @Override  // android.animation.Animator
    public void removeListener(Animator.AnimatorListener animator$AnimatorListener0) {
        this.listeners.remove(animator$AnimatorListener0);
    }

    @Override  // android.animation.ValueAnimator
    public void removeUpdateListener(ValueAnimator.AnimatorUpdateListener valueAnimator$AnimatorUpdateListener0) {
        this.updateListeners.remove(valueAnimator$AnimatorUpdateListener0);
    }

    @Override  // android.animation.ValueAnimator
    public Animator setDuration(long v) {
        return this.setDuration(v);
    }

    @Override  // android.animation.ValueAnimator
    public ValueAnimator setDuration(long v) {
        throw new UnsupportedOperationException("LottieAnimator does not support setDuration.");
    }

    @Override  // android.animation.ValueAnimator
    public void setInterpolator(TimeInterpolator timeInterpolator0) {
        throw new UnsupportedOperationException("LottieAnimator does not support setInterpolator.");
    }

    @Override  // android.animation.ValueAnimator
    public void setStartDelay(long v) {
        throw new UnsupportedOperationException("LottieAnimator does not support setStartDelay.");
    }
}

