package androidx.constraintlayout.core.motion;

public class CustomVariable {
    private static final String TAG = "TransitionLayout";
    boolean mBooleanValue;
    private float mFloatValue;
    private int mIntegerValue;
    String mName;
    private String mStringValue;
    private int mType;

    public CustomVariable(CustomVariable customVariable0) {
        this.mName = customVariable0.mName;
        this.mType = customVariable0.mType;
        this.mIntegerValue = customVariable0.mIntegerValue;
        this.mFloatValue = customVariable0.mFloatValue;
        this.mStringValue = customVariable0.mStringValue;
        this.mBooleanValue = customVariable0.mBooleanValue;
    }

    public CustomVariable(CustomVariable customVariable0, Object object0) {
        this.mIntegerValue = 0x80000000;
        this.mFloatValue = NaNf;
        this.mStringValue = null;
        this.mName = customVariable0.mName;
        this.mType = customVariable0.mType;
        this.setValue(object0);
    }

    public CustomVariable(String s, int v) {
        this.mIntegerValue = 0x80000000;
        this.mFloatValue = NaNf;
        this.mStringValue = null;
        this.mName = s;
        this.mType = v;
    }

    public CustomVariable(String s, int v, float f) {
        this.mIntegerValue = 0x80000000;
        this.mStringValue = null;
        this.mName = s;
        this.mType = v;
        this.mFloatValue = f;
    }

    public CustomVariable(String s, int v, int v1) {
        this.mIntegerValue = 0x80000000;
        this.mFloatValue = NaNf;
        this.mStringValue = null;
        this.mName = s;
        this.mType = v;
        if(v == 901) {
            this.mFloatValue = (float)v1;
            return;
        }
        this.mIntegerValue = v1;
    }

    public CustomVariable(String s, int v, Object object0) {
        this.mIntegerValue = 0x80000000;
        this.mFloatValue = NaNf;
        this.mStringValue = null;
        this.mName = s;
        this.mType = v;
        this.setValue(object0);
    }

    public CustomVariable(String s, int v, String s1) {
        this.mIntegerValue = 0x80000000;
        this.mFloatValue = NaNf;
        this.mName = s;
        this.mType = v;
        this.mStringValue = s1;
    }

    public CustomVariable(String s, int v, boolean z) {
        this.mIntegerValue = 0x80000000;
        this.mFloatValue = NaNf;
        this.mStringValue = null;
        this.mName = s;
        this.mType = v;
        this.mBooleanValue = z;
    }

    public void applyToWidget(MotionWidget motionWidget0) {
        int v = this.mType;
        switch(v) {
            case 903: {
                motionWidget0.setCustomAttribute(this.mName, 903, this.mStringValue);
                return;
            }
            case 904: {
                motionWidget0.setCustomAttribute(this.mName, 904, this.mBooleanValue);
                return;
            }
            case 901: 
            case 905: {
                motionWidget0.setCustomAttribute(this.mName, v, this.mFloatValue);
                return;
            }
            case 900: 
            case 902: 
            case 906: {
                motionWidget0.setCustomAttribute(this.mName, v, this.mIntegerValue);
            }
        }
    }

    private static int clamp(int v) {
        int v1 = (v & ~(v >> 0x1F)) - 0xFF;
        return (v1 & v1 >> 0x1F) + 0xFF;
    }

    public static String colorString(int v) {
        String s = "00000000" + Integer.toHexString(v);
        return "#" + s.substring(s.length() - 8);
    }

    public CustomVariable copy() {
        return new CustomVariable(this);
    }

    public boolean diff(CustomVariable customVariable0) {
        if(customVariable0 != null) {
            int v = this.mType;
            if(v == customVariable0.mType) {
                switch(v) {
                    case 901: {
                        return this.mFloatValue == customVariable0.mFloatValue;
                    }
                    case 902: {
                        return this.mIntegerValue == customVariable0.mIntegerValue;
                    }
                    case 903: {
                        return this.mIntegerValue == customVariable0.mIntegerValue;
                    }
                    case 904: {
                        return this.mBooleanValue == customVariable0.mBooleanValue;
                    }
                    case 905: {
                        return this.mFloatValue == customVariable0.mFloatValue;
                    }
                    case 900: 
                    case 906: {
                        return this.mIntegerValue == customVariable0.mIntegerValue;
                    }
                    default: {
                        return false;
                    }
                }
            }
        }
        return false;
    }

    public boolean getBooleanValue() {
        return this.mBooleanValue;
    }

    public int getColorValue() {
        return this.mIntegerValue;
    }

    public float getFloatValue() {
        return this.mFloatValue;
    }

    public int getIntegerValue() {
        return this.mIntegerValue;
    }

    public int getInterpolatedColor(float[] arr_f) {
        return CustomVariable.clamp(((int)(arr_f[3] * 255.0f))) << 24 | CustomVariable.clamp(((int)(((float)Math.pow(arr_f[0], 0.454545)) * 255.0f))) << 16 | CustomVariable.clamp(((int)(((float)Math.pow(arr_f[1], 0.454545)) * 255.0f))) << 8 | CustomVariable.clamp(((int)(((float)Math.pow(arr_f[2], 0.454545)) * 255.0f)));
    }

    public String getName() {
        return this.mName;
    }

    public String getStringValue() {
        return this.mStringValue;
    }

    public int getType() {
        return this.mType;
    }

    public float getValueToInterpolate() {
        switch(this.mType) {
            case 900: {
                return (float)this.mIntegerValue;
            }
            case 901: {
                return this.mFloatValue;
            }
            case 902: {
                throw new RuntimeException("Color does not have a single color to interpolate");
            }
            case 903: {
                throw new RuntimeException("Cannot interpolate String");
            }
            case 904: {
                return this.mBooleanValue ? 1.0f : 0.0f;
            }
            case 905: {
                return this.mFloatValue;
            }
            default: {
                return NaNf;
            }
        }
    }

    public void getValuesToInterpolate(float[] arr_f) {
        switch(this.mType) {
            case 900: {
                arr_f[0] = (float)this.mIntegerValue;
                return;
            }
            case 901: {
                arr_f[0] = this.mFloatValue;
                return;
            }
            case 902: {
                int v = this.mIntegerValue >> 24 & 0xFF;
                float f = (float)Math.pow(((float)(this.mIntegerValue >> 8 & 0xFF)) / 255.0f, 2.2);
                float f1 = (float)Math.pow(((float)(this.mIntegerValue & 0xFF)) / 255.0f, 2.2);
                arr_f[0] = (float)Math.pow(((float)(this.mIntegerValue >> 16 & 0xFF)) / 255.0f, 2.2);
                arr_f[1] = f;
                arr_f[2] = f1;
                arr_f[3] = ((float)v) / 255.0f;
                return;
            }
            case 903: {
                throw new RuntimeException("Cannot interpolate String");
            }
            case 904: {
                arr_f[0] = this.mBooleanValue ? 1.0f : 0.0f;
                return;
            }
            case 905: {
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
        return this.mType != 903 && this.mType != 904 && this.mType != 906;
    }

    public int numberOfInterpolatedValues() {
        return this.mType == 902 ? 4 : 1;
    }

    public static int rgbaTocColor(float f, float f1, float f2, float f3) {
        return CustomVariable.clamp(((int)(f * 255.0f))) << 16 | CustomVariable.clamp(((int)(f3 * 255.0f))) << 24 | CustomVariable.clamp(((int)(f1 * 255.0f))) << 8 | CustomVariable.clamp(((int)(f2 * 255.0f)));
    }

    public void setBooleanValue(boolean z) {
        this.mBooleanValue = z;
    }

    public void setFloatValue(float f) {
        this.mFloatValue = f;
    }

    public void setIntValue(int v) {
        this.mIntegerValue = v;
    }

    public void setInterpolatedValue(MotionWidget motionWidget0, float[] arr_f) {
        int v = this.mType;
        boolean z = true;
        switch(v) {
            case 900: {
                motionWidget0.setCustomAttribute(this.mName, 900, ((int)arr_f[0]));
                return;
            }
            case 902: {
                motionWidget0.setCustomAttribute(this.mName, this.mType, CustomVariable.clamp(((int)(arr_f[3] * 255.0f))) << 24 | CustomVariable.clamp(((int)(((float)Math.pow(arr_f[0], 0.454545)) * 255.0f))) << 16 | CustomVariable.clamp(((int)(((float)Math.pow(arr_f[1], 0.454545)) * 255.0f))) << 8 | CustomVariable.clamp(((int)(((float)Math.pow(arr_f[2], 0.454545)) * 255.0f))));
                return;
            }
            case 904: {
                String s = this.mName;
                if(arr_f[0] <= 0.5f) {
                    z = false;
                }
                motionWidget0.setCustomAttribute(s, 904, z);
                return;
            }
            case 901: 
            case 905: {
                motionWidget0.setCustomAttribute(this.mName, v, arr_f[0]);
                return;
            }
            case 903: 
            case 906: {
                throw new RuntimeException("unable to interpolate " + this.mName);
            }
        }
    }

    public void setStringValue(String s) {
        this.mStringValue = s;
    }

    public void setValue(Object object0) {
        switch(this.mType) {
            case 901: {
                this.mFloatValue = (float)(((Float)object0));
                return;
            }
            case 902: {
                this.mIntegerValue = (int)(((Integer)object0));
                return;
            }
            case 903: {
                this.mStringValue = (String)object0;
                return;
            }
            case 904: {
                this.mBooleanValue = ((Boolean)object0).booleanValue();
                return;
            }
            case 905: {
                this.mFloatValue = (float)(((Float)object0));
                return;
            }
            case 900: 
            case 906: {
                this.mIntegerValue = (int)(((Integer)object0));
            }
        }
    }

    public void setValue(float[] arr_f) {
        boolean z = true;
        switch(this.mType) {
            case 902: {
                this.mIntegerValue = (Math.round(arr_f[3] * 255.0f) & 0xFF) << 24 | (Math.round(((float)Math.pow(arr_f[0], 0.5)) * 255.0f) & 0xFF) << 16 | (Math.round(((float)Math.pow(arr_f[1], 0.5)) * 255.0f) & 0xFF) << 8 | Math.round(((float)Math.pow(arr_f[2], 0.5)) * 255.0f) & 0xFF;
                return;
            }
            case 903: {
                throw new RuntimeException("Cannot interpolate String");
            }
            case 904: {
                if(((double)arr_f[0]) <= 0.5) {
                    z = false;
                }
                this.mBooleanValue = z;
                return;
            }
            case 901: 
            case 905: {
                this.mFloatValue = arr_f[0];
                return;
            }
            case 900: 
            case 906: {
                this.mIntegerValue = (int)arr_f[0];
            }
        }
    }

    @Override
    public String toString() {
        String s = this.mName + ':';
        switch(this.mType) {
            case 900: {
                return s + this.mIntegerValue;
            }
            case 901: {
                return s + this.mFloatValue;
            }
            case 902: {
                return s + CustomVariable.colorString(this.mIntegerValue);
            }
            case 903: {
                return s + this.mStringValue;
            }
            case 904: {
                return s + Boolean.valueOf(this.mBooleanValue);
            }
            case 905: {
                return s + this.mFloatValue;
            }
            default: {
                return s + "????";
            }
        }
    }
}

