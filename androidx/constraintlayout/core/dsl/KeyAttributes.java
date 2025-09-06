package androidx.constraintlayout.core.dsl;

import java.util.Arrays;

public class KeyAttributes extends Keys {
    public static enum Fit {
        SPLINE,
        LINEAR;

        private static Fit[] $values() [...] // Inlined contents
    }

    public static enum Visibility {
        VISIBLE,
        INVISIBLE,
        GONE;

        private static Visibility[] $values() [...] // Inlined contents
    }

    protected String TYPE;
    private float[] mAlpha;
    private Fit mCurveFit;
    private int[] mFrames;
    private float[] mPivotX;
    private float[] mPivotY;
    private float[] mRotation;
    private float[] mRotationX;
    private float[] mRotationY;
    private float[] mScaleX;
    private float[] mScaleY;
    private String[] mTarget;
    private String mTransitionEasing;
    private float[] mTransitionPathRotate;
    private float[] mTranslationX;
    private float[] mTranslationY;
    private float[] mTranslationZ;
    private Visibility[] mVisibility;

    KeyAttributes(int v, String[] arr_s) {
        this.TYPE = "KeyAttributes";
        this.mCurveFit = null;
        this.mFrames = null;
        this.mVisibility = null;
        this.mAlpha = null;
        this.mRotation = null;
        this.mRotationX = null;
        this.mRotationY = null;
        this.mPivotX = null;
        this.mPivotY = null;
        this.mTransitionPathRotate = null;
        this.mScaleX = null;
        this.mScaleY = null;
        this.mTranslationX = null;
        this.mTranslationY = null;
        this.mTranslationZ = null;
        this.mTarget = arr_s;
        int[] arr_v = new int[v];
        this.mFrames = arr_v;
        float f = 100.0f / ((float)(arr_v.length + 1));
        for(int v1 = 0; true; ++v1) {
            int[] arr_v1 = this.mFrames;
            if(v1 >= arr_v1.length) {
                break;
            }
            arr_v1[v1] = (int)(((float)v1) * f + f);
        }
    }

    protected void attributesToString(StringBuilder stringBuilder0) {
        this.append(stringBuilder0, "target", this.mTarget);
        stringBuilder0.append("frame:");
        stringBuilder0.append(Arrays.toString(this.mFrames));
        stringBuilder0.append(",\n");
        this.append(stringBuilder0, "easing", this.mTransitionEasing);
        if(this.mCurveFit != null) {
            stringBuilder0.append("fit:\'");
            stringBuilder0.append(this.mCurveFit);
            stringBuilder0.append("\',\n");
        }
        if(this.mVisibility != null) {
            stringBuilder0.append("visibility:\'");
            stringBuilder0.append(Arrays.toString(this.mVisibility));
            stringBuilder0.append("\',\n");
        }
        this.append(stringBuilder0, "alpha", this.mAlpha);
        this.append(stringBuilder0, "rotationX", this.mRotationX);
        this.append(stringBuilder0, "rotationY", this.mRotationY);
        this.append(stringBuilder0, "rotationZ", this.mRotation);
        this.append(stringBuilder0, "pivotX", this.mPivotX);
        this.append(stringBuilder0, "pivotY", this.mPivotY);
        this.append(stringBuilder0, "pathRotate", this.mTransitionPathRotate);
        this.append(stringBuilder0, "scaleX", this.mScaleX);
        this.append(stringBuilder0, "scaleY", this.mScaleY);
        this.append(stringBuilder0, "translationX", this.mTranslationX);
        this.append(stringBuilder0, "translationY", this.mTranslationY);
        this.append(stringBuilder0, "translationZ", this.mTranslationZ);
    }

    public float[] getAlpha() {
        return this.mAlpha;
    }

    public Fit getCurveFit() {
        return this.mCurveFit;
    }

    public float[] getPivotX() {
        return this.mPivotX;
    }

    public float[] getPivotY() {
        return this.mPivotY;
    }

    public float[] getRotation() {
        return this.mRotation;
    }

    public float[] getRotationX() {
        return this.mRotationX;
    }

    public float[] getRotationY() {
        return this.mRotationY;
    }

    public float[] getScaleX() {
        return this.mScaleX;
    }

    public float[] getScaleY() {
        return this.mScaleY;
    }

    public String[] getTarget() {
        return this.mTarget;
    }

    public String getTransitionEasing() {
        return this.mTransitionEasing;
    }

    public float[] getTransitionPathRotate() {
        return this.mTransitionPathRotate;
    }

    public float[] getTranslationX() {
        return this.mTranslationX;
    }

    public float[] getTranslationY() {
        return this.mTranslationY;
    }

    public float[] getTranslationZ() {
        return this.mTranslationZ;
    }

    public Visibility[] getVisibility() {
        return this.mVisibility;
    }

    public void setAlpha(float[] arr_f) {
        this.mAlpha = arr_f;
    }

    public void setCurveFit(Fit keyAttributes$Fit0) {
        this.mCurveFit = keyAttributes$Fit0;
    }

    public void setPivotX(float[] arr_f) {
        this.mPivotX = arr_f;
    }

    public void setPivotY(float[] arr_f) {
        this.mPivotY = arr_f;
    }

    public void setRotation(float[] arr_f) {
        this.mRotation = arr_f;
    }

    public void setRotationX(float[] arr_f) {
        this.mRotationX = arr_f;
    }

    public void setRotationY(float[] arr_f) {
        this.mRotationY = arr_f;
    }

    public void setScaleX(float[] arr_f) {
        this.mScaleX = arr_f;
    }

    public void setScaleY(float[] arr_f) {
        this.mScaleY = arr_f;
    }

    public void setTarget(String[] arr_s) {
        this.mTarget = arr_s;
    }

    public void setTransitionEasing(String s) {
        this.mTransitionEasing = s;
    }

    public void setTransitionPathRotate(float[] arr_f) {
        this.mTransitionPathRotate = arr_f;
    }

    public void setTranslationX(float[] arr_f) {
        this.mTranslationX = arr_f;
    }

    public void setTranslationY(float[] arr_f) {
        this.mTranslationY = arr_f;
    }

    public void setTranslationZ(float[] arr_f) {
        this.mTranslationZ = arr_f;
    }

    public void setVisibility(Visibility[] arr_keyAttributes$Visibility) {
        this.mVisibility = arr_keyAttributes$Visibility;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder0 = new StringBuilder();
        stringBuilder0.append(this.TYPE);
        stringBuilder0.append(":{\n");
        this.attributesToString(stringBuilder0);
        stringBuilder0.append("},\n");
        return stringBuilder0.toString();
    }
}

