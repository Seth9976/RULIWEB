package com.google.android.material.color.utilities;

public class SchemeNeutral extends DynamicScheme {
    public SchemeNeutral(Hct hct0, boolean z, double f) {
        TonalPalette tonalPalette0 = TonalPalette.fromHueAndChroma(hct0.getHue(), 12.0);
        TonalPalette tonalPalette1 = TonalPalette.fromHueAndChroma(hct0.getHue(), 8.0);
        TonalPalette tonalPalette2 = TonalPalette.fromHueAndChroma(hct0.getHue(), 16.0);
        TonalPalette tonalPalette3 = TonalPalette.fromHueAndChroma(hct0.getHue(), 2.0);
        TonalPalette tonalPalette4 = TonalPalette.fromHueAndChroma(hct0.getHue(), 2.0);
        super(hct0, Variant.NEUTRAL, z, f, tonalPalette0, tonalPalette1, tonalPalette2, tonalPalette3, tonalPalette4);
    }
}

