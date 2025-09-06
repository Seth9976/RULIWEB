package androidx.constraintlayout.core.motion.utils;

public class VelocityMatrix {
    float mDRotate;
    float mDScaleX;
    float mDScaleY;
    float mDTranslateX;
    float mDTranslateY;
    float mRotate;
    private static String sTag = "VelocityMatrix";

    static {
    }

    public void applyTransform(float f, float f1, int v, int v1, float[] arr_f) {
        float f2 = (f - 0.5f) * 2.0f;
        float f3 = (f1 - 0.5f) * 2.0f;
        float f4 = arr_f[0] + this.mDTranslateX + this.mDScaleX * f2;
        float f5 = (float)Math.toRadians(this.mDRotate);
        double f6 = (double)(((float)Math.toRadians(this.mRotate)));
        double f7 = (double)(((float)v1) * f3);
        float f8 = arr_f[1] + this.mDTranslateY + this.mDScaleY * f3 + f5 * ((float)(((double)(((float)v) * f2)) * Math.cos(f6) - f7 * Math.sin(f6)));
        arr_f[0] = f4 + ((float)(((double)(((float)(-v)) * f2)) * Math.sin(f6) - Math.cos(f6) * f7)) * f5;
        arr_f[1] = f8;
    }

    public void clear() {
        this.mDRotate = 0.0f;
        this.mDTranslateY = 0.0f;
        this.mDTranslateX = 0.0f;
        this.mDScaleY = 0.0f;
        this.mDScaleX = 0.0f;
    }

    public void setRotationVelocity(KeyCycleOscillator keyCycleOscillator0, float f) {
        if(keyCycleOscillator0 != null) {
            this.mDRotate = keyCycleOscillator0.getSlope(f);
        }
    }

    public void setRotationVelocity(SplineSet splineSet0, float f) {
        if(splineSet0 != null) {
            this.mDRotate = splineSet0.getSlope(f);
            this.mRotate = splineSet0.get(f);
        }
    }

    public void setScaleVelocity(KeyCycleOscillator keyCycleOscillator0, KeyCycleOscillator keyCycleOscillator1, float f) {
        if(keyCycleOscillator0 != null) {
            this.mDScaleX = keyCycleOscillator0.getSlope(f);
        }
        if(keyCycleOscillator1 != null) {
            this.mDScaleY = keyCycleOscillator1.getSlope(f);
        }
    }

    public void setScaleVelocity(SplineSet splineSet0, SplineSet splineSet1, float f) {
        if(splineSet0 != null) {
            this.mDScaleX = splineSet0.getSlope(f);
        }
        if(splineSet1 != null) {
            this.mDScaleY = splineSet1.getSlope(f);
        }
    }

    public void setTranslationVelocity(KeyCycleOscillator keyCycleOscillator0, KeyCycleOscillator keyCycleOscillator1, float f) {
        if(keyCycleOscillator0 != null) {
            this.mDTranslateX = keyCycleOscillator0.getSlope(f);
        }
        if(keyCycleOscillator1 != null) {
            this.mDTranslateY = keyCycleOscillator1.getSlope(f);
        }
    }

    public void setTranslationVelocity(SplineSet splineSet0, SplineSet splineSet1, float f) {
        if(splineSet0 != null) {
            this.mDTranslateX = splineSet0.getSlope(f);
        }
        if(splineSet1 != null) {
            this.mDTranslateY = splineSet1.getSlope(f);
        }
    }
}

