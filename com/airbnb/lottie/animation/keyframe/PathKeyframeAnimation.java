package com.airbnb.lottie.animation.keyframe;

import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.PointF;
import com.airbnb.lottie.value.Keyframe;
import java.util.List;

public class PathKeyframeAnimation extends KeyframeAnimation {
    private PathMeasure pathMeasure;
    private PathKeyframe pathMeasureKeyframe;
    private final PointF point;
    private final float[] pos;

    public PathKeyframeAnimation(List list0) {
        super(list0);
        this.point = new PointF();
        this.pos = new float[2];
        this.pathMeasure = new PathMeasure();
    }

    public PointF getValue(Keyframe keyframe0, float f) {
        Path path0 = ((PathKeyframe)keyframe0).getPath();
        if(path0 == null) {
            return (PointF)keyframe0.startValue;
        }
        if(this.valueCallback != null) {
            PointF pointF0 = (PointF)this.valueCallback.getValueInternal(((PathKeyframe)keyframe0).startFrame, ((float)((PathKeyframe)keyframe0).endFrame), ((PathKeyframe)keyframe0).startValue, ((PathKeyframe)keyframe0).endValue, this.getLinearCurrentKeyframeProgress(), f, this.getProgress());
            if(pointF0 != null) {
                return pointF0;
            }
        }
        if(this.pathMeasureKeyframe != ((PathKeyframe)keyframe0)) {
            this.pathMeasure.setPath(path0, false);
            this.pathMeasureKeyframe = (PathKeyframe)keyframe0;
        }
        this.pathMeasure.getPosTan(this.pathMeasure.getLength() * f, this.pos, null);
        this.point.set(this.pos[0], this.pos[1]);
        return this.point;
    }

    @Override  // com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation
    public Object getValue(Keyframe keyframe0, float f) {
        return this.getValue(keyframe0, f);
    }
}

