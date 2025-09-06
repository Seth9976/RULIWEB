package androidx.constraintlayout.core.parser;

public class CLNumber extends CLElement {
    float mValue;

    public CLNumber(float f) {
        super(null);
        this.mValue = f;
    }

    public CLNumber(char[] arr_c) {
        super(arr_c);
        this.mValue = NaNf;
    }

    public static CLElement allocate(char[] arr_c) {
        return new CLNumber(arr_c);
    }

    @Override  // androidx.constraintlayout.core.parser.CLElement
    public boolean equals(Object object0) {
        if(this == object0) {
            return true;
        }
        if(object0 instanceof CLNumber) {
            float f = this.getFloat();
            float f1 = ((CLNumber)object0).getFloat();
            return !Float.isNaN(f) || !Float.isNaN(f1) ? f == f1 : true;
        }
        return false;
    }

    @Override  // androidx.constraintlayout.core.parser.CLElement
    public float getFloat() {
        if(Float.isNaN(this.mValue) && this.hasContent()) {
            this.mValue = Float.parseFloat(this.content());
        }
        return this.mValue;
    }

    @Override  // androidx.constraintlayout.core.parser.CLElement
    public int getInt() {
        if(Float.isNaN(this.mValue) && this.hasContent()) {
            this.mValue = (float)Integer.parseInt(this.content());
        }
        return (int)this.mValue;
    }

    @Override  // androidx.constraintlayout.core.parser.CLElement
    public int hashCode() {
        int v = super.hashCode();
        return this.mValue == 0.0f ? v * 0x1F : v * 0x1F + Float.floatToIntBits(this.mValue);
    }

    public boolean isInt() {
        float f = this.getFloat();
        return ((float)(((int)f))) == f;
    }

    public void putValue(float f) {
        this.mValue = f;
    }

    @Override  // androidx.constraintlayout.core.parser.CLElement
    protected String toFormattedJSON(int v, int v1) {
        StringBuilder stringBuilder0 = new StringBuilder();
        this.addIndent(stringBuilder0, v);
        float f = this.getFloat();
        if(((float)(((int)f))) == f) {
            stringBuilder0.append(((int)f));
            return stringBuilder0.toString();
        }
        stringBuilder0.append(f);
        return stringBuilder0.toString();
    }

    @Override  // androidx.constraintlayout.core.parser.CLElement
    protected String toJSON() {
        float f = this.getFloat();
        return ((float)(((int)f))) == f ? "" + ((int)f) : "" + f;
    }
}

