package com.airbnb.lottie.animation.content;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import com.airbnb.lottie.LottieDrawable;
import com.airbnb.lottie.LottieProperty;
import com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation;
import com.airbnb.lottie.animation.keyframe.ColorKeyframeAnimation;
import com.airbnb.lottie.animation.keyframe.ValueCallbackKeyframeAnimation;
import com.airbnb.lottie.model.content.ShapeStroke;
import com.airbnb.lottie.model.layer.BaseLayer;
import com.airbnb.lottie.value.LottieValueCallback;

public class StrokeContent extends BaseStrokeContent {
    private final BaseKeyframeAnimation colorAnimation;
    private BaseKeyframeAnimation colorFilterAnimation;
    private final boolean hidden;
    private final BaseLayer layer;
    private final String name;

    public StrokeContent(LottieDrawable lottieDrawable0, BaseLayer baseLayer0, ShapeStroke shapeStroke0) {
        super(lottieDrawable0, baseLayer0, shapeStroke0.getCapType().toPaintCap(), shapeStroke0.getJoinType().toPaintJoin(), shapeStroke0.getMiterLimit(), shapeStroke0.getOpacity(), shapeStroke0.getWidth(), shapeStroke0.getLineDashPattern(), shapeStroke0.getDashOffset());
        this.layer = baseLayer0;
        this.name = shapeStroke0.getName();
        this.hidden = shapeStroke0.isHidden();
        BaseKeyframeAnimation baseKeyframeAnimation0 = shapeStroke0.getColor().createAnimation();
        this.colorAnimation = baseKeyframeAnimation0;
        baseKeyframeAnimation0.addUpdateListener(this);
        baseLayer0.addAnimation(baseKeyframeAnimation0);
    }

    @Override  // com.airbnb.lottie.animation.content.BaseStrokeContent
    public void addValueCallback(Object object0, LottieValueCallback lottieValueCallback0) {
        super.addValueCallback(object0, lottieValueCallback0);
        if(object0 == LottieProperty.STROKE_COLOR) {
            this.colorAnimation.setValueCallback(lottieValueCallback0);
            return;
        }
        if(object0 == LottieProperty.COLOR_FILTER) {
            if(lottieValueCallback0 == null) {
                this.colorFilterAnimation = null;
                return;
            }
            ValueCallbackKeyframeAnimation valueCallbackKeyframeAnimation0 = new ValueCallbackKeyframeAnimation(lottieValueCallback0);
            this.colorFilterAnimation = valueCallbackKeyframeAnimation0;
            valueCallbackKeyframeAnimation0.addUpdateListener(this);
            this.layer.addAnimation(this.colorAnimation);
        }
    }

    @Override  // com.airbnb.lottie.animation.content.BaseStrokeContent
    public void draw(Canvas canvas0, Matrix matrix0, int v) {
        if(this.hidden) {
            return;
        }
        this.paint.setColor(((ColorKeyframeAnimation)this.colorAnimation).getIntValue());
        if(this.colorFilterAnimation != null) {
            this.paint.setColorFilter(((ColorFilter)this.colorFilterAnimation.getValue()));
        }
        super.draw(canvas0, matrix0, v);
    }

    @Override  // com.airbnb.lottie.animation.content.Content
    public String getName() {
        return this.name;
    }
}

