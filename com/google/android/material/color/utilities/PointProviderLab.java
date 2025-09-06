package com.google.android.material.color.utilities;

public final class PointProviderLab implements PointProvider {
    @Override  // com.google.android.material.color.utilities.PointProvider
    public double distance(double[] arr_f, double[] arr_f1) {
        double f = arr_f[0] - arr_f1[0];
        double f1 = arr_f[1] - arr_f1[1];
        double f2 = arr_f[2] - arr_f1[2];
        return f * f + f1 * f1 + f2 * f2;
    }

    @Override  // com.google.android.material.color.utilities.PointProvider
    public double[] fromInt(int v) {
        double[] arr_f = ColorUtils.labFromArgb(v);
        return new double[]{arr_f[0], arr_f[1], arr_f[2]};
    }

    @Override  // com.google.android.material.color.utilities.PointProvider
    public int toInt(double[] arr_f) {
        return ColorUtils.argbFromLab(arr_f[0], arr_f[1], arr_f[2]);
    }
}

