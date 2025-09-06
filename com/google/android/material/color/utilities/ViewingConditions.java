package com.google.android.material.color.utilities;

public final class ViewingConditions {
    public static final ViewingConditions DEFAULT;
    private final double aw;
    private final double c;
    private final double fl;
    private final double flRoot;
    private final double n;
    private final double nbb;
    private final double nc;
    private final double ncb;
    private final double[] rgbD;
    private final double z;

    static {
        ViewingConditions.DEFAULT = ViewingConditions.defaultWithBackgroundLstar(50.0);
    }

    private ViewingConditions(double f, double f1, double f2, double f3, double f4, double f5, double[] arr_f, double f6, double f7, double f8) {
        this.n = f;
        this.aw = f1;
        this.nbb = f2;
        this.ncb = f3;
        this.c = f4;
        this.nc = f5;
        this.rgbD = arr_f;
        this.fl = f6;
        this.flRoot = f7;
        this.z = f8;
    }

    // 去混淆评级： 低(20)
    public static ViewingConditions defaultWithBackgroundLstar(double f) {
        return ViewingConditions.make(ColorUtils.whitePointD65(), 11.725678, f, 2.0, false);
    }

    public double getAw() {
        return this.aw;
    }

    double getC() {
        return this.c;
    }

    double getFl() {
        return this.fl;
    }

    public double getFlRoot() {
        return this.flRoot;
    }

    public double getN() {
        return this.n;
    }

    public double getNbb() {
        return this.nbb;
    }

    double getNc() {
        return this.nc;
    }

    double getNcb() {
        return this.ncb;
    }

    public double[] getRgbD() {
        return this.rgbD;
    }

    double getZ() {
        return this.z;
    }

    public static ViewingConditions make(double[] arr_f, double f, double f1, double f2, boolean z) {
        double f3 = arr_f[0];
        double[] arr_f1 = Cam16.XYZ_TO_CAM16RGB[0];
        double f4 = arr_f1[0] * f3;
        double f5 = arr_f[1];
        double f6 = f4 + arr_f1[1] * f5;
        double f7 = arr_f[2];
        double f8 = f6 + arr_f1[2] * f7;
        double[] arr_f2 = Cam16.XYZ_TO_CAM16RGB[1];
        double f9 = arr_f2[0] * f3 + arr_f2[1] * f5 + arr_f2[2] * f7;
        double[] arr_f3 = Cam16.XYZ_TO_CAM16RGB[2];
        double f10 = f3 * arr_f3[0] + f5 * arr_f3[1] + f7 * arr_f3[2];
        double f11 = f2 / 10.0 + 0.8;
        double f12 = MathUtils.clampDouble(0.0, 1.0, (z ? 1.0 : (1.0 - Math.exp((-f - 42.0) / 92.0) * 0.277778) * f11));
        double[] arr_f4 = {100.0 / f8 * f12 + 1.0 - f12, 100.0 / f9 * f12 + 1.0 - f12, 100.0 / f10 * f12 + 1.0 - f12};
        double f13 = 1.0 / (5.0 * f + 1.0);
        double f14 = f13 * f13 * f13 * f13;
        double f15 = f14 * f + (1.0 - f14) * 0.1 * (1.0 - f14) * Math.cbrt(5.0 * f);
        double f16 = ColorUtils.yFromLstar(Math.max(0.1, f1)) / arr_f[1];
        double f17 = Math.pow(f16, 0.2);
        double f18 = Math.pow(arr_f4[0] * f15 * f8 / 100.0, 0.42);
        double f19 = Math.pow(arr_f4[1] * f15 * f9 / 100.0, 0.42);
        double f20 = Math.pow(arr_f4[2] * f15 * f10 / 100.0, 0.42);
        new double[]{f18 * 400.0 / (f18 + 27.13), f19 * 400.0 / (f19 + 27.13), 400.0 * f20 / (f20 + 27.13)};
        return new ViewingConditions(f16, (f18 * 400.0 / (f18 + 27.13) * 2.0 + f19 * 400.0 / (f19 + 27.13) + 400.0 * f20 / (f20 + 27.13) * 0.05) * (0.725 / f17), 0.725 / f17, 0.725 / f17, (f11 >= 0.9 ? (1.0 - (f11 - 0.9) * 10.0) * 0.59 + (f11 - 0.9) * 6.9 : (1.0 - (f11 - 0.8) * 10.0) * 0.525 + (f11 - 0.8) * 5.9), f11, arr_f4, f15, Math.pow(f15, 0.25), Math.sqrt(f16) + 1.48);
    }
}

