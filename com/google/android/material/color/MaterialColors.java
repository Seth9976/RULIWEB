package com.google.android.material.color;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.util.TypedValue;
import android.view.View;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.ColorUtils;
import com.google.android.material.R.attr;
import com.google.android.material.color.utilities.Blend;
import com.google.android.material.color.utilities.Hct;
import com.google.android.material.resources.MaterialAttributes;

public class MaterialColors {
    public static final float ALPHA_DISABLED = 0.38f;
    public static final float ALPHA_DISABLED_LOW = 0.12f;
    public static final float ALPHA_FULL = 1.0f;
    public static final float ALPHA_LOW = 0.32f;
    public static final float ALPHA_MEDIUM = 0.54f;
    private static final int CHROMA_NEUTRAL = 6;
    private static final int TONE_ACCENT_CONTAINER_DARK = 30;
    private static final int TONE_ACCENT_CONTAINER_LIGHT = 90;
    private static final int TONE_ACCENT_DARK = 80;
    private static final int TONE_ACCENT_LIGHT = 40;
    private static final int TONE_ON_ACCENT_CONTAINER_DARK = 90;
    private static final int TONE_ON_ACCENT_CONTAINER_LIGHT = 10;
    private static final int TONE_ON_ACCENT_DARK = 20;
    private static final int TONE_ON_ACCENT_LIGHT = 100;
    private static final int TONE_SURFACE_CONTAINER_DARK = 12;
    private static final int TONE_SURFACE_CONTAINER_HIGH_DARK = 17;
    private static final int TONE_SURFACE_CONTAINER_HIGH_LIGHT = 92;
    private static final int TONE_SURFACE_CONTAINER_LIGHT = 94;

    public static int compositeARGBWithAlpha(int v, int v1) {
        return ColorUtils.setAlphaComponent(v, Color.alpha(v) * v1 / 0xFF);
    }

    public static int getColor(Context context0, int v, int v1) {
        Integer integer0 = MaterialColors.getColorOrNull(context0, v);
        return integer0 == null ? v1 : ((int)integer0);
    }

    public static int getColor(Context context0, int v, String s) {
        return MaterialColors.resolveColor(context0, MaterialAttributes.resolveTypedValueOrThrow(context0, v, s));
    }

    public static int getColor(View view0, int v) {
        return MaterialColors.resolveColor(view0.getContext(), MaterialAttributes.resolveTypedValueOrThrow(view0, v));
    }

    public static int getColor(View view0, int v, int v1) {
        return MaterialColors.getColor(view0.getContext(), v, v1);
    }

    public static Integer getColorOrNull(Context context0, int v) {
        TypedValue typedValue0 = MaterialAttributes.resolve(context0, v);
        return typedValue0 == null ? null : MaterialColors.resolveColor(context0, typedValue0);
    }

    private static int getColorRole(int v, int v1) {
        Hct hct0 = Hct.fromInt(v);
        hct0.setTone(((double)v1));
        return hct0.toInt();
    }

    private static int getColorRole(int v, int v1, int v2) {
        Hct hct0 = Hct.fromInt(MaterialColors.getColorRole(v, v1));
        hct0.setChroma(((double)v2));
        return hct0.toInt();
    }

    // 去混淆评级： 低(20)
    public static ColorRoles getColorRoles(int v, boolean z) {
        return z ? new ColorRoles(MaterialColors.getColorRole(v, 40), MaterialColors.getColorRole(v, 100), MaterialColors.getColorRole(v, 90), MaterialColors.getColorRole(v, 10)) : new ColorRoles(MaterialColors.getColorRole(v, 80), MaterialColors.getColorRole(v, 20), MaterialColors.getColorRole(v, 30), MaterialColors.getColorRole(v, 90));
    }

    public static ColorRoles getColorRoles(Context context0, int v) {
        return MaterialColors.getColorRoles(v, MaterialColors.isLightTheme(context0));
    }

    public static ColorStateList getColorStateList(Context context0, int v, ColorStateList colorStateList0) {
        TypedValue typedValue0 = MaterialAttributes.resolve(context0, v);
        ColorStateList colorStateList1 = typedValue0 == null ? null : MaterialColors.resolveColorStateList(context0, typedValue0);
        return colorStateList1 == null ? colorStateList0 : colorStateList1;
    }

    public static ColorStateList getColorStateListOrNull(Context context0, int v) {
        TypedValue typedValue0 = MaterialAttributes.resolve(context0, v);
        if(typedValue0 == null) {
            return null;
        }
        if(typedValue0.resourceId != 0) {
            return ContextCompat.getColorStateList(context0, typedValue0.resourceId);
        }
        return typedValue0.data == 0 ? null : ColorStateList.valueOf(typedValue0.data);
    }

    // 去混淆评级： 低(20)
    public static int getSurfaceContainerFromSeed(Context context0, int v) {
        return MaterialColors.isLightTheme(context0) ? MaterialColors.getColorRole(v, 94, 6) : MaterialColors.getColorRole(v, 12, 6);
    }

    // 去混淆评级： 低(20)
    public static int getSurfaceContainerHighFromSeed(Context context0, int v) {
        return MaterialColors.isLightTheme(context0) ? MaterialColors.getColorRole(v, 92, 6) : MaterialColors.getColorRole(v, 17, 6);
    }

    public static int harmonize(int v, int v1) {
        return Blend.harmonize(v, v1);
    }

    public static int harmonizeWithPrimary(Context context0, int v) {
        return MaterialColors.harmonize(v, MaterialColors.getColor(context0, attr.colorPrimary, MaterialColors.class.getCanonicalName()));
    }

    public static boolean isColorLight(int v) {
        return v != 0 && ColorUtils.calculateLuminance(v) > 0.5;
    }

    static boolean isLightTheme(Context context0) {
        return MaterialAttributes.resolveBoolean(context0, attr.isLightTheme, true);
    }

    public static int layer(int v, int v1) {
        return ColorUtils.compositeColors(v1, v);
    }

    public static int layer(int v, int v1, float f) {
        return MaterialColors.layer(v, ColorUtils.setAlphaComponent(v1, Math.round(((float)Color.alpha(v1)) * f)));
    }

    public static int layer(View view0, int v, int v1) {
        return MaterialColors.layer(view0, v, v1, 1.0f);
    }

    public static int layer(View view0, int v, int v1, float f) {
        return MaterialColors.layer(MaterialColors.getColor(view0, v), MaterialColors.getColor(view0, v1), f);
    }

    private static int resolveColor(Context context0, TypedValue typedValue0) {
        return typedValue0.resourceId == 0 ? typedValue0.data : ContextCompat.getColor(context0, typedValue0.resourceId);
    }

    private static ColorStateList resolveColorStateList(Context context0, TypedValue typedValue0) {
        return typedValue0.resourceId == 0 ? ColorStateList.valueOf(typedValue0.data) : ContextCompat.getColorStateList(context0, typedValue0.resourceId);
    }
}

