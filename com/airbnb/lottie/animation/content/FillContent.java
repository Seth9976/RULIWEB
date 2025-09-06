package com.airbnb.lottie.animation.content;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import com.airbnb.lottie.L;
import com.airbnb.lottie.LottieDrawable;
import com.airbnb.lottie.LottieProperty;
import com.airbnb.lottie.animation.LPaint;
import com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation.AnimationListener;
import com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation;
import com.airbnb.lottie.animation.keyframe.ColorKeyframeAnimation;
import com.airbnb.lottie.animation.keyframe.ValueCallbackKeyframeAnimation;
import com.airbnb.lottie.model.KeyPath;
import com.airbnb.lottie.model.content.ShapeFill;
import com.airbnb.lottie.model.layer.BaseLayer;
import com.airbnb.lottie.utils.MiscUtils;
import com.airbnb.lottie.value.LottieValueCallback;
import java.util.ArrayList;
import java.util.List;

public class FillContent implements DrawingContent, KeyPathElementContent, AnimationListener {
    private final BaseKeyframeAnimation colorAnimation;
    private BaseKeyframeAnimation colorFilterAnimation;
    private final boolean hidden;
    private final BaseLayer layer;
    private final LottieDrawable lottieDrawable;
    private final String name;
    private final BaseKeyframeAnimation opacityAnimation;
    private final Paint paint;
    private final Path path;
    private final List paths;

    public FillContent(LottieDrawable lottieDrawable0, BaseLayer baseLayer0, ShapeFill shapeFill0) {
        Path path0 = new Path();
        this.path = path0;
        this.paint = new LPaint(1);
        this.paths = new ArrayList();
        this.layer = baseLayer0;
        this.name = shapeFill0.getName();
        this.hidden = shapeFill0.isHidden();
        this.lottieDrawable = lottieDrawable0;
        if(shapeFill0.getColor() != null && shapeFill0.getOpacity() != null) {
            path0.setFillType(shapeFill0.getFillType());
            BaseKeyframeAnimation baseKeyframeAnimation0 = shapeFill0.getColor().createAnimation();
            this.colorAnimation = baseKeyframeAnimation0;
            baseKeyframeAnimation0.addUpdateListener(this);
            baseLayer0.addAnimation(baseKeyframeAnimation0);
            BaseKeyframeAnimation baseKeyframeAnimation1 = shapeFill0.getOpacity().createAnimation();
            this.opacityAnimation = baseKeyframeAnimation1;
            baseKeyframeAnimation1.addUpdateListener(this);
            baseLayer0.addAnimation(baseKeyframeAnimation1);
            return;
        }
        this.colorAnimation = null;
        this.opacityAnimation = null;
    }

    @Override  // com.airbnb.lottie.model.KeyPathElement
    public void addValueCallback(Object object0, LottieValueCallback lottieValueCallback0) {
        if(object0 == LottieProperty.COLOR) {
            this.colorAnimation.setValueCallback(lottieValueCallback0);
            return;
        }
        if(object0 == LottieProperty.OPACITY) {
            this.opacityAnimation.setValueCallback(lottieValueCallback0);
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
            this.layer.addAnimation(this.colorFilterAnimation);
        }
    }

    @Override  // com.airbnb.lottie.animation.content.DrawingContent
    public void draw(Canvas canvas0, Matrix matrix0, int v) {
        if(this.hidden) {
            return;
        }
        L.beginSection("FillContent#draw");
        int v1 = ((ColorKeyframeAnimation)this.colorAnimation).getIntValue();
        this.paint.setColor(v1);
        int v3 = MiscUtils.clamp(((int)(((float)v) / 255.0f * ((float)(((int)(((Integer)this.opacityAnimation.getValue()))))) / 100.0f * 255.0f)), 0, 0xFF);
        this.paint.setAlpha(v3);
        BaseKeyframeAnimation baseKeyframeAnimation0 = this.colorFilterAnimation;
        if(baseKeyframeAnimation0 != null) {
            ColorFilter colorFilter0 = (ColorFilter)baseKeyframeAnimation0.getValue();
            this.paint.setColorFilter(colorFilter0);
        }
        this.path.reset();
        for(int v2 = 0; v2 < this.paths.size(); ++v2) {
            Path path0 = ((PathContent)this.paths.get(v2)).getPath();
            this.path.addPath(path0, matrix0);
        }
        canvas0.drawPath(this.path, this.paint);
        L.endSection("FillContent#draw");
    }

    @Override  // com.airbnb.lottie.animation.content.DrawingContent
    public void getBounds(RectF rectF0, Matrix matrix0, boolean z) {
        this.path.reset();
        for(int v = 0; v < this.paths.size(); ++v) {
            Path path0 = ((PathContent)this.paths.get(v)).getPath();
            this.path.addPath(path0, matrix0);
        }
        this.path.computeBounds(rectF0, false);
        rectF0.set(rectF0.left - 1.0f, rectF0.top - 1.0f, rectF0.right + 1.0f, rectF0.bottom + 1.0f);
    }

    @Override  // com.airbnb.lottie.animation.content.Content
    public String getName() {
        return this.name;
    }

    @Override  // com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation$AnimationListener
    public void onValueChanged() {
        this.lottieDrawable.invalidateSelf();
    }

    @Override  // com.airbnb.lottie.model.KeyPathElement
    public void resolveKeyPath(KeyPath keyPath0, int v, List list0, KeyPath keyPath1) {
        MiscUtils.resolveKeyPath(keyPath0, v, list0, keyPath1, this);
    }

    @Override  // com.airbnb.lottie.animation.content.Content
    public void setContents(List list0, List list1) {
        for(int v = 0; v < list1.size(); ++v) {
            Content content0 = (Content)list1.get(v);
            if(content0 instanceof PathContent) {
                this.paths.add(((PathContent)content0));
            }
        }
    }
}

