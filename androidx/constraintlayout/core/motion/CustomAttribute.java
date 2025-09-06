package androidx.constraintlayout.core.motion;

public class CustomAttribute {
    public static enum AttributeType {
        INT_TYPE,
        FLOAT_TYPE,
        COLOR_TYPE,
        COLOR_DRAWABLE_TYPE,
        STRING_TYPE,
        BOOLEAN_TYPE,
        DIMENSION_TYPE,
        REFERENCE_TYPE;

        private static AttributeType[] $values() [...] // Inlined contents
    }

    private static final String TAG = "TransitionLayout";
    boolean mBooleanValue;
    private int mColorValue;
    private float mFloatValue;
    private int mIntegerValue;
    private boolean mMethod;
    String mName;
    private String mStringValue;
    private AttributeType mType;

    public CustomAttribute(CustomAttribute customAttribute0, Object object0) {
        this.mMethod = false;
        this.mName = customAttribute0.mName;
        this.mType = customAttribute0.mType;
        this.setValue(object0);
    }

    public CustomAttribute(String s, AttributeType customAttribute$AttributeType0) {
        this.mMethod = false;
        this.mName = s;
        this.mType = customAttribute$AttributeType0;
    }

    public CustomAttribute(String s, AttributeType customAttribute$AttributeType0, Object object0, boolean z) {
        this.mName = s;
        this.mType = customAttribute$AttributeType0;
        this.mMethod = z;
        this.setValue(object0);
    }

    private static int clamp(int v) {
        int v1 = (v & ~(v >> 0x1F)) - 0xFF;
        return (v1 & v1 >> 0x1F) + 0xFF;
    }

    public boolean diff(CustomAttribute customAttribute0) {
        if(customAttribute0 != null) {
            AttributeType customAttribute$AttributeType0 = this.mType;
            if(customAttribute$AttributeType0 == customAttribute0.mType) {
                switch(customAttribute$AttributeType0.ordinal()) {
                    case 1: {
                        return this.mFloatValue == customAttribute0.mFloatValue;
                    }
                    case 2: 
                    case 3: {
                        return this.mColorValue == customAttribute0.mColorValue;
                    }
                    case 4: {
                        return this.mIntegerValue == customAttribute0.mIntegerValue;
                    }
                    case 5: {
                        return this.mBooleanValue == customAttribute0.mBooleanValue;
                    }
                    case 6: {
                        return this.mFloatValue == customAttribute0.mFloatValue;
                    }
                    case 0: 
                    case 7: {
                        return this.mIntegerValue == customAttribute0.mIntegerValue;
                    }
                    default: {
                        return false;
                    }
                }
            }
        }
        return false;
    }

    public AttributeType getType() {
        return this.mType;
    }

    public float getValueToInterpolate() {
        switch(this.mType.ordinal()) {
            case 0: {
                return (float)this.mIntegerValue;
            }
            case 1: {
                return this.mFloatValue;
            }
            case 2: 
            case 3: {
                throw new RuntimeException("Color does not have a single color to interpolate");
            }
            case 4: {
                throw new RuntimeException("Cannot interpolate String");
            }
            case 5: {
                return this.mBooleanValue ? 1.0f : 0.0f;
            }
            case 6: {
                return this.mFloatValue;
            }
            default: {
                return NaNf;
            }
        }
    }

    public void getValuesToInterpolate(float[] arr_f) {
        switch(this.mType.ordinal()) {
            case 0: {
                arr_f[0] = (float)this.mIntegerValue;
                return;
            }
            case 1: {
                arr_f[0] = this.mFloatValue;
                return;
            }
            case 2: 
            case 3: {
                int v = this.mColorValue >> 24 & 0xFF;
                float f = (float)Math.pow(((float)(this.mColorValue >> 8 & 0xFF)) / 255.0f, 2.2);
                float f1 = (float)Math.pow(((float)(this.mColorValue & 0xFF)) / 255.0f, 2.2);
                arr_f[0] = (float)Math.pow(((float)(this.mColorValue >> 16 & 0xFF)) / 255.0f, 2.2);
                arr_f[1] = f;
                arr_f[2] = f1;
                arr_f[3] = ((float)v) / 255.0f;
                return;
            }
            case 4: {
                throw new RuntimeException("Color does not have a single color to interpolate");
            }
            case 5: {
                arr_f[0] = this.mBooleanValue ? 1.0f : 0.0f;
                return;
            }
            case 6: {
                arr_f[0] = this.mFloatValue;
            }
        }
    }

    public static int hsvToRgb(float f, float f1, float f2) {
        float f3 = f * 6.0f - ((float)(((int)(f * 6.0f))));
        int v = (int)((1.0f - f1) * (f2 * 255.0f) + 0.5f);
        int v1 = (int)((1.0f - f3 * f1) * (f2 * 255.0f) + 0.5f);
        int v2 = (int)((1.0f - (1.0f - f3) * f1) * (f2 * 255.0f) + 0.5f);
        int v3 = (int)(f2 * 255.0f + 0.5f);
        switch(((int)(f * 6.0f))) {
            case 0: {
                return (v3 << 16) + (v2 << 8) + v | 0xFF000000;
            }
            case 1: {
                return (v1 << 16) + (v3 << 8) + v | 0xFF000000;
            }
            case 2: {
                return (v << 16) + (v3 << 8) + v2 | 0xFF000000;
            }
            case 3: {
                return (v << 16) + (v1 << 8) + v3 | 0xFF000000;
            }
            case 4: {
                return (v2 << 16) + (v << 8) + v3 | 0xFF000000;
            }
            case 5: {
                return (v3 << 16) + (v << 8) + v1 | 0xFF000000;
            }
            default: {
                return 0;
            }
        }
    }

    public boolean isContinuous() {
        switch(this.mType.ordinal()) {
            case 4: 
            case 5: 
            case 7: {
                return false;
            }
            default: {
                return true;
            }
        }
    }

    public int numberOfInterpolatedValues() {
        switch(this.mType.ordinal()) {
            case 2: 
            case 3: {
                return 4;
            }
            default: {
                return 1;
            }
        }
    }

    public void setColorValue(int v) {
        this.mColorValue = v;
    }

    public void setFloatValue(float f) {
        this.mFloatValue = f;
    }

    public void setIntValue(int v) {
        this.mIntegerValue = v;
    }

    public void setStringValue(String s) {
        this.mStringValue = s;
    }

    public void setValue(Object object0) {
        switch(this.mType.ordinal()) {
            case 1: {
                this.mFloatValue = (float)(((Float)object0));
                return;
            }
            case 2: 
            case 3: {
                this.mColorValue = (int)(((Integer)object0));
                return;
            }
            case 4: {
                this.mStringValue = (String)object0;
                return;
            }
            case 5: {
                this.mBooleanValue = ((Boolean)object0).booleanValue();
                return;
            }
            case 6: {
                this.mFloatValue = (float)(((Float)object0));
                return;
            }
            case 0: 
            case 7: {
                this.mIntegerValue = (int)(((Integer)object0));
            }
        }
    }

    public void setValue(float[] arr_f) {
        boolean z = true;
        switch(this.mType.ordinal()) {
            case 1: {
                this.mFloatValue = arr_f[0];
                return;
            }
            case 2: 
            case 3: {
                this.mColorValue = CustomAttribute.clamp(((int)(arr_f[3] * 255.0f))) << 24 | CustomAttribute.hsvToRgb(arr_f[0], arr_f[1], arr_f[2]) & 0xFFFFFF;
                return;
            }
            case 4: {
                throw new RuntimeException("Color does not have a single color to interpolate");
            }
            case 5: {
                if(((double)arr_f[0]) <= 0.5) {
                    z = false;
                }
                this.mBooleanValue = z;
                return;
            }
            case 6: {
                this.mFloatValue = arr_f[0];
                return;
            }
            case 0: 
            case 7: {
                this.mIntegerValue = (int)arr_f[0];
            }
        }
    }
}

