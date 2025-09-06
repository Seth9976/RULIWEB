package androidx.constraintlayout.core.dsl;

public class KeyAttribute extends Keys {
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
    private float mAlpha;
    private Fit mCurveFit;
    private int mFrame;
    private float mPivotX;
    private float mPivotY;
    private float mRotation;
    private float mRotationX;
    private float mRotationY;
    private float mScaleX;
    private float mScaleY;
    private String mTarget;
    private String mTransitionEasing;
    private float mTransitionPathRotate;
    private float mTranslationX;
    private float mTranslationY;
    private float mTranslationZ;
    private Visibility mVisibility;

    public KeyAttribute(int v, String s) {
        this.TYPE = "KeyAttributes";
        this.mCurveFit = null;
        this.mVisibility = null;
        this.mAlpha = NaNf;
        this.mRotation = NaNf;
        this.mRotationX = NaNf;
        this.mRotationY = NaNf;
        this.mPivotX = NaNf;
        this.mPivotY = NaNf;
        this.mTransitionPathRotate = NaNf;
        this.mScaleX = NaNf;
        this.mScaleY = NaNf;
        this.mTranslationX = NaNf;
        this.mTranslationY = NaNf;
        this.mTranslationZ = NaNf;
        this.mTarget = s;
        this.mFrame = v;
    }

    protected void attributesToString(StringBuilder stringBuilder0) {
        this.append(stringBuilder0, "target", this.mTarget);
        stringBuilder0.append("frame:");
        stringBuilder0.append(this.mFrame);
        stringBuilder0.append(",\n");
        this.append(stringBuilder0, "easing", this.mTransitionEasing);
        if(this.mCurveFit != null) {
            stringBuilder0.append("fit:\'");
            stringBuilder0.append(this.mCurveFit);
            stringBuilder0.append("\',\n");
        }
        if(this.mVisibility != null) {
            stringBuilder0.append("visibility:\'");
            stringBuilder0.append(this.mVisibility);
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

    public float getAlpha() {
        return this.mAlpha;
    }

    public Fit getCurveFit() {
        return this.mCurveFit;
    }

    public float getPivotX() {
        return this.mPivotX;
    }

    public float getPivotY() {
        return this.mPivotY;
    }

    public float getRotation() {
        return this.mRotation;
    }

    public float getRotationX() {
        return this.mRotationX;
    }

    public float getRotationY() {
        return this.mRotationY;
    }

    public float getScaleX() {
        return this.mScaleX;
    }

    public float getScaleY() {
        return this.mScaleY;
    }

    public String getTarget() {
        return this.mTarget;
    }

    public String getTransitionEasing() {
        return this.mTransitionEasing;
    }

    public float getTransitionPathRotate() {
        return this.mTransitionPathRotate;
    }

    public float getTranslationX() {
        return this.mTranslationX;
    }

    public float getTranslationY() {
        return this.mTranslationY;
    }

    public float getTranslationZ() {
        return this.mTranslationZ;
    }

    public Visibility getVisibility() {
        return this.mVisibility;
    }

    public void setAlpha(float f) {
        this.mAlpha = f;
    }

    public void setCurveFit(Fit keyAttribute$Fit0) {
        this.mCurveFit = keyAttribute$Fit0;
    }

    public void setPivotX(float f) {
        this.mPivotX = f;
    }

    public void setPivotY(float f) {
        this.mPivotY = f;
    }

    public void setRotation(float f) {
        this.mRotation = f;
    }

    public void setRotationX(float f) {
        this.mRotationX = f;
    }

    public void setRotationY(float f) {
        this.mRotationY = f;
    }

    public void setScaleX(float f) {
        this.mScaleX = f;
    }

    public void setScaleY(float f) {
        this.mScaleY = f;
    }

    public void setTarget(String s) {
        this.mTarget = s;
    }

    public void setTransitionEasing(String s) {
        this.mTransitionEasing = s;
    }

    public void setTransitionPathRotate(float f) {
        this.mTransitionPathRotate = f;
    }

    public void setTranslationX(float f) {
        this.mTranslationX = f;
    }

    public void setTranslationY(float f) {
        this.mTranslationY = f;
    }

    public void setTranslationZ(float f) {
        this.mTranslationZ = f;
    }

    public void setVisibility(Visibility keyAttribute$Visibility0) {
        this.mVisibility = keyAttribute$Visibility0;
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

