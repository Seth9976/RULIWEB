package androidx.constraintlayout.core.dsl;

import java.util.Arrays;

public class KeyPositions extends Keys {
    public static enum Type {
        CARTESIAN,
        SCREEN,
        PATH;

        private static Type[] $values() [...] // Inlined contents
    }

    private int[] mFrames;
    private float[] mPercentHeight;
    private float[] mPercentWidth;
    private float[] mPercentX;
    private float[] mPercentY;
    private Type mPositionType;
    private String[] mTarget;
    private String mTransitionEasing;

    public KeyPositions(int v, String[] arr_s) {
        this.mTransitionEasing = null;
        this.mPositionType = null;
        this.mFrames = null;
        this.mPercentWidth = null;
        this.mPercentHeight = null;
        this.mPercentX = null;
        this.mPercentY = null;
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

    public int[] getFrames() {
        return this.mFrames;
    }

    public float[] getPercentHeight() {
        return this.mPercentHeight;
    }

    public float[] getPercentWidth() {
        return this.mPercentWidth;
    }

    public float[] getPercentX() {
        return this.mPercentX;
    }

    public float[] getPercentY() {
        return this.mPercentY;
    }

    public Type getPositionType() {
        return this.mPositionType;
    }

    public String[] getTarget() {
        return this.mTarget;
    }

    public String getTransitionEasing() {
        return this.mTransitionEasing;
    }

    public void setFrames(int[] arr_v) {
        this.mFrames = arr_v;
    }

    public void setPercentHeight(float[] arr_f) {
        this.mPercentHeight = arr_f;
    }

    public void setPercentWidth(float[] arr_f) {
        this.mPercentWidth = arr_f;
    }

    public void setPercentX(float[] arr_f) {
        this.mPercentX = arr_f;
    }

    public void setPercentY(float[] arr_f) {
        this.mPercentY = arr_f;
    }

    public void setPositionType(Type keyPositions$Type0) {
        this.mPositionType = keyPositions$Type0;
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
        stringBuilder0.append(Arrays.toString(this.mFrames));
        stringBuilder0.append(",\n");
        if(this.mPositionType != null) {
            stringBuilder0.append("type:\'");
            stringBuilder0.append(this.mPositionType);
            stringBuilder0.append("\',\n");
        }
        this.append(stringBuilder0, "easing", this.mTransitionEasing);
        this.append(stringBuilder0, "percentX", this.mPercentX);
        this.append(stringBuilder0, "percentX", this.mPercentY);
        this.append(stringBuilder0, "percentWidth", this.mPercentWidth);
        this.append(stringBuilder0, "percentHeight", this.mPercentHeight);
        stringBuilder0.append("},\n");
        return stringBuilder0.toString();
    }
}

