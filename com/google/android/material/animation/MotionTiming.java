package com.google.android.material.animation;

import android.animation.Animator;
import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;

public class MotionTiming {
    private long delay;
    private long duration;
    private TimeInterpolator interpolator;
    private int repeatCount;
    private int repeatMode;

    public MotionTiming(long v, long v1) {
        this.interpolator = null;
        this.repeatCount = 0;
        this.repeatMode = 1;
        this.delay = v;
        this.duration = v1;
    }

    public MotionTiming(long v, long v1, TimeInterpolator timeInterpolator0) {
        this.repeatCount = 0;
        this.repeatMode = 1;
        this.delay = v;
        this.duration = v1;
        this.interpolator = timeInterpolator0;
    }

    public void apply(Animator animator0) {
        animator0.setStartDelay(this.getDelay());
        animator0.setDuration(this.getDuration());
        animator0.setInterpolator(this.getInterpolator());
        if(animator0 instanceof ValueAnimator) {
            ((ValueAnimator)animator0).setRepeatCount(this.getRepeatCount());
            ((ValueAnimator)animator0).setRepeatMode(this.getRepeatMode());
        }
    }

    static MotionTiming createFromAnimator(ValueAnimator valueAnimator0) {
        MotionTiming motionTiming0 = new MotionTiming(valueAnimator0.getStartDelay(), valueAnimator0.getDuration(), MotionTiming.getInterpolatorCompat(valueAnimator0));
        motionTiming0.repeatCount = valueAnimator0.getRepeatCount();
        motionTiming0.repeatMode = valueAnimator0.getRepeatMode();
        return motionTiming0;
    }

    @Override
    public boolean equals(Object object0) {
        if(this == object0) {
            return true;
        }
        if(!(object0 instanceof MotionTiming)) {
            return false;
        }
        if(this.getDelay() != ((MotionTiming)object0).getDelay()) {
            return false;
        }
        if(this.getDuration() != ((MotionTiming)object0).getDuration()) {
            return false;
        }
        if(this.getRepeatCount() != ((MotionTiming)object0).getRepeatCount()) {
            return false;
        }
        return this.getRepeatMode() == ((MotionTiming)object0).getRepeatMode() ? this.getInterpolator().getClass().equals(((MotionTiming)object0).getInterpolator().getClass()) : false;
    }

    public long getDelay() {
        return this.delay;
    }

    public long getDuration() {
        return this.duration;
    }

    public TimeInterpolator getInterpolator() {
        return this.interpolator == null ? AnimationUtils.FAST_OUT_SLOW_IN_INTERPOLATOR : this.interpolator;
    }

    private static TimeInterpolator getInterpolatorCompat(ValueAnimator valueAnimator0) {
        TimeInterpolator timeInterpolator0 = valueAnimator0.getInterpolator();
        if(!(timeInterpolator0 instanceof AccelerateDecelerateInterpolator) && timeInterpolator0 != null) {
            if(timeInterpolator0 instanceof AccelerateInterpolator) {
                return AnimationUtils.FAST_OUT_LINEAR_IN_INTERPOLATOR;
            }
            return timeInterpolator0 instanceof DecelerateInterpolator ? AnimationUtils.LINEAR_OUT_SLOW_IN_INTERPOLATOR : timeInterpolator0;
        }
        return AnimationUtils.FAST_OUT_SLOW_IN_INTERPOLATOR;
    }

    public int getRepeatCount() {
        return this.repeatCount;
    }

    public int getRepeatMode() {
        return this.repeatMode;
    }

    @Override
    public int hashCode() {
        return (((((int)(this.getDelay() ^ this.getDelay() >>> 0x20)) * 0x1F + ((int)(this.getDuration() ^ this.getDuration() >>> 0x20))) * 0x1F + this.getInterpolator().getClass().hashCode()) * 0x1F + this.getRepeatCount()) * 0x1F + this.getRepeatMode();
    }

    @Override
    public String toString() {
        return "\n" + this.getClass().getName() + '{' + Integer.toHexString(System.identityHashCode(this)) + " delay: " + this.getDelay() + " duration: " + this.getDuration() + " interpolator: " + this.getInterpolator().getClass() + " repeatCount: " + this.getRepeatCount() + " repeatMode: " + this.getRepeatMode() + "}\n";
    }
}

