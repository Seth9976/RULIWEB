package com.google.android.material.color.utilities;

public class HctSolver {
    static final double[] CRITICAL_PLANES;
    static final double[][] LINRGB_FROM_SCALED_DISCOUNT;
    static final double[][] SCALED_DISCOUNT_FROM_LINRGB;
    static final double[] Y_FROM_LINRGB;

    static {
        HctSolver.SCALED_DISCOUNT_FROM_LINRGB = new double[][]{new double[]{0.001201, 0.00239, 0.00028}, new double[]{0.000589, 0.002979, 0.000327}, new double[]{0.000101, 0.000536, 0.003298}};
        HctSolver.LINRGB_FROM_SCALED_DISCOUNT = new double[][]{new double[]{1373.219871, -1100.425119, -7.278681}, new double[]{-271.815969, 559.658047, -32.460475}, new double[]{1.96229, -57.173815, 308.72332}};
        HctSolver.Y_FROM_LINRGB = new double[]{0.2126, 0.7152, 0.0722};
        HctSolver.CRITICAL_PLANES = new double[]{0.015176, 0.045529, 0.075882, 0.106234, 0.136587, 0.16694, 0.197293, 0.227645, 0.257998, 0.288351, 0.31883, 0.350926, 0.384831, 0.420575, 0.458183, 0.497684, 0.539102, 0.582465, 0.627797, 0.675123, 0.724467, 0.775853, 0.829305, 0.884845, 0.942497, 1.002283, 1.064224, 1.128342, 1.194659, 1.263196, 1.333973, 1.407011, 1.48233, 1.55995, 1.639891, 1.722172, 1.806811, 1.893829, 1.983244, 2.075074, 2.169338, 2.266054, 2.365239, 2.466911, 2.571089, 2.677788, 2.787027, 2.898822, 3.01319, 3.130148, 3.249712, 3.371899, 3.496724, 3.624204, 3.754355, 3.887193, 4.022732, 4.160989, 4.301978, 4.445716, 4.592217, 4.741496, 4.893569, 5.048448, 5.206151, 5.36669, 5.53008, 5.696336, 5.865472, 6.037501, 6.212438, 6.390297, 6.571092, 6.754835, 6.941541, 7.131224, 7.323896, 7.51957, 7.718262, 7.919982, 8.124744, 8.332562, 8.543449, 8.757416, 8.974477, 9.194644, 9.41793, 9.644348, 9.873909, 10.106627, 10.342513, 10.58158, 10.82384, 11.069305, 11.317986, 11.569897, 11.825048, 12.083452, 12.34512, 12.610064, 12.878295, 13.149826, 13.424667, 13.702831, 13.984327, 14.269169, 14.557366, 14.848931, 15.143873, 15.442206, 15.743939, 16.049083, 16.357649, 16.669649, 16.985093, 17.303992, 17.626356, 17.952197, 18.281525, 18.61435, 18.950683, 19.290535, 19.633915, 19.980835, 20.331305, 20.685334, 21.042934, 21.404114, 21.768885, 22.137256, 22.509239, 22.884842, 23.264076, 23.646951, 24.033477, 24.423664, 24.817521, 25.215058, 25.616285, 26.021212, 26.429848, 26.842204, 27.258288, 27.67811, 28.101681, 28.529008, 28.960102, 29.394973, 29.833629, 30.27608, 30.722335, 31.172404, 31.626296, 32.084019, 32.545584, 33.010999, 33.480274, 33.953417, 34.430438, 34.911346, 35.396149, 35.884857, 36.377478, 36.874022, 37.374498, 37.878913, 38.387278, 38.8996, 39.415889, 39.936153, 40.460401, 40.988641, 41.520883, 42.057135, 42.597405, 43.141702, 43.690035, 44.242412, 44.798841, 45.359332, 45.923891, 46.492529, 47.065253, 47.642071, 48.222992, 48.808025, 49.397176, 49.990456, 50.587871, 51.18943, 51.795142, 52.405014, 53.019054, 53.637272, 54.259673, 54.886268, 55.517063, 56.152068, 56.791289, 57.434734, 58.082413, 58.734332, 59.390499, 60.050923, 60.715611, 61.384572, 62.057812, 62.735339, 63.417163, 64.103289, 64.793726, 65.488482, 66.187564, 66.89098, 67.598738, 68.310845, 69.027308, 69.748136, 70.473336, 71.202916, 71.936882, 72.675243, 73.418006, 74.165179, 74.916768, 75.672782, 76.433228, 77.198112, 77.967444, 78.741229, 79.519475, 80.30219, 81.089381, 81.881055, 82.677219, 83.477881, 84.283048, 85.092727, 85.906925, 86.72565, 87.548908, 88.376707, 89.209054, 90.045956, 90.88742, 91.733453, 92.584063, 93.439256, 94.299039, 95.163419, 96.032404, 96.906, 97.784214, 98.667053, 99.554525};
    }

    static boolean areInCyclicOrder(double f, double f1, double f2) {
        return (f1 - f + 25.132741) % 6.283185 < (f2 - f + 25.132741) % 6.283185;
    }

    static double[] bisectToLimit(double f, double f1) {
        int v2;
        int v1;
        double[][] arr2_f = HctSolver.bisectToSegment(f, f1);
        double[] arr_f = arr2_f[0];
        double f2 = HctSolver.hueOf(arr_f);
        double[] arr_f1 = arr2_f[1];
        for(int v = 0; v < 3; ++v) {
            double f3 = arr_f[v];
            double f4 = arr_f1[v];
            if(f3 != f4) {
                if(f3 < f4) {
                    v1 = HctSolver.criticalPlaneBelow(HctSolver.trueDelinearized(f3));
                    v2 = HctSolver.criticalPlaneAbove(HctSolver.trueDelinearized(arr_f1[v]));
                }
                else {
                    v1 = HctSolver.criticalPlaneAbove(HctSolver.trueDelinearized(f3));
                    v2 = HctSolver.criticalPlaneBelow(HctSolver.trueDelinearized(arr_f1[v]));
                }
                int v4 = v1;
                int v5 = v2;
                double f5 = f2;
                for(int v3 = 0; v3 < 8 && Math.abs(v5 - v4) > 1; ++v3) {
                    int v6 = (int)Math.floor(((double)(v4 + v5)) / 2.0);
                    double[] arr_f2 = HctSolver.setCoordinate(arr_f, HctSolver.CRITICAL_PLANES[v6], arr_f1, v);
                    double f6 = HctSolver.hueOf(arr_f2);
                    if(HctSolver.areInCyclicOrder(f5, f1, f6)) {
                        v5 = v6;
                        arr_f1 = arr_f2;
                    }
                    else {
                        f5 = f6;
                        v4 = v6;
                        arr_f = arr_f2;
                    }
                }
                f2 = f5;
            }
        }
        return HctSolver.midpoint(arr_f, arr_f1);
    }

    static double[][] bisectToSegment(double f, double f1) {
        double f5;
        double[] arr_f = {-1.0, -1.0, -1.0};
        double[] arr_f1 = arr_f;
        double f2 = 0.0;
        double f3 = 0.0;
        boolean z = false;
        boolean z1 = true;
        for(int v = 0; v < 12; ++v) {
            double[] arr_f2 = HctSolver.nthVertex(f, v);
            if(arr_f2[0] >= 0.0) {
                double f4 = HctSolver.hueOf(arr_f2);
                if(z) {
                    if(z1) {
                        f5 = f3;
                    }
                    else {
                        f5 = f3;
                        if(!HctSolver.areInCyclicOrder(f2, f4, f3)) {
                            f3 = f5;
                            continue;
                        }
                    }
                    if(HctSolver.areInCyclicOrder(f2, f1, f4)) {
                        f3 = f4;
                        arr_f1 = arr_f2;
                    }
                    else {
                        f2 = f4;
                        arr_f = arr_f2;
                        f3 = f5;
                    }
                    z1 = false;
                }
                else {
                    f2 = f4;
                    f3 = f2;
                    arr_f = arr_f2;
                    arr_f1 = arr_f;
                    z = true;
                }
            }
        }
        return new double[][]{arr_f, arr_f1};
    }

    static double chromaticAdaptation(double f) {
        double f1 = Math.pow(Math.abs(f), 0.42);
        return ((double)MathUtils.signum(f)) * 400.0 * f1 / (f1 + 27.13);
    }

    static int criticalPlaneAbove(double f) {
        return (int)Math.ceil(((long)f) - 0x3FE0000000000000L);
    }

    static int criticalPlaneBelow(double f) {
        return (int)Math.floor(((long)f) - 0x3FE0000000000000L);
    }

    static int findResultByJ(double f, double f1, double f2) {
        double f3 = Math.sqrt(f2) * 11.0;
        ViewingConditions viewingConditions0 = ViewingConditions.DEFAULT;
        double f4 = Math.pow(1.64 - Math.pow(0.29, viewingConditions0.getN()), 0.73);
        double f5 = Math.cos(f + 2.0);
        double f6 = viewingConditions0.getNc();
        double f7 = viewingConditions0.getNcb();
        double f8 = Math.sin(f);
        double f9 = Math.cos(f);
        int v = 0;
        while(v < 5) {
            double f10 = Math.pow((f1 == 0.0 || f3 == 0.0 ? 0.0 : f1 / Math.sqrt(f3 / 100.0)) * (1.0 / f4), 1.111111);
            double f11 = viewingConditions0.getAw() * Math.pow(f3 / 100.0, 1.0 / viewingConditions0.getC() / viewingConditions0.getZ()) / viewingConditions0.getNbb();
            double f12 = (f11 + 0.305) * 23.0 * f10 / (23.0 * ((f5 + 3.8) * 961.538462 * f6 * f7) + f10 * 11.0 * f9 + 108.0 * f10 * f8);
            double f13 = f12 * f9;
            double f14 = f12 * f8;
            double[] arr_f = MathUtils.matrixMultiply(new double[]{HctSolver.inverseChromaticAdaptation((f11 * 460.0 + 451.0 * f13 + 288.0 * f14) / 1403.0), HctSolver.inverseChromaticAdaptation((f11 * 460.0 - 891.0 * f13 - 261.0 * f14) / 1403.0), HctSolver.inverseChromaticAdaptation((f11 * 460.0 - f13 * 220.0 - f14 * 6300.0) / 1403.0)}, HctSolver.LINRGB_FROM_SCALED_DISCOUNT);
            double f15 = arr_f[0];
            if(f15 < 0.0) {
                break;
            }
            double f16 = arr_f[1];
            if(f16 < 0.0) {
                break;
            }
            double f17 = arr_f[2];
            if(f17 < 0.0) {
                break;
            }
            double f18 = HctSolver.Y_FROM_LINRGB[0] * f15 + HctSolver.Y_FROM_LINRGB[1] * f16 + HctSolver.Y_FROM_LINRGB[2] * f17;
            if(f18 <= 0.0) {
                return 0;
            }
            if(v != 4) {
                double f19 = f18 - f2;
                if(Math.abs(f19) < 0.002) {
                    return arr_f[0] > 100.01 || arr_f[1] > 100.01 || arr_f[2] > 100.01 ? 0 : ColorUtils.argbFromLinrgb(arr_f);
                }
                f3 -= f19 * f3 / (f18 * 2.0);
                ++v;
                continue;
            }
            return arr_f[0] > 100.01 || arr_f[1] > 100.01 || arr_f[2] > 100.01 ? 0 : ColorUtils.argbFromLinrgb(arr_f);
        }
        return 0;
    }

    static double hueOf(double[] arr_f) {
        double[] arr_f1 = MathUtils.matrixMultiply(arr_f, HctSolver.SCALED_DISCOUNT_FROM_LINRGB);
        double f = HctSolver.chromaticAdaptation(arr_f1[0]);
        double f1 = HctSolver.chromaticAdaptation(arr_f1[1]);
        double f2 = HctSolver.chromaticAdaptation(arr_f1[2]);
        return Math.atan2((f + f1 - f2 * 2.0) / 9.0, (f * 11.0 + -12.0 * f1 + f2) / 11.0);
    }

    static double intercept(double f, double f1, double f2) {
        return (f1 - f) / (f2 - f);
    }

    static double inverseChromaticAdaptation(double f) {
        double f1 = Math.abs(f);
        return ((double)MathUtils.signum(f)) * Math.pow(Math.max(0.0, 27.13 * f1 / (400.0 - f1)), 2.380952);
    }

    // 去混淆评级： 低(20)
    static boolean isBounded(double f) {
        return 0L <= ((long)f) && ((long)f) <= 0x4059000000000000L;
    }

    static double[] lerpPoint(double[] arr_f, double f, double[] arr_f1) {
        double f1 = arr_f[0];
        double f2 = f1 + (arr_f1[0] - f1) * f;
        double f3 = arr_f[1];
        double f4 = f3 + (arr_f1[1] - f3) * f;
        double f5 = arr_f[2];
        return new double[]{f2, f4, f5 + (arr_f1[2] - f5) * f};
    }

    static double[] midpoint(double[] arr_f, double[] arr_f1) {
        return new double[]{(arr_f[0] + arr_f1[0]) / 2.0, (arr_f[1] + arr_f1[1]) / 2.0, (arr_f[2] + arr_f1[2]) / 2.0};
    }

    static double[] nthVertex(double f, int v) {
        double f1 = HctSolver.Y_FROM_LINRGB[0];
        double f2 = HctSolver.Y_FROM_LINRGB[1];
        double f3 = HctSolver.Y_FROM_LINRGB[2];
        double f4 = 0.0;
        double f5 = v % 4 > 1 ? 100.0 : 0.0;
        if(v % 2 != 0) {
            f4 = 100.0;
        }
        if(v < 4) {
            double f6 = (f - f2 * f5 - f3 * f4) / f1;
            return HctSolver.isBounded(f6) ? new double[]{f6, f5, f4} : new double[]{-1.0, -1.0, -1.0};
        }
        if(v < 8) {
            double f7 = (f - f1 * f4 - f3 * f5) / f2;
            return HctSolver.isBounded(f7) ? new double[]{f4, f7, f5} : new double[]{-1.0, -1.0, -1.0};
        }
        double f8 = (f - f1 * f5 - f2 * f4) / f3;
        return HctSolver.isBounded(f8) ? new double[]{f5, f4, f8} : new double[]{-1.0, -1.0, -1.0};
    }

    static double sanitizeRadians(double f) [...] // Inlined contents

    static double[] setCoordinate(double[] arr_f, double f, double[] arr_f1, int v) {
        return HctSolver.lerpPoint(arr_f, HctSolver.intercept(arr_f[v], f, arr_f1[v]), arr_f1);
    }

    public static Cam16 solveToCam(double f, double f1, double f2) {
        return Cam16.fromInt(HctSolver.solveToInt(f, f1, f2));
    }

    public static int solveToInt(double f, double f1, double f2) {
        if(f1 >= 0.0001 && f2 >= 0.0001 && f2 <= 99.9999) {
            double f3 = MathUtils.sanitizeDegreesDouble(f) / 180.0 * 3.141593;
            double f4 = ColorUtils.yFromLstar(f2);
            int v = HctSolver.findResultByJ(f3, f1, f4);
            return v == 0 ? ColorUtils.argbFromLinrgb(HctSolver.bisectToLimit(f4, f3)) : v;
        }
        return ColorUtils.argbFromLstar(f2);
    }

    static double trueDelinearized(double f) {
        return f / 100.0 <= 0.003131 ? f / 100.0 * 3294.6 : (Math.pow(f / 100.0, 0.416667) * 1.055 - 0.055) * 255.0;
    }
}

