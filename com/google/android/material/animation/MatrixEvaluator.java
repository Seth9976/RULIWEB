package com.google.android.material.animation;

import android.animation.TypeEvaluator;
import android.graphics.Matrix;

public class MatrixEvaluator implements TypeEvaluator {
    private final float[] tempEndValues;
    private final Matrix tempMatrix;
    private final float[] tempStartValues;

    public MatrixEvaluator() {
        this.tempStartValues = new float[9];
        this.tempEndValues = new float[9];
        this.tempMatrix = new Matrix();
    }

    public Matrix evaluate(float f, Matrix matrix0, Matrix matrix1) {
        matrix0.getValues(this.tempStartValues);
        matrix1.getValues(this.tempEndValues);
        for(int v = 0; v < 9; ++v) {
            float f1 = this.tempEndValues[v];
            float f2 = this.tempStartValues[v];
            this.tempEndValues[v] = f2 + (f1 - f2) * f;
        }
        this.tempMatrix.setValues(this.tempEndValues);
        return this.tempMatrix;
    }

    @Override  // android.animation.TypeEvaluator
    public Object evaluate(float f, Object object0, Object object1) {
        return this.evaluate(f, ((Matrix)object0), ((Matrix)object1));
    }
}

