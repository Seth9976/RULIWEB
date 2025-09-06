package com.google.android.material.color.utilities;

public interface PointProvider {
    double distance(double[] arg1, double[] arg2);

    double[] fromInt(int arg1);

    int toInt(double[] arg1);
}

