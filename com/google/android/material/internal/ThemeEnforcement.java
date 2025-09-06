package com.google.android.material.internal;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.TypedValue;
import androidx.appcompat.widget.TintTypedArray;
import com.google.android.material.R.attr;
import com.google.android.material.R.styleable;
import com.google.android.material.resources.MaterialAttributes;

public final class ThemeEnforcement {
    private static final int[] APPCOMPAT_CHECK_ATTRS = null;
    private static final String APPCOMPAT_THEME_NAME = "Theme.AppCompat";
    private static final int[] MATERIAL_CHECK_ATTRS = null;
    private static final String MATERIAL_THEME_NAME = "Theme.MaterialComponents";

    static {
        ThemeEnforcement.APPCOMPAT_CHECK_ATTRS = new int[]{attr.colorPrimary};
        ThemeEnforcement.MATERIAL_CHECK_ATTRS = new int[]{attr.colorPrimaryVariant};
    }

    public static void checkAppCompatTheme(Context context0) {
        ThemeEnforcement.checkTheme(context0, ThemeEnforcement.APPCOMPAT_CHECK_ATTRS, "Theme.AppCompat");
    }

    private static void checkCompatibleTheme(Context context0, AttributeSet attributeSet0, int v, int v1) {
        TypedArray typedArray0 = context0.obtainStyledAttributes(attributeSet0, styleable.ThemeEnforcement, v, v1);
        boolean z = typedArray0.getBoolean(styleable.ThemeEnforcement_enforceMaterialTheme, false);
        typedArray0.recycle();
        if(z) {
            TypedValue typedValue0 = new TypedValue();
            if(!context0.getTheme().resolveAttribute(attr.isMaterialTheme, typedValue0, true) || typedValue0.type == 18 && typedValue0.data == 0) {
                ThemeEnforcement.checkMaterialTheme(context0);
            }
        }
        ThemeEnforcement.checkAppCompatTheme(context0);
    }

    public static void checkMaterialTheme(Context context0) {
        ThemeEnforcement.checkTheme(context0, ThemeEnforcement.MATERIAL_CHECK_ATTRS, "Theme.MaterialComponents");
    }

    private static void checkTextAppearance(Context context0, AttributeSet attributeSet0, int[] arr_v, int v, int v1, int[] arr_v1) {
        boolean z1;
        TypedArray typedArray0 = context0.obtainStyledAttributes(attributeSet0, styleable.ThemeEnforcement, v, v1);
        boolean z = false;
        if(!typedArray0.getBoolean(styleable.ThemeEnforcement_enforceTextAppearance, false)) {
            typedArray0.recycle();
            return;
        }
        if(arr_v1 == null || arr_v1.length == 0) {
            if(typedArray0.getResourceId(styleable.ThemeEnforcement_android_textAppearance, -1) != -1) {
                z = true;
            }
            z1 = z;
        }
        else {
            z1 = ThemeEnforcement.isCustomTextAppearanceValid(context0, attributeSet0, arr_v, v, v1, arr_v1);
        }
        typedArray0.recycle();
        if(!z1) {
            throw new IllegalArgumentException("This component requires that you specify a valid TextAppearance attribute. Update your app theme to inherit from Theme.MaterialComponents (or a descendant).");
        }
    }

    private static void checkTheme(Context context0, int[] arr_v, String s) {
        if(!ThemeEnforcement.isTheme(context0, arr_v)) {
            throw new IllegalArgumentException("The style on this component requires your app theme to be " + s + " (or a descendant).");
        }
    }

    public static boolean isAppCompatTheme(Context context0) {
        return ThemeEnforcement.isTheme(context0, ThemeEnforcement.APPCOMPAT_CHECK_ATTRS);
    }

    private static boolean isCustomTextAppearanceValid(Context context0, AttributeSet attributeSet0, int[] arr_v, int v, int v1, int[] arr_v1) {
        TypedArray typedArray0 = context0.obtainStyledAttributes(attributeSet0, arr_v, v, v1);
        for(int v2 = 0; v2 < arr_v1.length; ++v2) {
            if(typedArray0.getResourceId(arr_v1[v2], -1) == -1) {
                typedArray0.recycle();
                return false;
            }
        }
        typedArray0.recycle();
        return true;
    }

    public static boolean isMaterial3Theme(Context context0) {
        return MaterialAttributes.resolveBoolean(context0, attr.isMaterial3Theme, false);
    }

    public static boolean isMaterialTheme(Context context0) {
        return ThemeEnforcement.isTheme(context0, ThemeEnforcement.MATERIAL_CHECK_ATTRS);
    }

    private static boolean isTheme(Context context0, int[] arr_v) {
        TypedArray typedArray0 = context0.obtainStyledAttributes(arr_v);
        for(int v = 0; v < arr_v.length; ++v) {
            if(!typedArray0.hasValue(v)) {
                typedArray0.recycle();
                return false;
            }
        }
        typedArray0.recycle();
        return true;
    }

    public static TypedArray obtainStyledAttributes(Context context0, AttributeSet attributeSet0, int[] arr_v, int v, int v1, int[] arr_v1) {
        ThemeEnforcement.checkCompatibleTheme(context0, attributeSet0, v, v1);
        ThemeEnforcement.checkTextAppearance(context0, attributeSet0, arr_v, v, v1, arr_v1);
        return context0.obtainStyledAttributes(attributeSet0, arr_v, v, v1);
    }

    public static TintTypedArray obtainTintedStyledAttributes(Context context0, AttributeSet attributeSet0, int[] arr_v, int v, int v1, int[] arr_v1) {
        ThemeEnforcement.checkCompatibleTheme(context0, attributeSet0, v, v1);
        ThemeEnforcement.checkTextAppearance(context0, attributeSet0, arr_v, v, v1, arr_v1);
        return TintTypedArray.obtainStyledAttributes(context0, attributeSet0, arr_v, v, v1);
    }
}

