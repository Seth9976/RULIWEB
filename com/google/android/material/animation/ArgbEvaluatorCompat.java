package com.google.android.material.animation;

import android.animation.TypeEvaluator;

public class ArgbEvaluatorCompat implements TypeEvaluator {
    private static final ArgbEvaluatorCompat instance;

    static {
        ArgbEvaluatorCompat.instance = new ArgbEvaluatorCompat();
    }

    public Integer evaluate(float f, Integer integer0, Integer integer1) {
        int v = (int)integer0;
        float f1 = ((float)(v >> 24 & 0xFF)) / 255.0f;
        int v1 = (int)integer1;
        float f2 = (float)Math.pow(((float)(v >> 16 & 0xFF)) / 255.0f, 2.2);
        float f3 = (float)Math.pow(((float)(v >> 8 & 0xFF)) / 255.0f, 2.2);
        float f4 = (float)Math.pow(((float)(v & 0xFF)) / 255.0f, 2.2);
        return (int)(Math.round(((float)Math.pow(f2 + (((float)Math.pow(((float)(v1 >> 16 & 0xFF)) / 255.0f, 2.2)) - f2) * f, 0.454545)) * 255.0f) << 16 | Math.round((f1 + (((float)(v1 >> 24 & 0xFF)) / 255.0f - f1) * f) * 255.0f) << 24 | Math.round(((float)Math.pow(f3 + (((float)Math.pow(((float)(v1 >> 8 & 0xFF)) / 255.0f, 2.2)) - f3) * f, 0.454545)) * 255.0f) << 8 | Math.round(((float)Math.pow(f4 + f * (((float)Math.pow(((float)(v1 & 0xFF)) / 255.0f, 2.2)) - f4), 0.454545)) * 255.0f));
    }

    @Override  // android.animation.TypeEvaluator
    public Object evaluate(float f, Object object0, Object object1) {
        return this.evaluate(f, ((Integer)object0), ((Integer)object1));
    }

    public static ArgbEvaluatorCompat getInstance() {
        return ArgbEvaluatorCompat.instance;
    }
}

