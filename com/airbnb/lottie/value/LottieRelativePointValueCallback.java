package com.airbnb.lottie.value;

import android.graphics.PointF;
import com.airbnb.lottie.utils.MiscUtils;

public class LottieRelativePointValueCallback extends LottieValueCallback {
    private final PointF point;

    public LottieRelativePointValueCallback() {
        this.point = new PointF();
    }

    public LottieRelativePointValueCallback(PointF pointF0) {
        super(pointF0);
        this.point = new PointF();
    }

    public PointF getOffset(LottieFrameInfo lottieFrameInfo0) {
        if(this.value == null) {
            throw new IllegalArgumentException("You must provide a static value in the constructor , call setValue, or override getValue.");
        }
        return (PointF)this.value;
    }

    public final PointF getValue(LottieFrameInfo lottieFrameInfo0) {
        this.point.set(MiscUtils.lerp(((PointF)lottieFrameInfo0.getStartValue()).x, ((PointF)lottieFrameInfo0.getEndValue()).x, lottieFrameInfo0.getInterpolatedKeyframeProgress()), MiscUtils.lerp(((PointF)lottieFrameInfo0.getStartValue()).y, ((PointF)lottieFrameInfo0.getEndValue()).y, lottieFrameInfo0.getInterpolatedKeyframeProgress()));
        PointF pointF0 = this.getOffset(lottieFrameInfo0);
        this.point.offset(pointF0.x, pointF0.y);
        return this.point;
    }

    @Override  // com.airbnb.lottie.value.LottieValueCallback
    public Object getValue(LottieFrameInfo lottieFrameInfo0) {
        return this.getValue(lottieFrameInfo0);
    }
}

