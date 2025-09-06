package com.google.android.material.motion;

import android.animation.TimeInterpolator;
import android.content.Context;
import android.util.TypedValue;
import android.view.animation.AnimationUtils;
import androidx.core.graphics.PathParser;
import androidx.core.view.animation.PathInterpolatorCompat;
import com.google.android.material.resources.MaterialAttributes;

public class MotionUtils {
    private static final String EASING_TYPE_CUBIC_BEZIER = "cubic-bezier";
    private static final String EASING_TYPE_FORMAT_END = ")";
    private static final String EASING_TYPE_FORMAT_START = "(";
    private static final String EASING_TYPE_PATH = "path";

    private static float getLegacyControlPoint(String[] arr_s, int v) {
        float f = Float.parseFloat(arr_s[v]);
        if(f < 0.0f || f > 1.0f) {
            throw new IllegalArgumentException("Motion easing control point value must be between 0 and 1; instead got: " + f);
        }
        return f;
    }

    private static String getLegacyEasingContent(String s, String s1) {
        return s.substring(s1.length() + 1, s.length() - 1);
    }

    private static TimeInterpolator getLegacyThemeInterpolator(String s) {
        if(MotionUtils.isLegacyEasingType(s, "cubic-bezier")) {
            String[] arr_s = MotionUtils.getLegacyEasingContent(s, "cubic-bezier").split(",");
            if(arr_s.length != 4) {
                throw new IllegalArgumentException("Motion easing theme attribute must have 4 control points if using bezier curve format; instead got: " + arr_s.length);
            }
            return PathInterpolatorCompat.create(MotionUtils.getLegacyControlPoint(arr_s, 0), MotionUtils.getLegacyControlPoint(arr_s, 1), MotionUtils.getLegacyControlPoint(arr_s, 2), MotionUtils.getLegacyControlPoint(arr_s, 3));
        }
        if(!MotionUtils.isLegacyEasingType(s, "path")) {
            throw new IllegalArgumentException("Invalid motion easing type: " + s);
        }
        return PathInterpolatorCompat.create(PathParser.createPathFromPathData(MotionUtils.getLegacyEasingContent(s, "path")));
    }

    // 去混淆评级： 低(20)
    private static boolean isLegacyEasingAttribute(String s) {
        return MotionUtils.isLegacyEasingType(s, "cubic-bezier") || MotionUtils.isLegacyEasingType(s, "path");
    }

    // 去混淆评级： 低(20)
    private static boolean isLegacyEasingType(String s, String s1) {
        return s.startsWith(s1 + "(") && s.endsWith(")");
    }

    public static int resolveThemeDuration(Context context0, int v, int v1) {
        return MaterialAttributes.resolveInteger(context0, v, v1);
    }

    public static TimeInterpolator resolveThemeInterpolator(Context context0, int v, TimeInterpolator timeInterpolator0) {
        TypedValue typedValue0 = new TypedValue();
        if(!context0.getTheme().resolveAttribute(v, typedValue0, true)) {
            return timeInterpolator0;
        }
        if(typedValue0.type != 3) {
            throw new IllegalArgumentException("Motion easing theme attribute must be an @interpolator resource for ?attr/motionEasing*Interpolator attributes or a string for ?attr/motionEasing* attributes.");
        }
        String s = String.valueOf(typedValue0.string);
        return MotionUtils.isLegacyEasingAttribute(s) ? MotionUtils.getLegacyThemeInterpolator(s) : AnimationUtils.loadInterpolator(context0, typedValue0.resourceId);
    }
}

