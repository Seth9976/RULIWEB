package androidx.constraintlayout.core.motion.utils;

import java.util.Arrays;

public class StepCurve extends Easing {
    private static final boolean DEBUG = false;
    MonotonicCurveFit mCurveFit;

    StepCurve(String s) {
        this.mStr = s;
        double[] arr_f = new double[this.mStr.length() / 2];
        int v = s.indexOf(40) + 1;
        int v1 = s.indexOf(44, v);
        int v2;
        for(v2 = 0; v1 != -1; ++v2) {
            arr_f[v2] = Double.parseDouble(s.substring(v, v1).trim());
            v = v1 + 1;
            v1 = s.indexOf(44, v);
        }
        arr_f[v2] = Double.parseDouble(s.substring(v, s.indexOf(41, v)).trim());
        this.mCurveFit = StepCurve.genSpline(Arrays.copyOf(arr_f, v2 + 1));
    }

    private static MonotonicCurveFit genSpline(String s) {
        String[] arr_s = s.split("\\s+");
        double[] arr_f = new double[arr_s.length];
        for(int v = 0; v < arr_s.length; ++v) {
            arr_f[v] = Double.parseDouble(arr_s[v]);
        }
        return StepCurve.genSpline(arr_f);
    }

    private static MonotonicCurveFit genSpline(double[] arr_f) {
        int v = arr_f.length * 3 - 2;
        int v1 = arr_f.length - 1;
        double[][] arr2_f = new double[v][1];
        double[] arr_f1 = new double[v];
        for(int v2 = 0; v2 < arr_f.length; ++v2) {
            double f = arr_f[v2];
            int v3 = v2 + v1;
            arr2_f[v3][0] = f;
            double f1 = ((double)v2) * (1.0 / ((double)v1));
            arr_f1[v3] = f1;
            if(v2 > 0) {
                int v4 = v1 * 2 + v2;
                arr2_f[v4][0] = f + 1.0;
                arr_f1[v4] = f1 + 1.0;
                arr2_f[v2 - 1][0] = f - 1.0 - 1.0 / ((double)v1);
                arr_f1[v2 - 1] = f1 - 1.0 - 1.0 / ((double)v1);
            }
        }
        MonotonicCurveFit monotonicCurveFit0 = new MonotonicCurveFit(arr_f1, arr2_f);
        System.out.println(" 0 " + monotonicCurveFit0.getPos(0.0, 0));
        System.out.println(" 1 " + monotonicCurveFit0.getPos(1.0, 0));
        return monotonicCurveFit0;
    }

    @Override  // androidx.constraintlayout.core.motion.utils.Easing
    public double get(double f) {
        return this.mCurveFit.getPos(f, 0);
    }

    @Override  // androidx.constraintlayout.core.motion.utils.Easing
    public double getDiff(double f) {
        return this.mCurveFit.getSlope(f, 0);
    }
}

