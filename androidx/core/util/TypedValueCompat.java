package androidx.core.util;

import android.os.Build.VERSION;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class TypedValueCompat {
    static class Api34Impl {
        public static float deriveDimension(int v, float f, DisplayMetrics displayMetrics0) {
            return TypedValue.deriveDimension(v, f, displayMetrics0);
        }
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface ComplexDimensionUnit {
    }

    private static final float INCHES_PER_MM = 0.03937f;
    private static final float INCHES_PER_PT = 0.013889f;

    public static float deriveDimension(int v, float f, DisplayMetrics displayMetrics0) {
        if(Build.VERSION.SDK_INT >= 34) {
            return Api34Impl.deriveDimension(v, f, displayMetrics0);
        }
        switch(v) {
            case 0: {
                return f;
            }
            case 1: {
                return displayMetrics0.density == 0.0f ? 0.0f : f / displayMetrics0.density;
            }
            case 2: {
                return displayMetrics0.scaledDensity == 0.0f ? 0.0f : f / displayMetrics0.scaledDensity;
            }
            case 3: {
                return displayMetrics0.xdpi == 0.0f ? 0.0f : f / displayMetrics0.xdpi / 0.013889f;
            }
            case 4: {
                return displayMetrics0.xdpi == 0.0f ? 0.0f : f / displayMetrics0.xdpi;
            }
            case 5: {
                return displayMetrics0.xdpi == 0.0f ? 0.0f : f / displayMetrics0.xdpi / 0.03937f;
            }
            default: {
                throw new IllegalArgumentException("Invalid unitToConvertTo " + v);
            }
        }
    }

    public static float dpToPx(float f, DisplayMetrics displayMetrics0) {
        return TypedValue.applyDimension(1, f, displayMetrics0);
    }

    public static int getUnitFromComplexDimension(int v) [...] // Inlined contents

    public static float pxToDp(float f, DisplayMetrics displayMetrics0) {
        return TypedValueCompat.deriveDimension(1, f, displayMetrics0);
    }

    public static float pxToSp(float f, DisplayMetrics displayMetrics0) {
        return TypedValueCompat.deriveDimension(2, f, displayMetrics0);
    }

    public static float spToPx(float f, DisplayMetrics displayMetrics0) {
        return TypedValue.applyDimension(2, f, displayMetrics0);
    }
}

