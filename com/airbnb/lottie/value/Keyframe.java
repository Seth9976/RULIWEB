package com.airbnb.lottie.value;

import android.graphics.PointF;
import android.view.animation.Interpolator;
import com.airbnb.lottie.LottieComposition;

public class Keyframe {
    private static final float UNSET_FLOAT = -3987645.75f;
    private static final int UNSET_INT = 0x2EC8FB09;
    private final LottieComposition composition;
    public Float endFrame;
    private float endProgress;
    public Object endValue;
    private float endValueFloat;
    private int endValueInt;
    public final Interpolator interpolator;
    public PointF pathCp1;
    public PointF pathCp2;
    public final float startFrame;
    private float startProgress;
    public final Object startValue;
    private float startValueFloat;
    private int startValueInt;

    public Keyframe(LottieComposition lottieComposition0, Object object0, Object object1, Interpolator interpolator0, float f, Float float0) {
        this.startValueFloat = -3987645.75f;
        this.endValueFloat = -3987645.75f;
        this.startValueInt = 0x2EC8FB09;
        this.endValueInt = 0x2EC8FB09;
        this.startProgress = 1.401298E-45f;
        this.endProgress = 1.401298E-45f;
        this.pathCp1 = null;
        this.pathCp2 = null;
        this.composition = lottieComposition0;
        this.startValue = object0;
        this.endValue = object1;
        this.interpolator = interpolator0;
        this.startFrame = f;
        this.endFrame = float0;
    }

    public Keyframe(Object object0) {
        this.startValueFloat = -3987645.75f;
        this.endValueFloat = -3987645.75f;
        this.startValueInt = 0x2EC8FB09;
        this.endValueInt = 0x2EC8FB09;
        this.startProgress = 1.401298E-45f;
        this.endProgress = 1.401298E-45f;
        this.pathCp1 = null;
        this.pathCp2 = null;
        this.composition = null;
        this.startValue = object0;
        this.endValue = object0;
        this.interpolator = null;
        this.startFrame = 1.401298E-45f;
        this.endFrame = 3.402823E+38f;
    }

    public boolean containsProgress(float f) {
        return f >= this.getStartProgress() && f < this.getEndProgress();
    }

    public float getEndProgress() {
        if(this.composition == null) {
            return 1.0f;
        }
        if(this.endProgress == 1.401298E-45f) {
            if(this.endFrame == null) {
                this.endProgress = 1.0f;
                return 1.0f;
            }
            this.endProgress = this.getStartProgress() + (((float)this.endFrame) - this.startFrame) / this.composition.getDurationFrames();
        }
        return this.endProgress;
    }

    public float getEndValueFloat() {
        if(this.endValueFloat == -3987645.75f) {
            this.endValueFloat = (float)(((Float)this.endValue));
        }
        return this.endValueFloat;
    }

    public int getEndValueInt() {
        if(this.endValueInt == 0x2EC8FB09) {
            this.endValueInt = (int)(((Integer)this.endValue));
        }
        return this.endValueInt;
    }

    public float getStartProgress() {
        LottieComposition lottieComposition0 = this.composition;
        if(lottieComposition0 == null) {
            return 0.0f;
        }
        if(this.startProgress == 1.401298E-45f) {
            this.startProgress = (this.startFrame - lottieComposition0.getStartFrame()) / this.composition.getDurationFrames();
        }
        return this.startProgress;
    }

    public float getStartValueFloat() {
        if(this.startValueFloat == -3987645.75f) {
            this.startValueFloat = (float)(((Float)this.startValue));
        }
        return this.startValueFloat;
    }

    public int getStartValueInt() {
        if(this.startValueInt == 0x2EC8FB09) {
            this.startValueInt = (int)(((Integer)this.startValue));
        }
        return this.startValueInt;
    }

    public boolean isStatic() {
        return this.interpolator == null;
    }

    @Override
    public String toString() {
        return "Keyframe{startValue=" + this.startValue + ", endValue=" + this.endValue + ", startFrame=" + this.startFrame + ", endFrame=" + this.endFrame + ", interpolator=" + this.interpolator + '}';
    }
}

