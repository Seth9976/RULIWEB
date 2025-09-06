package com.airbnb.lottie.model.layer;

import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.RectF;
import com.airbnb.lottie.LottieDrawable;
import com.airbnb.lottie.animation.content.ContentGroup;
import com.airbnb.lottie.model.KeyPath;
import com.airbnb.lottie.model.content.ShapeGroup;
import java.util.Collections;
import java.util.List;

public class ShapeLayer extends BaseLayer {
    private final ContentGroup contentGroup;

    ShapeLayer(LottieDrawable lottieDrawable0, Layer layer0) {
        super(lottieDrawable0, layer0);
        ContentGroup contentGroup0 = new ContentGroup(lottieDrawable0, this, new ShapeGroup("__container", layer0.getShapes(), false));
        this.contentGroup = contentGroup0;
        contentGroup0.setContents(Collections.EMPTY_LIST, Collections.EMPTY_LIST);
    }

    @Override  // com.airbnb.lottie.model.layer.BaseLayer
    void drawLayer(Canvas canvas0, Matrix matrix0, int v) {
        this.contentGroup.draw(canvas0, matrix0, v);
    }

    @Override  // com.airbnb.lottie.model.layer.BaseLayer
    public void getBounds(RectF rectF0, Matrix matrix0, boolean z) {
        super.getBounds(rectF0, matrix0, z);
        this.contentGroup.getBounds(rectF0, this.boundsMatrix, z);
    }

    @Override  // com.airbnb.lottie.model.layer.BaseLayer
    protected void resolveChildKeyPath(KeyPath keyPath0, int v, List list0, KeyPath keyPath1) {
        this.contentGroup.resolveKeyPath(keyPath0, v, list0, keyPath1);
    }
}

