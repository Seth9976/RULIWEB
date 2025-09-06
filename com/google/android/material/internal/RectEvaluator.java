package com.google.android.material.internal;

import android.animation.TypeEvaluator;
import android.graphics.Rect;

public class RectEvaluator implements TypeEvaluator {
    private final Rect rect;

    public RectEvaluator(Rect rect0) {
        this.rect = rect0;
    }

    public Rect evaluate(float f, Rect rect0, Rect rect1) {
        this.rect.set(rect0.left + ((int)(((float)(rect1.left - rect0.left)) * f)), rect0.top + ((int)(((float)(rect1.top - rect0.top)) * f)), rect0.right + ((int)(((float)(rect1.right - rect0.right)) * f)), rect0.bottom + ((int)(((float)(rect1.bottom - rect0.bottom)) * f)));
        return this.rect;
    }

    @Override  // android.animation.TypeEvaluator
    public Object evaluate(float f, Object object0, Object object1) {
        return this.evaluate(f, ((Rect)object0), ((Rect)object1));
    }
}

