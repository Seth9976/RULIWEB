package com.google.android.material.color.utilities;

public class SchemeVibrant extends DynamicScheme {
    private static final double[] HUES;
    private static final double[] SECONDARY_ROTATIONS;
    private static final double[] TERTIARY_ROTATIONS;

    static {
        SchemeVibrant.HUES = new double[]{0.0, 41.0, 61.0, 101.0, 131.0, 181.0, 251.0, 301.0, 360.0};
        SchemeVibrant.SECONDARY_ROTATIONS = new double[]{18.0, 15.0, 10.0, 12.0, 15.0, 18.0, 15.0, 12.0, 12.0};
        SchemeVibrant.TERTIARY_ROTATIONS = new double[]{35.0, 30.0, 20.0, 25.0, 30.0, 35.0, 30.0, 25.0, 25.0};
    }

    public SchemeVibrant(Hct hct0, boolean z, double f) {
        TonalPalette tonalPalette0 = TonalPalette.fromHueAndChroma(hct0.getHue(), 200.0);
        TonalPalette tonalPalette1 = TonalPalette.fromHueAndChroma(DynamicScheme.getRotatedHue(hct0, SchemeVibrant.HUES, SchemeVibrant.SECONDARY_ROTATIONS), 24.0);
        TonalPalette tonalPalette2 = TonalPalette.fromHueAndChroma(DynamicScheme.getRotatedHue(hct0, SchemeVibrant.HUES, SchemeVibrant.TERTIARY_ROTATIONS), 32.0);
        TonalPalette tonalPalette3 = TonalPalette.fromHueAndChroma(hct0.getHue(), 10.0);
        TonalPalette tonalPalette4 = TonalPalette.fromHueAndChroma(hct0.getHue(), 12.0);
        super(hct0, Variant.VIBRANT, z, f, tonalPalette0, tonalPalette1, tonalPalette2, tonalPalette3, tonalPalette4);
    }
}

