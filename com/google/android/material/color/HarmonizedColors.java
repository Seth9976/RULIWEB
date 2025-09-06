package com.google.android.material.color;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.view.ContextThemeWrapper;
import androidx.core.content.ContextCompat;
import com.google.android.material.R.style;
import java.util.HashMap;
import java.util.Map;

public class HarmonizedColors {
    private static final String TAG = "HarmonizedColors";

    static {
    }

    private static void addHarmonizedColorAttributesToReplacementMap(Map map0, TypedArray typedArray0, TypedArray typedArray1, int v) {
        if(typedArray1 == null) {
            typedArray1 = typedArray0;
        }
        for(int v1 = 0; v1 < typedArray0.getIndexCount(); ++v1) {
            int v2 = typedArray1.getResourceId(v1, 0);
            if(v2 != 0 && typedArray0.hasValue(v1) && ResourcesLoaderUtils.isColorResource(typedArray0.getType(v1))) {
                map0.put(v2, MaterialColors.harmonize(typedArray0.getColor(v1, 0), v));
            }
        }
    }

    public static void applyToContextIfAvailable(Context context0, HarmonizedColorsOptions harmonizedColorsOptions0) {
        Map map0 = HarmonizedColors.createHarmonizedColorReplacementMap(context0, harmonizedColorsOptions0);
        int v = harmonizedColorsOptions0.getThemeOverlayResourceId(0);
        if(ResourcesLoaderUtils.addResourcesLoaderToContext(context0, map0) && v != 0) {
            ThemeUtils.applyThemeOverlay(context0, v);
        }
    }

    private static Map createHarmonizedColorReplacementMap(Context context0, HarmonizedColorsOptions harmonizedColorsOptions0) {
        Map map0 = new HashMap();
        int v = MaterialColors.getColor(context0, harmonizedColorsOptions0.getColorAttributeToHarmonizeWith(), "HarmonizedColors");
        int[] arr_v = harmonizedColorsOptions0.getColorResourceIds();
        for(int v1 = 0; v1 < arr_v.length; ++v1) {
            int v2 = arr_v[v1];
            map0.put(v2, MaterialColors.harmonize(ContextCompat.getColor(context0, v2), v));
        }
        HarmonizedColorAttributes harmonizedColorAttributes0 = harmonizedColorsOptions0.getColorAttributes();
        if(harmonizedColorAttributes0 != null) {
            int[] arr_v1 = harmonizedColorAttributes0.getAttributes();
            if(arr_v1.length > 0) {
                int v3 = harmonizedColorAttributes0.getThemeOverlay();
                TypedArray typedArray0 = context0.obtainStyledAttributes(arr_v1);
                TypedArray typedArray1 = v3 == 0 ? null : new ContextThemeWrapper(context0, v3).obtainStyledAttributes(arr_v1);
                HarmonizedColors.addHarmonizedColorAttributesToReplacementMap(map0, typedArray0, typedArray1, v);
                typedArray0.recycle();
                if(typedArray1 != null) {
                    typedArray1.recycle();
                }
            }
        }
        return map0;
    }

    public static boolean isHarmonizedColorAvailable() [...] // 潜在的解密器

    public static Context wrapContextIfAvailable(Context context0, HarmonizedColorsOptions harmonizedColorsOptions0) {
        Map map0 = HarmonizedColors.createHarmonizedColorReplacementMap(context0, harmonizedColorsOptions0);
        Context context1 = new ContextThemeWrapper(context0, harmonizedColorsOptions0.getThemeOverlayResourceId(style.ThemeOverlay_Material3_HarmonizedColors_Empty));
        ((ContextThemeWrapper)context1).applyOverrideConfiguration(new Configuration());
        return ResourcesLoaderUtils.addResourcesLoaderToContext(context1, map0) ? context1 : context0;
    }
}

