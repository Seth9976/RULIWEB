package com.google.android.material.color;

import com.google.android.material.R.color;
import com.google.android.material.color.utilities.DynamicColor;
import com.google.android.material.color.utilities.DynamicScheme;
import com.google.android.material.color.utilities.MaterialDynamicColors;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Map;

public final class MaterialColorUtilitiesHelper {
    private static final Map colorResourceIdToColorValue;
    private static final MaterialDynamicColors dynamicColors;

    static {
        MaterialDynamicColors materialDynamicColors0 = new MaterialDynamicColors();
        MaterialColorUtilitiesHelper.dynamicColors = materialDynamicColors0;
        HashMap hashMap0 = new HashMap();
        DynamicColor dynamicColor0 = materialDynamicColors0.primary();
        hashMap0.put(color.material_personalized_color_primary, dynamicColor0);
        DynamicColor dynamicColor1 = materialDynamicColors0.onPrimary();
        hashMap0.put(color.material_personalized_color_on_primary, dynamicColor1);
        DynamicColor dynamicColor2 = materialDynamicColors0.inversePrimary();
        hashMap0.put(color.material_personalized_color_primary_inverse, dynamicColor2);
        DynamicColor dynamicColor3 = materialDynamicColors0.primaryContainer();
        hashMap0.put(color.material_personalized_color_primary_container, dynamicColor3);
        DynamicColor dynamicColor4 = materialDynamicColors0.onPrimaryContainer();
        hashMap0.put(color.material_personalized_color_on_primary_container, dynamicColor4);
        DynamicColor dynamicColor5 = materialDynamicColors0.secondary();
        hashMap0.put(color.material_personalized_color_secondary, dynamicColor5);
        DynamicColor dynamicColor6 = materialDynamicColors0.onSecondary();
        hashMap0.put(color.material_personalized_color_on_secondary, dynamicColor6);
        DynamicColor dynamicColor7 = materialDynamicColors0.secondaryContainer();
        hashMap0.put(color.material_personalized_color_secondary_container, dynamicColor7);
        DynamicColor dynamicColor8 = materialDynamicColors0.onSecondaryContainer();
        hashMap0.put(color.material_personalized_color_on_secondary_container, dynamicColor8);
        DynamicColor dynamicColor9 = materialDynamicColors0.tertiary();
        hashMap0.put(color.material_personalized_color_tertiary, dynamicColor9);
        DynamicColor dynamicColor10 = materialDynamicColors0.onTertiary();
        hashMap0.put(color.material_personalized_color_on_tertiary, dynamicColor10);
        DynamicColor dynamicColor11 = materialDynamicColors0.tertiaryContainer();
        hashMap0.put(color.material_personalized_color_tertiary_container, dynamicColor11);
        DynamicColor dynamicColor12 = materialDynamicColors0.onTertiaryContainer();
        hashMap0.put(color.material_personalized_color_on_tertiary_container, dynamicColor12);
        DynamicColor dynamicColor13 = materialDynamicColors0.background();
        hashMap0.put(color.material_personalized_color_background, dynamicColor13);
        DynamicColor dynamicColor14 = materialDynamicColors0.onBackground();
        hashMap0.put(color.material_personalized_color_on_background, dynamicColor14);
        DynamicColor dynamicColor15 = materialDynamicColors0.surface();
        hashMap0.put(color.material_personalized_color_surface, dynamicColor15);
        DynamicColor dynamicColor16 = materialDynamicColors0.onSurface();
        hashMap0.put(color.material_personalized_color_on_surface, dynamicColor16);
        DynamicColor dynamicColor17 = materialDynamicColors0.surfaceVariant();
        hashMap0.put(color.material_personalized_color_surface_variant, dynamicColor17);
        DynamicColor dynamicColor18 = materialDynamicColors0.onSurfaceVariant();
        hashMap0.put(color.material_personalized_color_on_surface_variant, dynamicColor18);
        DynamicColor dynamicColor19 = materialDynamicColors0.inverseSurface();
        hashMap0.put(color.material_personalized_color_surface_inverse, dynamicColor19);
        DynamicColor dynamicColor20 = materialDynamicColors0.inverseOnSurface();
        hashMap0.put(color.material_personalized_color_on_surface_inverse, dynamicColor20);
        DynamicColor dynamicColor21 = materialDynamicColors0.surfaceBright();
        hashMap0.put(color.material_personalized_color_surface_bright, dynamicColor21);
        DynamicColor dynamicColor22 = materialDynamicColors0.surfaceDim();
        hashMap0.put(color.material_personalized_color_surface_dim, dynamicColor22);
        DynamicColor dynamicColor23 = materialDynamicColors0.surfaceContainer();
        hashMap0.put(color.material_personalized_color_surface_container, dynamicColor23);
        DynamicColor dynamicColor24 = materialDynamicColors0.surfaceContainerLow();
        hashMap0.put(color.material_personalized_color_surface_container_low, dynamicColor24);
        DynamicColor dynamicColor25 = materialDynamicColors0.surfaceContainerHigh();
        hashMap0.put(color.material_personalized_color_surface_container_high, dynamicColor25);
        DynamicColor dynamicColor26 = materialDynamicColors0.surfaceContainerLowest();
        hashMap0.put(color.material_personalized_color_surface_container_lowest, dynamicColor26);
        DynamicColor dynamicColor27 = materialDynamicColors0.surfaceContainerHighest();
        hashMap0.put(color.material_personalized_color_surface_container_highest, dynamicColor27);
        DynamicColor dynamicColor28 = materialDynamicColors0.outline();
        hashMap0.put(color.material_personalized_color_outline, dynamicColor28);
        DynamicColor dynamicColor29 = materialDynamicColors0.outlineVariant();
        hashMap0.put(color.material_personalized_color_outline_variant, dynamicColor29);
        DynamicColor dynamicColor30 = materialDynamicColors0.error();
        hashMap0.put(color.material_personalized_color_error, dynamicColor30);
        DynamicColor dynamicColor31 = materialDynamicColors0.onError();
        hashMap0.put(color.material_personalized_color_on_error, dynamicColor31);
        DynamicColor dynamicColor32 = materialDynamicColors0.errorContainer();
        hashMap0.put(color.material_personalized_color_error_container, dynamicColor32);
        DynamicColor dynamicColor33 = materialDynamicColors0.onErrorContainer();
        hashMap0.put(color.material_personalized_color_on_error_container, dynamicColor33);
        DynamicColor dynamicColor34 = materialDynamicColors0.controlActivated();
        hashMap0.put(color.material_personalized_color_control_activated, dynamicColor34);
        DynamicColor dynamicColor35 = materialDynamicColors0.controlNormal();
        hashMap0.put(color.material_personalized_color_control_normal, dynamicColor35);
        DynamicColor dynamicColor36 = materialDynamicColors0.controlHighlight();
        hashMap0.put(color.material_personalized_color_control_highlight, dynamicColor36);
        DynamicColor dynamicColor37 = materialDynamicColors0.textPrimaryInverse();
        hashMap0.put(color.material_personalized_color_text_primary_inverse, dynamicColor37);
        DynamicColor dynamicColor38 = materialDynamicColors0.textSecondaryAndTertiaryInverse();
        hashMap0.put(color.material_personalized_color_text_secondary_and_tertiary_inverse, dynamicColor38);
        DynamicColor dynamicColor39 = materialDynamicColors0.textSecondaryAndTertiaryInverseDisabled();
        hashMap0.put(color.material_personalized_color_text_secondary_and_tertiary_inverse_disabled, dynamicColor39);
        DynamicColor dynamicColor40 = materialDynamicColors0.textPrimaryInverseDisableOnly();
        hashMap0.put(color.material_personalized_color_text_primary_inverse_disable_only, dynamicColor40);
        DynamicColor dynamicColor41 = materialDynamicColors0.textHintInverse();
        hashMap0.put(color.material_personalized_color_text_hint_foreground_inverse, dynamicColor41);
        MaterialColorUtilitiesHelper.colorResourceIdToColorValue = Collections.unmodifiableMap(hashMap0);
    }

    public static Map createColorResourcesIdsToColorValues(DynamicScheme dynamicScheme0) {
        HashMap hashMap0 = new HashMap();
        for(Object object0: MaterialColorUtilitiesHelper.colorResourceIdToColorValue.entrySet()) {
            hashMap0.put(((Integer)((Map.Entry)object0).getKey()), ((DynamicColor)((Map.Entry)object0).getValue()).getArgb(dynamicScheme0));
        }
        return Collections.unmodifiableMap(hashMap0);
    }
}

