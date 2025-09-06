package com.airbnb.lottie.animation.content;

import android.graphics.Path;
import android.graphics.PointF;
import com.airbnb.lottie.LottieDrawable;
import com.airbnb.lottie.LottieProperty;
import com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation.AnimationListener;
import com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation;
import com.airbnb.lottie.model.KeyPath;
import com.airbnb.lottie.model.content.CircleShape;
import com.airbnb.lottie.model.content.ShapeTrimPath.Type;
import com.airbnb.lottie.model.layer.BaseLayer;
import com.airbnb.lottie.utils.MiscUtils;
import com.airbnb.lottie.value.LottieValueCallback;
import java.util.List;

public class EllipseContent implements KeyPathElementContent, PathContent, AnimationListener {
    private static final float ELLIPSE_CONTROL_POINT_PERCENTAGE = 0.55228f;
    private final CircleShape circleShape;
    private boolean isPathValid;
    private final LottieDrawable lottieDrawable;
    private final String name;
    private final Path path;
    private final BaseKeyframeAnimation positionAnimation;
    private final BaseKeyframeAnimation sizeAnimation;
    private CompoundTrimPathContent trimPaths;

    public EllipseContent(LottieDrawable lottieDrawable0, BaseLayer baseLayer0, CircleShape circleShape0) {
        this.path = new Path();
        this.trimPaths = new CompoundTrimPathContent();
        this.name = circleShape0.getName();
        this.lottieDrawable = lottieDrawable0;
        BaseKeyframeAnimation baseKeyframeAnimation0 = circleShape0.getSize().createAnimation();
        this.sizeAnimation = baseKeyframeAnimation0;
        BaseKeyframeAnimation baseKeyframeAnimation1 = circleShape0.getPosition().createAnimation();
        this.positionAnimation = baseKeyframeAnimation1;
        this.circleShape = circleShape0;
        baseLayer0.addAnimation(baseKeyframeAnimation0);
        baseLayer0.addAnimation(baseKeyframeAnimation1);
        baseKeyframeAnimation0.addUpdateListener(this);
        baseKeyframeAnimation1.addUpdateListener(this);
    }

    @Override  // com.airbnb.lottie.model.KeyPathElement
    public void addValueCallback(Object object0, LottieValueCallback lottieValueCallback0) {
        if(object0 == LottieProperty.ELLIPSE_SIZE) {
            this.sizeAnimation.setValueCallback(lottieValueCallback0);
            return;
        }
        if(object0 == LottieProperty.POSITION) {
            this.positionAnimation.setValueCallback(lottieValueCallback0);
        }
    }

    @Override  // com.airbnb.lottie.animation.content.Content
    public String getName() {
        return this.name;
    }

    @Override  // com.airbnb.lottie.animation.content.PathContent
    public Path getPath() {
        if(this.isPathValid) {
            return this.path;
        }
        this.path.reset();
        if(this.circleShape.isHidden()) {
            this.isPathValid = true;
            return this.path;
        }
        PointF pointF0 = (PointF)this.sizeAnimation.getValue();
        float f = pointF0.x / 2.0f;
        float f1 = pointF0.y / 2.0f;
        this.path.reset();
        if(this.circleShape.isReversed()) {
            this.path.moveTo(0.0f, -f1);
            float f2 = 0.0f - f * 0.55228f;
            float f3 = 0.0f - 0.55228f * f1;
            this.path.cubicTo(f2, -f1, -f, f3, -f, 0.0f);
            float f4 = 0.55228f * f1 + 0.0f;
            this.path.cubicTo(-f, f4, f2, f1, 0.0f, f1);
            float f5 = f * 0.55228f + 0.0f;
            this.path.cubicTo(f5, f1, f, f4, f, 0.0f);
            this.path.cubicTo(f, f3, f5, -f1, 0.0f, -f1);
        }
        else {
            this.path.moveTo(0.0f, -f1);
            float f6 = 0.0f - 0.55228f * f1;
            float f7 = f * 0.55228f + 0.0f;
            this.path.cubicTo(f7, -f1, f, f6, f, 0.0f);
            float f8 = 0.55228f * f1 + 0.0f;
            this.path.cubicTo(f, f8, f7, f1, 0.0f, f1);
            float f9 = 0.0f - f * 0.55228f;
            this.path.cubicTo(f9, f1, -f, f8, -f, 0.0f);
            this.path.cubicTo(-f, f6, f9, -f1, 0.0f, -f1);
        }
        PointF pointF1 = (PointF)this.positionAnimation.getValue();
        this.path.offset(pointF1.x, pointF1.y);
        this.path.close();
        this.trimPaths.apply(this.path);
        this.isPathValid = true;
        return this.path;
    }

    private void invalidate() {
        this.isPathValid = false;
        this.lottieDrawable.invalidateSelf();
    }

    @Override  // com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation$AnimationListener
    public void onValueChanged() {
        this.invalidate();
    }

    @Override  // com.airbnb.lottie.model.KeyPathElement
    public void resolveKeyPath(KeyPath keyPath0, int v, List list0, KeyPath keyPath1) {
        MiscUtils.resolveKeyPath(keyPath0, v, list0, keyPath1, this);
    }

    @Override  // com.airbnb.lottie.animation.content.Content
    public void setContents(List list0, List list1) {
        for(int v = 0; v < list0.size(); ++v) {
            Content content0 = (Content)list0.get(v);
            if(content0 instanceof TrimPathContent && ((TrimPathContent)content0).getType() == Type.SIMULTANEOUSLY) {
                this.trimPaths.addTrimPath(((TrimPathContent)content0));
                ((TrimPathContent)content0).addListener(this);
            }
        }
    }
}

