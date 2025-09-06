package com.airbnb.lottie.value;

public class LottieFrameInfo {
    private float endFrame;
    private Object endValue;
    private float interpolatedKeyframeProgress;
    private float linearKeyframeProgress;
    private float overallProgress;
    private float startFrame;
    private Object startValue;

    public float getEndFrame() {
        return this.endFrame;
    }

    public Object getEndValue() {
        return this.endValue;
    }

    public float getInterpolatedKeyframeProgress() {
        return this.interpolatedKeyframeProgress;
    }

    public float getLinearKeyframeProgress() {
        return this.linearKeyframeProgress;
    }

    public float getOverallProgress() {
        return this.overallProgress;
    }

    public float getStartFrame() {
        return this.startFrame;
    }

    public Object getStartValue() {
        return this.startValue;
    }

    public LottieFrameInfo set(float f, float f1, Object object0, Object object1, float f2, float f3, float f4) {
        this.startFrame = f;
        this.endFrame = f1;
        this.startValue = object0;
        this.endValue = object1;
        this.linearKeyframeProgress = f2;
        this.interpolatedKeyframeProgress = f3;
        this.overallProgress = f4;
        return this;
    }
}

