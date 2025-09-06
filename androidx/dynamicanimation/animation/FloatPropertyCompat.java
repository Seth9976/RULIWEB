package androidx.dynamicanimation.animation;

import android.util.FloatProperty;

public abstract class FloatPropertyCompat {
    final String mPropertyName;

    public FloatPropertyCompat(String s) {
        this.mPropertyName = s;
    }

    public static FloatPropertyCompat createFloatPropertyCompat(FloatProperty floatProperty0) {
        return new FloatPropertyCompat(floatProperty0.getName()) {
            @Override  // androidx.dynamicanimation.animation.FloatPropertyCompat
            public float getValue(Object object0) {
                return (float)(((Float)floatProperty0.get(object0)));
            }

            @Override  // androidx.dynamicanimation.animation.FloatPropertyCompat
            public void setValue(Object object0, float f) {
                floatProperty0.setValue(object0, f);
            }
        };
    }

    public abstract float getValue(Object arg1);

    public abstract void setValue(Object arg1, float arg2);
}

