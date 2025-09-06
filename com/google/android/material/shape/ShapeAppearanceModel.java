package com.google.android.material.shape;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.ContextThemeWrapper;
import com.google.android.material.R.styleable;

public class ShapeAppearanceModel {
    public static final class Builder {
        private EdgeTreatment bottomEdge;
        private CornerTreatment bottomLeftCorner;
        private CornerSize bottomLeftCornerSize;
        private CornerTreatment bottomRightCorner;
        private CornerSize bottomRightCornerSize;
        private EdgeTreatment leftEdge;
        private EdgeTreatment rightEdge;
        private EdgeTreatment topEdge;
        private CornerTreatment topLeftCorner;
        private CornerSize topLeftCornerSize;
        private CornerTreatment topRightCorner;
        private CornerSize topRightCornerSize;

        public Builder() {
            this.topLeftCorner = MaterialShapeUtils.createDefaultCornerTreatment();
            this.topRightCorner = MaterialShapeUtils.createDefaultCornerTreatment();
            this.bottomRightCorner = MaterialShapeUtils.createDefaultCornerTreatment();
            this.bottomLeftCorner = MaterialShapeUtils.createDefaultCornerTreatment();
            this.topLeftCornerSize = new AbsoluteCornerSize(0.0f);
            this.topRightCornerSize = new AbsoluteCornerSize(0.0f);
            this.bottomRightCornerSize = new AbsoluteCornerSize(0.0f);
            this.bottomLeftCornerSize = new AbsoluteCornerSize(0.0f);
            this.topEdge = MaterialShapeUtils.createDefaultEdgeTreatment();
            this.rightEdge = MaterialShapeUtils.createDefaultEdgeTreatment();
            this.bottomEdge = MaterialShapeUtils.createDefaultEdgeTreatment();
            this.leftEdge = MaterialShapeUtils.createDefaultEdgeTreatment();
        }

        public Builder(ShapeAppearanceModel shapeAppearanceModel0) {
            this.topLeftCorner = MaterialShapeUtils.createDefaultCornerTreatment();
            this.topRightCorner = MaterialShapeUtils.createDefaultCornerTreatment();
            this.bottomRightCorner = MaterialShapeUtils.createDefaultCornerTreatment();
            this.bottomLeftCorner = MaterialShapeUtils.createDefaultCornerTreatment();
            this.topLeftCornerSize = new AbsoluteCornerSize(0.0f);
            this.topRightCornerSize = new AbsoluteCornerSize(0.0f);
            this.bottomRightCornerSize = new AbsoluteCornerSize(0.0f);
            this.bottomLeftCornerSize = new AbsoluteCornerSize(0.0f);
            this.topEdge = MaterialShapeUtils.createDefaultEdgeTreatment();
            this.rightEdge = MaterialShapeUtils.createDefaultEdgeTreatment();
            this.bottomEdge = MaterialShapeUtils.createDefaultEdgeTreatment();
            MaterialShapeUtils.createDefaultEdgeTreatment();
            this.topLeftCorner = shapeAppearanceModel0.topLeftCorner;
            this.topRightCorner = shapeAppearanceModel0.topRightCorner;
            this.bottomRightCorner = shapeAppearanceModel0.bottomRightCorner;
            this.bottomLeftCorner = shapeAppearanceModel0.bottomLeftCorner;
            this.topLeftCornerSize = shapeAppearanceModel0.topLeftCornerSize;
            this.topRightCornerSize = shapeAppearanceModel0.topRightCornerSize;
            this.bottomRightCornerSize = shapeAppearanceModel0.bottomRightCornerSize;
            this.bottomLeftCornerSize = shapeAppearanceModel0.bottomLeftCornerSize;
            this.topEdge = shapeAppearanceModel0.topEdge;
            this.rightEdge = shapeAppearanceModel0.rightEdge;
            this.bottomEdge = shapeAppearanceModel0.bottomEdge;
            this.leftEdge = shapeAppearanceModel0.leftEdge;
        }

        static CornerTreatment access$100(Builder shapeAppearanceModel$Builder0) {
            return shapeAppearanceModel$Builder0.topLeftCorner;
        }

        static EdgeTreatment access$1000(Builder shapeAppearanceModel$Builder0) {
            return shapeAppearanceModel$Builder0.rightEdge;
        }

        static EdgeTreatment access$1100(Builder shapeAppearanceModel$Builder0) {
            return shapeAppearanceModel$Builder0.bottomEdge;
        }

        static EdgeTreatment access$1200(Builder shapeAppearanceModel$Builder0) {
            return shapeAppearanceModel$Builder0.leftEdge;
        }

        static CornerTreatment access$200(Builder shapeAppearanceModel$Builder0) {
            return shapeAppearanceModel$Builder0.topRightCorner;
        }

        static CornerTreatment access$300(Builder shapeAppearanceModel$Builder0) {
            return shapeAppearanceModel$Builder0.bottomRightCorner;
        }

        static CornerTreatment access$400(Builder shapeAppearanceModel$Builder0) {
            return shapeAppearanceModel$Builder0.bottomLeftCorner;
        }

        static CornerSize access$500(Builder shapeAppearanceModel$Builder0) {
            return shapeAppearanceModel$Builder0.topLeftCornerSize;
        }

        static CornerSize access$600(Builder shapeAppearanceModel$Builder0) {
            return shapeAppearanceModel$Builder0.topRightCornerSize;
        }

        static CornerSize access$700(Builder shapeAppearanceModel$Builder0) {
            return shapeAppearanceModel$Builder0.bottomRightCornerSize;
        }

        static CornerSize access$800(Builder shapeAppearanceModel$Builder0) {
            return shapeAppearanceModel$Builder0.bottomLeftCornerSize;
        }

        static EdgeTreatment access$900(Builder shapeAppearanceModel$Builder0) {
            return shapeAppearanceModel$Builder0.topEdge;
        }

        public ShapeAppearanceModel build() {
            return new ShapeAppearanceModel(this, null);
        }

        private static float compatCornerTreatmentSize(CornerTreatment cornerTreatment0) {
            if(cornerTreatment0 instanceof RoundedCornerTreatment) {
                return ((RoundedCornerTreatment)cornerTreatment0).radius;
            }
            return cornerTreatment0 instanceof CutCornerTreatment ? ((CutCornerTreatment)cornerTreatment0).size : -1.0f;
        }

        public Builder setAllCornerSizes(float f) {
            return this.setTopLeftCornerSize(f).setTopRightCornerSize(f).setBottomRightCornerSize(f).setBottomLeftCornerSize(f);
        }

        public Builder setAllCornerSizes(CornerSize cornerSize0) {
            return this.setTopLeftCornerSize(cornerSize0).setTopRightCornerSize(cornerSize0).setBottomRightCornerSize(cornerSize0).setBottomLeftCornerSize(cornerSize0);
        }

        public Builder setAllCorners(int v, float f) {
            return this.setAllCorners(MaterialShapeUtils.createCornerTreatment(v)).setAllCornerSizes(f);
        }

        public Builder setAllCorners(CornerTreatment cornerTreatment0) {
            return this.setTopLeftCorner(cornerTreatment0).setTopRightCorner(cornerTreatment0).setBottomRightCorner(cornerTreatment0).setBottomLeftCorner(cornerTreatment0);
        }

        public Builder setAllEdges(EdgeTreatment edgeTreatment0) {
            return this.setLeftEdge(edgeTreatment0).setTopEdge(edgeTreatment0).setRightEdge(edgeTreatment0).setBottomEdge(edgeTreatment0);
        }

        public Builder setBottomEdge(EdgeTreatment edgeTreatment0) {
            this.bottomEdge = edgeTreatment0;
            return this;
        }

        public Builder setBottomLeftCorner(int v, float f) {
            return this.setBottomLeftCorner(MaterialShapeUtils.createCornerTreatment(v)).setBottomLeftCornerSize(f);
        }

        public Builder setBottomLeftCorner(int v, CornerSize cornerSize0) {
            return this.setBottomLeftCorner(MaterialShapeUtils.createCornerTreatment(v)).setBottomLeftCornerSize(cornerSize0);
        }

        public Builder setBottomLeftCorner(CornerTreatment cornerTreatment0) {
            this.bottomLeftCorner = cornerTreatment0;
            float f = Builder.compatCornerTreatmentSize(cornerTreatment0);
            if(f != -1.0f) {
                this.setBottomLeftCornerSize(f);
            }
            return this;
        }

        public Builder setBottomLeftCornerSize(float f) {
            this.bottomLeftCornerSize = new AbsoluteCornerSize(f);
            return this;
        }

        public Builder setBottomLeftCornerSize(CornerSize cornerSize0) {
            this.bottomLeftCornerSize = cornerSize0;
            return this;
        }

        public Builder setBottomRightCorner(int v, float f) {
            return this.setBottomRightCorner(MaterialShapeUtils.createCornerTreatment(v)).setBottomRightCornerSize(f);
        }

        public Builder setBottomRightCorner(int v, CornerSize cornerSize0) {
            return this.setBottomRightCorner(MaterialShapeUtils.createCornerTreatment(v)).setBottomRightCornerSize(cornerSize0);
        }

        public Builder setBottomRightCorner(CornerTreatment cornerTreatment0) {
            this.bottomRightCorner = cornerTreatment0;
            float f = Builder.compatCornerTreatmentSize(cornerTreatment0);
            if(f != -1.0f) {
                this.setBottomRightCornerSize(f);
            }
            return this;
        }

        public Builder setBottomRightCornerSize(float f) {
            this.bottomRightCornerSize = new AbsoluteCornerSize(f);
            return this;
        }

        public Builder setBottomRightCornerSize(CornerSize cornerSize0) {
            this.bottomRightCornerSize = cornerSize0;
            return this;
        }

        public Builder setLeftEdge(EdgeTreatment edgeTreatment0) {
            this.leftEdge = edgeTreatment0;
            return this;
        }

        public Builder setRightEdge(EdgeTreatment edgeTreatment0) {
            this.rightEdge = edgeTreatment0;
            return this;
        }

        public Builder setTopEdge(EdgeTreatment edgeTreatment0) {
            this.topEdge = edgeTreatment0;
            return this;
        }

        public Builder setTopLeftCorner(int v, float f) {
            return this.setTopLeftCorner(MaterialShapeUtils.createCornerTreatment(v)).setTopLeftCornerSize(f);
        }

        public Builder setTopLeftCorner(int v, CornerSize cornerSize0) {
            return this.setTopLeftCorner(MaterialShapeUtils.createCornerTreatment(v)).setTopLeftCornerSize(cornerSize0);
        }

        public Builder setTopLeftCorner(CornerTreatment cornerTreatment0) {
            this.topLeftCorner = cornerTreatment0;
            float f = Builder.compatCornerTreatmentSize(cornerTreatment0);
            if(f != -1.0f) {
                this.setTopLeftCornerSize(f);
            }
            return this;
        }

        public Builder setTopLeftCornerSize(float f) {
            this.topLeftCornerSize = new AbsoluteCornerSize(f);
            return this;
        }

        public Builder setTopLeftCornerSize(CornerSize cornerSize0) {
            this.topLeftCornerSize = cornerSize0;
            return this;
        }

        public Builder setTopRightCorner(int v, float f) {
            return this.setTopRightCorner(MaterialShapeUtils.createCornerTreatment(v)).setTopRightCornerSize(f);
        }

        public Builder setTopRightCorner(int v, CornerSize cornerSize0) {
            return this.setTopRightCorner(MaterialShapeUtils.createCornerTreatment(v)).setTopRightCornerSize(cornerSize0);
        }

        public Builder setTopRightCorner(CornerTreatment cornerTreatment0) {
            this.topRightCorner = cornerTreatment0;
            float f = Builder.compatCornerTreatmentSize(cornerTreatment0);
            if(f != -1.0f) {
                this.setTopRightCornerSize(f);
            }
            return this;
        }

        public Builder setTopRightCornerSize(float f) {
            this.topRightCornerSize = new AbsoluteCornerSize(f);
            return this;
        }

        public Builder setTopRightCornerSize(CornerSize cornerSize0) {
            this.topRightCornerSize = cornerSize0;
            return this;
        }
    }

    public interface CornerSizeUnaryOperator {
        CornerSize apply(CornerSize arg1);
    }

    public static final CornerSize PILL;
    EdgeTreatment bottomEdge;
    CornerTreatment bottomLeftCorner;
    CornerSize bottomLeftCornerSize;
    CornerTreatment bottomRightCorner;
    CornerSize bottomRightCornerSize;
    EdgeTreatment leftEdge;
    EdgeTreatment rightEdge;
    EdgeTreatment topEdge;
    CornerTreatment topLeftCorner;
    CornerSize topLeftCornerSize;
    CornerTreatment topRightCorner;
    CornerSize topRightCornerSize;

    static {
        ShapeAppearanceModel.PILL = new RelativeCornerSize(0.5f);
    }

    public ShapeAppearanceModel() {
        this.topLeftCorner = MaterialShapeUtils.createDefaultCornerTreatment();
        this.topRightCorner = MaterialShapeUtils.createDefaultCornerTreatment();
        this.bottomRightCorner = MaterialShapeUtils.createDefaultCornerTreatment();
        this.bottomLeftCorner = MaterialShapeUtils.createDefaultCornerTreatment();
        this.topLeftCornerSize = new AbsoluteCornerSize(0.0f);
        this.topRightCornerSize = new AbsoluteCornerSize(0.0f);
        this.bottomRightCornerSize = new AbsoluteCornerSize(0.0f);
        this.bottomLeftCornerSize = new AbsoluteCornerSize(0.0f);
        this.topEdge = MaterialShapeUtils.createDefaultEdgeTreatment();
        this.rightEdge = MaterialShapeUtils.createDefaultEdgeTreatment();
        this.bottomEdge = MaterialShapeUtils.createDefaultEdgeTreatment();
        this.leftEdge = MaterialShapeUtils.createDefaultEdgeTreatment();
    }

    private ShapeAppearanceModel(Builder shapeAppearanceModel$Builder0) {
        this.topLeftCorner = Builder.access$100(shapeAppearanceModel$Builder0);
        this.topRightCorner = Builder.access$200(shapeAppearanceModel$Builder0);
        this.bottomRightCorner = Builder.access$300(shapeAppearanceModel$Builder0);
        this.bottomLeftCorner = Builder.access$400(shapeAppearanceModel$Builder0);
        this.topLeftCornerSize = Builder.access$500(shapeAppearanceModel$Builder0);
        this.topRightCornerSize = Builder.access$600(shapeAppearanceModel$Builder0);
        this.bottomRightCornerSize = Builder.access$700(shapeAppearanceModel$Builder0);
        this.bottomLeftCornerSize = Builder.access$800(shapeAppearanceModel$Builder0);
        this.topEdge = Builder.access$900(shapeAppearanceModel$Builder0);
        this.rightEdge = Builder.access$1000(shapeAppearanceModel$Builder0);
        this.bottomEdge = Builder.access$1100(shapeAppearanceModel$Builder0);
        this.leftEdge = Builder.access$1200(shapeAppearanceModel$Builder0);
    }

    ShapeAppearanceModel(Builder shapeAppearanceModel$Builder0, com.google.android.material.shape.ShapeAppearanceModel.1 shapeAppearanceModel$10) {
        this(shapeAppearanceModel$Builder0);
    }

    public static Builder builder() {
        return new Builder();
    }

    public static Builder builder(Context context0, int v, int v1) {
        return ShapeAppearanceModel.builder(context0, v, v1, 0);
    }

    private static Builder builder(Context context0, int v, int v1, int v2) {
        return ShapeAppearanceModel.builder(context0, v, v1, new AbsoluteCornerSize(((float)v2)));
    }

    private static Builder builder(Context context0, int v, int v1, CornerSize cornerSize0) {
        ContextThemeWrapper contextThemeWrapper0 = new ContextThemeWrapper(context0, v);
        if(v1 != 0) {
            contextThemeWrapper0 = new ContextThemeWrapper(contextThemeWrapper0, v1);
        }
        TypedArray typedArray0 = contextThemeWrapper0.obtainStyledAttributes(styleable.ShapeAppearance);
        try {
            int v3 = typedArray0.getInt(styleable.ShapeAppearance_cornerFamily, 0);
            int v4 = typedArray0.getInt(styleable.ShapeAppearance_cornerFamilyTopLeft, v3);
            int v5 = typedArray0.getInt(styleable.ShapeAppearance_cornerFamilyTopRight, v3);
            int v6 = typedArray0.getInt(styleable.ShapeAppearance_cornerFamilyBottomRight, v3);
            int v7 = typedArray0.getInt(styleable.ShapeAppearance_cornerFamilyBottomLeft, v3);
            CornerSize cornerSize1 = ShapeAppearanceModel.getCornerSize(typedArray0, styleable.ShapeAppearance_cornerSize, cornerSize0);
            CornerSize cornerSize2 = ShapeAppearanceModel.getCornerSize(typedArray0, styleable.ShapeAppearance_cornerSizeTopLeft, cornerSize1);
            CornerSize cornerSize3 = ShapeAppearanceModel.getCornerSize(typedArray0, styleable.ShapeAppearance_cornerSizeTopRight, cornerSize1);
            CornerSize cornerSize4 = ShapeAppearanceModel.getCornerSize(typedArray0, styleable.ShapeAppearance_cornerSizeBottomRight, cornerSize1);
            CornerSize cornerSize5 = ShapeAppearanceModel.getCornerSize(typedArray0, styleable.ShapeAppearance_cornerSizeBottomLeft, cornerSize1);
            return new Builder().setTopLeftCorner(v4, cornerSize2).setTopRightCorner(v5, cornerSize3).setBottomRightCorner(v6, cornerSize4).setBottomLeftCorner(v7, cornerSize5);
        }
        finally {
            typedArray0.recycle();
        }
    }

    public static Builder builder(Context context0, AttributeSet attributeSet0, int v, int v1) {
        return ShapeAppearanceModel.builder(context0, attributeSet0, v, v1, 0);
    }

    public static Builder builder(Context context0, AttributeSet attributeSet0, int v, int v1, int v2) {
        return ShapeAppearanceModel.builder(context0, attributeSet0, v, v1, new AbsoluteCornerSize(((float)v2)));
    }

    public static Builder builder(Context context0, AttributeSet attributeSet0, int v, int v1, CornerSize cornerSize0) {
        TypedArray typedArray0 = context0.obtainStyledAttributes(attributeSet0, styleable.MaterialShape, v, v1);
        int v2 = typedArray0.getResourceId(styleable.MaterialShape_shapeAppearance, 0);
        int v3 = typedArray0.getResourceId(styleable.MaterialShape_shapeAppearanceOverlay, 0);
        typedArray0.recycle();
        return ShapeAppearanceModel.builder(context0, v2, v3, cornerSize0);
    }

    public EdgeTreatment getBottomEdge() {
        return this.bottomEdge;
    }

    public CornerTreatment getBottomLeftCorner() {
        return this.bottomLeftCorner;
    }

    public CornerSize getBottomLeftCornerSize() {
        return this.bottomLeftCornerSize;
    }

    public CornerTreatment getBottomRightCorner() {
        return this.bottomRightCorner;
    }

    public CornerSize getBottomRightCornerSize() {
        return this.bottomRightCornerSize;
    }

    private static CornerSize getCornerSize(TypedArray typedArray0, int v, CornerSize cornerSize0) {
        TypedValue typedValue0 = typedArray0.peekValue(v);
        if(typedValue0 != null) {
            switch(typedValue0.type) {
                case 5: {
                    return new AbsoluteCornerSize(((float)TypedValue.complexToDimensionPixelSize(typedValue0.data, typedArray0.getResources().getDisplayMetrics())));
                }
                case 6: {
                    return new RelativeCornerSize(typedValue0.getFraction(1.0f, 1.0f));
                }
                default: {
                    return cornerSize0;
                }
            }
        }
        return cornerSize0;
    }

    public EdgeTreatment getLeftEdge() {
        return this.leftEdge;
    }

    public EdgeTreatment getRightEdge() {
        return this.rightEdge;
    }

    public EdgeTreatment getTopEdge() {
        return this.topEdge;
    }

    public CornerTreatment getTopLeftCorner() {
        return this.topLeftCorner;
    }

    public CornerSize getTopLeftCornerSize() {
        return this.topLeftCornerSize;
    }

    public CornerTreatment getTopRightCorner() {
        return this.topRightCorner;
    }

    public CornerSize getTopRightCornerSize() {
        return this.topRightCornerSize;
    }

    // 去混淆评级： 低(36)
    public boolean isRoundRect(RectF rectF0) {
        boolean z = this.leftEdge.getClass().equals(EdgeTreatment.class) && this.rightEdge.getClass().equals(EdgeTreatment.class) && this.topEdge.getClass().equals(EdgeTreatment.class) && this.bottomEdge.getClass().equals(EdgeTreatment.class);
        float f = this.topLeftCornerSize.getCornerSize(rectF0);
        return z && (this.topRightCornerSize.getCornerSize(rectF0) == f && this.bottomLeftCornerSize.getCornerSize(rectF0) == f && this.bottomRightCornerSize.getCornerSize(rectF0) == f) && (this.topRightCorner instanceof RoundedCornerTreatment && this.topLeftCorner instanceof RoundedCornerTreatment && this.bottomRightCorner instanceof RoundedCornerTreatment && this.bottomLeftCorner instanceof RoundedCornerTreatment);
    }

    public Builder toBuilder() {
        return new Builder(this);
    }

    public ShapeAppearanceModel withCornerSize(float f) {
        return this.toBuilder().setAllCornerSizes(f).build();
    }

    public ShapeAppearanceModel withCornerSize(CornerSize cornerSize0) {
        return this.toBuilder().setAllCornerSizes(cornerSize0).build();
    }

    public ShapeAppearanceModel withTransformedCornerSizes(CornerSizeUnaryOperator shapeAppearanceModel$CornerSizeUnaryOperator0) {
        return this.toBuilder().setTopLeftCornerSize(shapeAppearanceModel$CornerSizeUnaryOperator0.apply(this.getTopLeftCornerSize())).setTopRightCornerSize(shapeAppearanceModel$CornerSizeUnaryOperator0.apply(this.getTopRightCornerSize())).setBottomLeftCornerSize(shapeAppearanceModel$CornerSizeUnaryOperator0.apply(this.getBottomLeftCornerSize())).setBottomRightCornerSize(shapeAppearanceModel$CornerSizeUnaryOperator0.apply(this.getBottomRightCornerSize())).build();
    }

    class com.google.android.material.shape.ShapeAppearanceModel.1 {
    }

}

