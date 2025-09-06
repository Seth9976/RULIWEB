package com.google.android.material.color.utilities;

import java.util.HashMap;
import java.util.Map;

public final class TonalPalette {
    Map cache;
    double chroma;
    double hue;
    Hct keyColor;

    private TonalPalette(double f, double f1, Hct hct0) {
        this.cache = new HashMap();
        this.hue = f;
        this.chroma = f1;
        this.keyColor = hct0;
    }

    private static Hct createKeyColor(double f, double f1) {
        Hct hct0 = Hct.from(f, f1, 50.0);
        double f2 = Math.abs(hct0.getChroma() - f1);
        for(double f3 = 1.0; f3 < 50.0 && Math.round(f1) != Math.round(hct0.getChroma()); ++f3) {
            Hct hct1 = Hct.from(f, f1, f3 + 50.0);
            double f4 = Math.abs(hct1.getChroma() - f1);
            if(f4 < f2) {
                f2 = f4;
                hct0 = hct1;
            }
            Hct hct2 = Hct.from(f, f1, 50.0 - f3);
            double f5 = Math.abs(hct2.getChroma() - f1);
            if(f5 < f2) {
                f2 = f5;
                hct0 = hct2;
            }
        }
        return hct0;
    }

    public static TonalPalette fromHct(Hct hct0) {
        return new TonalPalette(hct0.getHue(), hct0.getChroma(), hct0);
    }

    public static TonalPalette fromHueAndChroma(double f, double f1) {
        return new TonalPalette(f, f1, TonalPalette.createKeyColor(f, f1));
    }

    public static TonalPalette fromInt(int v) {
        return TonalPalette.fromHct(Hct.fromInt(v));
    }

    public double getChroma() {
        return this.chroma;
    }

    public Hct getHct(double f) {
        return Hct.from(this.hue, this.chroma, f);
    }

    public double getHue() {
        return this.hue;
    }

    public Hct getKeyColor() {
        return this.keyColor;
    }

    public int tone(int v) {
        Integer integer0 = (Integer)this.cache.get(v);
        if(integer0 == null) {
            integer0 = Hct.from(this.hue, this.chroma, v).toInt();
            this.cache.put(v, integer0);
        }
        return (int)integer0;
    }
}

