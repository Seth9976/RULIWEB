package com.google.android.material.transition;

import android.graphics.Canvas;
import android.graphics.Path.Op;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Region.Op;
import android.os.Build.VERSION;
import com.google.android.material.shape.ShapeAppearanceModel;
import com.google.android.material.shape.ShapeAppearancePathProvider;

class MaskEvaluator {
    private ShapeAppearanceModel currentShapeAppearanceModel;
    private final Path endPath;
    private final Path path;
    private final ShapeAppearancePathProvider pathProvider;
    private final Path startPath;

    MaskEvaluator() {
        this.path = new Path();
        this.startPath = new Path();
        this.endPath = new Path();
        this.pathProvider = ShapeAppearancePathProvider.getInstance();
    }

    void clip(Canvas canvas0) {
        if(Build.VERSION.SDK_INT >= 23) {
            canvas0.clipPath(this.path);
            return;
        }
        canvas0.clipPath(this.startPath);
        canvas0.clipPath(this.endPath, Region.Op.UNION);
    }

    void evaluate(float f, ShapeAppearanceModel shapeAppearanceModel0, ShapeAppearanceModel shapeAppearanceModel1, RectF rectF0, RectF rectF1, RectF rectF2, ProgressThresholds materialContainerTransform$ProgressThresholds0) {
        ShapeAppearanceModel shapeAppearanceModel2 = TransitionUtils.lerp(shapeAppearanceModel0, shapeAppearanceModel1, rectF0, rectF2, materialContainerTransform$ProgressThresholds0.getStart(), materialContainerTransform$ProgressThresholds0.getEnd(), f);
        this.currentShapeAppearanceModel = shapeAppearanceModel2;
        this.pathProvider.calculatePath(shapeAppearanceModel2, 1.0f, rectF1, this.startPath);
        this.pathProvider.calculatePath(this.currentShapeAppearanceModel, 1.0f, rectF2, this.endPath);
        if(Build.VERSION.SDK_INT >= 23) {
            this.path.op(this.startPath, this.endPath, Path.Op.UNION);
        }
    }

    ShapeAppearanceModel getCurrentShapeAppearanceModel() {
        return this.currentShapeAppearanceModel;
    }

    Path getPath() {
        return this.path;
    }
}

