package com.airbnb.lottie.utils;

import android.view.Choreographer.FrameCallback;
import android.view.Choreographer;
import com.airbnb.lottie.L;
import com.airbnb.lottie.LottieComposition;

public class LottieValueAnimator extends BaseLottieAnimator implements Choreographer.FrameCallback {
    private LottieComposition composition;
    private float frame;
    private long lastFrameTimeNs;
    private float maxFrame;
    private float minFrame;
    private int repeatCount;
    protected boolean running;
    private float speed;
    private boolean speedReversedForRepeatMode;

    public LottieValueAnimator() {
        this.speed = 1.0f;
        this.speedReversedForRepeatMode = false;
        this.lastFrameTimeNs = 0L;
        this.frame = 0.0f;
        this.repeatCount = 0;
        this.minFrame = -2147483648.0f;
        this.maxFrame = 2147483648.0f;
        this.running = false;
    }

    @Override  // android.animation.ValueAnimator
    public void cancel() {
        this.notifyCancel();
        this.removeFrameCallback();
    }

    public void clearComposition() {
        this.composition = null;
        this.minFrame = -2147483648.0f;
        this.maxFrame = 2147483648.0f;
    }

    @Override  // android.view.Choreographer$FrameCallback
    public void doFrame(long v) {
        this.postFrameCallback();
        if(this.composition != null && this.isRunning()) {
            L.beginSection("LottieValueAnimator#doFrame");
            float f = ((float)(this.lastFrameTimeNs == 0L ? 0L : v - this.lastFrameTimeNs)) / this.getFrameDurationNs();
            float f1 = this.frame;
            if(this.isReversed()) {
                f = -f;
            }
            float f2 = f1 + f;
            this.frame = f2;
            boolean z = MiscUtils.contains(f2, this.getMinFrame(), this.getMaxFrame());
            this.frame = MiscUtils.clamp(this.frame, this.getMinFrame(), this.getMaxFrame());
            this.lastFrameTimeNs = v;
            this.notifyUpdate();
            if(!z) {
                if(this.getRepeatCount() == -1 || this.repeatCount < this.getRepeatCount()) {
                    this.notifyRepeat();
                    ++this.repeatCount;
                    if(this.getRepeatMode() == 2) {
                        this.speedReversedForRepeatMode = !this.speedReversedForRepeatMode;
                        this.reverseAnimationSpeed();
                    }
                    else {
                        this.frame = this.isReversed() ? this.getMaxFrame() : this.getMinFrame();
                    }
                    this.lastFrameTimeNs = v;
                }
                else {
                    this.frame = this.speed < 0.0f ? this.getMinFrame() : this.getMaxFrame();
                    this.removeFrameCallback();
                    this.notifyEnd(this.isReversed());
                }
            }
            this.verifyFrame();
            L.endSection("LottieValueAnimator#doFrame");
        }
    }

    public void endAnimation() {
        this.removeFrameCallback();
        this.notifyEnd(this.isReversed());
    }

    @Override  // android.animation.ValueAnimator
    public float getAnimatedFraction() {
        if(this.composition == null) {
            return 0.0f;
        }
        return this.isReversed() ? (this.getMaxFrame() - this.frame) / (this.getMaxFrame() - this.getMinFrame()) : (this.frame - this.getMinFrame()) / (this.getMaxFrame() - this.getMinFrame());
    }

    @Override  // android.animation.ValueAnimator
    public Object getAnimatedValue() {
        return this.getAnimatedValueAbsolute();
    }

    public float getAnimatedValueAbsolute() {
        return this.composition == null ? 0.0f : (this.frame - this.composition.getStartFrame()) / (this.composition.getEndFrame() - this.composition.getStartFrame());
    }

    @Override  // android.animation.ValueAnimator
    public long getDuration() {
        return this.composition == null ? 0L : ((long)this.composition.getDuration());
    }

    public float getFrame() {
        return this.frame;
    }

    private float getFrameDurationNs() {
        return this.composition == null ? 3.402823E+38f : 1000000000.0f / this.composition.getFrameRate() / Math.abs(this.speed);
    }

    public float getMaxFrame() {
        LottieComposition lottieComposition0 = this.composition;
        if(lottieComposition0 == null) {
            return 0.0f;
        }
        return this.maxFrame == 2147483648.0f ? lottieComposition0.getEndFrame() : this.maxFrame;
    }

    public float getMinFrame() {
        LottieComposition lottieComposition0 = this.composition;
        if(lottieComposition0 == null) {
            return 0.0f;
        }
        return this.minFrame == -2147483648.0f ? lottieComposition0.getStartFrame() : this.minFrame;
    }

    public float getSpeed() {
        return this.speed;
    }

    private boolean isReversed() {
        return this.getSpeed() < 0.0f;
    }

    @Override  // android.animation.ValueAnimator
    public boolean isRunning() {
        return this.running;
    }

    public void pauseAnimation() {
        this.removeFrameCallback();
    }

    public void playAnimation() {
        this.running = true;
        this.notifyStart(this.isReversed());
        this.setFrame(((float)(((int)(this.isReversed() ? this.getMaxFrame() : this.getMinFrame())))));
        this.lastFrameTimeNs = 0L;
        this.repeatCount = 0;
        this.postFrameCallback();
    }

    protected void postFrameCallback() {
        if(this.isRunning()) {
            this.removeFrameCallback(false);
            Choreographer.getInstance().postFrameCallback(this);
        }
    }

    protected void removeFrameCallback() {
        this.removeFrameCallback(true);
    }

    protected void removeFrameCallback(boolean z) {
        Choreographer.getInstance().removeFrameCallback(this);
        if(z) {
            this.running = false;
        }
    }

    public void resumeAnimation() {
        this.running = true;
        this.postFrameCallback();
        this.lastFrameTimeNs = 0L;
        if(this.isReversed() && this.getFrame() == this.getMinFrame()) {
            this.frame = this.getMaxFrame();
            return;
        }
        if(!this.isReversed() && this.getFrame() == this.getMaxFrame()) {
            this.frame = this.getMinFrame();
        }
    }

    public void reverseAnimationSpeed() {
        this.setSpeed(-this.getSpeed());
    }

    public void setComposition(LottieComposition lottieComposition0) {
        boolean z = this.composition == null;
        this.composition = lottieComposition0;
        if(z) {
            this.setMinAndMaxFrames(((float)(((int)Math.max(this.minFrame, lottieComposition0.getStartFrame())))), ((float)(((int)Math.min(this.maxFrame, lottieComposition0.getEndFrame())))));
        }
        else {
            this.setMinAndMaxFrames(((float)(((int)lottieComposition0.getStartFrame()))), ((float)(((int)lottieComposition0.getEndFrame()))));
        }
        float f = this.frame;
        this.frame = 0.0f;
        this.setFrame(((float)(((int)f))));
    }

    public void setFrame(float f) {
        if(this.frame == f) {
            return;
        }
        this.frame = MiscUtils.clamp(f, this.getMinFrame(), this.getMaxFrame());
        this.lastFrameTimeNs = 0L;
        this.notifyUpdate();
    }

    public void setMaxFrame(float f) {
        this.setMinAndMaxFrames(this.minFrame, f);
    }

    public void setMinAndMaxFrames(float f, float f1) {
        if(f > f1) {
            throw new IllegalArgumentException(String.format("minFrame (%s) must be <= maxFrame (%s)", f, f1));
        }
        float f2 = this.composition == null ? -3.402823E+38f : this.composition.getStartFrame();
        float f3 = this.composition == null ? 3.402823E+38f : this.composition.getEndFrame();
        this.minFrame = MiscUtils.clamp(f, f2, f3);
        this.maxFrame = MiscUtils.clamp(f1, f2, f3);
        this.setFrame(((float)(((int)MiscUtils.clamp(this.frame, f, f1)))));
    }

    public void setMinFrame(int v) {
        this.setMinAndMaxFrames(((float)v), ((float)(((int)this.maxFrame))));
    }

    @Override  // android.animation.ValueAnimator
    public void setRepeatMode(int v) {
        super.setRepeatMode(v);
        if(v != 2 && this.speedReversedForRepeatMode) {
            this.speedReversedForRepeatMode = false;
            this.reverseAnimationSpeed();
        }
    }

    public void setSpeed(float f) {
        this.speed = f;
    }

    private void verifyFrame() {
        if(this.composition != null && (this.frame < this.minFrame || this.frame > this.maxFrame)) {
            throw new IllegalStateException(String.format("Frame must be [%f,%f]. It is %f", this.minFrame, this.maxFrame, this.frame));
        }
    }
}

