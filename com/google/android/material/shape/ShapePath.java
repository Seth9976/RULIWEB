package com.google.android.material.shape;

import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Path;
import android.graphics.RectF;
import com.google.android.material.shadow.ShadowRenderer;
import java.util.ArrayList;
import java.util.List;

public class ShapePath {
    static class ArcShadowOperation extends ShadowCompatOperation {
        private final PathArcOperation operation;

        public ArcShadowOperation(PathArcOperation shapePath$PathArcOperation0) {
            this.operation = shapePath$PathArcOperation0;
        }

        @Override  // com.google.android.material.shape.ShapePath$ShadowCompatOperation
        public void draw(Matrix matrix0, ShadowRenderer shadowRenderer0, int v, Canvas canvas0) {
            float f = PathArcOperation.access$800(this.operation);
            float f1 = PathArcOperation.access$900(this.operation);
            shadowRenderer0.drawCornerShadow(canvas0, matrix0, new RectF(PathArcOperation.access$1000(this.operation), PathArcOperation.access$1100(this.operation), PathArcOperation.access$1200(this.operation), PathArcOperation.access$1300(this.operation)), v, f, f1);
        }
    }

    static class InnerCornerShadowOperation extends ShadowCompatOperation {
        private final PathLineOperation operation1;
        private final PathLineOperation operation2;
        private final float startX;
        private final float startY;

        public InnerCornerShadowOperation(PathLineOperation shapePath$PathLineOperation0, PathLineOperation shapePath$PathLineOperation1, float f, float f1) {
            this.operation1 = shapePath$PathLineOperation0;
            this.operation2 = shapePath$PathLineOperation1;
            this.startX = f;
            this.startY = f1;
        }

        @Override  // com.google.android.material.shape.ShapePath$ShadowCompatOperation
        public void draw(Matrix matrix0, ShadowRenderer shadowRenderer0, int v, Canvas canvas0) {
            float f = this.getSweepAngle();
            if(f <= 0.0f) {
                double f1 = Math.hypot(PathLineOperation.access$000(this.operation1) - this.startX, PathLineOperation.access$100(this.operation1) - this.startY);
                double f2 = Math.hypot(PathLineOperation.access$000(this.operation2) - PathLineOperation.access$000(this.operation1), PathLineOperation.access$100(this.operation2) - PathLineOperation.access$100(this.operation1));
                float f3 = (float)Math.min(v, Math.min(f1, f2));
                double f4 = Math.tan(Math.toRadians(-f / 2.0f)) * ((double)f3);
                if(f1 > f4) {
                    RectF rectF0 = new RectF(0.0f, 0.0f, ((float)(f1 - f4)), 0.0f);
                    this.renderMatrix.set(matrix0);
                    this.renderMatrix.preTranslate(this.startX, this.startY);
                    this.renderMatrix.preRotate(this.getStartAngle());
                    shadowRenderer0.drawEdgeShadow(canvas0, this.renderMatrix, rectF0, v);
                }
                RectF rectF1 = new RectF(0.0f, 0.0f, f3 * 2.0f, f3 * 2.0f);
                this.renderMatrix.set(matrix0);
                this.renderMatrix.preTranslate(PathLineOperation.access$000(this.operation1), PathLineOperation.access$100(this.operation1));
                this.renderMatrix.preRotate(this.getStartAngle());
                this.renderMatrix.preTranslate(((float)(-f4 - ((double)f3))), -2.0f * f3);
                shadowRenderer0.drawInnerCornerShadow(canvas0, this.renderMatrix, rectF1, ((int)f3), 450.0f, f, new float[]{((float)(((double)f3) + f4)), f3 * 2.0f});
                if(f2 > f4) {
                    RectF rectF2 = new RectF(0.0f, 0.0f, ((float)(f2 - f4)), 0.0f);
                    this.renderMatrix.set(matrix0);
                    this.renderMatrix.preTranslate(PathLineOperation.access$000(this.operation1), PathLineOperation.access$100(this.operation1));
                    this.renderMatrix.preRotate(this.getEndAngle());
                    this.renderMatrix.preTranslate(((float)f4), 0.0f);
                    shadowRenderer0.drawEdgeShadow(canvas0, this.renderMatrix, rectF2, v);
                }
            }
        }

        float getEndAngle() {
            return (float)Math.toDegrees(Math.atan((PathLineOperation.access$100(this.operation2) - PathLineOperation.access$100(this.operation1)) / (PathLineOperation.access$000(this.operation2) - PathLineOperation.access$000(this.operation1))));
        }

        float getStartAngle() {
            return (float)Math.toDegrees(Math.atan((PathLineOperation.access$100(this.operation1) - this.startY) / (PathLineOperation.access$000(this.operation1) - this.startX)));
        }

        float getSweepAngle() {
            float f = (this.getEndAngle() - this.getStartAngle() + 360.0f) % 360.0f;
            return f <= 180.0f ? f : f - 360.0f;
        }
    }

    static class LineShadowOperation extends ShadowCompatOperation {
        private final PathLineOperation operation;
        private final float startX;
        private final float startY;

        public LineShadowOperation(PathLineOperation shapePath$PathLineOperation0, float f, float f1) {
            this.operation = shapePath$PathLineOperation0;
            this.startX = f;
            this.startY = f1;
        }

        @Override  // com.google.android.material.shape.ShapePath$ShadowCompatOperation
        public void draw(Matrix matrix0, ShadowRenderer shadowRenderer0, int v, Canvas canvas0) {
            RectF rectF0 = new RectF(0.0f, 0.0f, ((float)Math.hypot(PathLineOperation.access$100(this.operation) - this.startY, PathLineOperation.access$000(this.operation) - this.startX)), 0.0f);
            this.renderMatrix.set(matrix0);
            this.renderMatrix.preTranslate(this.startX, this.startY);
            this.renderMatrix.preRotate(this.getAngle());
            shadowRenderer0.drawEdgeShadow(canvas0, this.renderMatrix, rectF0, v);
        }

        float getAngle() {
            return (float)Math.toDegrees(Math.atan((PathLineOperation.access$100(this.operation) - this.startY) / (PathLineOperation.access$000(this.operation) - this.startX)));
        }
    }

    public static class PathArcOperation extends PathOperation {
        @Deprecated
        public float bottom;
        @Deprecated
        public float left;
        private static final RectF rectF;
        @Deprecated
        public float right;
        @Deprecated
        public float startAngle;
        @Deprecated
        public float sweepAngle;
        @Deprecated
        public float top;

        static {
            PathArcOperation.rectF = new RectF();
        }

        public PathArcOperation(float f, float f1, float f2, float f3) {
            this.setLeft(f);
            this.setTop(f1);
            this.setRight(f2);
            this.setBottom(f3);
        }

        static float access$1000(PathArcOperation shapePath$PathArcOperation0) {
            return shapePath$PathArcOperation0.getLeft();
        }

        static float access$1100(PathArcOperation shapePath$PathArcOperation0) {
            return shapePath$PathArcOperation0.getTop();
        }

        static float access$1200(PathArcOperation shapePath$PathArcOperation0) {
            return shapePath$PathArcOperation0.getRight();
        }

        static float access$1300(PathArcOperation shapePath$PathArcOperation0) {
            return shapePath$PathArcOperation0.getBottom();
        }

        static void access$600(PathArcOperation shapePath$PathArcOperation0, float f) {
            shapePath$PathArcOperation0.setStartAngle(f);
        }

        static void access$700(PathArcOperation shapePath$PathArcOperation0, float f) {
            shapePath$PathArcOperation0.setSweepAngle(f);
        }

        static float access$800(PathArcOperation shapePath$PathArcOperation0) {
            return shapePath$PathArcOperation0.getStartAngle();
        }

        static float access$900(PathArcOperation shapePath$PathArcOperation0) {
            return shapePath$PathArcOperation0.getSweepAngle();
        }

        @Override  // com.google.android.material.shape.ShapePath$PathOperation
        public void applyToPath(Matrix matrix0, Path path0) {
            Matrix matrix1 = this.matrix;
            matrix0.invert(matrix1);
            path0.transform(matrix1);
            PathArcOperation.rectF.set(this.getLeft(), this.getTop(), this.getRight(), this.getBottom());
            path0.arcTo(PathArcOperation.rectF, this.getStartAngle(), this.getSweepAngle(), false);
            path0.transform(matrix0);
        }

        private float getBottom() {
            return this.bottom;
        }

        private float getLeft() {
            return this.left;
        }

        private float getRight() {
            return this.right;
        }

        private float getStartAngle() {
            return this.startAngle;
        }

        private float getSweepAngle() {
            return this.sweepAngle;
        }

        private float getTop() {
            return this.top;
        }

        private void setBottom(float f) {
            this.bottom = f;
        }

        private void setLeft(float f) {
            this.left = f;
        }

        private void setRight(float f) {
            this.right = f;
        }

        private void setStartAngle(float f) {
            this.startAngle = f;
        }

        private void setSweepAngle(float f) {
            this.sweepAngle = f;
        }

        private void setTop(float f) {
            this.top = f;
        }
    }

    public static class PathCubicOperation extends PathOperation {
        private float controlX1;
        private float controlX2;
        private float controlY1;
        private float controlY2;
        private float endX;
        private float endY;

        public PathCubicOperation(float f, float f1, float f2, float f3, float f4, float f5) {
            this.setControlX1(f);
            this.setControlY1(f1);
            this.setControlX2(f2);
            this.setControlY2(f3);
            this.setEndX(f4);
            this.setEndY(f5);
        }

        @Override  // com.google.android.material.shape.ShapePath$PathOperation
        public void applyToPath(Matrix matrix0, Path path0) {
            Matrix matrix1 = this.matrix;
            matrix0.invert(matrix1);
            path0.transform(matrix1);
            path0.cubicTo(this.controlX1, this.controlY1, this.controlX2, this.controlY2, this.endX, this.endY);
            path0.transform(matrix0);
        }

        private float getControlX1() {
            return this.controlX1;
        }

        private float getControlX2() {
            return this.controlX2;
        }

        private float getControlY1() {
            return this.controlY1;
        }

        private float getControlY2() {
            return this.controlY1;
        }

        private float getEndX() {
            return this.endX;
        }

        private float getEndY() {
            return this.endY;
        }

        private void setControlX1(float f) {
            this.controlX1 = f;
        }

        private void setControlX2(float f) {
            this.controlX2 = f;
        }

        private void setControlY1(float f) {
            this.controlY1 = f;
        }

        private void setControlY2(float f) {
            this.controlY2 = f;
        }

        private void setEndX(float f) {
            this.endX = f;
        }

        private void setEndY(float f) {
            this.endY = f;
        }
    }

    public static class PathLineOperation extends PathOperation {
        private float x;
        private float y;

        static float access$000(PathLineOperation shapePath$PathLineOperation0) {
            return shapePath$PathLineOperation0.x;
        }

        static float access$002(PathLineOperation shapePath$PathLineOperation0, float f) {
            shapePath$PathLineOperation0.x = f;
            return f;
        }

        static float access$100(PathLineOperation shapePath$PathLineOperation0) {
            return shapePath$PathLineOperation0.y;
        }

        static float access$102(PathLineOperation shapePath$PathLineOperation0, float f) {
            shapePath$PathLineOperation0.y = f;
            return f;
        }

        @Override  // com.google.android.material.shape.ShapePath$PathOperation
        public void applyToPath(Matrix matrix0, Path path0) {
            Matrix matrix1 = this.matrix;
            matrix0.invert(matrix1);
            path0.transform(matrix1);
            path0.lineTo(this.x, this.y);
            path0.transform(matrix0);
        }
    }

    public static abstract class PathOperation {
        protected final Matrix matrix;

        public PathOperation() {
            this.matrix = new Matrix();
        }

        public abstract void applyToPath(Matrix arg1, Path arg2);
    }

    public static class PathQuadOperation extends PathOperation {
        @Deprecated
        public float controlX;
        @Deprecated
        public float controlY;
        @Deprecated
        public float endX;
        @Deprecated
        public float endY;

        static void access$200(PathQuadOperation shapePath$PathQuadOperation0, float f) {
            shapePath$PathQuadOperation0.setControlX(f);
        }

        static void access$300(PathQuadOperation shapePath$PathQuadOperation0, float f) {
            shapePath$PathQuadOperation0.setControlY(f);
        }

        static void access$400(PathQuadOperation shapePath$PathQuadOperation0, float f) {
            shapePath$PathQuadOperation0.setEndX(f);
        }

        static void access$500(PathQuadOperation shapePath$PathQuadOperation0, float f) {
            shapePath$PathQuadOperation0.setEndY(f);
        }

        @Override  // com.google.android.material.shape.ShapePath$PathOperation
        public void applyToPath(Matrix matrix0, Path path0) {
            Matrix matrix1 = this.matrix;
            matrix0.invert(matrix1);
            path0.transform(matrix1);
            path0.quadTo(this.getControlX(), this.getControlY(), this.getEndX(), this.getEndY());
            path0.transform(matrix0);
        }

        private float getControlX() {
            return this.controlX;
        }

        private float getControlY() {
            return this.controlY;
        }

        private float getEndX() {
            return this.endX;
        }

        private float getEndY() {
            return this.endY;
        }

        private void setControlX(float f) {
            this.controlX = f;
        }

        private void setControlY(float f) {
            this.controlY = f;
        }

        private void setEndX(float f) {
            this.endX = f;
        }

        private void setEndY(float f) {
            this.endY = f;
        }
    }

    static abstract class ShadowCompatOperation {
        static final Matrix IDENTITY_MATRIX;
        final Matrix renderMatrix;

        static {
            ShadowCompatOperation.IDENTITY_MATRIX = new Matrix();
        }

        ShadowCompatOperation() {
            this.renderMatrix = new Matrix();
        }

        public abstract void draw(Matrix arg1, ShadowRenderer arg2, int arg3, Canvas arg4);

        public final void draw(ShadowRenderer shadowRenderer0, int v, Canvas canvas0) {
            this.draw(ShadowCompatOperation.IDENTITY_MATRIX, shadowRenderer0, v, canvas0);
        }
    }

    protected static final float ANGLE_LEFT = 180.0f;
    private static final float ANGLE_UP = 270.0f;
    private boolean containsIncompatibleShadowOp;
    @Deprecated
    public float currentShadowAngle;
    @Deprecated
    public float endShadowAngle;
    @Deprecated
    public float endX;
    @Deprecated
    public float endY;
    private final List operations;
    private final List shadowCompatOperations;
    @Deprecated
    public float startX;
    @Deprecated
    public float startY;

    public ShapePath() {
        this.operations = new ArrayList();
        this.shadowCompatOperations = new ArrayList();
        this.reset(0.0f, 0.0f);
    }

    public ShapePath(float f, float f1) {
        this.operations = new ArrayList();
        this.shadowCompatOperations = new ArrayList();
        this.reset(f, f1);
    }

    public void addArc(float f, float f1, float f2, float f3, float f4, float f5) {
        PathArcOperation shapePath$PathArcOperation0 = new PathArcOperation(f, f1, f2, f3);
        PathArcOperation.access$600(shapePath$PathArcOperation0, f4);
        PathArcOperation.access$700(shapePath$PathArcOperation0, f5);
        this.operations.add(shapePath$PathArcOperation0);
        ArcShadowOperation shapePath$ArcShadowOperation0 = new ArcShadowOperation(shapePath$PathArcOperation0);
        float f6 = f4 + f5;
        if(f5 < 0.0f) {
            f4 = (f4 + 180.0f) % 360.0f;
        }
        this.addShadowCompatOperation(shapePath$ArcShadowOperation0, f4, (f5 < 0.0f ? (f6 + 180.0f) % 360.0f : f6));
        this.setEndX((f + f2) * 0.5f + (f2 - f) / 2.0f * ((float)Math.cos(Math.toRadians(f6))));
        this.setEndY((f1 + f3) * 0.5f + (f3 - f1) / 2.0f * ((float)Math.sin(Math.toRadians(f6))));
    }

    private void addConnectingShadowIfNecessary(float f) {
        if(this.getCurrentShadowAngle() != f) {
            float f1 = (f - this.getCurrentShadowAngle() + 360.0f) % 360.0f;
            if(f1 <= 180.0f) {
                PathArcOperation shapePath$PathArcOperation0 = new PathArcOperation(this.getEndX(), this.getEndY(), this.getEndX(), this.getEndY());
                PathArcOperation.access$600(shapePath$PathArcOperation0, this.getCurrentShadowAngle());
                PathArcOperation.access$700(shapePath$PathArcOperation0, f1);
                ArcShadowOperation shapePath$ArcShadowOperation0 = new ArcShadowOperation(shapePath$PathArcOperation0);
                this.shadowCompatOperations.add(shapePath$ArcShadowOperation0);
                this.setCurrentShadowAngle(f);
            }
        }
    }

    private void addShadowCompatOperation(ShadowCompatOperation shapePath$ShadowCompatOperation0, float f, float f1) {
        this.addConnectingShadowIfNecessary(f);
        this.shadowCompatOperations.add(shapePath$ShadowCompatOperation0);
        this.setCurrentShadowAngle(f1);
    }

    public void applyToPath(Matrix matrix0, Path path0) {
        int v = this.operations.size();
        for(int v1 = 0; v1 < v; ++v1) {
            ((PathOperation)this.operations.get(v1)).applyToPath(matrix0, path0);
        }
    }

    boolean containsIncompatibleShadowOp() {
        return this.containsIncompatibleShadowOp;
    }

    ShadowCompatOperation createShadowCompatOperation(Matrix matrix0) {
        this.addConnectingShadowIfNecessary(this.getEndShadowAngle());
        Matrix matrix1 = new Matrix(matrix0);
        return new ShadowCompatOperation() {
            @Override  // com.google.android.material.shape.ShapePath$ShadowCompatOperation
            public void draw(Matrix matrix0, ShadowRenderer shadowRenderer0, int v, Canvas canvas0) {
                for(Object object0: new ArrayList(this.shadowCompatOperations)) {
                    ((ShadowCompatOperation)object0).draw(matrix1, shadowRenderer0, v, canvas0);
                }
            }
        };
    }

    public void cubicToPoint(float f, float f1, float f2, float f3, float f4, float f5) {
        PathCubicOperation shapePath$PathCubicOperation0 = new PathCubicOperation(f, f1, f2, f3, f4, f5);
        this.operations.add(shapePath$PathCubicOperation0);
        this.containsIncompatibleShadowOp = true;
        this.setEndX(f4);
        this.setEndY(f5);
    }

    private float getCurrentShadowAngle() {
        return this.currentShadowAngle;
    }

    private float getEndShadowAngle() {
        return this.endShadowAngle;
    }

    float getEndX() {
        return this.endX;
    }

    float getEndY() {
        return this.endY;
    }

    float getStartX() {
        return this.startX;
    }

    float getStartY() {
        return this.startY;
    }

    public void lineTo(float f, float f1) {
        PathLineOperation shapePath$PathLineOperation0 = new PathLineOperation();
        PathLineOperation.access$002(shapePath$PathLineOperation0, f);
        PathLineOperation.access$102(shapePath$PathLineOperation0, f1);
        this.operations.add(shapePath$PathLineOperation0);
        LineShadowOperation shapePath$LineShadowOperation0 = new LineShadowOperation(shapePath$PathLineOperation0, this.getEndX(), this.getEndY());
        this.addShadowCompatOperation(shapePath$LineShadowOperation0, shapePath$LineShadowOperation0.getAngle() + 270.0f, shapePath$LineShadowOperation0.getAngle() + 270.0f);
        this.setEndX(f);
        this.setEndY(f1);
    }

    public void lineTo(float f, float f1, float f2, float f3) {
        if(Math.abs(f - this.getEndX()) < 0.001f && Math.abs(f1 - this.getEndY()) < 0.001f || Math.abs(f - f2) < 0.001f && Math.abs(f1 - f3) < 0.001f) {
            this.lineTo(f2, f3);
            return;
        }
        PathLineOperation shapePath$PathLineOperation0 = new PathLineOperation();
        PathLineOperation.access$002(shapePath$PathLineOperation0, f);
        PathLineOperation.access$102(shapePath$PathLineOperation0, f1);
        this.operations.add(shapePath$PathLineOperation0);
        PathLineOperation shapePath$PathLineOperation1 = new PathLineOperation();
        PathLineOperation.access$002(shapePath$PathLineOperation1, f2);
        PathLineOperation.access$102(shapePath$PathLineOperation1, f3);
        this.operations.add(shapePath$PathLineOperation1);
        InnerCornerShadowOperation shapePath$InnerCornerShadowOperation0 = new InnerCornerShadowOperation(shapePath$PathLineOperation0, shapePath$PathLineOperation1, this.getEndX(), this.getEndY());
        if(shapePath$InnerCornerShadowOperation0.getSweepAngle() > 0.0f) {
            this.lineTo(f, f1);
            this.lineTo(f2, f3);
            return;
        }
        this.addShadowCompatOperation(shapePath$InnerCornerShadowOperation0, shapePath$InnerCornerShadowOperation0.getStartAngle() + 270.0f, shapePath$InnerCornerShadowOperation0.getEndAngle() + 270.0f);
        this.setEndX(f2);
        this.setEndY(f3);
    }

    public void quadToPoint(float f, float f1, float f2, float f3) {
        PathQuadOperation shapePath$PathQuadOperation0 = new PathQuadOperation();
        PathQuadOperation.access$200(shapePath$PathQuadOperation0, f);
        PathQuadOperation.access$300(shapePath$PathQuadOperation0, f1);
        PathQuadOperation.access$400(shapePath$PathQuadOperation0, f2);
        PathQuadOperation.access$500(shapePath$PathQuadOperation0, f3);
        this.operations.add(shapePath$PathQuadOperation0);
        this.containsIncompatibleShadowOp = true;
        this.setEndX(f2);
        this.setEndY(f3);
    }

    public void reset(float f, float f1) {
        this.reset(f, f1, 270.0f, 0.0f);
    }

    public void reset(float f, float f1, float f2, float f3) {
        this.setStartX(f);
        this.setStartY(f1);
        this.setEndX(f);
        this.setEndY(f1);
        this.setCurrentShadowAngle(f2);
        this.setEndShadowAngle((f2 + f3) % 360.0f);
        this.operations.clear();
        this.shadowCompatOperations.clear();
        this.containsIncompatibleShadowOp = false;
    }

    private void setCurrentShadowAngle(float f) {
        this.currentShadowAngle = f;
    }

    private void setEndShadowAngle(float f) {
        this.endShadowAngle = f;
    }

    private void setEndX(float f) {
        this.endX = f;
    }

    private void setEndY(float f) {
        this.endY = f;
    }

    private void setStartX(float f) {
        this.startX = f;
    }

    private void setStartY(float f) {
        this.startY = f;
    }
}

