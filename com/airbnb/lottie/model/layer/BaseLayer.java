package com.airbnb.lottie.model.layer;

import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.os.Build.VERSION;
import com.airbnb.lottie.L;
import com.airbnb.lottie.LottieComposition;
import com.airbnb.lottie.LottieDrawable;
import com.airbnb.lottie.animation.LPaint;
import com.airbnb.lottie.animation.content.DrawingContent;
import com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation.AnimationListener;
import com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation;
import com.airbnb.lottie.animation.keyframe.FloatKeyframeAnimation;
import com.airbnb.lottie.animation.keyframe.MaskKeyframeAnimation;
import com.airbnb.lottie.animation.keyframe.TransformKeyframeAnimation;
import com.airbnb.lottie.model.KeyPath;
import com.airbnb.lottie.model.KeyPathElement;
import com.airbnb.lottie.model.content.Mask;
import com.airbnb.lottie.utils.Logger;
import com.airbnb.lottie.utils.Utils;
import com.airbnb.lottie.value.LottieValueCallback;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class BaseLayer implements DrawingContent, AnimationListener, KeyPathElement {
    private static final int CLIP_SAVE_FLAG = 2;
    private static final int CLIP_TO_LAYER_SAVE_FLAG = 16;
    private static final int MATRIX_SAVE_FLAG = 1;
    private static final int SAVE_FLAGS = 19;
    private final List animations;
    final Matrix boundsMatrix;
    private final Paint clearPaint;
    private final Paint contentPaint;
    private final String drawTraceName;
    private final Paint dstInPaint;
    private final Paint dstOutPaint;
    final Layer layerModel;
    final LottieDrawable lottieDrawable;
    private MaskKeyframeAnimation mask;
    private final RectF maskBoundsRect;
    private final Matrix matrix;
    private final RectF matteBoundsRect;
    private BaseLayer matteLayer;
    private final Paint mattePaint;
    private BaseLayer parentLayer;
    private List parentLayers;
    private final Path path;
    private final RectF rect;
    private final RectF tempMaskBoundsRect;
    final TransformKeyframeAnimation transform;
    private boolean visible;

    BaseLayer(LottieDrawable lottieDrawable0, Layer layer0) {
        this.path = new Path();
        this.matrix = new Matrix();
        this.contentPaint = new LPaint(1);
        this.dstInPaint = new LPaint(1, PorterDuff.Mode.DST_IN);
        this.dstOutPaint = new LPaint(1, PorterDuff.Mode.DST_OUT);
        LPaint lPaint0 = new LPaint(1);
        this.mattePaint = lPaint0;
        this.clearPaint = new LPaint(PorterDuff.Mode.CLEAR);
        this.rect = new RectF();
        this.maskBoundsRect = new RectF();
        this.matteBoundsRect = new RectF();
        this.tempMaskBoundsRect = new RectF();
        this.boundsMatrix = new Matrix();
        this.animations = new ArrayList();
        this.visible = true;
        this.lottieDrawable = lottieDrawable0;
        this.layerModel = layer0;
        this.drawTraceName = layer0.getName() + "#draw";
        if(layer0.getMatteType() == MatteType.INVERT) {
            lPaint0.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OUT));
        }
        else {
            lPaint0.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
        }
        TransformKeyframeAnimation transformKeyframeAnimation0 = layer0.getTransform().createAnimation();
        this.transform = transformKeyframeAnimation0;
        transformKeyframeAnimation0.addListener(this);
        if(layer0.getMasks() != null && !layer0.getMasks().isEmpty()) {
            MaskKeyframeAnimation maskKeyframeAnimation0 = new MaskKeyframeAnimation(layer0.getMasks());
            this.mask = maskKeyframeAnimation0;
            for(Object object0: maskKeyframeAnimation0.getMaskAnimations()) {
                ((BaseKeyframeAnimation)object0).addUpdateListener(this);
            }
            for(Object object1: this.mask.getOpacityAnimations()) {
                this.addAnimation(((BaseKeyframeAnimation)object1));
                ((BaseKeyframeAnimation)object1).addUpdateListener(this);
            }
        }
        this.setupInOutAnimations();
    }

    public void addAnimation(BaseKeyframeAnimation baseKeyframeAnimation0) {
        if(baseKeyframeAnimation0 == null) {
            return;
        }
        this.animations.add(baseKeyframeAnimation0);
    }

    @Override  // com.airbnb.lottie.model.KeyPathElement
    public void addValueCallback(Object object0, LottieValueCallback lottieValueCallback0) {
        this.transform.applyValueCallback(object0, lottieValueCallback0);
    }

    private void applyAddMask(Canvas canvas0, Matrix matrix0, Mask mask0, BaseKeyframeAnimation baseKeyframeAnimation0, BaseKeyframeAnimation baseKeyframeAnimation1) {
        Path path0 = (Path)baseKeyframeAnimation0.getValue();
        this.path.set(path0);
        this.path.transform(matrix0);
        int v = (int)(((float)(((int)(((Integer)baseKeyframeAnimation1.getValue()))))) * 2.55f);
        this.contentPaint.setAlpha(v);
        canvas0.drawPath(this.path, this.contentPaint);
    }

    private void applyIntersectMask(Canvas canvas0, Matrix matrix0, Mask mask0, BaseKeyframeAnimation baseKeyframeAnimation0, BaseKeyframeAnimation baseKeyframeAnimation1) {
        Utils.saveLayerCompat(canvas0, this.rect, this.dstInPaint);
        Path path0 = (Path)baseKeyframeAnimation0.getValue();
        this.path.set(path0);
        this.path.transform(matrix0);
        int v = (int)(((float)(((int)(((Integer)baseKeyframeAnimation1.getValue()))))) * 2.55f);
        this.contentPaint.setAlpha(v);
        canvas0.drawPath(this.path, this.contentPaint);
        canvas0.restore();
    }

    private void applyInvertedAddMask(Canvas canvas0, Matrix matrix0, Mask mask0, BaseKeyframeAnimation baseKeyframeAnimation0, BaseKeyframeAnimation baseKeyframeAnimation1) {
        Utils.saveLayerCompat(canvas0, this.rect, this.contentPaint);
        canvas0.drawRect(this.rect, this.contentPaint);
        Path path0 = (Path)baseKeyframeAnimation0.getValue();
        this.path.set(path0);
        this.path.transform(matrix0);
        int v = (int)(((float)(((int)(((Integer)baseKeyframeAnimation1.getValue()))))) * 2.55f);
        this.contentPaint.setAlpha(v);
        canvas0.drawPath(this.path, this.dstOutPaint);
        canvas0.restore();
    }

    private void applyInvertedIntersectMask(Canvas canvas0, Matrix matrix0, Mask mask0, BaseKeyframeAnimation baseKeyframeAnimation0, BaseKeyframeAnimation baseKeyframeAnimation1) {
        Utils.saveLayerCompat(canvas0, this.rect, this.dstInPaint);
        canvas0.drawRect(this.rect, this.contentPaint);
        int v = (int)(((float)(((int)(((Integer)baseKeyframeAnimation1.getValue()))))) * 2.55f);
        this.dstOutPaint.setAlpha(v);
        Path path0 = (Path)baseKeyframeAnimation0.getValue();
        this.path.set(path0);
        this.path.transform(matrix0);
        canvas0.drawPath(this.path, this.dstOutPaint);
        canvas0.restore();
    }

    private void applyInvertedSubtractMask(Canvas canvas0, Matrix matrix0, Mask mask0, BaseKeyframeAnimation baseKeyframeAnimation0, BaseKeyframeAnimation baseKeyframeAnimation1) {
        Utils.saveLayerCompat(canvas0, this.rect, this.dstOutPaint);
        canvas0.drawRect(this.rect, this.contentPaint);
        int v = (int)(((float)(((int)(((Integer)baseKeyframeAnimation1.getValue()))))) * 2.55f);
        this.dstOutPaint.setAlpha(v);
        Path path0 = (Path)baseKeyframeAnimation0.getValue();
        this.path.set(path0);
        this.path.transform(matrix0);
        canvas0.drawPath(this.path, this.dstOutPaint);
        canvas0.restore();
    }

    private void applyMasks(Canvas canvas0, Matrix matrix0) {
        L.beginSection("Layer#saveLayer");
        Utils.saveLayerCompat(canvas0, this.rect, this.dstInPaint, 19);
        if(Build.VERSION.SDK_INT < 28) {
            canvas0.drawColor(0);
        }
        L.endSection("Layer#saveLayer");
        for(int v = 0; v < this.mask.getMasks().size(); ++v) {
            Mask mask0 = (Mask)this.mask.getMasks().get(v);
            BaseKeyframeAnimation baseKeyframeAnimation0 = (BaseKeyframeAnimation)this.mask.getMaskAnimations().get(v);
            BaseKeyframeAnimation baseKeyframeAnimation1 = (BaseKeyframeAnimation)this.mask.getOpacityAnimations().get(v);
            switch(com.airbnb.lottie.model.layer.BaseLayer.2.$SwitchMap$com$airbnb$lottie$model$content$Mask$MaskMode[mask0.getMaskMode().ordinal()]) {
                case 1: {
                    if(v == 0) {
                        this.contentPaint.setColor(0xFF000000);
                        this.contentPaint.setAlpha(0xFF);
                        canvas0.drawRect(this.rect, this.contentPaint);
                    }
                    if(mask0.isInverted()) {
                        this.applyInvertedSubtractMask(canvas0, matrix0, mask0, baseKeyframeAnimation0, baseKeyframeAnimation1);
                    }
                    else {
                        this.applySubtractMask(canvas0, matrix0, mask0, baseKeyframeAnimation0, baseKeyframeAnimation1);
                    }
                    break;
                }
                case 2: {
                    if(mask0.isInverted()) {
                        this.applyInvertedIntersectMask(canvas0, matrix0, mask0, baseKeyframeAnimation0, baseKeyframeAnimation1);
                    }
                    else {
                        this.applyIntersectMask(canvas0, matrix0, mask0, baseKeyframeAnimation0, baseKeyframeAnimation1);
                    }
                    break;
                }
                case 3: {
                    if(mask0.isInverted()) {
                        this.applyInvertedAddMask(canvas0, matrix0, mask0, baseKeyframeAnimation0, baseKeyframeAnimation1);
                    }
                    else {
                        this.applyAddMask(canvas0, matrix0, mask0, baseKeyframeAnimation0, baseKeyframeAnimation1);
                    }
                }
            }
        }
        L.beginSection("Layer#restoreLayer");
        canvas0.restore();
        L.endSection("Layer#restoreLayer");
    }

    private void applySubtractMask(Canvas canvas0, Matrix matrix0, Mask mask0, BaseKeyframeAnimation baseKeyframeAnimation0, BaseKeyframeAnimation baseKeyframeAnimation1) {
        Path path0 = (Path)baseKeyframeAnimation0.getValue();
        this.path.set(path0);
        this.path.transform(matrix0);
        canvas0.drawPath(this.path, this.dstOutPaint);
    }

    private void buildParentLayerListIfNeeded() {
        if(this.parentLayers == null) {
            if(this.parentLayer == null) {
                this.parentLayers = Collections.EMPTY_LIST;
                return;
            }
            this.parentLayers = new ArrayList();
            for(BaseLayer baseLayer0 = this.parentLayer; baseLayer0 != null; baseLayer0 = baseLayer0.parentLayer) {
                this.parentLayers.add(baseLayer0);
            }
        }
    }

    private void clearCanvas(Canvas canvas0) {
        L.beginSection("Layer#clearLayer");
        canvas0.drawRect(this.rect.left - 1.0f, this.rect.top - 1.0f, this.rect.right + 1.0f, this.rect.bottom + 1.0f, this.clearPaint);
        L.endSection("Layer#clearLayer");
    }

    @Override  // com.airbnb.lottie.animation.content.DrawingContent
    public void draw(Canvas canvas0, Matrix matrix0, int v) {
        L.beginSection(this.drawTraceName);
        if(this.visible && !this.layerModel.isHidden()) {
            this.buildParentLayerListIfNeeded();
            L.beginSection("Layer#parentMatrix");
            this.matrix.reset();
            this.matrix.set(matrix0);
            for(int v1 = this.parentLayers.size() - 1; v1 >= 0; --v1) {
                Matrix matrix1 = ((BaseLayer)this.parentLayers.get(v1)).transform.getMatrix();
                this.matrix.preConcat(matrix1);
            }
            L.endSection("Layer#parentMatrix");
            int v2 = (int)(((float)v) / 255.0f * ((float)(this.transform.getOpacity() == null ? 100 : ((int)(((Integer)this.transform.getOpacity().getValue()))))) / 100.0f * 255.0f);
            if(!this.hasMatteOnThisLayer() && !this.hasMasksOnThisLayer()) {
                Matrix matrix2 = this.transform.getMatrix();
                this.matrix.preConcat(matrix2);
                L.beginSection("Layer#drawLayer");
                this.drawLayer(canvas0, this.matrix, v2);
                L.endSection("Layer#drawLayer");
                this.recordRenderTime(L.endSection(this.drawTraceName));
                return;
            }
            L.beginSection("Layer#computeBounds");
            this.getBounds(this.rect, this.matrix, false);
            this.intersectBoundsWithMatte(this.rect, matrix0);
            Matrix matrix3 = this.transform.getMatrix();
            this.matrix.preConcat(matrix3);
            this.intersectBoundsWithMask(this.rect, this.matrix);
            float f = (float)canvas0.getWidth();
            float f1 = (float)canvas0.getHeight();
            if(!this.rect.intersect(0.0f, 0.0f, f, f1)) {
                this.rect.set(0.0f, 0.0f, 0.0f, 0.0f);
            }
            L.endSection("Layer#computeBounds");
            if(!this.rect.isEmpty()) {
                L.beginSection("Layer#saveLayer");
                Utils.saveLayerCompat(canvas0, this.rect, this.contentPaint);
                L.endSection("Layer#saveLayer");
                this.clearCanvas(canvas0);
                L.beginSection("Layer#drawLayer");
                this.drawLayer(canvas0, this.matrix, v2);
                L.endSection("Layer#drawLayer");
                if(this.hasMasksOnThisLayer()) {
                    this.applyMasks(canvas0, this.matrix);
                }
                if(this.hasMatteOnThisLayer()) {
                    L.beginSection("Layer#drawMatte");
                    L.beginSection("Layer#saveLayer");
                    Utils.saveLayerCompat(canvas0, this.rect, this.mattePaint, 19);
                    L.endSection("Layer#saveLayer");
                    this.clearCanvas(canvas0);
                    this.matteLayer.draw(canvas0, matrix0, v2);
                    L.beginSection("Layer#restoreLayer");
                    canvas0.restore();
                    L.endSection("Layer#restoreLayer");
                    L.endSection("Layer#drawMatte");
                }
                L.beginSection("Layer#restoreLayer");
                canvas0.restore();
                L.endSection("Layer#restoreLayer");
            }
            this.recordRenderTime(L.endSection(this.drawTraceName));
            return;
        }
        L.endSection(this.drawTraceName);
    }

    abstract void drawLayer(Canvas arg1, Matrix arg2, int arg3);

    static BaseLayer forModel(Layer layer0, LottieDrawable lottieDrawable0, LottieComposition lottieComposition0) {
        switch(com.airbnb.lottie.model.layer.BaseLayer.2.$SwitchMap$com$airbnb$lottie$model$layer$Layer$LayerType[layer0.getLayerType().ordinal()]) {
            case 1: {
                return new ShapeLayer(lottieDrawable0, layer0);
            }
            case 2: {
                return new CompositionLayer(lottieDrawable0, layer0, lottieComposition0.getPrecomps(layer0.getRefId()), lottieComposition0);
            }
            case 3: {
                return new SolidLayer(lottieDrawable0, layer0);
            }
            case 4: {
                return new ImageLayer(lottieDrawable0, layer0);
            }
            case 5: {
                return new NullLayer(lottieDrawable0, layer0);
            }
            case 6: {
                return new TextLayer(lottieDrawable0, layer0);
            }
            default: {
                Logger.warning(("Unknown layer type " + layer0.getLayerType()));
                return null;
            }
        }
    }

    @Override  // com.airbnb.lottie.animation.content.DrawingContent
    public void getBounds(RectF rectF0, Matrix matrix0, boolean z) {
        this.rect.set(0.0f, 0.0f, 0.0f, 0.0f);
        this.buildParentLayerListIfNeeded();
        this.boundsMatrix.set(matrix0);
        if(z) {
            List list0 = this.parentLayers;
            if(list0 == null) {
                BaseLayer baseLayer0 = this.parentLayer;
                if(baseLayer0 != null) {
                    Matrix matrix2 = baseLayer0.transform.getMatrix();
                    this.boundsMatrix.preConcat(matrix2);
                }
            }
            else {
                for(int v = list0.size() - 1; v >= 0; --v) {
                    Matrix matrix1 = ((BaseLayer)this.parentLayers.get(v)).transform.getMatrix();
                    this.boundsMatrix.preConcat(matrix1);
                }
            }
        }
        Matrix matrix3 = this.transform.getMatrix();
        this.boundsMatrix.preConcat(matrix3);
    }

    Layer getLayerModel() {
        return this.layerModel;
    }

    @Override  // com.airbnb.lottie.animation.content.Content
    public String getName() {
        return this.layerModel.getName();
    }

    boolean hasMasksOnThisLayer() {
        return this.mask != null && !this.mask.getMaskAnimations().isEmpty();
    }

    boolean hasMatteOnThisLayer() {
        return this.matteLayer != null;
    }

    private void intersectBoundsWithMask(RectF rectF0, Matrix matrix0) {
        this.maskBoundsRect.set(0.0f, 0.0f, 0.0f, 0.0f);
        if(this.hasMasksOnThisLayer()) {
            int v = this.mask.getMasks().size();
            int v1 = 0;
        alab1:
            while(true) {
                if(v1 < v) {
                    Mask mask0 = (Mask)this.mask.getMasks().get(v1);
                    Path path0 = (Path)((BaseKeyframeAnimation)this.mask.getMaskAnimations().get(v1)).getValue();
                    this.path.set(path0);
                    this.path.transform(matrix0);
                    switch(com.airbnb.lottie.model.layer.BaseLayer.2.$SwitchMap$com$airbnb$lottie$model$content$Mask$MaskMode[mask0.getMaskMode().ordinal()]) {
                        case 1: {
                            break alab1;
                        }
                        case 2: 
                        case 3: {
                            if(mask0.isInverted()) {
                                break alab1;
                            }
                        label_12:
                            this.path.computeBounds(this.tempMaskBoundsRect, false);
                            if(v1 == 0) {
                                this.maskBoundsRect.set(this.tempMaskBoundsRect);
                            }
                            else {
                                this.maskBoundsRect.set(Math.min(this.maskBoundsRect.left, this.tempMaskBoundsRect.left), Math.min(this.maskBoundsRect.top, this.tempMaskBoundsRect.top), Math.max(this.maskBoundsRect.right, this.tempMaskBoundsRect.right), Math.max(this.maskBoundsRect.bottom, this.tempMaskBoundsRect.bottom));
                            }
                            ++v1;
                            continue;
                        }
                        default: {
                            goto label_12;
                        }
                    }
                }
                if(rectF0.intersect(this.maskBoundsRect)) {
                    break;
                }
                rectF0.set(0.0f, 0.0f, 0.0f, 0.0f);
                break;
            }
        }
    }

    private void intersectBoundsWithMatte(RectF rectF0, Matrix matrix0) {
        if(this.hasMatteOnThisLayer() && this.layerModel.getMatteType() != MatteType.INVERT) {
            this.matteBoundsRect.set(0.0f, 0.0f, 0.0f, 0.0f);
            this.matteLayer.getBounds(this.matteBoundsRect, matrix0, true);
            if(!rectF0.intersect(this.matteBoundsRect)) {
                rectF0.set(0.0f, 0.0f, 0.0f, 0.0f);
            }
        }
    }

    private void invalidateSelf() {
        this.lottieDrawable.invalidateSelf();
    }

    @Override  // com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation$AnimationListener
    public void onValueChanged() {
        this.invalidateSelf();
    }

    private void recordRenderTime(float f) {
        this.lottieDrawable.getComposition().getPerformanceTracker().recordRenderTime(this.layerModel.getName(), f);
    }

    public void removeAnimation(BaseKeyframeAnimation baseKeyframeAnimation0) {
        this.animations.remove(baseKeyframeAnimation0);
    }

    void resolveChildKeyPath(KeyPath keyPath0, int v, List list0, KeyPath keyPath1) {
    }

    @Override  // com.airbnb.lottie.model.KeyPathElement
    public void resolveKeyPath(KeyPath keyPath0, int v, List list0, KeyPath keyPath1) {
        if(keyPath0.matches(this.getName(), v)) {
            if(!"__container".equals(this.getName())) {
                keyPath1 = keyPath1.addKey(this.getName());
                if(keyPath0.fullyResolvesTo(this.getName(), v)) {
                    list0.add(keyPath1.resolve(this));
                }
            }
            if(keyPath0.propagateToChildren(this.getName(), v)) {
                this.resolveChildKeyPath(keyPath0, v + keyPath0.incrementDepthBy(this.getName(), v), list0, keyPath1);
            }
        }
    }

    @Override  // com.airbnb.lottie.animation.content.Content
    public void setContents(List list0, List list1) {
    }

    void setMatteLayer(BaseLayer baseLayer0) {
        this.matteLayer = baseLayer0;
    }

    void setParentLayer(BaseLayer baseLayer0) {
        this.parentLayer = baseLayer0;
    }

    void setProgress(float f) {
        this.transform.setProgress(f);
        if(this.mask != null) {
            for(int v1 = 0; v1 < this.mask.getMaskAnimations().size(); ++v1) {
                ((BaseKeyframeAnimation)this.mask.getMaskAnimations().get(v1)).setProgress(f);
            }
        }
        if(this.layerModel.getTimeStretch() != 0.0f) {
            f /= this.layerModel.getTimeStretch();
        }
        BaseLayer baseLayer0 = this.matteLayer;
        if(baseLayer0 != null) {
            this.matteLayer.setProgress(baseLayer0.layerModel.getTimeStretch() * f);
        }
        for(int v = 0; v < this.animations.size(); ++v) {
            ((BaseKeyframeAnimation)this.animations.get(v)).setProgress(f);
        }
    }

    private void setVisible(boolean z) {
        if(z != this.visible) {
            this.visible = z;
            this.invalidateSelf();
        }
    }

    private void setupInOutAnimations() {
        boolean z = true;
        if(!this.layerModel.getInOutKeyframes().isEmpty()) {
            FloatKeyframeAnimation floatKeyframeAnimation0 = new FloatKeyframeAnimation(this.layerModel.getInOutKeyframes());
            floatKeyframeAnimation0.setIsDiscrete();
            floatKeyframeAnimation0.addUpdateListener(new AnimationListener() {
                @Override  // com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation$AnimationListener
                public void onValueChanged() {
                    boolean z = floatKeyframeAnimation0.getFloatValue() == 1.0f;
                    BaseLayer.this.setVisible(z);
                }
            });
            if(((float)(((Float)floatKeyframeAnimation0.getValue()))) != 1.0f) {
                z = false;
            }
            this.setVisible(z);
            this.addAnimation(floatKeyframeAnimation0);
            return;
        }
        this.setVisible(true);
    }
}

