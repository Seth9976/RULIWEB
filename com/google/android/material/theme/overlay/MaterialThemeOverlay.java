package com.google.android.material.theme.overlay;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import androidx.appcompat.view.ContextThemeWrapper;
import com.google.android.material.R.attr;

public class MaterialThemeOverlay {
    private static final int[] ANDROID_THEME_OVERLAY_ATTRS;
    private static final int[] MATERIAL_THEME_OVERLAY_ATTR;

    static {
        MaterialThemeOverlay.ANDROID_THEME_OVERLAY_ATTRS = new int[]{0x1010000, attr.theme};
        MaterialThemeOverlay.MATERIAL_THEME_OVERLAY_ATTR = new int[]{attr.materialThemeOverlay};
    }

    private static int obtainAndroidThemeOverlayId(Context context0, AttributeSet attributeSet0) {
        TypedArray typedArray0 = context0.obtainStyledAttributes(attributeSet0, MaterialThemeOverlay.ANDROID_THEME_OVERLAY_ATTRS);
        int v = typedArray0.getResourceId(0, 0);
        int v1 = typedArray0.getResourceId(1, 0);
        typedArray0.recycle();
        return v == 0 ? v1 : v;
    }

    private static int obtainMaterialThemeOverlayId(Context context0, AttributeSet attributeSet0, int v, int v1) {
        TypedArray typedArray0 = context0.obtainStyledAttributes(attributeSet0, MaterialThemeOverlay.MATERIAL_THEME_OVERLAY_ATTR, v, v1);
        int v2 = typedArray0.getResourceId(0, 0);
        typedArray0.recycle();
        return v2;
    }

    public static Context wrap(Context context0, AttributeSet attributeSet0, int v, int v1) {
        int v2 = MaterialThemeOverlay.obtainMaterialThemeOverlayId(context0, attributeSet0, v, v1);
        if(v2 != 0 && (!(context0 instanceof ContextThemeWrapper) || ((ContextThemeWrapper)context0).getThemeResId() != v2)) {
            Context context1 = new ContextThemeWrapper(context0, v2);
            int v3 = MaterialThemeOverlay.obtainAndroidThemeOverlayId(context0, attributeSet0);
            if(v3 != 0) {
                context1.getTheme().applyStyle(v3, true);
            }
            return context1;
        }
        return context0;
    }
}

