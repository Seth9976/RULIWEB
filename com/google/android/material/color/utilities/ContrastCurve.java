package com.google.android.material.color.utilities;

public final class ContrastCurve {
    private final double high;
    private final double low;
    private final double medium;
    private final double normal;

    public ContrastCurve(double f, double f1, double f2, double f3) {
        this.low = f;
        this.normal = f1;
        this.medium = f2;
        this.high = f3;
    }

    public double getContrast(double f) {
        if(f <= -1.0) {
            return this.low;
        }
        if(f < 0.0) {
            return (1.0 - (f + 1.0) / 1.0) * this.low + (f + 1.0) / 1.0 * this.normal;
        }
        if(f < 0.5) {
            return (1.0 - (f - 0.0) / 0.5) * this.normal + (f - 0.0) / 0.5 * this.medium;
        }
        return f < 1.0 ? (1.0 - (f - 0.5) / 0.5) * this.medium + (f - 0.5) / 0.5 * this.high : this.high;
    }
}

