package com.google.android.material.color.utilities;

public final class Contrast {
    private static final double CONTRAST_RATIO_EPSILON = 0.04;
    private static final double LUMINANCE_GAMUT_MAP_TOLERANCE = 0.4;
    public static final double RATIO_30 = 3.0;
    public static final double RATIO_45 = 4.5;
    public static final double RATIO_70 = 7.0;
    public static final double RATIO_MAX = 21.0;
    public static final double RATIO_MIN = 1.0;

    public static double darker(double f, double f1) {
        if(f >= 0.0 && f <= 100.0) {
            double f2 = ColorUtils.yFromLstar(f);
            double f3 = (f2 + 5.0) / f1 - 5.0;
            if(f3 >= 0.0 && f3 <= 100.0) {
                double f4 = Contrast.ratioOfYs(f2, f3);
                if(f4 < f1 && Math.abs(f4 - f1) > 0.04) {
                    return -1.0;
                }
                double f5 = ColorUtils.lstarFromY(f3);
                return f5 - 0.4 < 0.0 || f5 - 0.4 > 100.0 ? -1.0 : f5 - 0.4;
            }
        }
        return -1.0;
    }

    public static double darkerUnsafe(double f, double f1) {
        return Math.max(0.0, Contrast.darker(f, f1));
    }

    public static double lighter(double f, double f1) {
        if(f >= 0.0 && f <= 100.0) {
            double f2 = ColorUtils.yFromLstar(f);
            double f3 = (f2 + 5.0) * f1 - 5.0;
            if(f3 >= 0.0 && f3 <= 100.0) {
                double f4 = Contrast.ratioOfYs(f3, f2);
                if(f4 < f1 && Math.abs(f4 - f1) > 0.04) {
                    return -1.0;
                }
                double f5 = ColorUtils.lstarFromY(f3);
                return f5 + 0.4 < 0.0 || f5 + 0.4 > 100.0 ? -1.0 : f5 + 0.4;
            }
        }
        return -1.0;
    }

    public static double lighterUnsafe(double f, double f1) {
        double f2 = Contrast.lighter(f, f1);
        return f2 < 0.0 ? 100.0 : f2;
    }

    public static double ratioOfTones(double f, double f1) {
        return Contrast.ratioOfYs(ColorUtils.yFromLstar(f), ColorUtils.yFromLstar(f1));
    }

    public static double ratioOfYs(double f, double f1) {
        double f2 = Math.max(f, f1);
        if(f2 != f1) {
            f = f1;
        }
        return (f2 + 5.0) / (f + 5.0);
    }
}

