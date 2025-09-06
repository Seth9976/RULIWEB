package androidx.constraintlayout.core.motion.utils;

import java.util.Arrays;

public class Oscillator {
    public static final int BOUNCE = 6;
    public static final int COS_WAVE = 5;
    public static final int CUSTOM = 7;
    public static final int REVERSE_SAW_WAVE = 4;
    public static final int SAW_WAVE = 3;
    public static final int SIN_WAVE = 0;
    public static final int SQUARE_WAVE = 1;
    public static String TAG = "Oscillator";
    public static final int TRIANGLE_WAVE = 2;
    double[] mArea;
    MonotonicCurveFit mCustomCurve;
    String mCustomType;
    private boolean mNormalized;
    double mPI2;
    float[] mPeriod;
    double[] mPosition;
    int mType;

    static {
    }

    public Oscillator() {
        this.mPeriod = new float[0];
        this.mPosition = new double[0];
        this.mPI2 = 6.283185;
        this.mNormalized = false;
    }

    public void addPoint(double f, float f1) {
        int v = this.mPeriod.length + 1;
        int v1 = Arrays.binarySearch(this.mPosition, f);
        if(v1 < 0) {
            v1 = -v1 - 1;
        }
        this.mPosition = Arrays.copyOf(this.mPosition, v);
        this.mPeriod = Arrays.copyOf(this.mPeriod, v);
        this.mArea = new double[v];
        System.arraycopy(this.mPosition, v1, this.mPosition, v1 + 1, v - v1 - 1);
        this.mPosition[v1] = f;
        this.mPeriod[v1] = f1;
        this.mNormalized = false;
    }

    double getDP(double f) {
        if(f <= 0.0) {
            return 0.0;
        }
        if(f >= 1.0) {
            return 1.0;
        }
        int v = Arrays.binarySearch(this.mPosition, f);
        if(v < 0) {
            v = -v - 1;
        }
        float[] arr_f = this.mPeriod;
        float f1 = arr_f[v];
        float f2 = arr_f[v - 1];
        double[] arr_f1 = this.mPosition;
        double f3 = arr_f1[v];
        double f4 = arr_f1[v - 1];
        double f5 = ((double)(f1 - f2)) / (f3 - f4);
        return f * f5 + (((double)f2) - f5 * f4);
    }

    double getP(double f) {
        if(f <= 0.0) {
            return 0.0;
        }
        if(f >= 1.0) {
            return 1.0;
        }
        int v = Arrays.binarySearch(this.mPosition, f);
        if(v < 0) {
            v = -v - 1;
        }
        float[] arr_f = this.mPeriod;
        float f1 = arr_f[v];
        float f2 = arr_f[v - 1];
        double[] arr_f1 = this.mPosition;
        double f3 = arr_f1[v];
        double f4 = arr_f1[v - 1];
        double f5 = ((double)(f1 - f2)) / (f3 - f4);
        return this.mArea[v - 1] + (((double)f2) - f5 * f4) * (f - f4) + f5 * (f * f - f4 * f4) / 2.0;
    }

    public double getSlope(double f, double f1, double f2) {
        double f3 = f1 + this.getP(f);
        double f4 = this.getDP(f) + f2;
        switch(this.mType) {
            case 1: {
                return 0.0;
            }
            case 2: {
                return f4 * 4.0 * Math.signum((f3 * 4.0 + 3.0) % 4.0 - 2.0);
            }
            case 3: {
                return f4 * 2.0;
            }
            case 4: {
                return -f4 * 2.0;
            }
            case 5: {
                return -this.mPI2 * f4 * Math.sin(this.mPI2 * f3);
            }
            case 6: {
                return f4 * 4.0 * ((f3 * 4.0 + 2.0) % 4.0 - 2.0);
            }
            case 7: {
                return this.mCustomCurve.getSlope(f3 % 1.0, 0);
            }
            default: {
                return f4 * this.mPI2 * Math.cos(this.mPI2 * f3);
            }
        }
    }

    public double getValue(double f, double f1) {
        double f2 = this.getP(f) + f1;
        switch(this.mType) {
            case 1: {
                return Math.signum(0.5 - f2 % 1.0);
            }
            case 2: {
                return 1.0 - Math.abs((f2 * 4.0 + 1.0) % 4.0 - 2.0);
            }
            case 3: {
                return (f2 * 2.0 + 1.0) % 2.0 - 1.0;
            }
            case 4: {
                return 1.0 - (f2 * 2.0 + 1.0) % 2.0;
            }
            case 5: {
                return Math.cos(this.mPI2 * (f1 + f2));
            }
            case 6: {
                double f3 = Math.abs(f2 * 4.0 % 4.0 - 2.0);
                return 1.0 - (1.0 - f3) * (1.0 - f3);
            }
            case 7: {
                return this.mCustomCurve.getPos(f2 % 1.0, 0);
            }
            default: {
                return Math.sin(this.mPI2 * f2);
            }
        }
    }

    public void normalize() {
        double f = 0.0;
        for(int v = 0; true; ++v) {
            float[] arr_f = this.mPeriod;
            if(v >= arr_f.length) {
                break;
            }
            f += (double)arr_f[v];
        }
        double f1 = 0.0;
        for(int v1 = 1; true; ++v1) {
            float[] arr_f1 = this.mPeriod;
            if(v1 >= arr_f1.length) {
                break;
            }
            f1 += (this.mPosition[v1] - this.mPosition[v1 - 1]) * ((double)((arr_f1[v1 - 1] + arr_f1[v1]) / 2.0f));
        }
        for(int v2 = 0; true; ++v2) {
            float[] arr_f2 = this.mPeriod;
            if(v2 >= arr_f2.length) {
                break;
            }
            arr_f2[v2] *= (float)(f / f1);
        }
        this.mArea[0] = 0.0;
        for(int v3 = 1; true; ++v3) {
            float[] arr_f3 = this.mPeriod;
            if(v3 >= arr_f3.length) {
                break;
            }
            this.mArea[v3] = this.mArea[v3 - 1] + (this.mPosition[v3] - this.mPosition[v3 - 1]) * ((double)((arr_f3[v3 - 1] + arr_f3[v3]) / 2.0f));
        }
        this.mNormalized = true;
    }

    public void setType(int v, String s) {
        this.mType = v;
        this.mCustomType = s;
        if(s != null) {
            this.mCustomCurve = MonotonicCurveFit.buildWave(s);
        }
    }

    @Override
    public String toString() {
        return "pos =" + Arrays.toString(this.mPosition) + " period=" + Arrays.toString(this.mPeriod);
    }
}

