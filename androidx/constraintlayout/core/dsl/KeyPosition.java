package androidx.constraintlayout.core.dsl;

public class KeyPosition extends Keys {
    public static enum Type {
        CARTESIAN,
        SCREEN,
        PATH;

        private static Type[] $values() [...] // Inlined contents
    }

    private int mFrame;
    private float mPercentHeight;
    private float mPercentWidth;
    private float mPercentX;
    private float mPercentY;
    private Type mPositionType;
    private String mTarget;
    private String mTransitionEasing;

    public KeyPosition(String s, int v) {
        this.mTransitionEasing = null;
        this.mPercentWidth = NaNf;
        this.mPercentHeight = NaNf;
        this.mPercentX = NaNf;
        this.mPercentY = NaNf;
        this.mPositionType = Type.CARTESIAN;
        this.mTarget = s;
        this.mFrame = v;
    }

    public int getFrames() {
        return this.mFrame;
    }

    public float getPercentHeight() {
        return this.mPercentHeight;
    }

    public float getPercentWidth() {
        return this.mPercentWidth;
    }

    public float getPercentX() {
        return this.mPercentX;
    }

    public float getPercentY() {
        return this.mPercentY;
    }

    public Type getPositionType() {
        return this.mPositionType;
    }

    public String getTarget() {
        return this.mTarget;
    }

    public String getTransitionEasing() {
        return this.mTransitionEasing;
    }

    public void setFrames(int v) {
        this.mFrame = v;
    }

    public void setPercentHeight(float f) {
        this.mPercentHeight = f;
    }

    public void setPercentWidth(float f) {
        this.mPercentWidth = f;
    }

    public void setPercentX(float f) {
        this.mPercentX = f;
    }

    public void setPercentY(float f) {
        this.mPercentY = f;
    }

    public void setPositionType(Type keyPosition$Type0) {
        this.mPositionType = keyPosition$Type0;
    }

    public void setTarget(String s) {
        this.mTarget = s;
    }

    public void setTransitionEasing(String s) {
        this.mTransitionEasing = s;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder0 = new StringBuilder();
        stringBuilder0.append("KeyPositions:{\n");
        this.append(stringBuilder0, "target", this.mTarget);
        stringBuilder0.append("frame:");
        stringBuilder0.append(this.mFrame);
        stringBuilder0.append(",\n");
        if(this.mPositionType != null) {
            stringBuilder0.append("type:\'");
            stringBuilder0.append(this.mPositionType);
            stringBuilder0.append("\',\n");
        }
        this.append(stringBuilder0, "easing", this.mTransitionEasing);
        this.append(stringBuilder0, "percentX", this.mPercentX);
        this.append(stringBuilder0, "percentY", this.mPercentY);
        this.append(stringBuilder0, "percentWidth", this.mPercentWidth);
        this.append(stringBuilder0, "percentHeight", this.mPercentHeight);
        stringBuilder0.append("},\n");
        return stringBuilder0.toString();
    }
}

