package androidx.dynamicanimation.animation;

public final class FloatValueHolder {
    private float mValue;

    public FloatValueHolder() {
        this.mValue = 0.0f;
    }

    public FloatValueHolder(float f) {
        this.mValue = 0.0f;
        this.setValue(f);
    }

    public float getValue() {
        return this.mValue;
    }

    public void setValue(float f) {
        this.mValue = f;
    }
}

