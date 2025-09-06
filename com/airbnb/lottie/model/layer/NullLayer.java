package com.airbnb.lottie.model.layer;

import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.RectF;
import com.airbnb.lottie.LottieDrawable;

public class NullLayer extends BaseLayer {
    NullLayer(LottieDrawable lottieDrawable0, Layer layer0) {
        super(lottieDrawable0, layer0);
    }

    @Override  // com.airbnb.lottie.model.layer.BaseLayer
    void drawLayer(Canvas canvas0, Matrix matrix0, int v) {
    }

    @Override  // com.airbnb.lottie.model.layer.BaseLayer
    public void getBounds(RectF rectF0, Matrix matrix0, boolean z) {
        super.getBounds(rectF0, matrix0, z);
        rectF0.set(0.0f, 0.0f, 0.0f, 0.0f);
    }
}

