package androidx.vectordrawable.graphics.drawable;

import android.animation.TypeEvaluator;

public class ArgbEvaluator implements TypeEvaluator {
    private static final ArgbEvaluator sInstance;

    static {
        ArgbEvaluator.sInstance = new ArgbEvaluator();
    }

    @Override  // android.animation.TypeEvaluator
    public Object evaluate(float f, Object object0, Object object1) {
        int v = (int)(((Integer)object0));
        float f1 = ((float)(v >> 24 & 0xFF)) / 255.0f;
        int v1 = (int)(((Integer)object1));
        float f2 = (float)Math.pow(((float)(v >> 16 & 0xFF)) / 255.0f, 2.2);
        float f3 = (float)Math.pow(((float)(v >> 8 & 0xFF)) / 255.0f, 2.2);
        float f4 = (float)Math.pow(((float)(v & 0xFF)) / 255.0f, 2.2);
        return (int)(Math.round(((float)Math.pow(f2 + (((float)Math.pow(((float)(v1 >> 16 & 0xFF)) / 255.0f, 2.2)) - f2) * f, 0.454545)) * 255.0f) << 16 | Math.round((f1 + (((float)(v1 >> 24 & 0xFF)) / 255.0f - f1) * f) * 255.0f) << 24 | Math.round(((float)Math.pow(f3 + (((float)Math.pow(((float)(v1 >> 8 & 0xFF)) / 255.0f, 2.2)) - f3) * f, 0.454545)) * 255.0f) << 8 | Math.round(((float)Math.pow(f4 + f * (((float)Math.pow(((float)(v1 & 0xFF)) / 255.0f, 2.2)) - f4), 0.454545)) * 255.0f));
    }

    public static ArgbEvaluator getInstance() {
        return ArgbEvaluator.sInstance;
    }
}

