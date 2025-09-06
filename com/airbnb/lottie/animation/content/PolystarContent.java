package com.airbnb.lottie.animation.content;

import android.graphics.Path;
import android.graphics.PointF;
import com.airbnb.lottie.LottieDrawable;
import com.airbnb.lottie.LottieProperty;
import com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation.AnimationListener;
import com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation;
import com.airbnb.lottie.model.KeyPath;
import com.airbnb.lottie.model.content.PolystarShape.Type;
import com.airbnb.lottie.model.content.PolystarShape;
import com.airbnb.lottie.model.layer.BaseLayer;
import com.airbnb.lottie.utils.MiscUtils;
import com.airbnb.lottie.value.LottieValueCallback;
import java.util.List;

public class PolystarContent implements KeyPathElementContent, PathContent, AnimationListener {
    private static final float POLYGON_MAGIC_NUMBER = 0.25f;
    private static final float POLYSTAR_MAGIC_NUMBER = 0.47829f;
    private final boolean hidden;
    private final BaseKeyframeAnimation innerRadiusAnimation;
    private final BaseKeyframeAnimation innerRoundednessAnimation;
    private boolean isPathValid;
    private final LottieDrawable lottieDrawable;
    private final String name;
    private final BaseKeyframeAnimation outerRadiusAnimation;
    private final BaseKeyframeAnimation outerRoundednessAnimation;
    private final Path path;
    private final BaseKeyframeAnimation pointsAnimation;
    private final BaseKeyframeAnimation positionAnimation;
    private final BaseKeyframeAnimation rotationAnimation;
    private CompoundTrimPathContent trimPaths;
    private final Type type;

    public PolystarContent(LottieDrawable lottieDrawable0, BaseLayer baseLayer0, PolystarShape polystarShape0) {
        this.path = new Path();
        this.trimPaths = new CompoundTrimPathContent();
        this.lottieDrawable = lottieDrawable0;
        this.name = polystarShape0.getName();
        Type polystarShape$Type0 = polystarShape0.getType();
        this.type = polystarShape$Type0;
        this.hidden = polystarShape0.isHidden();
        BaseKeyframeAnimation baseKeyframeAnimation0 = polystarShape0.getPoints().createAnimation();
        this.pointsAnimation = baseKeyframeAnimation0;
        BaseKeyframeAnimation baseKeyframeAnimation1 = polystarShape0.getPosition().createAnimation();
        this.positionAnimation = baseKeyframeAnimation1;
        BaseKeyframeAnimation baseKeyframeAnimation2 = polystarShape0.getRotation().createAnimation();
        this.rotationAnimation = baseKeyframeAnimation2;
        BaseKeyframeAnimation baseKeyframeAnimation3 = polystarShape0.getOuterRadius().createAnimation();
        this.outerRadiusAnimation = baseKeyframeAnimation3;
        BaseKeyframeAnimation baseKeyframeAnimation4 = polystarShape0.getOuterRoundedness().createAnimation();
        this.outerRoundednessAnimation = baseKeyframeAnimation4;
        if(polystarShape$Type0 == Type.STAR) {
            this.innerRadiusAnimation = polystarShape0.getInnerRadius().createAnimation();
            this.innerRoundednessAnimation = polystarShape0.getInnerRoundedness().createAnimation();
        }
        else {
            this.innerRadiusAnimation = null;
            this.innerRoundednessAnimation = null;
        }
        baseLayer0.addAnimation(baseKeyframeAnimation0);
        baseLayer0.addAnimation(baseKeyframeAnimation1);
        baseLayer0.addAnimation(baseKeyframeAnimation2);
        baseLayer0.addAnimation(baseKeyframeAnimation3);
        baseLayer0.addAnimation(baseKeyframeAnimation4);
        if(polystarShape$Type0 == Type.STAR) {
            baseLayer0.addAnimation(this.innerRadiusAnimation);
            baseLayer0.addAnimation(this.innerRoundednessAnimation);
        }
        baseKeyframeAnimation0.addUpdateListener(this);
        baseKeyframeAnimation1.addUpdateListener(this);
        baseKeyframeAnimation2.addUpdateListener(this);
        baseKeyframeAnimation3.addUpdateListener(this);
        baseKeyframeAnimation4.addUpdateListener(this);
        if(polystarShape$Type0 == Type.STAR) {
            this.innerRadiusAnimation.addUpdateListener(this);
            this.innerRoundednessAnimation.addUpdateListener(this);
        }
    }

    @Override  // com.airbnb.lottie.model.KeyPathElement
    public void addValueCallback(Object object0, LottieValueCallback lottieValueCallback0) {
        if(object0 == LottieProperty.POLYSTAR_POINTS) {
            this.pointsAnimation.setValueCallback(lottieValueCallback0);
            return;
        }
        if(object0 == LottieProperty.POLYSTAR_ROTATION) {
            this.rotationAnimation.setValueCallback(lottieValueCallback0);
            return;
        }
        if(object0 == LottieProperty.POSITION) {
            this.positionAnimation.setValueCallback(lottieValueCallback0);
            return;
        }
        if(object0 == LottieProperty.POLYSTAR_INNER_RADIUS) {
            BaseKeyframeAnimation baseKeyframeAnimation0 = this.innerRadiusAnimation;
            if(baseKeyframeAnimation0 != null) {
                baseKeyframeAnimation0.setValueCallback(lottieValueCallback0);
                return;
            }
        }
        if(object0 == LottieProperty.POLYSTAR_OUTER_RADIUS) {
            this.outerRadiusAnimation.setValueCallback(lottieValueCallback0);
            return;
        }
        if(object0 == LottieProperty.POLYSTAR_INNER_ROUNDEDNESS) {
            BaseKeyframeAnimation baseKeyframeAnimation1 = this.innerRoundednessAnimation;
            if(baseKeyframeAnimation1 != null) {
                baseKeyframeAnimation1.setValueCallback(lottieValueCallback0);
                return;
            }
        }
        if(object0 == LottieProperty.POLYSTAR_OUTER_ROUNDEDNESS) {
            this.outerRoundednessAnimation.setValueCallback(lottieValueCallback0);
        }
    }

    private void createPolygonPath() {
        int v = (int)Math.floor(((float)(((Float)this.pointsAnimation.getValue()))));
        double f = Math.toRadians((this.rotationAnimation == null ? 0.0 : ((double)(((float)(((Float)this.rotationAnimation.getValue())))))) - 90.0);
        float f1 = (float)(((Float)this.outerRoundednessAnimation.getValue()));
        float f2 = (float)(((Float)this.outerRadiusAnimation.getValue()));
        float f3 = (float)(Math.cos(f) * ((double)f2));
        float f4 = (float)(Math.sin(f) * ((double)f2));
        this.path.moveTo(f3, f4);
        double f5 = (double)(((float)(6.283185 / ((double)v))));
        double f6 = f + f5;
        double f7 = Math.ceil(v);
        for(int v1 = 0; ((double)v1) < f7; ++v1) {
            float f8 = (float)(Math.cos(f6) * ((double)f2));
            float f9 = (float)(((double)f2) * Math.sin(f6));
            if(f1 / 100.0f == 0.0f) {
                f4 = f9;
                f3 = f8;
                this.path.lineTo(f3, f4);
            }
            else {
                double f10 = (double)(((float)(Math.atan2(f4, f3) - 1.570796)));
                double f11 = (double)(((float)(Math.atan2(f9, f8) - 1.570796)));
                float f12 = f2 * (f1 / 100.0f) * 0.25f;
                this.path.cubicTo(f3 - f12 * ((float)Math.cos(f10)), f4 - f12 * ((float)Math.sin(f10)), f8 + ((float)Math.cos(f11)) * f12, f9 + f12 * ((float)Math.sin(f11)), f8, f9);
                f3 = f8;
                f4 = f9;
            }
            f6 += f5;
        }
        PointF pointF0 = (PointF)this.positionAnimation.getValue();
        this.path.offset(pointF0.x, pointF0.y);
        this.path.close();
    }

    private void createStarPath() {
        double f26;
        float f25;
        float f24;
        float f23;
        float f20;
        double f19;
        float f18;
        int v5;
        float f13;
        float f12;
        double f11;
        float f10;
        int v1;
        float f8;
        float f = (float)(((Float)this.pointsAnimation.getValue()));
        double f1 = Math.toRadians((this.rotationAnimation == null ? 0.0 : ((double)(((float)(((Float)this.rotationAnimation.getValue())))))) - 90.0);
        float f2 = (float)(6.283185 / ((double)f));
        float f3 = f - ((float)(((int)f)));
        int v = Float.compare(f3, 0.0f);
        if(v != 0) {
            f1 += (double)((1.0f - f3) * (f2 / 2.0f));
        }
        float f4 = (float)(((Float)this.outerRadiusAnimation.getValue()));
        float f5 = (float)(((Float)this.innerRadiusAnimation.getValue()));
        float f6 = this.innerRoundednessAnimation == null ? 0.0f : ((float)(((Float)this.innerRoundednessAnimation.getValue()))) / 100.0f;
        float f7 = this.outerRoundednessAnimation == null ? 0.0f : ((float)(((Float)this.outerRoundednessAnimation.getValue()))) / 100.0f;
        if(v == 0) {
            v1 = 0;
            f12 = (float)(Math.cos(f1) * ((double)f4));
            f10 = (float)(((double)f4) * Math.sin(f1));
            this.path.moveTo(f12, f10);
            f13 = f2 / 2.0f;
            f11 = f1 + ((double)f13);
            f8 = 0.0f;
        }
        else {
            f8 = (f4 - f5) * f3 + f5;
            v1 = v;
            float f9 = (float)(((double)f8) * Math.cos(f1));
            f10 = (float)(((double)f8) * Math.sin(f1));
            this.path.moveTo(f9, f10);
            f11 = f1 + ((double)(f2 * f3 / 2.0f));
            f12 = f9;
            f13 = f2 / 2.0f;
        }
        double f14 = Math.ceil(f);
        int v2 = 0;
        int v3 = 0;
        float f15 = f10;
        float f16 = f12;
        double f17 = f11;
        while(((double)v2) < f14 * 2.0) {
            int v4 = Float.compare(f8, 0.0f);
            if(v4 == 0 || ((double)v2) != f14 * 2.0 - 2.0) {
                v5 = v2;
                f18 = f13;
            }
            else {
                v5 = v2;
                f18 = f2 * f3 / 2.0f;
            }
            if(v4 == 0 || ((double)v2) != f14 * 2.0 - 1.0) {
                f19 = (double)v2;
                f20 = v3 == 0 ? f5 : f4;
            }
            else {
                f19 = (double)v2;
                f20 = f8;
            }
            float f21 = (float)(((double)f20) * Math.cos(f17));
            float f22 = (float)(((double)f20) * Math.sin(f17));
            if(f6 != 0.0f || f7 != 0.0f) {
                f25 = f13;
                f26 = f17;
                double f27 = (double)(((float)(Math.atan2(f15, f16) - 1.570796)));
                f24 = f22;
                double f28 = (double)(((float)(Math.atan2(f22, f21) - 1.570796)));
                float f29 = (v3 == 0 ? f4 : f5) * (v3 == 0 ? f7 : f6) * 0.47829f;
                float f30 = ((float)Math.cos(f27)) * f29;
                float f31 = f29 * ((float)Math.sin(f27));
                float f32 = (v3 == 0 ? f5 : f4) * (v3 == 0 ? f6 : f7) * 0.47829f;
                float f33 = ((float)Math.cos(f28)) * f32;
                float f34 = f32 * ((float)Math.sin(f28));
                if(v1 != 0) {
                    if(v5 == 0) {
                        f30 *= f3;
                        f31 *= f3;
                    }
                    else if(f19 == f14 * 2.0 - 1.0) {
                        f33 *= f3;
                        f34 *= f3;
                    }
                }
                f23 = f21;
                this.path.cubicTo(f16 - f30, f15 - f31, f21 + f33, f24 + f34, f23, f24);
            }
            else {
                this.path.lineTo(f21, f22);
                f23 = f21;
                f24 = f22;
                f25 = f13;
                f26 = f17;
            }
            f17 = f26 + ((double)f18);
            v3 ^= 1;
            v2 = v5 + 1;
            f13 = f25;
            f16 = f23;
            f15 = f24;
        }
        PointF pointF0 = (PointF)this.positionAnimation.getValue();
        this.path.offset(pointF0.x, pointF0.y);
        this.path.close();
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
        switch(com.airbnb.lottie.animation.content.PolystarContent.1.$SwitchMap$com$airbnb$lottie$model$content$PolystarShape$Type[this.type.ordinal()]) {
            case 1: {
                this.createStarPath();
                break;
            }
            case 2: {
                this.createPolygonPath();
            }
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
            if(content0 instanceof TrimPathContent && ((TrimPathContent)content0).getType() == com.airbnb.lottie.model.content.ShapeTrimPath.Type.SIMULTANEOUSLY) {
                this.trimPaths.addTrimPath(((TrimPathContent)content0));
                ((TrimPathContent)content0).addListener(this);
            }
        }
    }

    class com.airbnb.lottie.animation.content.PolystarContent.1 {
        static final int[] $SwitchMap$com$airbnb$lottie$model$content$PolystarShape$Type;

        static {
            int[] arr_v = new int[Type.values().length];
            com.airbnb.lottie.animation.content.PolystarContent.1.$SwitchMap$com$airbnb$lottie$model$content$PolystarShape$Type = arr_v;
            try {
                arr_v[Type.STAR.ordinal()] = 1;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                com.airbnb.lottie.animation.content.PolystarContent.1.$SwitchMap$com$airbnb$lottie$model$content$PolystarShape$Type[Type.POLYGON.ordinal()] = 2;
            }
            catch(NoSuchFieldError unused_ex) {
            }
        }
    }

}

