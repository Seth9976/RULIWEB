package com.google.android.material.color.utilities;

public class Blend {
    public static int cam16Ucs(int v, int v1, double f) {
        Cam16 cam160 = Cam16.fromInt(v);
        Cam16 cam161 = Cam16.fromInt(v1);
        double f1 = cam160.getJstar();
        double f2 = cam160.getAstar();
        double f3 = cam160.getBstar();
        return Cam16.fromUcs(f1 + (cam161.getJstar() - f1) * f, f2 + (cam161.getAstar() - f2) * f, f3 + (cam161.getBstar() - f3) * f).toInt();
    }

    public static int harmonize(int v, int v1) {
        Hct hct0 = Hct.fromInt(v);
        Hct hct1 = Hct.fromInt(v1);
        return Hct.from(MathUtils.sanitizeDegreesDouble(hct0.getHue() + Math.min(MathUtils.differenceDegrees(hct0.getHue(), hct1.getHue()) * 0.5, 15.0) * MathUtils.rotationDirection(hct0.getHue(), hct1.getHue())), hct0.getChroma(), hct0.getTone()).toInt();
    }

    public static int hctHue(int v, int v1, double f) {
        return Hct.from(Cam16.fromInt(Blend.cam16Ucs(v, v1, f)).getHue(), Cam16.fromInt(v).getChroma(), ColorUtils.lstarFromArgb(v)).toInt();
    }
}

