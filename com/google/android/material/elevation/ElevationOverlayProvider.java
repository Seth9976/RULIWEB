package com.google.android.material.elevation;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import androidx.core.graphics.ColorUtils;
import com.google.android.material.R.attr;
import com.google.android.material.color.MaterialColors;
import com.google.android.material.internal.ViewUtils;
import com.google.android.material.resources.MaterialAttributes;

public class ElevationOverlayProvider {
    private static final float FORMULA_MULTIPLIER = 4.5f;
    private static final float FORMULA_OFFSET = 2.0f;
    private static final int OVERLAY_ACCENT_COLOR_ALPHA;
    private final int colorSurface;
    private final float displayDensity;
    private final int elevationOverlayAccentColor;
    private final int elevationOverlayColor;
    private final boolean elevationOverlayEnabled;

    static {
        ElevationOverlayProvider.OVERLAY_ACCENT_COLOR_ALPHA = 5;
    }

    public ElevationOverlayProvider(Context context0) {
        this(MaterialAttributes.resolveBoolean(context0, attr.elevationOverlayEnabled, false), MaterialColors.getColor(context0, attr.elevationOverlayColor, 0), MaterialColors.getColor(context0, attr.elevationOverlayAccentColor, 0), MaterialColors.getColor(context0, attr.colorSurface, 0), context0.getResources().getDisplayMetrics().density);
    }

    public ElevationOverlayProvider(boolean z, int v, int v1, int v2, float f) {
        this.elevationOverlayEnabled = z;
        this.elevationOverlayColor = v;
        this.elevationOverlayAccentColor = v1;
        this.colorSurface = v2;
        this.displayDensity = f;
    }

    public int calculateOverlayAlpha(float f) {
        return Math.round(this.calculateOverlayAlphaFraction(f) * 255.0f);
    }

    public float calculateOverlayAlphaFraction(float f) {
        return this.displayDensity <= 0.0f || f <= 0.0f ? 0.0f : Math.min((((float)Math.log1p(f / this.displayDensity)) * 4.5f + 2.0f) / 100.0f, 1.0f);
    }

    public int compositeOverlay(int v, float f) {
        float f1 = this.calculateOverlayAlphaFraction(f);
        int v1 = Color.alpha(v);
        int v2 = MaterialColors.layer(ColorUtils.setAlphaComponent(v, 0xFF), this.elevationOverlayColor, f1);
        if(f1 > 0.0f) {
            int v3 = this.elevationOverlayAccentColor;
            if(v3 != 0) {
                v2 = MaterialColors.layer(v2, ColorUtils.setAlphaComponent(v3, ElevationOverlayProvider.OVERLAY_ACCENT_COLOR_ALPHA));
            }
        }
        return ColorUtils.setAlphaComponent(v2, v1);
    }

    public int compositeOverlay(int v, float f, View view0) {
        return this.compositeOverlay(v, f + this.getParentAbsoluteElevation(view0));
    }

    // 去混淆评级： 低(20)
    public int compositeOverlayIfNeeded(int v, float f) {
        return !this.elevationOverlayEnabled || !this.isThemeSurfaceColor(v) ? v : this.compositeOverlay(v, f);
    }

    public int compositeOverlayIfNeeded(int v, float f, View view0) {
        return this.compositeOverlayIfNeeded(v, f + this.getParentAbsoluteElevation(view0));
    }

    public int compositeOverlayWithThemeSurfaceColorIfNeeded(float f) {
        return this.compositeOverlayIfNeeded(this.colorSurface, f);
    }

    public int compositeOverlayWithThemeSurfaceColorIfNeeded(float f, View view0) {
        return this.compositeOverlayWithThemeSurfaceColorIfNeeded(f + this.getParentAbsoluteElevation(view0));
    }

    public float getParentAbsoluteElevation(View view0) {
        return ViewUtils.getParentAbsoluteElevation(view0);
    }

    public int getThemeElevationOverlayColor() {
        return this.elevationOverlayColor;
    }

    public int getThemeSurfaceColor() {
        return this.colorSurface;
    }

    public boolean isThemeElevationOverlayEnabled() {
        return this.elevationOverlayEnabled;
    }

    private boolean isThemeSurfaceColor(int v) {
        return ColorUtils.setAlphaComponent(v, 0xFF) == this.colorSurface;
    }
}

