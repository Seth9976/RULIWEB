package com.airbnb.lottie.model.layer;

import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import androidx.collection.LongSparseArray;
import com.airbnb.lottie.L;
import com.airbnb.lottie.LottieComposition;
import com.airbnb.lottie.LottieDrawable;
import com.airbnb.lottie.LottieProperty;
import com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation;
import com.airbnb.lottie.animation.keyframe.ValueCallbackKeyframeAnimation;
import com.airbnb.lottie.model.KeyPath;
import com.airbnb.lottie.model.animatable.AnimatableFloatValue;
import com.airbnb.lottie.utils.Utils;
import com.airbnb.lottie.value.LottieValueCallback;
import java.util.ArrayList;
import java.util.List;

public class CompositionLayer extends BaseLayer {
    private Boolean hasMasks;
    private Boolean hasMatte;
    private Paint layerPaint;
    private final List layers;
    private final RectF newClipRect;
    private final RectF rect;
    private BaseKeyframeAnimation timeRemapping;

    public CompositionLayer(LottieDrawable lottieDrawable0, Layer layer0, List list0, LottieComposition lottieComposition0) {
        super(lottieDrawable0, layer0);
        this.layers = new ArrayList();
        this.rect = new RectF();
        this.newClipRect = new RectF();
        this.layerPaint = new Paint();
        AnimatableFloatValue animatableFloatValue0 = layer0.getTimeRemapping();
        if(animatableFloatValue0 == null) {
            this.timeRemapping = null;
        }
        else {
            BaseKeyframeAnimation baseKeyframeAnimation0 = animatableFloatValue0.createAnimation();
            this.timeRemapping = baseKeyframeAnimation0;
            this.addAnimation(baseKeyframeAnimation0);
            this.timeRemapping.addUpdateListener(this);
        }
        LongSparseArray longSparseArray0 = new LongSparseArray(lottieComposition0.getLayers().size());
        int v = list0.size() - 1;
        BaseLayer baseLayer0 = null;
        while(v >= 0) {
            Layer layer1 = (Layer)list0.get(v);
            BaseLayer baseLayer1 = BaseLayer.forModel(layer1, lottieDrawable0, lottieComposition0);
            if(baseLayer1 != null) {
                longSparseArray0.put(baseLayer1.getLayerModel().getId(), baseLayer1);
                if(baseLayer0 == null) {
                    this.layers.add(0, baseLayer1);
                    switch(com.airbnb.lottie.model.layer.CompositionLayer.1.$SwitchMap$com$airbnb$lottie$model$layer$Layer$MatteType[layer1.getMatteType().ordinal()]) {
                        case 1: 
                        case 2: {
                            baseLayer0 = baseLayer1;
                        }
                    }
                }
                else {
                    baseLayer0.setMatteLayer(baseLayer1);
                    baseLayer0 = null;
                }
            }
            --v;
        }
        for(int v1 = 0; v1 < longSparseArray0.size(); ++v1) {
            BaseLayer baseLayer2 = (BaseLayer)longSparseArray0.get(longSparseArray0.keyAt(v1));
            if(baseLayer2 != null) {
                BaseLayer baseLayer3 = (BaseLayer)longSparseArray0.get(baseLayer2.getLayerModel().getParentId());
                if(baseLayer3 != null) {
                    baseLayer2.setParentLayer(baseLayer3);
                }
            }
        }
    }

    @Override  // com.airbnb.lottie.model.layer.BaseLayer
    public void addValueCallback(Object object0, LottieValueCallback lottieValueCallback0) {
        super.addValueCallback(object0, lottieValueCallback0);
        if(object0 == LottieProperty.TIME_REMAP) {
            if(lottieValueCallback0 == null) {
                this.timeRemapping = null;
                return;
            }
            ValueCallbackKeyframeAnimation valueCallbackKeyframeAnimation0 = new ValueCallbackKeyframeAnimation(lottieValueCallback0);
            this.timeRemapping = valueCallbackKeyframeAnimation0;
            this.addAnimation(valueCallbackKeyframeAnimation0);
        }
    }

    @Override  // com.airbnb.lottie.model.layer.BaseLayer
    void drawLayer(Canvas canvas0, Matrix matrix0, int v) {
        L.beginSection("CompositionLayer#draw");
        this.newClipRect.set(0.0f, 0.0f, ((float)this.layerModel.getPreCompWidth()), ((float)this.layerModel.getPreCompHeight()));
        matrix0.mapRect(this.newClipRect);
        boolean z = this.lottieDrawable.isApplyingOpacityToLayersEnabled() && this.layers.size() > 1 && v != 0xFF;
        if(z) {
            this.layerPaint.setAlpha(v);
            Utils.saveLayerCompat(canvas0, this.newClipRect, this.layerPaint);
        }
        else {
            canvas0.save();
        }
        if(z) {
            v = 0xFF;
        }
        for(int v1 = this.layers.size() - 1; v1 >= 0; --v1) {
            if((this.newClipRect.isEmpty() ? true : canvas0.clipRect(this.newClipRect))) {
                ((BaseLayer)this.layers.get(v1)).draw(canvas0, matrix0, v);
            }
        }
        canvas0.restore();
        L.endSection("CompositionLayer#draw");
    }

    @Override  // com.airbnb.lottie.model.layer.BaseLayer
    public void getBounds(RectF rectF0, Matrix matrix0, boolean z) {
        super.getBounds(rectF0, matrix0, z);
        for(int v = this.layers.size() - 1; v >= 0; --v) {
            this.rect.set(0.0f, 0.0f, 0.0f, 0.0f);
            ((BaseLayer)this.layers.get(v)).getBounds(this.rect, this.boundsMatrix, true);
            rectF0.union(this.rect);
        }
    }

    public boolean hasMasks() {
        if(this.hasMasks == null) {
            for(int v = this.layers.size() - 1; v >= 0; --v) {
                BaseLayer baseLayer0 = (BaseLayer)this.layers.get(v);
                if(baseLayer0 instanceof ShapeLayer) {
                    if(baseLayer0.hasMasksOnThisLayer()) {
                        this.hasMasks = Boolean.TRUE;
                        return true;
                    }
                }
                else if(baseLayer0 instanceof CompositionLayer && ((CompositionLayer)baseLayer0).hasMasks()) {
                    this.hasMasks = Boolean.TRUE;
                    return true;
                }
            }
            this.hasMasks = Boolean.FALSE;
        }
        return this.hasMasks.booleanValue();
    }

    public boolean hasMatte() {
        if(this.hasMatte == null) {
            if(this.hasMatteOnThisLayer()) {
                this.hasMatte = Boolean.TRUE;
                return true;
            }
            for(int v = this.layers.size() - 1; v >= 0; --v) {
                if(((BaseLayer)this.layers.get(v)).hasMatteOnThisLayer()) {
                    this.hasMatte = Boolean.TRUE;
                    return true;
                }
            }
            this.hasMatte = Boolean.FALSE;
        }
        return this.hasMatte.booleanValue();
    }

    @Override  // com.airbnb.lottie.model.layer.BaseLayer
    protected void resolveChildKeyPath(KeyPath keyPath0, int v, List list0, KeyPath keyPath1) {
        for(int v1 = 0; v1 < this.layers.size(); ++v1) {
            ((BaseLayer)this.layers.get(v1)).resolveKeyPath(keyPath0, v, list0, keyPath1);
        }
    }

    @Override  // com.airbnb.lottie.model.layer.BaseLayer
    public void setProgress(float f) {
        super.setProgress(f);
        if(this.timeRemapping != null) {
            float f1 = this.lottieDrawable.getComposition().getDurationFrames();
            float f2 = this.layerModel.getComposition().getStartFrame();
            f = (((float)(((Float)this.timeRemapping.getValue()))) * this.layerModel.getComposition().getFrameRate() - f2) / (f1 + 0.01f);
        }
        if(this.layerModel.getTimeStretch() != 0.0f) {
            f /= this.layerModel.getTimeStretch();
        }
        if(this.timeRemapping == null) {
            f -= this.layerModel.getStartProgress();
        }
        for(int v = this.layers.size() - 1; v >= 0; --v) {
            ((BaseLayer)this.layers.get(v)).setProgress(f);
        }
    }

    class com.airbnb.lottie.model.layer.CompositionLayer.1 {
        static final int[] $SwitchMap$com$airbnb$lottie$model$layer$Layer$MatteType;

        static {
            int[] arr_v = new int[MatteType.values().length];
            com.airbnb.lottie.model.layer.CompositionLayer.1.$SwitchMap$com$airbnb$lottie$model$layer$Layer$MatteType = arr_v;
            try {
                arr_v[MatteType.ADD.ordinal()] = 1;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                com.airbnb.lottie.model.layer.CompositionLayer.1.$SwitchMap$com$airbnb$lottie$model$layer$Layer$MatteType[MatteType.INVERT.ordinal()] = 2;
            }
            catch(NoSuchFieldError unused_ex) {
            }
        }
    }

}

