package com.google.android.material.color.utilities;

public class SchemeFruitSalad extends DynamicScheme {
    public SchemeFruitSalad(Hct hct0, boolean z, double f) {
        TonalPalette tonalPalette0 = TonalPalette.fromHueAndChroma(MathUtils.sanitizeDegreesDouble(hct0.getHue() - 50.0), 48.0);
        TonalPalette tonalPalette1 = TonalPalette.fromHueAndChroma(MathUtils.sanitizeDegreesDouble(hct0.getHue() - 50.0), 36.0);
        TonalPalette tonalPalette2 = TonalPalette.fromHueAndChroma(hct0.getHue(), 36.0);
        TonalPalette tonalPalette3 = TonalPalette.fromHueAndChroma(hct0.getHue(), 10.0);
        TonalPalette tonalPalette4 = TonalPalette.fromHueAndChroma(hct0.getHue(), 16.0);
        super(hct0, Variant.FRUIT_SALAD, z, f, tonalPalette0, tonalPalette1, tonalPalette2, tonalPalette3, tonalPalette4);
    }
}

