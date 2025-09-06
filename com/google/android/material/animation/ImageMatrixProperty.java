package com.google.android.material.animation;

import android.graphics.Matrix;
import android.util.Property;
import android.widget.ImageView;

public class ImageMatrixProperty extends Property {
    private final Matrix matrix;

    public ImageMatrixProperty() {
        super(Matrix.class, "imageMatrixProperty");
        this.matrix = new Matrix();
    }

    public Matrix get(ImageView imageView0) {
        Matrix matrix0 = imageView0.getImageMatrix();
        this.matrix.set(matrix0);
        return this.matrix;
    }

    @Override  // android.util.Property
    public Object get(Object object0) {
        return this.get(((ImageView)object0));
    }

    public void set(ImageView imageView0, Matrix matrix0) {
        imageView0.setImageMatrix(matrix0);
    }

    @Override  // android.util.Property
    public void set(Object object0, Object object1) {
        this.set(((ImageView)object0), ((Matrix)object1));
    }
}

