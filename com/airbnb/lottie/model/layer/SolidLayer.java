package com.airbnb.lottie.model.layer;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint.Style;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import com.airbnb.lottie.LottieDrawable;
import com.airbnb.lottie.LottieProperty;
import com.airbnb.lottie.animation.LPaint;
import com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation;
import com.airbnb.lottie.animation.keyframe.ValueCallbackKeyframeAnimation;
import com.airbnb.lottie.value.LottieValueCallback;

public class SolidLayer extends BaseLayer {
    private BaseKeyframeAnimation colorFilterAnimation;
    private final Layer layerModel;
    private final Paint paint;
    private final Path path;
    private final float[] points;
    private final RectF rect;

    SolidLayer(LottieDrawable lottieDrawable0, Layer layer0) {
        super(lottieDrawable0, layer0);
        this.rect = new RectF();
        LPaint lPaint0 = new LPaint();
        this.paint = lPaint0;
        this.points = new float[8];
        this.path = new Path();
        this.layerModel = layer0;
        lPaint0.setAlpha(0);
        lPaint0.setStyle(Paint.Style.FILL);
        lPaint0.setColor(layer0.getSolidColor());
    }

    @Override  // com.airbnb.lottie.model.layer.BaseLayer
    public void addValueCallback(Object object0, LottieValueCallback lottieValueCallback0) {
        super.addValueCallback(object0, lottieValueCallback0);
        if(object0 == LottieProperty.COLOR_FILTER) {
            if(lottieValueCallback0 == null) {
                this.colorFilterAnimation = null;
                return;
            }
            this.colorFilterAnimation = new ValueCallbackKeyframeAnimation(lottieValueCallback0);
        }
    }

    @Override  // com.airbnb.lottie.model.layer.BaseLayer
    public void drawLayer(Canvas canvas0, Matrix matrix0, int v) {
        int v1 = Color.alpha(this.layerModel.getSolidColor());
        if(v1 != 0) {
            int v2 = (int)(((float)v) / 255.0f * (((float)v1) / 255.0f * ((float)(this.transform.getOpacity() == null ? 100 : ((int)(((Integer)this.transform.getOpacity().getValue()))))) / 100.0f) * 255.0f);
            this.paint.setAlpha(v2);
            BaseKeyframeAnimation baseKeyframeAnimation0 = this.colorFilterAnimation;
            if(baseKeyframeAnimation0 != null) {
                ColorFilter colorFilter0 = (ColorFilter)baseKeyframeAnimation0.getValue();
                this.paint.setColorFilter(colorFilter0);
            }
            if(v2 > 0) {
                this.points[0] = 0.0f;
                this.points[1] = 0.0f;
                this.points[2] = (float)this.layerModel.getSolidWidth();
                this.points[3] = 0.0f;
                this.points[4] = (float)this.layerModel.getSolidWidth();
                this.points[5] = (float)this.layerModel.getSolidHeight();
                this.points[6] = 0.0f;
                this.points[7] = (float)this.layerModel.getSolidHeight();
                matrix0.mapPoints(this.points);
                this.path.reset();
                this.path.moveTo(this.points[0], this.points[1]);
                this.path.lineTo(this.points[2], this.points[3]);
                this.path.lineTo(this.points[4], this.points[5]);
                this.path.lineTo(this.points[6], this.points[7]);
                this.path.lineTo(this.points[0], this.points[1]);
                this.path.close();
                canvas0.drawPath(this.path, this.paint);
            }
        }
    }

    @Override  // com.airbnb.lottie.model.layer.BaseLayer
    public void getBounds(RectF rectF0, Matrix matrix0, boolean z) {
        super.getBounds(rectF0, matrix0, z);
        this.rect.set(0.0f, 0.0f, ((float)this.layerModel.getSolidWidth()), ((float)this.layerModel.getSolidHeight()));
        this.boundsMatrix.mapRect(this.rect);
        rectF0.set(this.rect);
    }
}

