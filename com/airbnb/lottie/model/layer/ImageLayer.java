package com.airbnb.lottie.model.layer;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import com.airbnb.lottie.LottieDrawable;
import com.airbnb.lottie.LottieProperty;
import com.airbnb.lottie.animation.LPaint;
import com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation;
import com.airbnb.lottie.animation.keyframe.ValueCallbackKeyframeAnimation;
import com.airbnb.lottie.utils.Utils;
import com.airbnb.lottie.value.LottieValueCallback;

public class ImageLayer extends BaseLayer {
    private BaseKeyframeAnimation colorFilterAnimation;
    private final Rect dst;
    private final Paint paint;
    private final Rect src;

    ImageLayer(LottieDrawable lottieDrawable0, Layer layer0) {
        super(lottieDrawable0, layer0);
        this.paint = new LPaint(3);
        this.src = new Rect();
        this.dst = new Rect();
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
        Bitmap bitmap0 = this.getBitmap();
        if(bitmap0 != null && !bitmap0.isRecycled()) {
            float f = Utils.dpScale();
            this.paint.setAlpha(v);
            BaseKeyframeAnimation baseKeyframeAnimation0 = this.colorFilterAnimation;
            if(baseKeyframeAnimation0 != null) {
                ColorFilter colorFilter0 = (ColorFilter)baseKeyframeAnimation0.getValue();
                this.paint.setColorFilter(colorFilter0);
            }
            canvas0.save();
            canvas0.concat(matrix0);
            int v1 = bitmap0.getWidth();
            int v2 = bitmap0.getHeight();
            this.src.set(0, 0, v1, v2);
            int v3 = (int)(((float)bitmap0.getWidth()) * f);
            int v4 = (int)(((float)bitmap0.getHeight()) * f);
            this.dst.set(0, 0, v3, v4);
            canvas0.drawBitmap(bitmap0, this.src, this.dst, this.paint);
            canvas0.restore();
        }
    }

    private Bitmap getBitmap() {
        return this.lottieDrawable.getImageAsset(this.layerModel.getRefId());
    }

    @Override  // com.airbnb.lottie.model.layer.BaseLayer
    public void getBounds(RectF rectF0, Matrix matrix0, boolean z) {
        super.getBounds(rectF0, matrix0, z);
        Bitmap bitmap0 = this.getBitmap();
        if(bitmap0 != null) {
            rectF0.set(0.0f, 0.0f, ((float)bitmap0.getWidth()) * Utils.dpScale(), ((float)bitmap0.getHeight()) * Utils.dpScale());
            this.boundsMatrix.mapRect(rectF0);
        }
    }
}

