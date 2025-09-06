package com.google.android.material.ripple;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.InsetDrawable;
import android.graphics.drawable.RippleDrawable;
import android.os.Build.VERSION;
import android.util.Log;
import android.util.StateSet;
import androidx.core.graphics.ColorUtils;
import com.google.android.material.R.attr;
import com.google.android.material.color.MaterialColors;

public class RippleUtils {
    static class RippleUtilsLollipop {
        static Drawable access$000(Context context0, int v) {
            return RippleUtilsLollipop.createOvalRipple(context0, v);
        }

        private static Drawable createOvalRipple(Context context0, int v) {
            GradientDrawable gradientDrawable0 = new GradientDrawable();
            gradientDrawable0.setColor(-1);
            gradientDrawable0.setShape(1);
            InsetDrawable insetDrawable0 = new InsetDrawable(gradientDrawable0, v, v, v, v);
            ColorStateList colorStateList0 = ColorStateList.valueOf(0);
            return new RippleDrawable(MaterialColors.getColorStateList(context0, attr.colorControlHighlight, colorStateList0), null, insetDrawable0);
        }
    }

    private static final int[] ENABLED_PRESSED_STATE_SET = null;
    private static final int[] FOCUSED_STATE_SET = null;
    private static final int[] HOVERED_FOCUSED_STATE_SET = null;
    private static final int[] HOVERED_STATE_SET = null;
    static final String LOG_TAG = null;
    private static final int[] PRESSED_STATE_SET = null;
    private static final int[] SELECTED_FOCUSED_STATE_SET = null;
    private static final int[] SELECTED_HOVERED_FOCUSED_STATE_SET = null;
    private static final int[] SELECTED_HOVERED_STATE_SET = null;
    private static final int[] SELECTED_PRESSED_STATE_SET = null;
    private static final int[] SELECTED_STATE_SET = null;
    static final String TRANSPARENT_DEFAULT_COLOR_WARNING = "Use a non-transparent color for the default color as it will be used to finish ripple animations.";
    public static final boolean USE_FRAMEWORK_RIPPLE;

    static {
        RippleUtils.USE_FRAMEWORK_RIPPLE = true;
        RippleUtils.PRESSED_STATE_SET = new int[]{0x10100A7};
        RippleUtils.HOVERED_FOCUSED_STATE_SET = new int[]{0x1010367, 0x101009C};
        RippleUtils.FOCUSED_STATE_SET = new int[]{0x101009C};
        RippleUtils.HOVERED_STATE_SET = new int[]{0x1010367};
        RippleUtils.SELECTED_PRESSED_STATE_SET = new int[]{0x10100A1, 0x10100A7};
        RippleUtils.SELECTED_HOVERED_FOCUSED_STATE_SET = new int[]{0x10100A1, 0x1010367, 0x101009C};
        RippleUtils.SELECTED_FOCUSED_STATE_SET = new int[]{0x10100A1, 0x101009C};
        RippleUtils.SELECTED_HOVERED_STATE_SET = new int[]{0x10100A1, 0x1010367};
        RippleUtils.SELECTED_STATE_SET = new int[]{0x10100A1};
        RippleUtils.ENABLED_PRESSED_STATE_SET = new int[]{0x101009E, 0x10100A7};
        RippleUtils.LOG_TAG = "RippleUtils";
    }

    public static ColorStateList convertToRippleDrawableColor(ColorStateList colorStateList0) {
        if(RippleUtils.USE_FRAMEWORK_RIPPLE) {
            int[][] arr2_v = new int[3][];
            int[] arr_v = new int[3];
            arr2_v[0] = RippleUtils.SELECTED_STATE_SET;
            arr_v[0] = RippleUtils.getColorForState(colorStateList0, RippleUtils.SELECTED_PRESSED_STATE_SET);
            arr2_v[1] = RippleUtils.FOCUSED_STATE_SET;
            arr_v[1] = RippleUtils.getColorForState(colorStateList0, RippleUtils.FOCUSED_STATE_SET);
            arr2_v[2] = StateSet.NOTHING;
            arr_v[2] = RippleUtils.getColorForState(colorStateList0, RippleUtils.PRESSED_STATE_SET);
            return new ColorStateList(arr2_v, arr_v);
        }
        int[][] arr2_v1 = new int[10][];
        int[] arr_v1 = new int[10];
        arr2_v1[0] = RippleUtils.SELECTED_PRESSED_STATE_SET;
        arr_v1[0] = RippleUtils.getColorForState(colorStateList0, RippleUtils.SELECTED_PRESSED_STATE_SET);
        arr2_v1[1] = RippleUtils.SELECTED_HOVERED_FOCUSED_STATE_SET;
        arr_v1[1] = RippleUtils.getColorForState(colorStateList0, RippleUtils.SELECTED_HOVERED_FOCUSED_STATE_SET);
        arr2_v1[2] = RippleUtils.SELECTED_FOCUSED_STATE_SET;
        arr_v1[2] = RippleUtils.getColorForState(colorStateList0, RippleUtils.SELECTED_FOCUSED_STATE_SET);
        arr2_v1[3] = RippleUtils.SELECTED_HOVERED_STATE_SET;
        arr_v1[3] = RippleUtils.getColorForState(colorStateList0, RippleUtils.SELECTED_HOVERED_STATE_SET);
        arr2_v1[4] = RippleUtils.SELECTED_STATE_SET;
        arr_v1[4] = 0;
        arr2_v1[5] = RippleUtils.PRESSED_STATE_SET;
        arr_v1[5] = RippleUtils.getColorForState(colorStateList0, RippleUtils.PRESSED_STATE_SET);
        arr2_v1[6] = RippleUtils.HOVERED_FOCUSED_STATE_SET;
        arr_v1[6] = RippleUtils.getColorForState(colorStateList0, RippleUtils.HOVERED_FOCUSED_STATE_SET);
        arr2_v1[7] = RippleUtils.FOCUSED_STATE_SET;
        arr_v1[7] = RippleUtils.getColorForState(colorStateList0, RippleUtils.FOCUSED_STATE_SET);
        arr2_v1[8] = RippleUtils.HOVERED_STATE_SET;
        arr_v1[8] = RippleUtils.getColorForState(colorStateList0, RippleUtils.HOVERED_STATE_SET);
        arr2_v1[9] = StateSet.NOTHING;
        arr_v1[9] = 0;
        return new ColorStateList(arr2_v1, arr_v1);
    }

    public static Drawable createOvalRippleLollipop(Context context0, int v) {
        return RippleUtilsLollipop.access$000(context0, v);
    }

    private static int doubleAlpha(int v) {
        return ColorUtils.setAlphaComponent(v, Math.min(Color.alpha(v) * 2, 0xFF));
    }

    private static int getColorForState(ColorStateList colorStateList0, int[] arr_v) {
        int v = colorStateList0 == null ? 0 : colorStateList0.getColorForState(arr_v, colorStateList0.getDefaultColor());
        return RippleUtils.USE_FRAMEWORK_RIPPLE ? RippleUtils.doubleAlpha(v) : v;
    }

    public static ColorStateList sanitizeRippleDrawableColor(ColorStateList colorStateList0) {
        if(colorStateList0 != null) {
            if(Build.VERSION.SDK_INT >= 22 && Build.VERSION.SDK_INT <= 27 && Color.alpha(colorStateList0.getDefaultColor()) == 0 && Color.alpha(colorStateList0.getColorForState(RippleUtils.ENABLED_PRESSED_STATE_SET, 0)) != 0) {
                Log.w("RippleUtils", "Use a non-transparent color for the default color as it will be used to finish ripple animations.");
            }
            return colorStateList0;
        }
        return ColorStateList.valueOf(0);
    }

    public static boolean shouldDrawRippleCompat(int[] arr_v) {
        boolean z = false;
        boolean z1 = false;
        for(int v = 0; v < arr_v.length; ++v) {
            int v1 = arr_v[v];
            if(v1 == 0x101009E) {
                z = true;
            }
            else if(v1 == 0x101009C || v1 == 0x10100A7 || v1 == 0x1010367) {
                z1 = true;
            }
        }
        return z && z1;
    }
}

