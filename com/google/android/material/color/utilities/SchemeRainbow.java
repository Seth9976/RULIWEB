package com.google.android.material.color.utilities;

public class SchemeRainbow extends DynamicScheme {
    public SchemeRainbow(Hct hct0, boolean z, double f) {
        TonalPalette tonalPalette0 = TonalPalette.fromHueAndChroma(hct0.getHue(), 48.0);
        TonalPalette tonalPalette1 = TonalPalette.fromHueAndChroma(hct0.getHue(), 16.0);
        TonalPalette tonalPalette2 = TonalPalette.fromHueAndChroma(MathUtils.sanitizeDegreesDouble(hct0.getHue() + 60.0), 24.0);
        TonalPalette tonalPalette3 = TonalPalette.fromHueAndChroma(hct0.getHue(), 0.0);
        TonalPalette tonalPalette4 = TonalPalette.fromHueAndChroma(hct0.getHue(), 0.0);
        super(hct0, Variant.RAINBOW, z, f, tonalPalette0, tonalPalette1, tonalPalette2, tonalPalette3, tonalPalette4);
    }
}

