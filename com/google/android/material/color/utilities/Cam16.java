package com.google.android.material.color.utilities;

public final class Cam16 {
    static final double[][] CAM16RGB_TO_XYZ;
    static final double[][] XYZ_TO_CAM16RGB;
    private final double astar;
    private final double bstar;
    private final double chroma;
    private final double hue;
    private final double j;
    private final double jstar;
    private final double m;
    private final double q;
    private final double s;
    private final double[] tempArray;

    static {
        Cam16.XYZ_TO_CAM16RGB = new double[][]{new double[]{0.401288, 0.650173, -0.051461}, new double[]{-0.250268, 1.204414, 0.045854}, new double[]{-0.002079, 0.048952, 0.953127}};
        Cam16.CAM16RGB_TO_XYZ = new double[][]{new double[]{1.862068, -1.011255, 0.149187}, new double[]{0.387527, 0.621447, -0.008974}, new double[]{-0.015842, -0.034123, 1.049964}};
    }

    private Cam16(double f, double f1, double f2, double f3, double f4, double f5, double f6, double f7, double f8) {
        this.tempArray = new double[]{0.0, 0.0, 0.0};
        this.hue = f;
        this.chroma = f1;
        this.j = f2;
        this.q = f3;
        this.m = f4;
        this.s = f5;
        this.jstar = f6;
        this.astar = f7;
        this.bstar = f8;
    }

    double distance(Cam16 cam160) {
        double f = this.getJstar();
        double f1 = cam160.getJstar();
        double f2 = this.getAstar();
        double f3 = cam160.getAstar();
        double f4 = this.getBstar();
        double f5 = cam160.getBstar();
        return Math.pow(Math.sqrt((f - f1) * (f - f1) + (f2 - f3) * (f2 - f3) + (f4 - f5) * (f4 - f5)), 0.63) * 1.41;
    }

    public static Cam16 fromInt(int v) {
        return Cam16.fromIntInViewingConditions(v, ViewingConditions.DEFAULT);
    }

    static Cam16 fromIntInViewingConditions(int v, ViewingConditions viewingConditions0) {
        double f = ColorUtils.linearized((0xFF0000 & v) >> 16);
        double f1 = ColorUtils.linearized((0xFF00 & v) >> 8);
        double f2 = ColorUtils.linearized(v & 0xFF);
        return Cam16.fromXyzInViewingConditions(0.412339 * f + 0.357621 * f1 + 0.18051 * f2, 0.2126 * f + 0.7152 * f1 + 0.0722 * f2, f * 0.019321 + f1 * 0.119164 + f2 * 0.950345, viewingConditions0);
    }

    static Cam16 fromJch(double f, double f1, double f2) {
        return Cam16.fromJchInViewingConditions(f, f1, f2, ViewingConditions.DEFAULT);
    }

    private static Cam16 fromJchInViewingConditions(double f, double f1, double f2, ViewingConditions viewingConditions0) {
        double f3 = f1 * viewingConditions0.getFlRoot();
        double f4 = Math.toRadians(f2);
        double f5 = Math.log1p(0.0228 * f3);
        return new Cam16(f2, f1, f, 4.0 / viewingConditions0.getC() * Math.sqrt(f / 100.0) * (viewingConditions0.getAw() + 4.0) * viewingConditions0.getFlRoot(), f3, Math.sqrt(f1 / Math.sqrt(f / 100.0) * viewingConditions0.getC() / (viewingConditions0.getAw() + 4.0)) * 50.0, 1.7 * f / (0.007 * f + 1.0), f5 * 43.859649 * Math.cos(f4), f5 * 43.859649 * Math.sin(f4));
    }

    public static Cam16 fromUcs(double f, double f1, double f2) {
        return Cam16.fromUcsInViewingConditions(f, f1, f2, ViewingConditions.DEFAULT);
    }

    public static Cam16 fromUcsInViewingConditions(double f, double f1, double f2, ViewingConditions viewingConditions0) {
        double f3 = Math.atan2(f2, f1) * 57.29578;
        return Cam16.fromJchInViewingConditions(f / (1.0 - (f - 100.0) * 0.007), Math.expm1(Math.hypot(f1, f2) * 0.0228) / 0.0228 / viewingConditions0.getFlRoot(), (f3 < 0.0 ? f3 + 360.0 : Math.atan2(f2, f1) * 57.29578), viewingConditions0);
    }

    static Cam16 fromXyzInViewingConditions(double f, double f1, double f2, ViewingConditions viewingConditions0) {
        double[] arr_f = Cam16.XYZ_TO_CAM16RGB[0];
        double f3 = arr_f[0] * f + arr_f[1] * f1 + arr_f[2] * f2;
        double[] arr_f1 = Cam16.XYZ_TO_CAM16RGB[1];
        double f4 = arr_f1[0] * f + arr_f1[1] * f1 + arr_f1[2] * f2;
        double[] arr_f2 = Cam16.XYZ_TO_CAM16RGB[2];
        double f5 = arr_f2[0] * f + arr_f2[1] * f1 + arr_f2[2] * f2;
        double f6 = viewingConditions0.getRgbD()[0] * f3;
        double f7 = viewingConditions0.getRgbD()[1] * f4;
        double f8 = viewingConditions0.getRgbD()[2] * f5;
        double f9 = Math.pow(viewingConditions0.getFl() * Math.abs(f6) / 100.0, 0.42);
        double f10 = Math.pow(viewingConditions0.getFl() * Math.abs(f7) / 100.0, 0.42);
        double f11 = Math.pow(viewingConditions0.getFl() * Math.abs(f8) / 100.0, 0.42);
        double f12 = Math.signum(f6) * 400.0 * f9 / (f9 + 27.13);
        double f13 = Math.signum(f7) * 400.0 * f10 / (f10 + 27.13);
        double f14 = Math.signum(f8) * 400.0 * f11 / (f11 + 27.13);
        double f15 = (f12 * 11.0 + -12.0 * f13 + f14) / 11.0;
        double f16 = (f12 + f13 - f14 * 2.0) / 9.0;
        double f17 = Math.toDegrees(Math.atan2(f16, f15));
        if(f17 < 0.0) {
            f17 += 360.0;
        }
        else if(f17 >= 360.0) {
            f17 -= 360.0;
        }
        double f18 = Math.toRadians(f17);
        double f19 = Math.pow((f12 * 40.0 + f13 * 20.0 + f14) / 20.0 * viewingConditions0.getNbb() / viewingConditions0.getAw(), viewingConditions0.getC() * viewingConditions0.getZ());
        double f20 = f19 * 100.0 / 100.0;
        double f21 = Math.pow(1.64 - Math.pow(0.29, viewingConditions0.getN()), 0.73) * Math.pow((Math.cos(Math.toRadians((f17 < 20.14 ? f17 + 360.0 : f17)) + 2.0) + 3.8) * 961.538462 * viewingConditions0.getNc() * viewingConditions0.getNcb() * Math.hypot(f15, f16) / ((f12 * 20.0 + f13 * 20.0 + 21.0 * f14) / 20.0 + 0.305), 0.9);
        double f22 = f21 * Math.sqrt(f20);
        double f23 = f22 * viewingConditions0.getFlRoot();
        double f24 = Math.log1p(0.0228 * f23);
        return new Cam16(f17, f22, f19 * 100.0, viewingConditions0.getFlRoot() * (4.0 / viewingConditions0.getC() * Math.sqrt(f20) * (viewingConditions0.getAw() + 4.0)), f23, Math.sqrt(f21 * viewingConditions0.getC() / (viewingConditions0.getAw() + 4.0)) * 50.0, 1.7 * (f19 * 100.0) / (0.007 * (f19 * 100.0) + 1.0), f24 * 43.859649 * Math.cos(f18), f24 * 43.859649 * Math.sin(f18));
    }

    public double getAstar() {
        return this.astar;
    }

    public double getBstar() {
        return this.bstar;
    }

    public double getChroma() {
        return this.chroma;
    }

    public double getHue() {
        return this.hue;
    }

    public double getJ() {
        return this.j;
    }

    public double getJstar() {
        return this.jstar;
    }

    public double getM() {
        return this.m;
    }

    public double getQ() {
        return this.q;
    }

    public double getS() {
        return this.s;
    }

    public int toInt() {
        return this.viewed(ViewingConditions.DEFAULT);
    }

    int viewed(ViewingConditions viewingConditions0) {
        double[] arr_f = this.xyzInViewingConditions(viewingConditions0, this.tempArray);
        return ColorUtils.argbFromXyz(arr_f[0], arr_f[1], arr_f[2]);
    }

    double[] xyzInViewingConditions(ViewingConditions viewingConditions0, double[] arr_f) {
        double f = Math.pow((this.getChroma() == 0.0 || this.getJ() == 0.0 ? 0.0 : this.getChroma() / Math.sqrt(this.getJ() / 100.0)) / Math.pow(1.64 - Math.pow(0.29, viewingConditions0.getN()), 0.73), 1.111111);
        double f1 = Math.toRadians(this.getHue());
        double f2 = viewingConditions0.getAw() * Math.pow(this.getJ() / 100.0, 1.0 / viewingConditions0.getC() / viewingConditions0.getZ()) / viewingConditions0.getNbb();
        double f3 = Math.sin(f1);
        double f4 = Math.cos(f1);
        double f5 = (f2 + 0.305) * 23.0 * f / ((Math.cos(f1 + 2.0) + 3.8) * 961.538462 * viewingConditions0.getNc() * viewingConditions0.getNcb() * 23.0 + 11.0 * f * f4 + f * 108.0 * f3);
        double f6 = f4 * f5;
        double f7 = f5 * f3;
        double f8 = (451.0 * f6 + f2 * 460.0 + 288.0 * f7) / 1403.0;
        double f9 = (f2 * 460.0 - 891.0 * f6 - 261.0 * f7) / 1403.0;
        double f10 = (f2 * 460.0 - f6 * 220.0 - f7 * 6300.0) / 1403.0;
        double f11 = Math.signum(f8) * (100.0 / viewingConditions0.getFl()) * Math.pow(Math.max(0.0, Math.abs(f8) * 27.13 / (400.0 - Math.abs(f8))), 2.380952) / viewingConditions0.getRgbD()[0];
        double f12 = Math.signum(f9) * (100.0 / viewingConditions0.getFl()) * Math.pow(Math.max(0.0, Math.abs(f9) * 27.13 / (400.0 - Math.abs(f9))), 2.380952) / viewingConditions0.getRgbD()[1];
        double f13 = Math.signum(f10) * (100.0 / viewingConditions0.getFl()) * Math.pow(Math.max(0.0, Math.abs(f10) * 27.13 / (400.0 - Math.abs(f10))), 2.380952) / viewingConditions0.getRgbD()[2];
        double[] arr_f1 = Cam16.CAM16RGB_TO_XYZ[0];
        double f14 = arr_f1[0] * f11 + arr_f1[1] * f12 + arr_f1[2] * f13;
        double[] arr_f2 = Cam16.CAM16RGB_TO_XYZ[1];
        double f15 = arr_f2[0] * f11 + arr_f2[1] * f12 + arr_f2[2] * f13;
        double[] arr_f3 = Cam16.CAM16RGB_TO_XYZ[2];
        double f16 = f11 * arr_f3[0] + f12 * arr_f3[1] + f13 * arr_f3[2];
        if(arr_f != null) {
            arr_f[0] = f14;
            arr_f[1] = f15;
            arr_f[2] = f16;
            return arr_f;
        }
        return new double[]{f14, f15, f16};
    }
}

