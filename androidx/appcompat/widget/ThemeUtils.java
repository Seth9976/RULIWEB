package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import androidx.appcompat.R.styleable;
import androidx.core.graphics.ColorUtils;

public class ThemeUtils {
    static final int[] ACTIVATED_STATE_SET = null;
    static final int[] CHECKED_STATE_SET = null;
    static final int[] DISABLED_STATE_SET = null;
    static final int[] EMPTY_STATE_SET = null;
    static final int[] FOCUSED_STATE_SET = null;
    static final int[] NOT_PRESSED_OR_FOCUSED_STATE_SET = null;
    static final int[] PRESSED_STATE_SET = null;
    static final int[] SELECTED_STATE_SET = null;
    private static final String TAG = "ThemeUtils";
    private static final int[] TEMP_ARRAY;
    private static final ThreadLocal TL_TYPED_VALUE;

    static {
        ThemeUtils.TL_TYPED_VALUE = new ThreadLocal();
        ThemeUtils.DISABLED_STATE_SET = new int[]{0xFEFEFF62};
        ThemeUtils.FOCUSED_STATE_SET = new int[]{0x101009C};
        ThemeUtils.ACTIVATED_STATE_SET = new int[]{0x10102FE};
        ThemeUtils.PRESSED_STATE_SET = new int[]{0x10100A7};
        ThemeUtils.CHECKED_STATE_SET = new int[]{0x10100A0};
        ThemeUtils.SELECTED_STATE_SET = new int[]{0x10100A1};
        ThemeUtils.NOT_PRESSED_OR_FOCUSED_STATE_SET = new int[]{0xFEFEFF59, 0xFEFEFF64};
        ThemeUtils.EMPTY_STATE_SET = new int[0];
        ThemeUtils.TEMP_ARRAY = new int[1];
    }

    public static void checkAppCompatTheme(View view0, Context context0) {
        TypedArray typedArray0 = context0.obtainStyledAttributes(styleable.AppCompatTheme);
        try {
            if(!typedArray0.hasValue(styleable.AppCompatTheme_windowActionBar)) {
                Log.e("ThemeUtils", "View " + view0.getClass() + " is an AppCompat widget that can only be used with a Theme.AppCompat theme (or descendant).");
            }
        }
        finally {
            typedArray0.recycle();
        }
    }

    public static ColorStateList createDisabledStateList(int v, int v1) {
        int[][] arr2_v = new int[2][];
        int[] arr_v = new int[2];
        arr2_v[0] = ThemeUtils.DISABLED_STATE_SET;
        arr_v[0] = v1;
        arr2_v[1] = ThemeUtils.EMPTY_STATE_SET;
        arr_v[1] = v;
        return new ColorStateList(arr2_v, arr_v);
    }

    public static int getDisabledThemeAttrColor(Context context0, int v) {
        ColorStateList colorStateList0 = ThemeUtils.getThemeAttrColorStateList(context0, v);
        if(colorStateList0 != null && colorStateList0.isStateful()) {
            int v1 = colorStateList0.getDefaultColor();
            return colorStateList0.getColorForState(ThemeUtils.DISABLED_STATE_SET, v1);
        }
        TypedValue typedValue0 = ThemeUtils.getTypedValue();
        context0.getTheme().resolveAttribute(0x1010033, typedValue0, true);
        return ThemeUtils.getThemeAttrColor(context0, v, typedValue0.getFloat());
    }

    public static int getThemeAttrColor(Context context0, int v) {
        ThemeUtils.TEMP_ARRAY[0] = v;
        TintTypedArray tintTypedArray0 = TintTypedArray.obtainStyledAttributes(context0, null, ThemeUtils.TEMP_ARRAY);
        try {
            return tintTypedArray0.getColor(0, 0);
        }
        finally {
            tintTypedArray0.recycle();
        }
    }

    static int getThemeAttrColor(Context context0, int v, float f) {
        int v1 = ThemeUtils.getThemeAttrColor(context0, v);
        return ColorUtils.setAlphaComponent(v1, Math.round(((float)Color.alpha(v1)) * f));
    }

    public static ColorStateList getThemeAttrColorStateList(Context context0, int v) {
        ThemeUtils.TEMP_ARRAY[0] = v;
        TintTypedArray tintTypedArray0 = TintTypedArray.obtainStyledAttributes(context0, null, ThemeUtils.TEMP_ARRAY);
        try {
            return tintTypedArray0.getColorStateList(0);
        }
        finally {
            tintTypedArray0.recycle();
        }
    }

    private static TypedValue getTypedValue() {
        ThreadLocal threadLocal0 = ThemeUtils.TL_TYPED_VALUE;
        TypedValue typedValue0 = (TypedValue)threadLocal0.get();
        if(typedValue0 == null) {
            typedValue0 = new TypedValue();
            threadLocal0.set(typedValue0);
        }
        return typedValue0;
    }
}

