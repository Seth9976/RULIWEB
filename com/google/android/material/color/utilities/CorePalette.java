package com.google.android.material.color.utilities;

public final class CorePalette {
    public TonalPalette a1;
    public TonalPalette a2;
    public TonalPalette a3;
    public TonalPalette error;
    public TonalPalette n1;
    public TonalPalette n2;

    private CorePalette(int v, boolean z) {
        Hct hct0 = Hct.fromInt(v);
        double f = hct0.getHue();
        double f1 = hct0.getChroma();
        if(z) {
            this.a1 = TonalPalette.fromHueAndChroma(f, f1);
            this.a2 = TonalPalette.fromHueAndChroma(f, f1 / 3.0);
            this.a3 = TonalPalette.fromHueAndChroma(f + 60.0, f1 / 2.0);
            this.n1 = TonalPalette.fromHueAndChroma(f, Math.min(f1 / 12.0, 4.0));
            this.n2 = TonalPalette.fromHueAndChroma(f, Math.min(f1 / 6.0, 8.0));
        }
        else {
            this.a1 = TonalPalette.fromHueAndChroma(f, Math.max(48.0, f1));
            this.a2 = TonalPalette.fromHueAndChroma(f, 16.0);
            this.a3 = TonalPalette.fromHueAndChroma(f + 60.0, 24.0);
            this.n1 = TonalPalette.fromHueAndChroma(f, 4.0);
            this.n2 = TonalPalette.fromHueAndChroma(f, 8.0);
        }
        this.error = TonalPalette.fromHueAndChroma(25.0, 84.0);
    }

    public static CorePalette contentOf(int v) {
        return new CorePalette(v, true);
    }

    public static CorePalette of(int v) {
        return new CorePalette(v, false);
    }
}

