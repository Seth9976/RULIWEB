package androidx.constraintlayout.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.util.TypedValue;
import android.util.Xml;
import android.view.View;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import org.xmlpull.v1.XmlPullParser;

public class ConstraintAttribute {
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

    private static final boolean DEBUG = false;
    private static final String TAG = "TransitionLayout";
    boolean mBooleanValue;
    private int mColorValue;
    private float mFloatValue;
    private int mIntegerValue;
    private boolean mMethod;
    String mName;
    private String mStringValue;
    private AttributeType mType;

    public ConstraintAttribute(ConstraintAttribute constraintAttribute0, Object object0) {
        this.mMethod = false;
        this.mName = constraintAttribute0.mName;
        this.mType = constraintAttribute0.mType;
        this.setValue(object0);
    }

    public ConstraintAttribute(String s, AttributeType constraintAttribute$AttributeType0) {
        this.mMethod = false;
        this.mName = s;
        this.mType = constraintAttribute$AttributeType0;
    }

    public ConstraintAttribute(String s, AttributeType constraintAttribute$AttributeType0, Object object0, boolean z) {
        this.mName = s;
        this.mType = constraintAttribute$AttributeType0;
        this.mMethod = z;
        this.setValue(object0);
    }

    public void applyCustom(View view0) {
        Class class0 = view0.getClass();
        String s = this.mName;
        String s1 = this.mMethod ? s : "set" + s;
        try {
            switch(this.mType.ordinal()) {
                case 1: {
                    class0.getMethod(s1, Float.TYPE).invoke(view0, this.mFloatValue);
                    return;
                }
                case 2: {
                    class0.getMethod(s1, Integer.TYPE).invoke(view0, this.mColorValue);
                    return;
                }
                case 3: {
                    Method method0 = class0.getMethod(s1, Drawable.class);
                    ColorDrawable colorDrawable0 = new ColorDrawable();
                    colorDrawable0.setColor(this.mColorValue);
                    method0.invoke(view0, colorDrawable0);
                    return;
                }
                case 4: {
                    class0.getMethod(s1, CharSequence.class).invoke(view0, this.mStringValue);
                    return;
                }
                case 5: {
                    class0.getMethod(s1, Boolean.TYPE).invoke(view0, Boolean.valueOf(this.mBooleanValue));
                    return;
                }
                case 6: {
                    class0.getMethod(s1, Float.TYPE).invoke(view0, this.mFloatValue);
                    return;
                }
                case 0: 
                case 7: {
                    class0.getMethod(s1, Integer.TYPE).invoke(view0, this.mIntegerValue);
                }
            }
        }
        catch(NoSuchMethodException noSuchMethodException0) {
            Log.e("TransitionLayout", class0.getName() + " must have a method " + s1, noSuchMethodException0);
        }
        catch(IllegalAccessException illegalAccessException0) {
            Log.e("TransitionLayout", " Custom Attribute \"" + s + "\" not found on " + class0.getName(), illegalAccessException0);
        }
        catch(InvocationTargetException invocationTargetException0) {
            Log.e("TransitionLayout", " Custom Attribute \"" + s + "\" not found on " + class0.getName(), invocationTargetException0);
        }
    }

    private static int clamp(int v) {
        int v1 = (v & ~(v >> 0x1F)) - 0xFF;
        return (v1 & v1 >> 0x1F) + 0xFF;
    }

    public boolean diff(ConstraintAttribute constraintAttribute0) {
        if(constraintAttribute0 != null) {
            AttributeType constraintAttribute$AttributeType0 = this.mType;
            if(constraintAttribute$AttributeType0 == constraintAttribute0.mType) {
                switch(constraintAttribute$AttributeType0.ordinal()) {
                    case 1: {
                        return this.mFloatValue == constraintAttribute0.mFloatValue;
                    }
                    case 2: 
                    case 3: {
                        return this.mColorValue == constraintAttribute0.mColorValue;
                    }
                    case 4: {
                        return this.mIntegerValue == constraintAttribute0.mIntegerValue;
                    }
                    case 5: {
                        return this.mBooleanValue == constraintAttribute0.mBooleanValue;
                    }
                    case 6: {
                        return this.mFloatValue == constraintAttribute0.mFloatValue;
                    }
                    case 0: 
                    case 7: {
                        return this.mIntegerValue == constraintAttribute0.mIntegerValue;
                    }
                    default: {
                        return false;
                    }
                }
            }
        }
        return false;
    }

    public static HashMap extractAttributes(HashMap hashMap0, View view0) {
        HashMap hashMap1 = new HashMap();
        Class class0 = view0.getClass();
        for(Object object0: hashMap0.keySet()) {
            String s = (String)object0;
            ConstraintAttribute constraintAttribute0 = (ConstraintAttribute)hashMap0.get(s);
            try {
                if(s.equals("BackgroundColor")) {
                    hashMap1.put(s, new ConstraintAttribute(constraintAttribute0, ((ColorDrawable)view0.getBackground()).getColor()));
                    continue;
                }
                hashMap1.put(s, new ConstraintAttribute(constraintAttribute0, class0.getMethod("getMap" + s, null).invoke(view0, null)));
            }
            catch(NoSuchMethodException noSuchMethodException0) {
                Log.e("TransitionLayout", class0.getName() + " must have a method " + s, noSuchMethodException0);
            }
            catch(IllegalAccessException illegalAccessException0) {
                Log.e("TransitionLayout", " Custom Attribute \"" + s + "\" not found on " + class0.getName(), illegalAccessException0);
            }
            catch(InvocationTargetException invocationTargetException0) {
                Log.e("TransitionLayout", " Custom Attribute \"" + s + "\" not found on " + class0.getName(), invocationTargetException0);
            }
        }
        return hashMap1;
    }

    public int getColorValue() {
        return this.mColorValue;
    }

    public float getFloatValue() {
        return this.mFloatValue;
    }

    public int getIntegerValue() {
        return this.mIntegerValue;
    }

    public String getName() {
        return this.mName;
    }

    public String getStringValue() {
        return this.mStringValue;
    }

    public AttributeType getType() {
        return this.mType;
    }

    public float getValueToInterpolate() {
        switch(this.mType.ordinal()) {
            case 0: {
                return (float)this.mIntegerValue;
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
            case 1: 
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

    public boolean isBooleanValue() {
        return this.mBooleanValue;
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

    public boolean isMethod() {
        return this.mMethod;
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

    public static void parse(Context context0, XmlPullParser xmlPullParser0, HashMap hashMap0) {
        Integer integer0;
        TypedArray typedArray0 = context0.obtainStyledAttributes(Xml.asAttributeSet(xmlPullParser0), styleable.CustomAttribute);
        int v = typedArray0.getIndexCount();
        String s = null;
        Object object0 = null;
        AttributeType constraintAttribute$AttributeType0 = null;
        boolean z = false;
        for(int v1 = 0; v1 < v; ++v1) {
            int v2 = typedArray0.getIndex(v1);
            if(v2 == styleable.CustomAttribute_attributeName) {
                s = typedArray0.getString(v2);
                if(s != null && s.length() > 0) {
                    s = Character.toUpperCase(s.charAt(0)) + s.substring(1);
                }
            }
            else if(v2 == styleable.CustomAttribute_methodName) {
                s = typedArray0.getString(v2);
                z = true;
            }
            else if(v2 == styleable.CustomAttribute_customBoolean) {
                object0 = Boolean.valueOf(typedArray0.getBoolean(v2, false));
                constraintAttribute$AttributeType0 = AttributeType.BOOLEAN_TYPE;
            }
            else if(v2 == styleable.CustomAttribute_customColorValue) {
                integer0 = typedArray0.getColor(v2, 0);
                constraintAttribute$AttributeType0 = AttributeType.COLOR_TYPE;
                object0 = integer0;
            }
            else if(v2 == styleable.CustomAttribute_customColorDrawableValue) {
                integer0 = typedArray0.getColor(v2, 0);
                constraintAttribute$AttributeType0 = AttributeType.COLOR_DRAWABLE_TYPE;
                object0 = integer0;
            }
            else if(v2 == styleable.CustomAttribute_customPixelDimension) {
                integer0 = TypedValue.applyDimension(1, typedArray0.getDimension(v2, 0.0f), context0.getResources().getDisplayMetrics());
                constraintAttribute$AttributeType0 = AttributeType.DIMENSION_TYPE;
                object0 = integer0;
            }
            else if(v2 == styleable.CustomAttribute_customDimension) {
                integer0 = typedArray0.getDimension(v2, 0.0f);
                constraintAttribute$AttributeType0 = AttributeType.DIMENSION_TYPE;
                object0 = integer0;
            }
            else if(v2 == styleable.CustomAttribute_customFloatValue) {
                integer0 = typedArray0.getFloat(v2, NaNf);
                constraintAttribute$AttributeType0 = AttributeType.FLOAT_TYPE;
                object0 = integer0;
            }
            else if(v2 == styleable.CustomAttribute_customIntegerValue) {
                integer0 = typedArray0.getInteger(v2, -1);
                constraintAttribute$AttributeType0 = AttributeType.INT_TYPE;
                object0 = integer0;
            }
            else if(v2 == styleable.CustomAttribute_customStringValue) {
                integer0 = typedArray0.getString(v2);
                constraintAttribute$AttributeType0 = AttributeType.STRING_TYPE;
                object0 = integer0;
            }
            else if(v2 == styleable.CustomAttribute_customReference) {
                AttributeType constraintAttribute$AttributeType1 = AttributeType.REFERENCE_TYPE;
                int v3 = typedArray0.getResourceId(v2, -1);
                if(v3 == -1) {
                    v3 = typedArray0.getInt(v2, -1);
                }
                constraintAttribute$AttributeType0 = constraintAttribute$AttributeType1;
                object0 = v3;
            }
        }
        if(s != null && object0 != null) {
            hashMap0.put(s, new ConstraintAttribute(s, constraintAttribute$AttributeType0, object0, z));
        }
        typedArray0.recycle();
    }

    public static void setAttributes(View view0, HashMap hashMap0) {
        Class class0 = view0.getClass();
        for(Object object0: hashMap0.keySet()) {
            String s = (String)object0;
            ConstraintAttribute constraintAttribute0 = (ConstraintAttribute)hashMap0.get(s);
            String s1 = constraintAttribute0.mMethod ? s : "set" + s;
            try {
                switch(constraintAttribute0.mType.ordinal()) {
                    case 0: {
                        class0.getMethod(s1, Integer.TYPE).invoke(view0, constraintAttribute0.mIntegerValue);
                        goto label_34;
                    }
                    case 1: {
                        class0.getMethod(s1, Float.TYPE).invoke(view0, constraintAttribute0.mFloatValue);
                        goto label_34;
                    }
                    case 2: {
                        class0.getMethod(s1, Integer.TYPE).invoke(view0, constraintAttribute0.mColorValue);
                        goto label_34;
                    }
                    case 3: {
                        Method method0 = class0.getMethod(s1, Drawable.class);
                        ColorDrawable colorDrawable0 = new ColorDrawable();
                        colorDrawable0.setColor(constraintAttribute0.mColorValue);
                        method0.invoke(view0, colorDrawable0);
                        goto label_34;
                    }
                    case 4: {
                        class0.getMethod(s1, CharSequence.class).invoke(view0, constraintAttribute0.mStringValue);
                        goto label_34;
                    }
                    case 5: {
                        class0.getMethod(s1, Boolean.TYPE).invoke(view0, Boolean.valueOf(constraintAttribute0.mBooleanValue));
                        goto label_34;
                    }
                    case 6: {
                        class0.getMethod(s1, Float.TYPE).invoke(view0, constraintAttribute0.mFloatValue);
                        goto label_34;
                    }
                    case 7: {
                        class0.getMethod(s1, Integer.TYPE).invoke(view0, constraintAttribute0.mIntegerValue);
                    }
                }
            }
            catch(NoSuchMethodException noSuchMethodException0) {
                Log.e("TransitionLayout", class0.getName() + " must have a method " + s1, noSuchMethodException0);
            }
            catch(IllegalAccessException illegalAccessException0) {
                Log.e("TransitionLayout", " Custom Attribute \"" + s + "\" not found on " + class0.getName(), illegalAccessException0);
            label_34:
            }
            catch(InvocationTargetException invocationTargetException0) {
                Log.e("TransitionLayout", " Custom Attribute \"" + s + "\" not found on " + class0.getName(), invocationTargetException0);
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
        boolean z = false;
        switch(this.mType.ordinal()) {
            case 1: {
                this.mFloatValue = arr_f[0];
                return;
            }
            case 2: 
            case 3: {
                int v = Color.HSVToColor(arr_f);
                this.mColorValue = ConstraintAttribute.clamp(((int)(arr_f[3] * 255.0f))) << 24 | v & 0xFFFFFF;
                return;
            }
            case 4: {
                throw new RuntimeException("Color does not have a single color to interpolate");
            }
            case 5: {
                if(((double)arr_f[0]) > 0.5) {
                    z = true;
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

