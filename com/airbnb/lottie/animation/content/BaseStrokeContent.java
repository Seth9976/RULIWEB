package com.airbnb.lottie.animation.content;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.DashPathEffect;
import android.graphics.Matrix;
import android.graphics.Paint.Cap;
import android.graphics.Paint.Join;
import android.graphics.Paint.Style;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.RectF;
import com.airbnb.lottie.L;
import com.airbnb.lottie.LottieDrawable;
import com.airbnb.lottie.LottieProperty;
import com.airbnb.lottie.animation.LPaint;
import com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation.AnimationListener;
import com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation;
import com.airbnb.lottie.animation.keyframe.FloatKeyframeAnimation;
import com.airbnb.lottie.animation.keyframe.IntegerKeyframeAnimation;
import com.airbnb.lottie.animation.keyframe.ValueCallbackKeyframeAnimation;
import com.airbnb.lottie.model.KeyPath;
import com.airbnb.lottie.model.animatable.AnimatableFloatValue;
import com.airbnb.lottie.model.animatable.AnimatableIntegerValue;
import com.airbnb.lottie.model.content.ShapeTrimPath.Type;
import com.airbnb.lottie.model.layer.BaseLayer;
import com.airbnb.lottie.utils.MiscUtils;
import com.airbnb.lottie.utils.Utils;
import com.airbnb.lottie.value.LottieValueCallback;
import java.util.ArrayList;
import java.util.List;

public abstract class BaseStrokeContent implements DrawingContent, KeyPathElementContent, AnimationListener {
    static final class PathGroup {
        private final List paths;
        private final TrimPathContent trimPath;

        private PathGroup(TrimPathContent trimPathContent0) {
            this.paths = new ArrayList();
            this.trimPath = trimPathContent0;
        }

        PathGroup(TrimPathContent trimPathContent0, com.airbnb.lottie.animation.content.BaseStrokeContent.1 baseStrokeContent$10) {
            this(trimPathContent0);
        }
    }

    private BaseKeyframeAnimation colorFilterAnimation;
    private final List dashPatternAnimations;
    private final BaseKeyframeAnimation dashPatternOffsetAnimation;
    private final float[] dashPatternValues;
    protected final BaseLayer layer;
    private final LottieDrawable lottieDrawable;
    private final BaseKeyframeAnimation opacityAnimation;
    final Paint paint;
    private final Path path;
    private final List pathGroups;
    private final PathMeasure pm;
    private final RectF rect;
    private final Path trimPathPath;
    private final BaseKeyframeAnimation widthAnimation;

    BaseStrokeContent(LottieDrawable lottieDrawable0, BaseLayer baseLayer0, Paint.Cap paint$Cap0, Paint.Join paint$Join0, float f, AnimatableIntegerValue animatableIntegerValue0, AnimatableFloatValue animatableFloatValue0, List list0, AnimatableFloatValue animatableFloatValue1) {
        this.pm = new PathMeasure();
        this.path = new Path();
        this.trimPathPath = new Path();
        this.rect = new RectF();
        this.pathGroups = new ArrayList();
        LPaint lPaint0 = new LPaint(1);
        this.paint = lPaint0;
        this.lottieDrawable = lottieDrawable0;
        this.layer = baseLayer0;
        lPaint0.setStyle(Paint.Style.STROKE);
        lPaint0.setStrokeCap(paint$Cap0);
        lPaint0.setStrokeJoin(paint$Join0);
        lPaint0.setStrokeMiter(f);
        this.opacityAnimation = animatableIntegerValue0.createAnimation();
        this.widthAnimation = animatableFloatValue0.createAnimation();
        this.dashPatternOffsetAnimation = animatableFloatValue1 == null ? null : animatableFloatValue1.createAnimation();
        this.dashPatternAnimations = new ArrayList(list0.size());
        this.dashPatternValues = new float[list0.size()];
        for(int v1 = 0; v1 < list0.size(); ++v1) {
            BaseKeyframeAnimation baseKeyframeAnimation0 = ((AnimatableFloatValue)list0.get(v1)).createAnimation();
            this.dashPatternAnimations.add(baseKeyframeAnimation0);
        }
        baseLayer0.addAnimation(this.opacityAnimation);
        baseLayer0.addAnimation(this.widthAnimation);
        for(int v2 = 0; v2 < this.dashPatternAnimations.size(); ++v2) {
            baseLayer0.addAnimation(((BaseKeyframeAnimation)this.dashPatternAnimations.get(v2)));
        }
        BaseKeyframeAnimation baseKeyframeAnimation1 = this.dashPatternOffsetAnimation;
        if(baseKeyframeAnimation1 != null) {
            baseLayer0.addAnimation(baseKeyframeAnimation1);
        }
        this.opacityAnimation.addUpdateListener(this);
        this.widthAnimation.addUpdateListener(this);
        for(int v = 0; v < list0.size(); ++v) {
            ((BaseKeyframeAnimation)this.dashPatternAnimations.get(v)).addUpdateListener(this);
        }
        BaseKeyframeAnimation baseKeyframeAnimation2 = this.dashPatternOffsetAnimation;
        if(baseKeyframeAnimation2 != null) {
            baseKeyframeAnimation2.addUpdateListener(this);
        }
    }

    @Override  // com.airbnb.lottie.model.KeyPathElement
    public void addValueCallback(Object object0, LottieValueCallback lottieValueCallback0) {
        if(object0 == LottieProperty.OPACITY) {
            this.opacityAnimation.setValueCallback(lottieValueCallback0);
            return;
        }
        if(object0 == LottieProperty.STROKE_WIDTH) {
            this.widthAnimation.setValueCallback(lottieValueCallback0);
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

    private void applyDashPatternIfNeeded(Matrix matrix0) {
        L.beginSection("StrokeContent#applyDashPattern");
        if(this.dashPatternAnimations.isEmpty()) {
            L.endSection("StrokeContent#applyDashPattern");
            return;
        }
        float f = Utils.getScale(matrix0);
        for(int v = 0; v < this.dashPatternAnimations.size(); ++v) {
            this.dashPatternValues[v] = (float)(((Float)((BaseKeyframeAnimation)this.dashPatternAnimations.get(v)).getValue()));
            if(v % 2 == 0) {
                float[] arr_f = this.dashPatternValues;
                if(arr_f[v] < 1.0f) {
                    arr_f[v] = 1.0f;
                }
            }
            else {
                float[] arr_f1 = this.dashPatternValues;
                if(arr_f1[v] < 0.1f) {
                    arr_f1[v] = 0.1f;
                }
            }
            this.dashPatternValues[v] *= f;
        }
        float f1 = this.dashPatternOffsetAnimation == null ? 0.0f : f * ((float)(((Float)this.dashPatternOffsetAnimation.getValue())));
        DashPathEffect dashPathEffect0 = new DashPathEffect(this.dashPatternValues, f1);
        this.paint.setPathEffect(dashPathEffect0);
        L.endSection("StrokeContent#applyDashPattern");
    }

    private void applyTrimPath(Canvas canvas0, PathGroup baseStrokeContent$PathGroup0, Matrix matrix0) {
        L.beginSection("StrokeContent#applyTrimPath");
        if(baseStrokeContent$PathGroup0.trimPath == null) {
            L.endSection("StrokeContent#applyTrimPath");
            return;
        }
        this.path.reset();
        for(int v = baseStrokeContent$PathGroup0.paths.size() - 1; v >= 0; --v) {
            Path path0 = ((PathContent)baseStrokeContent$PathGroup0.paths.get(v)).getPath();
            this.path.addPath(path0, matrix0);
        }
        this.pm.setPath(this.path, false);
        float f;
        for(f = this.pm.getLength(); this.pm.nextContour(); f += this.pm.getLength()) {
        }
        float f1 = ((float)(((Float)baseStrokeContent$PathGroup0.trimPath.getOffset().getValue()))) * f / 360.0f;
        float f2 = ((float)(((Float)baseStrokeContent$PathGroup0.trimPath.getStart().getValue()))) * f / 100.0f + f1;
        float f3 = ((float)(((Float)baseStrokeContent$PathGroup0.trimPath.getEnd().getValue()))) * f / 100.0f + f1;
        int v1 = baseStrokeContent$PathGroup0.paths.size() - 1;
        float f4 = 0.0f;
        while(v1 >= 0) {
            Path path1 = ((PathContent)baseStrokeContent$PathGroup0.paths.get(v1)).getPath();
            this.trimPathPath.set(path1);
            this.trimPathPath.transform(matrix0);
            this.pm.setPath(this.trimPathPath, false);
            float f5 = this.pm.getLength();
            float f6 = 1.0f;
            if(f3 > f) {
                float f7 = f3 - f;
                if(f7 < f4 + f5 && f4 < f7) {
                    Utils.applyTrimPathIfNeeded(this.trimPathPath, (f2 > f ? (f2 - f) / f5 : 0.0f), Math.min(f7 / f5, 1.0f), 0.0f);
                    canvas0.drawPath(this.trimPathPath, this.paint);
                }
            }
            else {
                float f8 = f4 + f5;
                if(f8 >= f2 && f4 <= f3) {
                    if(f8 > f3 || f2 >= f4) {
                        if(f3 <= f8) {
                            f6 = (f3 - f4) / f5;
                        }
                        Utils.applyTrimPathIfNeeded(this.trimPathPath, (f2 < f4 ? 0.0f : (f2 - f4) / f5), f6, 0.0f);
                    }
                    canvas0.drawPath(this.trimPathPath, this.paint);
                }
            }
            f4 += f5;
            --v1;
        }
        L.endSection("StrokeContent#applyTrimPath");
    }

    @Override  // com.airbnb.lottie.animation.content.DrawingContent
    public void draw(Canvas canvas0, Matrix matrix0, int v) {
        L.beginSection("StrokeContent#draw");
        if(Utils.hasZeroScaleAxis(matrix0)) {
            L.endSection("StrokeContent#draw");
            return;
        }
        int v2 = MiscUtils.clamp(((int)(((float)v) / 255.0f * ((float)((IntegerKeyframeAnimation)this.opacityAnimation).getIntValue()) / 100.0f * 255.0f)), 0, 0xFF);
        this.paint.setAlpha(v2);
        float f = ((FloatKeyframeAnimation)this.widthAnimation).getFloatValue();
        float f1 = Utils.getScale(matrix0);
        this.paint.setStrokeWidth(f * f1);
        if(this.paint.getStrokeWidth() <= 0.0f) {
            L.endSection("StrokeContent#draw");
            return;
        }
        this.applyDashPatternIfNeeded(matrix0);
        BaseKeyframeAnimation baseKeyframeAnimation0 = this.colorFilterAnimation;
        if(baseKeyframeAnimation0 != null) {
            ColorFilter colorFilter0 = (ColorFilter)baseKeyframeAnimation0.getValue();
            this.paint.setColorFilter(colorFilter0);
        }
        for(int v1 = 0; v1 < this.pathGroups.size(); ++v1) {
            PathGroup baseStrokeContent$PathGroup0 = (PathGroup)this.pathGroups.get(v1);
            if(PathGroup.access$200(baseStrokeContent$PathGroup0) == null) {
                L.beginSection("StrokeContent#buildPath");
                this.path.reset();
                for(int v3 = PathGroup.access$100(baseStrokeContent$PathGroup0).size() - 1; v3 >= 0; --v3) {
                    Path path0 = ((PathContent)PathGroup.access$100(baseStrokeContent$PathGroup0).get(v3)).getPath();
                    this.path.addPath(path0, matrix0);
                }
                L.endSection("StrokeContent#buildPath");
                L.beginSection("StrokeContent#drawPath");
                canvas0.drawPath(this.path, this.paint);
                L.endSection("StrokeContent#drawPath");
            }
            else {
                this.applyTrimPath(canvas0, baseStrokeContent$PathGroup0, matrix0);
            }
        }
        L.endSection("StrokeContent#draw");
    }

    @Override  // com.airbnb.lottie.animation.content.DrawingContent
    public void getBounds(RectF rectF0, Matrix matrix0, boolean z) {
        L.beginSection("StrokeContent#getBounds");
        this.path.reset();
        for(int v = 0; v < this.pathGroups.size(); ++v) {
            PathGroup baseStrokeContent$PathGroup0 = (PathGroup)this.pathGroups.get(v);
            for(int v1 = 0; v1 < baseStrokeContent$PathGroup0.paths.size(); ++v1) {
                Path path0 = ((PathContent)baseStrokeContent$PathGroup0.paths.get(v1)).getPath();
                this.path.addPath(path0, matrix0);
            }
        }
        this.path.computeBounds(this.rect, false);
        float f = ((FloatKeyframeAnimation)this.widthAnimation).getFloatValue();
        this.rect.set(this.rect.left - f / 2.0f, this.rect.top - f / 2.0f, this.rect.right + f / 2.0f, this.rect.bottom + f / 2.0f);
        rectF0.set(this.rect);
        rectF0.set(rectF0.left - 1.0f, rectF0.top - 1.0f, rectF0.right + 1.0f, rectF0.bottom + 1.0f);
        L.endSection("StrokeContent#getBounds");
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
        int v = list0.size() - 1;
        TrimPathContent trimPathContent0 = null;
        while(v >= 0) {
            Content content0 = (Content)list0.get(v);
            if(content0 instanceof TrimPathContent && ((TrimPathContent)content0).getType() == Type.INDIVIDUALLY) {
                trimPathContent0 = (TrimPathContent)content0;
            }
            --v;
        }
        if(trimPathContent0 != null) {
            trimPathContent0.addListener(this);
        }
        int v1 = list1.size() - 1;
        PathGroup baseStrokeContent$PathGroup0 = null;
        while(v1 >= 0) {
            Content content1 = (Content)list1.get(v1);
            if(content1 instanceof TrimPathContent && ((TrimPathContent)content1).getType() == Type.INDIVIDUALLY) {
                if(baseStrokeContent$PathGroup0 != null) {
                    this.pathGroups.add(baseStrokeContent$PathGroup0);
                }
                baseStrokeContent$PathGroup0 = new PathGroup(((TrimPathContent)content1), null);
                ((TrimPathContent)content1).addListener(this);
            }
            else if(content1 instanceof PathContent) {
                if(baseStrokeContent$PathGroup0 == null) {
                    baseStrokeContent$PathGroup0 = new PathGroup(trimPathContent0, null);
                }
                PathGroup.access$100(baseStrokeContent$PathGroup0).add(((PathContent)content1));
            }
            --v1;
        }
        if(baseStrokeContent$PathGroup0 != null) {
            this.pathGroups.add(baseStrokeContent$PathGroup0);
        }
    }

    class com.airbnb.lottie.animation.content.BaseStrokeContent.1 {
    }

}

