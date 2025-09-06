package com.google.android.material.color.utilities;

public class DynamicScheme {
    public final double contrastLevel;
    public final TonalPalette errorPalette;
    public final boolean isDark;
    public final TonalPalette neutralPalette;
    public final TonalPalette neutralVariantPalette;
    public final TonalPalette primaryPalette;
    public final TonalPalette secondaryPalette;
    public final int sourceColorArgb;
    public final Hct sourceColorHct;
    public final TonalPalette tertiaryPalette;
    public final Variant variant;

    public DynamicScheme(Hct hct0, Variant variant0, boolean z, double f, TonalPalette tonalPalette0, TonalPalette tonalPalette1, TonalPalette tonalPalette2, TonalPalette tonalPalette3, TonalPalette tonalPalette4) {
        this.sourceColorArgb = hct0.toInt();
        this.sourceColorHct = hct0;
        this.variant = variant0;
        this.isDark = z;
        this.contrastLevel = f;
        this.primaryPalette = tonalPalette0;
        this.secondaryPalette = tonalPalette1;
        this.tertiaryPalette = tonalPalette2;
        this.neutralPalette = tonalPalette3;
        this.neutralVariantPalette = tonalPalette4;
        this.errorPalette = TonalPalette.fromHueAndChroma(25.0, 84.0);
    }

    public static double getRotatedHue(Hct hct0, double[] arr_f, double[] arr_f1) {
        double f = hct0.getHue();
        if(arr_f1.length == 1) {
            return MathUtils.sanitizeDegreesDouble(f + arr_f1[0]);
        }
        for(int v = 0; v <= arr_f.length - 2; ++v) {
            if(arr_f[v] < f && f < arr_f[v + 1]) {
                return MathUtils.sanitizeDegreesDouble(f + arr_f1[v]);
            }
        }
        return f;
    }
}

