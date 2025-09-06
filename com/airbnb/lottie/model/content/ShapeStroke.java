package com.airbnb.lottie.model.content;

import android.graphics.Paint.Cap;
import android.graphics.Paint.Join;
import com.airbnb.lottie.LottieDrawable;
import com.airbnb.lottie.animation.content.Content;
import com.airbnb.lottie.animation.content.StrokeContent;
import com.airbnb.lottie.model.animatable.AnimatableColorValue;
import com.airbnb.lottie.model.animatable.AnimatableFloatValue;
import com.airbnb.lottie.model.animatable.AnimatableIntegerValue;
import com.airbnb.lottie.model.layer.BaseLayer;
import java.util.List;

public class ShapeStroke implements ContentModel {
    public static enum LineCapType {
        BUTT,
        ROUND,
        UNKNOWN;

        public Paint.Cap toPaintCap() {
            switch(com.airbnb.lottie.model.content.ShapeStroke.1.$SwitchMap$com$airbnb$lottie$model$content$ShapeStroke$LineCapType[this.ordinal()]) {
                case 1: {
                    return Paint.Cap.BUTT;
                }
                case 2: {
                    return Paint.Cap.ROUND;
                }
                default: {
                    return Paint.Cap.SQUARE;
                }
            }
        }
    }

    public static enum LineJoinType {
        MITER,
        ROUND,
        BEVEL;

        public Paint.Join toPaintJoin() {
            switch(com.airbnb.lottie.model.content.ShapeStroke.1.$SwitchMap$com$airbnb$lottie$model$content$ShapeStroke$LineJoinType[this.ordinal()]) {
                case 1: {
                    return Paint.Join.BEVEL;
                }
                case 2: {
                    return Paint.Join.MITER;
                }
                case 3: {
                    return Paint.Join.ROUND;
                }
                default: {
                    return null;
                }
            }
        }
    }

    private final LineCapType capType;
    private final AnimatableColorValue color;
    private final boolean hidden;
    private final LineJoinType joinType;
    private final List lineDashPattern;
    private final float miterLimit;
    private final String name;
    private final AnimatableFloatValue offset;
    private final AnimatableIntegerValue opacity;
    private final AnimatableFloatValue width;

    public ShapeStroke(String s, AnimatableFloatValue animatableFloatValue0, List list0, AnimatableColorValue animatableColorValue0, AnimatableIntegerValue animatableIntegerValue0, AnimatableFloatValue animatableFloatValue1, LineCapType shapeStroke$LineCapType0, LineJoinType shapeStroke$LineJoinType0, float f, boolean z) {
        this.name = s;
        this.offset = animatableFloatValue0;
        this.lineDashPattern = list0;
        this.color = animatableColorValue0;
        this.opacity = animatableIntegerValue0;
        this.width = animatableFloatValue1;
        this.capType = shapeStroke$LineCapType0;
        this.joinType = shapeStroke$LineJoinType0;
        this.miterLimit = f;
        this.hidden = z;
    }

    public LineCapType getCapType() {
        return this.capType;
    }

    public AnimatableColorValue getColor() {
        return this.color;
    }

    public AnimatableFloatValue getDashOffset() {
        return this.offset;
    }

    public LineJoinType getJoinType() {
        return this.joinType;
    }

    public List getLineDashPattern() {
        return this.lineDashPattern;
    }

    public float getMiterLimit() {
        return this.miterLimit;
    }

    public String getName() {
        return this.name;
    }

    public AnimatableIntegerValue getOpacity() {
        return this.opacity;
    }

    public AnimatableFloatValue getWidth() {
        return this.width;
    }

    public boolean isHidden() {
        return this.hidden;
    }

    @Override  // com.airbnb.lottie.model.content.ContentModel
    public Content toContent(LottieDrawable lottieDrawable0, BaseLayer baseLayer0) {
        return new StrokeContent(lottieDrawable0, baseLayer0, this);
    }

    class com.airbnb.lottie.model.content.ShapeStroke.1 {
        static final int[] $SwitchMap$com$airbnb$lottie$model$content$ShapeStroke$LineCapType;
        static final int[] $SwitchMap$com$airbnb$lottie$model$content$ShapeStroke$LineJoinType;

        static {
            int[] arr_v = new int[LineJoinType.values().length];
            com.airbnb.lottie.model.content.ShapeStroke.1.$SwitchMap$com$airbnb$lottie$model$content$ShapeStroke$LineJoinType = arr_v;
            try {
                arr_v[LineJoinType.BEVEL.ordinal()] = 1;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                com.airbnb.lottie.model.content.ShapeStroke.1.$SwitchMap$com$airbnb$lottie$model$content$ShapeStroke$LineJoinType[LineJoinType.MITER.ordinal()] = 2;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                com.airbnb.lottie.model.content.ShapeStroke.1.$SwitchMap$com$airbnb$lottie$model$content$ShapeStroke$LineJoinType[LineJoinType.ROUND.ordinal()] = 3;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            int[] arr_v1 = new int[LineCapType.values().length];
            com.airbnb.lottie.model.content.ShapeStroke.1.$SwitchMap$com$airbnb$lottie$model$content$ShapeStroke$LineCapType = arr_v1;
            try {
                arr_v1[LineCapType.BUTT.ordinal()] = 1;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                com.airbnb.lottie.model.content.ShapeStroke.1.$SwitchMap$com$airbnb$lottie$model$content$ShapeStroke$LineCapType[LineCapType.ROUND.ordinal()] = 2;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                com.airbnb.lottie.model.content.ShapeStroke.1.$SwitchMap$com$airbnb$lottie$model$content$ShapeStroke$LineCapType[LineCapType.UNKNOWN.ordinal()] = 3;
            }
            catch(NoSuchFieldError unused_ex) {
            }
        }
    }

}

