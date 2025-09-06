package com.airbnb.lottie.animation.keyframe;

import android.graphics.Path;
import com.airbnb.lottie.model.content.ShapeData;
import com.airbnb.lottie.utils.MiscUtils;
import com.airbnb.lottie.value.Keyframe;
import java.util.List;

public class ShapeKeyframeAnimation extends BaseKeyframeAnimation {
    private final Path tempPath;
    private final ShapeData tempShapeData;

    public ShapeKeyframeAnimation(List list0) {
        super(list0);
        this.tempShapeData = new ShapeData();
        this.tempPath = new Path();
    }

    public Path getValue(Keyframe keyframe0, float f) {
        this.tempShapeData.interpolateBetween(((ShapeData)keyframe0.startValue), ((ShapeData)keyframe0.endValue), f);
        MiscUtils.getPathFromData(this.tempShapeData, this.tempPath);
        return this.tempPath;
    }

    @Override  // com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation
    public Object getValue(Keyframe keyframe0, float f) {
        return this.getValue(keyframe0, f);
    }
}

