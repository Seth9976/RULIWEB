package com.google.android.material.color.utilities;

public class SchemeFidelity extends DynamicScheme {
    public SchemeFidelity(Hct hct0, boolean z, double f) {
        TonalPalette tonalPalette0 = TonalPalette.fromHueAndChroma(hct0.getHue(), hct0.getChroma());
        TonalPalette tonalPalette1 = TonalPalette.fromHueAndChroma(hct0.getHue(), Math.max(hct0.getChroma() - 32.0, hct0.getChroma() * 0.5));
        TonalPalette tonalPalette2 = TonalPalette.fromHct(DislikeAnalyzer.fixIfDisliked(new TemperatureCache(hct0).getComplement()));
        TonalPalette tonalPalette3 = TonalPalette.fromHueAndChroma(hct0.getHue(), hct0.getChroma() / 8.0);
        TonalPalette tonalPalette4 = TonalPalette.fromHueAndChroma(hct0.getHue(), hct0.getChroma() / 8.0 + 4.0);
        super(hct0, Variant.FIDELITY, z, f, tonalPalette0, tonalPalette1, tonalPalette2, tonalPalette3, tonalPalette4);
    }
}

