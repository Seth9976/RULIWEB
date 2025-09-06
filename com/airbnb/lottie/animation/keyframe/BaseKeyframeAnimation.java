package com.airbnb.lottie.animation.keyframe;

import com.airbnb.lottie.L;
import com.airbnb.lottie.value.Keyframe;
import com.airbnb.lottie.value.LottieValueCallback;
import java.util.ArrayList;
import java.util.List;

public abstract class BaseKeyframeAnimation {
    public interface AnimationListener {
        void onValueChanged();
    }

    private float cachedEndProgress;
    private Object cachedGetValue;
    private Keyframe cachedGetValueKeyframe;
    private float cachedGetValueProgress;
    private Keyframe cachedKeyframe;
    private float cachedStartDelayProgress;
    private boolean isDiscrete;
    private final List keyframes;
    final List listeners;
    private float progress;
    protected LottieValueCallback valueCallback;

    BaseKeyframeAnimation(List list0) {
        this.listeners = new ArrayList(1);
        this.isDiscrete = false;
        this.progress = 0.0f;
        this.cachedGetValueProgress = -1.0f;
        this.cachedGetValue = null;
        this.cachedStartDelayProgress = -1.0f;
        this.cachedEndProgress = -1.0f;
        this.keyframes = list0;
    }

    public void addUpdateListener(AnimationListener baseKeyframeAnimation$AnimationListener0) {
        this.listeners.add(baseKeyframeAnimation$AnimationListener0);
    }

    protected Keyframe getCurrentKeyframe() {
        L.beginSection("BaseKeyframeAnimation#getCurrentKeyframe");
        if(this.cachedKeyframe != null && this.cachedKeyframe.containsProgress(this.progress)) {
            L.endSection("BaseKeyframeAnimation#getCurrentKeyframe");
            return this.cachedKeyframe;
        }
        Keyframe keyframe0 = (Keyframe)this.keyframes.get(this.keyframes.size() - 1);
        if(this.progress < keyframe0.getStartProgress()) {
            for(int v = this.keyframes.size() - 1; v >= 0; --v) {
                keyframe0 = (Keyframe)this.keyframes.get(v);
                if(keyframe0.containsProgress(this.progress)) {
                    break;
                }
            }
        }
        this.cachedKeyframe = keyframe0;
        L.endSection("BaseKeyframeAnimation#getCurrentKeyframe");
        return keyframe0;
    }

    float getEndProgress() {
        if(this.cachedEndProgress == -1.0f) {
            this.cachedEndProgress = this.keyframes.isEmpty() ? 1.0f : ((Keyframe)this.keyframes.get(this.keyframes.size() - 1)).getEndProgress();
        }
        return this.cachedEndProgress;
    }

    protected float getInterpolatedCurrentKeyframeProgress() {
        Keyframe keyframe0 = this.getCurrentKeyframe();
        if(keyframe0.isStatic()) {
            return 0.0f;
        }
        float f = this.getLinearCurrentKeyframeProgress();
        return keyframe0.interpolator.getInterpolation(f);
    }

    float getLinearCurrentKeyframeProgress() {
        if(this.isDiscrete) {
            return 0.0f;
        }
        Keyframe keyframe0 = this.getCurrentKeyframe();
        return keyframe0.isStatic() ? 0.0f : (this.progress - keyframe0.getStartProgress()) / (keyframe0.getEndProgress() - keyframe0.getStartProgress());
    }

    public float getProgress() {
        return this.progress;
    }

    private float getStartDelayProgress() {
        if(this.cachedStartDelayProgress == -1.0f) {
            this.cachedStartDelayProgress = this.keyframes.isEmpty() ? 0.0f : ((Keyframe)this.keyframes.get(0)).getStartProgress();
        }
        return this.cachedStartDelayProgress;
    }

    public Object getValue() {
        Keyframe keyframe0 = this.getCurrentKeyframe();
        float f = this.getInterpolatedCurrentKeyframeProgress();
        if(this.valueCallback == null && keyframe0 == this.cachedGetValueKeyframe && this.cachedGetValueProgress == f) {
            return this.cachedGetValue;
        }
        this.cachedGetValueKeyframe = keyframe0;
        this.cachedGetValueProgress = f;
        Object object0 = this.getValue(keyframe0, f);
        this.cachedGetValue = object0;
        return object0;
    }

    abstract Object getValue(Keyframe arg1, float arg2);

    public void notifyListeners() {
        for(int v = 0; v < this.listeners.size(); ++v) {
            ((AnimationListener)this.listeners.get(v)).onValueChanged();
        }
    }

    public void setIsDiscrete() {
        this.isDiscrete = true;
    }

    public void setProgress(float f) {
        if(!this.keyframes.isEmpty()) {
            Keyframe keyframe0 = this.getCurrentKeyframe();
            if(f < this.getStartDelayProgress()) {
                f = this.getStartDelayProgress();
            }
            else if(f > this.getEndProgress()) {
                f = this.getEndProgress();
            }
            if(f != this.progress) {
                this.progress = f;
                Keyframe keyframe1 = this.getCurrentKeyframe();
                if(keyframe0 != keyframe1 || !keyframe1.isStatic()) {
                    this.notifyListeners();
                }
            }
        }
    }

    public void setValueCallback(LottieValueCallback lottieValueCallback0) {
        LottieValueCallback lottieValueCallback1 = this.valueCallback;
        if(lottieValueCallback1 != null) {
            lottieValueCallback1.setAnimation(null);
        }
        this.valueCallback = lottieValueCallback0;
        if(lottieValueCallback0 != null) {
            lottieValueCallback0.setAnimation(this);
        }
    }
}

