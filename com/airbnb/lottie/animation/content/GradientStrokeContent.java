package com.airbnb.lottie.animation.content;

import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.graphics.RadialGradient;
import android.graphics.RectF;
import android.graphics.Shader.TileMode;
import androidx.collection.LongSparseArray;
import com.airbnb.lottie.LottieDrawable;
import com.airbnb.lottie.LottieProperty;
import com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation;
import com.airbnb.lottie.animation.keyframe.ValueCallbackKeyframeAnimation;
import com.airbnb.lottie.model.content.GradientColor;
import com.airbnb.lottie.model.content.GradientStroke;
import com.airbnb.lottie.model.content.GradientType;
import com.airbnb.lottie.model.layer.BaseLayer;
import com.airbnb.lottie.value.LottieValueCallback;

public class GradientStrokeContent extends BaseStrokeContent {
    private static final int CACHE_STEPS_MS = 0x20;
    private final RectF boundsRect;
    private final int cacheSteps;
    private final BaseKeyframeAnimation colorAnimation;
    private ValueCallbackKeyframeAnimation colorCallbackAnimation;
    private final BaseKeyframeAnimation endPointAnimation;
    private final boolean hidden;
    private final LongSparseArray linearGradientCache;
    private final String name;
    private final LongSparseArray radialGradientCache;
    private final BaseKeyframeAnimation startPointAnimation;
    private final GradientType type;

    public GradientStrokeContent(LottieDrawable lottieDrawable0, BaseLayer baseLayer0, GradientStroke gradientStroke0) {
        super(lottieDrawable0, baseLayer0, gradientStroke0.getCapType().toPaintCap(), gradientStroke0.getJoinType().toPaintJoin(), gradientStroke0.getMiterLimit(), gradientStroke0.getOpacity(), gradientStroke0.getWidth(), gradientStroke0.getLineDashPattern(), gradientStroke0.getDashOffset());
        this.linearGradientCache = new LongSparseArray();
        this.radialGradientCache = new LongSparseArray();
        this.boundsRect = new RectF();
        this.name = gradientStroke0.getName();
        this.type = gradientStroke0.getGradientType();
        this.hidden = gradientStroke0.isHidden();
        this.cacheSteps = (int)(lottieDrawable0.getComposition().getDuration() / 32.0f);
        BaseKeyframeAnimation baseKeyframeAnimation0 = gradientStroke0.getGradientColor().createAnimation();
        this.colorAnimation = baseKeyframeAnimation0;
        baseKeyframeAnimation0.addUpdateListener(this);
        baseLayer0.addAnimation(baseKeyframeAnimation0);
        BaseKeyframeAnimation baseKeyframeAnimation1 = gradientStroke0.getStartPoint().createAnimation();
        this.startPointAnimation = baseKeyframeAnimation1;
        baseKeyframeAnimation1.addUpdateListener(this);
        baseLayer0.addAnimation(baseKeyframeAnimation1);
        BaseKeyframeAnimation baseKeyframeAnimation2 = gradientStroke0.getEndPoint().createAnimation();
        this.endPointAnimation = baseKeyframeAnimation2;
        baseKeyframeAnimation2.addUpdateListener(this);
        baseLayer0.addAnimation(baseKeyframeAnimation2);
    }

    @Override  // com.airbnb.lottie.animation.content.BaseStrokeContent
    public void addValueCallback(Object object0, LottieValueCallback lottieValueCallback0) {
        super.addValueCallback(object0, lottieValueCallback0);
        if(object0 == LottieProperty.GRADIENT_COLOR) {
            if(lottieValueCallback0 == null) {
                if(this.colorCallbackAnimation != null) {
                    this.layer.removeAnimation(this.colorCallbackAnimation);
                }
                this.colorCallbackAnimation = null;
                return;
            }
            ValueCallbackKeyframeAnimation valueCallbackKeyframeAnimation0 = new ValueCallbackKeyframeAnimation(lottieValueCallback0);
            this.colorCallbackAnimation = valueCallbackKeyframeAnimation0;
            valueCallbackKeyframeAnimation0.addUpdateListener(this);
            this.layer.addAnimation(this.colorCallbackAnimation);
        }
    }

    private int[] applyDynamicColorsIfNeeded(int[] arr_v) {
        ValueCallbackKeyframeAnimation valueCallbackKeyframeAnimation0 = this.colorCallbackAnimation;
        if(valueCallbackKeyframeAnimation0 != null) {
            Integer[] arr_integer = (Integer[])valueCallbackKeyframeAnimation0.getValue();
            int v = 0;
            if(arr_v.length == arr_integer.length) {
                while(v < arr_v.length) {
                    arr_v[v] = (int)arr_integer[v];
                    ++v;
                }
                return arr_v;
            }
            arr_v = new int[arr_integer.length];
            while(v < arr_integer.length) {
                arr_v[v] = (int)arr_integer[v];
                ++v;
            }
        }
        return arr_v;
    }

    @Override  // com.airbnb.lottie.animation.content.BaseStrokeContent
    public void draw(Canvas canvas0, Matrix matrix0, int v) {
        if(this.hidden) {
            return;
        }
        this.getBounds(this.boundsRect, matrix0, false);
        LinearGradient linearGradient0 = this.type == GradientType.LINEAR ? this.getLinearGradient() : this.getRadialGradient();
        linearGradient0.setLocalMatrix(matrix0);
        this.paint.setShader(linearGradient0);
        super.draw(canvas0, matrix0, v);
    }

    private int getGradientHash() {
        int v = Math.round(this.startPointAnimation.getProgress() * ((float)this.cacheSteps));
        int v1 = Math.round(this.endPointAnimation.getProgress() * ((float)this.cacheSteps));
        int v2 = Math.round(this.colorAnimation.getProgress() * ((float)this.cacheSteps));
        int v3 = v == 0 ? 17 : 0x20F * v;
        if(v1 != 0) {
            v3 = v3 * 0x1F * v1;
        }
        return v2 == 0 ? v3 : v3 * 0x1F * v2;
    }

    private LinearGradient getLinearGradient() {
        long v = (long)this.getGradientHash();
        LinearGradient linearGradient0 = (LinearGradient)this.linearGradientCache.get(v);
        if(linearGradient0 != null) {
            return linearGradient0;
        }
        PointF pointF0 = (PointF)this.startPointAnimation.getValue();
        PointF pointF1 = (PointF)this.endPointAnimation.getValue();
        GradientColor gradientColor0 = (GradientColor)this.colorAnimation.getValue();
        int[] arr_v = this.applyDynamicColorsIfNeeded(gradientColor0.getColors());
        LinearGradient linearGradient1 = new LinearGradient(pointF0.x, pointF0.y, pointF1.x, pointF1.y, arr_v, gradientColor0.getPositions(), Shader.TileMode.CLAMP);
        this.linearGradientCache.put(v, linearGradient1);
        return linearGradient1;
    }

    @Override  // com.airbnb.lottie.animation.content.Content
    public String getName() {
        return this.name;
    }

    private RadialGradient getRadialGradient() {
        long v = (long)this.getGradientHash();
        RadialGradient radialGradient0 = (RadialGradient)this.radialGradientCache.get(v);
        if(radialGradient0 != null) {
            return radialGradient0;
        }
        PointF pointF0 = (PointF)this.startPointAnimation.getValue();
        PointF pointF1 = (PointF)this.endPointAnimation.getValue();
        GradientColor gradientColor0 = (GradientColor)this.colorAnimation.getValue();
        int[] arr_v = this.applyDynamicColorsIfNeeded(gradientColor0.getColors());
        RadialGradient radialGradient1 = new RadialGradient(pointF0.x, pointF0.y, ((float)Math.hypot(pointF1.x - pointF0.x, pointF1.y - pointF0.y)), arr_v, gradientColor0.getPositions(), Shader.TileMode.CLAMP);
        this.radialGradientCache.put(v, radialGradient1);
        return radialGradient1;
    }
}

