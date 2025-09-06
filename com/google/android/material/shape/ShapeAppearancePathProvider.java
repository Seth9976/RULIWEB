package com.google.android.material.shape;

import android.graphics.Matrix;
import android.graphics.Path.Direction;
import android.graphics.Path.Op;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.RectF;

public class ShapeAppearancePathProvider {
    static class Lazy {
        static final ShapeAppearancePathProvider INSTANCE;

        static {
            Lazy.INSTANCE = new ShapeAppearancePathProvider();
        }
    }

    public interface PathListener {
        void onCornerPathCreated(ShapePath arg1, Matrix arg2, int arg3);

        void onEdgePathCreated(ShapePath arg1, Matrix arg2, int arg3);
    }

    static final class ShapeAppearancePathSpec {
        public final RectF bounds;
        public final float interpolation;
        public final Path path;
        public final PathListener pathListener;
        public final ShapeAppearanceModel shapeAppearanceModel;

        ShapeAppearancePathSpec(ShapeAppearanceModel shapeAppearanceModel0, float f, RectF rectF0, PathListener shapeAppearancePathProvider$PathListener0, Path path0) {
            this.pathListener = shapeAppearancePathProvider$PathListener0;
            this.shapeAppearanceModel = shapeAppearanceModel0;
            this.interpolation = f;
            this.bounds = rectF0;
            this.path = path0;
        }
    }

    private final Path boundsPath;
    private final Path cornerPath;
    private final ShapePath[] cornerPaths;
    private final Matrix[] cornerTransforms;
    private boolean edgeIntersectionCheckEnabled;
    private final Path edgePath;
    private final Matrix[] edgeTransforms;
    private final Path overlappedEdgePath;
    private final PointF pointF;
    private final float[] scratch;
    private final float[] scratch2;
    private final ShapePath shapePath;

    public ShapeAppearancePathProvider() {
        this.cornerPaths = new ShapePath[4];
        this.cornerTransforms = new Matrix[4];
        this.edgeTransforms = new Matrix[4];
        this.pointF = new PointF();
        this.overlappedEdgePath = new Path();
        this.boundsPath = new Path();
        this.shapePath = new ShapePath();
        this.scratch = new float[2];
        this.scratch2 = new float[2];
        this.edgePath = new Path();
        this.cornerPath = new Path();
        this.edgeIntersectionCheckEnabled = true;
        for(int v = 0; v < 4; ++v) {
            this.cornerPaths[v] = new ShapePath();
            this.cornerTransforms[v] = new Matrix();
            this.edgeTransforms[v] = new Matrix();
        }
    }

    private float angleOfEdge(int v) [...] // Inlined contents

    private void appendCornerPath(ShapeAppearancePathSpec shapeAppearancePathProvider$ShapeAppearancePathSpec0, int v) {
        this.scratch[0] = this.cornerPaths[v].getStartX();
        this.scratch[1] = this.cornerPaths[v].getStartY();
        this.cornerTransforms[v].mapPoints(this.scratch);
        if(v == 0) {
            shapeAppearancePathProvider$ShapeAppearancePathSpec0.path.moveTo(this.scratch[0], this.scratch[1]);
        }
        else {
            shapeAppearancePathProvider$ShapeAppearancePathSpec0.path.lineTo(this.scratch[0], this.scratch[1]);
        }
        this.cornerPaths[v].applyToPath(this.cornerTransforms[v], shapeAppearancePathProvider$ShapeAppearancePathSpec0.path);
        if(shapeAppearancePathProvider$ShapeAppearancePathSpec0.pathListener != null) {
            shapeAppearancePathProvider$ShapeAppearancePathSpec0.pathListener.onCornerPathCreated(this.cornerPaths[v], this.cornerTransforms[v], v);
        }
    }

    private void appendEdgePath(ShapeAppearancePathSpec shapeAppearancePathProvider$ShapeAppearancePathSpec0, int v) {
        int v1 = (v + 1) % 4;
        this.scratch[0] = this.cornerPaths[v].getEndX();
        this.scratch[1] = this.cornerPaths[v].getEndY();
        this.cornerTransforms[v].mapPoints(this.scratch);
        this.scratch2[0] = this.cornerPaths[v1].getStartX();
        this.scratch2[1] = this.cornerPaths[v1].getStartY();
        this.cornerTransforms[v1].mapPoints(this.scratch2);
        float f = Math.max(((float)Math.hypot(this.scratch[0] - this.scratch2[0], this.scratch[1] - this.scratch2[1])) - 0.001f, 0.0f);
        float f1 = this.getEdgeCenterForIndex(shapeAppearancePathProvider$ShapeAppearancePathSpec0.bounds, v);
        this.shapePath.reset(0.0f, 0.0f);
        EdgeTreatment edgeTreatment0 = this.getEdgeTreatmentForIndex(v, shapeAppearancePathProvider$ShapeAppearancePathSpec0.shapeAppearanceModel);
        edgeTreatment0.getEdgePath(f, f1, shapeAppearancePathProvider$ShapeAppearancePathSpec0.interpolation, this.shapePath);
        this.edgePath.reset();
        this.shapePath.applyToPath(this.edgeTransforms[v], this.edgePath);
        if(!this.edgeIntersectionCheckEnabled || !edgeTreatment0.forceIntersection() && !this.pathOverlapsCorner(this.edgePath, v) && !this.pathOverlapsCorner(this.edgePath, v1)) {
            this.shapePath.applyToPath(this.edgeTransforms[v], shapeAppearancePathProvider$ShapeAppearancePathSpec0.path);
        }
        else {
            this.edgePath.op(this.edgePath, this.boundsPath, Path.Op.DIFFERENCE);
            this.scratch[0] = this.shapePath.getStartX();
            this.scratch[1] = this.shapePath.getStartY();
            this.edgeTransforms[v].mapPoints(this.scratch);
            this.overlappedEdgePath.moveTo(this.scratch[0], this.scratch[1]);
            this.shapePath.applyToPath(this.edgeTransforms[v], this.overlappedEdgePath);
        }
        if(shapeAppearancePathProvider$ShapeAppearancePathSpec0.pathListener != null) {
            shapeAppearancePathProvider$ShapeAppearancePathSpec0.pathListener.onEdgePathCreated(this.shapePath, this.edgeTransforms[v], v);
        }
    }

    public void calculatePath(ShapeAppearanceModel shapeAppearanceModel0, float f, RectF rectF0, Path path0) {
        this.calculatePath(shapeAppearanceModel0, f, rectF0, null, path0);
    }

    public void calculatePath(ShapeAppearanceModel shapeAppearanceModel0, float f, RectF rectF0, PathListener shapeAppearancePathProvider$PathListener0, Path path0) {
        path0.rewind();
        this.overlappedEdgePath.rewind();
        this.boundsPath.rewind();
        this.boundsPath.addRect(rectF0, Path.Direction.CW);
        ShapeAppearancePathSpec shapeAppearancePathProvider$ShapeAppearancePathSpec0 = new ShapeAppearancePathSpec(shapeAppearanceModel0, f, rectF0, shapeAppearancePathProvider$PathListener0, path0);
        for(int v1 = 0; v1 < 4; ++v1) {
            this.setCornerPathAndTransform(shapeAppearancePathProvider$ShapeAppearancePathSpec0, v1);
            this.setEdgePathAndTransform(v1);
        }
        for(int v = 0; v < 4; ++v) {
            this.appendCornerPath(shapeAppearancePathProvider$ShapeAppearancePathSpec0, v);
            this.appendEdgePath(shapeAppearancePathProvider$ShapeAppearancePathSpec0, v);
        }
        path0.close();
        this.overlappedEdgePath.close();
        if(!this.overlappedEdgePath.isEmpty()) {
            path0.op(this.overlappedEdgePath, Path.Op.UNION);
        }
    }

    private void getCoordinatesOfCorner(int v, RectF rectF0, PointF pointF0) {
        switch(v) {
            case 1: {
                pointF0.set(rectF0.right, rectF0.bottom);
                return;
            }
            case 2: {
                pointF0.set(rectF0.left, rectF0.bottom);
                return;
            }
            case 3: {
                pointF0.set(rectF0.left, rectF0.top);
                return;
            }
            default: {
                pointF0.set(rectF0.right, rectF0.top);
            }
        }
    }

    private CornerSize getCornerSizeForIndex(int v, ShapeAppearanceModel shapeAppearanceModel0) {
        switch(v) {
            case 1: {
                return shapeAppearanceModel0.getBottomRightCornerSize();
            }
            case 2: {
                return shapeAppearanceModel0.getBottomLeftCornerSize();
            }
            case 3: {
                return shapeAppearanceModel0.getTopLeftCornerSize();
            }
            default: {
                return shapeAppearanceModel0.getTopRightCornerSize();
            }
        }
    }

    private CornerTreatment getCornerTreatmentForIndex(int v, ShapeAppearanceModel shapeAppearanceModel0) {
        switch(v) {
            case 1: {
                return shapeAppearanceModel0.getBottomRightCorner();
            }
            case 2: {
                return shapeAppearanceModel0.getBottomLeftCorner();
            }
            case 3: {
                return shapeAppearanceModel0.getTopLeftCorner();
            }
            default: {
                return shapeAppearanceModel0.getTopRightCorner();
            }
        }
    }

    private float getEdgeCenterForIndex(RectF rectF0, int v) {
        this.scratch[0] = this.cornerPaths[v].endX;
        this.scratch[1] = this.cornerPaths[v].endY;
        this.cornerTransforms[v].mapPoints(this.scratch);
        return v == 1 || v == 3 ? Math.abs(rectF0.centerX() - this.scratch[0]) : Math.abs(rectF0.centerY() - this.scratch[1]);
    }

    private EdgeTreatment getEdgeTreatmentForIndex(int v, ShapeAppearanceModel shapeAppearanceModel0) {
        switch(v) {
            case 1: {
                return shapeAppearanceModel0.getBottomEdge();
            }
            case 2: {
                return shapeAppearanceModel0.getLeftEdge();
            }
            case 3: {
                return shapeAppearanceModel0.getTopEdge();
            }
            default: {
                return shapeAppearanceModel0.getRightEdge();
            }
        }
    }

    public static ShapeAppearancePathProvider getInstance() {
        return Lazy.INSTANCE;
    }

    private boolean pathOverlapsCorner(Path path0, int v) {
        this.cornerPath.reset();
        this.cornerPaths[v].applyToPath(this.cornerTransforms[v], this.cornerPath);
        RectF rectF0 = new RectF();
        path0.computeBounds(rectF0, true);
        this.cornerPath.computeBounds(rectF0, true);
        path0.op(this.cornerPath, Path.Op.INTERSECT);
        path0.computeBounds(rectF0, true);
        return !rectF0.isEmpty() || rectF0.width() > 1.0f && rectF0.height() > 1.0f;
    }

    private void setCornerPathAndTransform(ShapeAppearancePathSpec shapeAppearancePathProvider$ShapeAppearancePathSpec0, int v) {
        CornerSize cornerSize0 = this.getCornerSizeForIndex(v, shapeAppearancePathProvider$ShapeAppearancePathSpec0.shapeAppearanceModel);
        this.getCornerTreatmentForIndex(v, shapeAppearancePathProvider$ShapeAppearancePathSpec0.shapeAppearanceModel).getCornerPath(this.cornerPaths[v], 90.0f, shapeAppearancePathProvider$ShapeAppearancePathSpec0.interpolation, shapeAppearancePathProvider$ShapeAppearancePathSpec0.bounds, cornerSize0);
        this.cornerTransforms[v].reset();
        this.getCoordinatesOfCorner(v, shapeAppearancePathProvider$ShapeAppearancePathSpec0.bounds, this.pointF);
        this.cornerTransforms[v].setTranslate(this.pointF.x, this.pointF.y);
        this.cornerTransforms[v].preRotate(((float)((v + 1) % 4 * 90)));
    }

    void setEdgeIntersectionCheckEnable(boolean z) {
        this.edgeIntersectionCheckEnabled = z;
    }

    private void setEdgePathAndTransform(int v) {
        this.scratch[0] = this.cornerPaths[v].getEndX();
        this.scratch[1] = this.cornerPaths[v].getEndY();
        this.cornerTransforms[v].mapPoints(this.scratch);
        this.edgeTransforms[v].reset();
        this.edgeTransforms[v].setTranslate(this.scratch[0], this.scratch[1]);
        this.edgeTransforms[v].preRotate(((float)((v + 1) % 4 * 90)));
    }
}

