package com.airbnb.lottie.animation.content;

import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.RectF;
import com.airbnb.lottie.LottieDrawable;
import com.airbnb.lottie.LottieProperty;
import com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation.AnimationListener;
import com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation;
import com.airbnb.lottie.animation.keyframe.FloatKeyframeAnimation;
import com.airbnb.lottie.model.KeyPath;
import com.airbnb.lottie.model.content.RectangleShape;
import com.airbnb.lottie.model.content.ShapeTrimPath.Type;
import com.airbnb.lottie.model.layer.BaseLayer;
import com.airbnb.lottie.utils.MiscUtils;
import com.airbnb.lottie.value.LottieValueCallback;
import java.util.List;

public class RectangleContent implements KeyPathElementContent, PathContent, AnimationListener {
    private final BaseKeyframeAnimation cornerRadiusAnimation;
    private final boolean hidden;
    private boolean isPathValid;
    private final LottieDrawable lottieDrawable;
    private final String name;
    private final Path path;
    private final BaseKeyframeAnimation positionAnimation;
    private final RectF rect;
    private final BaseKeyframeAnimation sizeAnimation;
    private CompoundTrimPathContent trimPaths;

    public RectangleContent(LottieDrawable lottieDrawable0, BaseLayer baseLayer0, RectangleShape rectangleShape0) {
        this.path = new Path();
        this.rect = new RectF();
        this.trimPaths = new CompoundTrimPathContent();
        this.name = rectangleShape0.getName();
        this.hidden = rectangleShape0.isHidden();
        this.lottieDrawable = lottieDrawable0;
        BaseKeyframeAnimation baseKeyframeAnimation0 = rectangleShape0.getPosition().createAnimation();
        this.positionAnimation = baseKeyframeAnimation0;
        BaseKeyframeAnimation baseKeyframeAnimation1 = rectangleShape0.getSize().createAnimation();
        this.sizeAnimation = baseKeyframeAnimation1;
        BaseKeyframeAnimation baseKeyframeAnimation2 = rectangleShape0.getCornerRadius().createAnimation();
        this.cornerRadiusAnimation = baseKeyframeAnimation2;
        baseLayer0.addAnimation(baseKeyframeAnimation0);
        baseLayer0.addAnimation(baseKeyframeAnimation1);
        baseLayer0.addAnimation(baseKeyframeAnimation2);
        baseKeyframeAnimation0.addUpdateListener(this);
        baseKeyframeAnimation1.addUpdateListener(this);
        baseKeyframeAnimation2.addUpdateListener(this);
    }

    @Override  // com.airbnb.lottie.model.KeyPathElement
    public void addValueCallback(Object object0, LottieValueCallback lottieValueCallback0) {
        if(object0 == LottieProperty.RECTANGLE_SIZE) {
            this.sizeAnimation.setValueCallback(lottieValueCallback0);
            return;
        }
        if(object0 == LottieProperty.POSITION) {
            this.positionAnimation.setValueCallback(lottieValueCallback0);
            return;
        }
        if(object0 == LottieProperty.CORNER_RADIUS) {
            this.cornerRadiusAnimation.setValueCallback(lottieValueCallback0);
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
        if(this.hidden) {
            this.isPathValid = true;
            return this.path;
        }
        PointF pointF0 = (PointF)this.sizeAnimation.getValue();
        float f = pointF0.x / 2.0f;
        float f1 = pointF0.y / 2.0f;
        float f2 = this.cornerRadiusAnimation == null ? 0.0f : ((FloatKeyframeAnimation)this.cornerRadiusAnimation).getFloatValue();
        float f3 = Math.min(f, f1);
        if(f2 > f3) {
            f2 = f3;
        }
        PointF pointF1 = (PointF)this.positionAnimation.getValue();
        this.path.moveTo(pointF1.x + f, pointF1.y - f1 + f2);
        this.path.lineTo(pointF1.x + f, pointF1.y + f1 - f2);
        int v = Float.compare(f2, 0.0f);
        if(v > 0) {
            this.rect.set(pointF1.x + f - f2 * 2.0f, pointF1.y + f1 - f2 * 2.0f, pointF1.x + f, pointF1.y + f1);
            this.path.arcTo(this.rect, 0.0f, 90.0f, false);
        }
        this.path.lineTo(pointF1.x - f + f2, pointF1.y + f1);
        if(v > 0) {
            this.rect.set(pointF1.x - f, pointF1.y + f1 - f2 * 2.0f, pointF1.x - f + f2 * 2.0f, pointF1.y + f1);
            this.path.arcTo(this.rect, 90.0f, 90.0f, false);
        }
        this.path.lineTo(pointF1.x - f, pointF1.y - f1 + f2);
        if(v > 0) {
            this.rect.set(pointF1.x - f, pointF1.y - f1, pointF1.x - f + f2 * 2.0f, pointF1.y - f1 + f2 * 2.0f);
            this.path.arcTo(this.rect, 180.0f, 90.0f, false);
        }
        this.path.lineTo(pointF1.x + f - f2, pointF1.y - f1);
        if(v > 0) {
            this.rect.set(pointF1.x + f - f2 * 2.0f, pointF1.y - f1, pointF1.x + f, pointF1.y - f1 + f2 * 2.0f);
            this.path.arcTo(this.rect, 270.0f, 90.0f, false);
        }
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

