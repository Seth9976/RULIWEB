package com.airbnb.lottie.value;

import com.airbnb.lottie.utils.MiscUtils;

public class LottieRelativeFloatValueCallback extends LottieValueCallback {
    public LottieRelativeFloatValueCallback() {
    }

    public LottieRelativeFloatValueCallback(Float float0) {
        super(float0);
    }

    public Float getOffset(LottieFrameInfo lottieFrameInfo0) {
        if(this.value == null) {
            throw new IllegalArgumentException("You must provide a static value in the constructor , call setValue, or override getValue.");
        }
        return (Float)this.value;
    }

    public Float getValue(LottieFrameInfo lottieFrameInfo0) {
        return (float)(MiscUtils.lerp(((float)(((Float)lottieFrameInfo0.getStartValue()))), ((float)(((Float)lottieFrameInfo0.getEndValue()))), lottieFrameInfo0.getInterpolatedKeyframeProgress()) + ((float)this.getOffset(lottieFrameInfo0)));
    }

    @Override  // com.airbnb.lottie.value.LottieValueCallback
    public Object getValue(LottieFrameInfo lottieFrameInfo0) {
        return this.getValue(lottieFrameInfo0);
    }
}

