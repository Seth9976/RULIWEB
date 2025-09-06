package com.google.android.material.color;

import com.google.android.material.R.attr;
import com.google.android.material.R.style;

public final class HarmonizedColorAttributes {
    private static final int[] HARMONIZED_MATERIAL_ATTRIBUTES;
    private final int[] attributes;
    private final int themeOverlay;

    static {
        HarmonizedColorAttributes.HARMONIZED_MATERIAL_ATTRIBUTES = new int[]{attr.colorError, attr.colorOnError, attr.colorErrorContainer, attr.colorOnErrorContainer};
    }

    private HarmonizedColorAttributes(int[] arr_v, int v) {
        if(v != 0 && arr_v.length == 0) {
            throw new IllegalArgumentException("Theme overlay should be used with the accompanying int[] attributes.");
        }
        this.attributes = arr_v;
        this.themeOverlay = v;
    }

    public static HarmonizedColorAttributes create(int[] arr_v) {
        return new HarmonizedColorAttributes(arr_v, 0);
    }

    public static HarmonizedColorAttributes create(int[] arr_v, int v) {
        return new HarmonizedColorAttributes(arr_v, v);
    }

    public static HarmonizedColorAttributes createMaterialDefaults() {
        return HarmonizedColorAttributes.create(HarmonizedColorAttributes.HARMONIZED_MATERIAL_ATTRIBUTES, style.ThemeOverlay_Material3_HarmonizedColors);
    }

    public int[] getAttributes() {
        return this.attributes;
    }

    public int getThemeOverlay() {
        return this.themeOverlay;
    }
}

