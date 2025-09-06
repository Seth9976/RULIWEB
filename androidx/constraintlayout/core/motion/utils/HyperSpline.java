package androidx.constraintlayout.core.motion.utils;

public class HyperSpline {
    public static class Cubic {
        double mA;
        double mB;
        double mC;
        double mD;

        public Cubic(double f, double f1, double f2, double f3) {
            this.mA = f;
            this.mB = f1;
            this.mC = f2;
            this.mD = f3;
        }

        public double eval(double f) {
            return ((this.mD * f + this.mC) * f + this.mB) * f + this.mA;
        }

        public double vel(double f) {
            return (this.mD * 3.0 * f + this.mC * 2.0) * f + this.mB;
        }
    }

    double[][] mCtl;
    Cubic[][] mCurve;
    double[] mCurveLength;
    int mDimensionality;
    int mPoints;
    double mTotalLength;

    public HyperSpline() {
    }

    public HyperSpline(double[][] arr2_f) {
        this.setup(arr2_f);
    }

    public double approxLength(Cubic[] arr_hyperSpline$Cubic) {
        int v;
        double[] arr_f = new double[arr_hyperSpline$Cubic.length];
        double f = 0.0;
        double f2 = 0.0;
        for(double f1 = 0.0; true; f1 += 0.1) {
            v = 0;
            if(f1 >= 1.0) {
                break;
            }
            double f3 = 0.0;
            while(v < arr_hyperSpline$Cubic.length) {
                double f4 = arr_f[v];
                double f5 = arr_hyperSpline$Cubic[v].eval(f1);
                arr_f[v] = f5;
                f3 += (f4 - f5) * (f4 - f5);
                ++v;
            }
            if(f1 > 0.0) {
                f2 += Math.sqrt(f3);
            }
        }
        while(v < arr_hyperSpline$Cubic.length) {
            double f6 = arr_f[v];
            double f7 = arr_hyperSpline$Cubic[v].eval(1.0);
            arr_f[v] = f7;
            f += (f6 - f7) * (f6 - f7);
            ++v;
        }
        return f2 + Math.sqrt(f);
    }

    static Cubic[] calcNaturalCubic(int v, double[] arr_f) {
        double[] arr_f1 = new double[v];
        double[] arr_f2 = new double[v];
        double[] arr_f3 = new double[v];
        arr_f1[0] = 0.5;
        for(int v3 = 1; v3 < v - 1; ++v3) {
            arr_f1[v3] = 1.0 / (4.0 - arr_f1[v3 - 1]);
        }
        int v4 = v - 2;
        arr_f1[v - 1] = 1.0 / (2.0 - arr_f1[v4]);
        arr_f2[0] = (arr_f[1] - arr_f[0]) * 3.0 * arr_f1[0];
        for(int v2 = 1; v2 < v - 1; ++v2) {
            arr_f2[v2] = ((arr_f[v2 + 1] - arr_f[v2 - 1]) * 3.0 - arr_f2[v2 - 1]) * arr_f1[v2];
        }
        double f = ((arr_f[v - 1] - arr_f[v4]) * 3.0 - arr_f2[v4]) * arr_f1[v - 1];
        arr_f2[v - 1] = f;
        arr_f3[v - 1] = f;
        while(v4 >= 0) {
            arr_f3[v4] = arr_f2[v4] - arr_f1[v4] * arr_f3[v4 + 1];
            --v4;
        }
        Cubic[] arr_hyperSpline$Cubic = new Cubic[v - 1];
        for(int v1 = 0; v1 < v - 1; ++v1) {
            double f1 = arr_f[v1];
            double f2 = arr_f3[v1];
            double f3 = arr_f[v1 + 1];
            double f4 = arr_f3[v1 + 1];
            arr_hyperSpline$Cubic[v1] = new Cubic(((double)(((float)f1))), f2, (f3 - f1) * 3.0 - f2 * 2.0 - f4, (f1 - f3) * 2.0 + f2 + f4);
        }
        return arr_hyperSpline$Cubic;
    }

    public double getPos(double f, int v) {
        double[] arr_f;
        double f1 = f * this.mTotalLength;
        int v1;
        for(v1 = 0; true; ++v1) {
            arr_f = this.mCurveLength;
            if(v1 >= arr_f.length - 1) {
                break;
            }
            double f2 = arr_f[v1];
            if(f2 >= f1) {
                break;
            }
            f1 -= f2;
        }
        return this.mCurve[v][v1].eval(f1 / arr_f[v1]);
    }

    public void getPos(double f, double[] arr_f) {
        double f1 = f * this.mTotalLength;
        int v1;
        for(v1 = 0; true; ++v1) {
            double[] arr_f1 = this.mCurveLength;
            if(v1 >= arr_f1.length - 1) {
                break;
            }
            double f2 = arr_f1[v1];
            if(f2 >= f1) {
                break;
            }
            f1 -= f2;
        }
        for(int v = 0; v < arr_f.length; ++v) {
            arr_f[v] = this.mCurve[v][v1].eval(f1 / this.mCurveLength[v1]);
        }
    }

    public void getPos(double f, float[] arr_f) {
        double f1 = f * this.mTotalLength;
        int v1;
        for(v1 = 0; true; ++v1) {
            double[] arr_f1 = this.mCurveLength;
            if(v1 >= arr_f1.length - 1) {
                break;
            }
            double f2 = arr_f1[v1];
            if(f2 >= f1) {
                break;
            }
            f1 -= f2;
        }
        for(int v = 0; v < arr_f.length; ++v) {
            arr_f[v] = (float)this.mCurve[v][v1].eval(f1 / this.mCurveLength[v1]);
        }
    }

    public void getVelocity(double f, double[] arr_f) {
        double f1 = f * this.mTotalLength;
        int v1;
        for(v1 = 0; true; ++v1) {
            double[] arr_f1 = this.mCurveLength;
            if(v1 >= arr_f1.length - 1) {
                break;
            }
            double f2 = arr_f1[v1];
            if(f2 >= f1) {
                break;
            }
            f1 -= f2;
        }
        for(int v = 0; v < arr_f.length; ++v) {
            arr_f[v] = this.mCurve[v][v1].vel(f1 / this.mCurveLength[v1]);
        }
    }

    public void setup(double[][] arr2_f) {
        int v3;
        this.mDimensionality = arr2_f[0].length;
        this.mPoints = arr2_f.length;
        this.mCtl = new double[arr2_f[0].length][arr2_f.length];
        this.mCurve = new Cubic[this.mDimensionality][];
        for(int v = 0; v < this.mDimensionality; ++v) {
            for(int v1 = 0; v1 < this.mPoints; ++v1) {
                this.mCtl[v][v1] = arr2_f[v1][v];
            }
        }
        for(int v2 = 0; true; ++v2) {
            v3 = this.mDimensionality;
            if(v2 >= v3) {
                break;
            }
            Cubic[][] arr2_hyperSpline$Cubic = this.mCurve;
            double[] arr_f = this.mCtl[v2];
            arr2_hyperSpline$Cubic[v2] = HyperSpline.calcNaturalCubic(arr_f.length, arr_f);
        }
        this.mCurveLength = new double[this.mPoints - 1];
        this.mTotalLength = 0.0;
        Cubic[] arr_hyperSpline$Cubic = new Cubic[v3];
        for(int v4 = 0; v4 < this.mCurveLength.length; ++v4) {
            for(int v5 = 0; v5 < this.mDimensionality; ++v5) {
                arr_hyperSpline$Cubic[v5] = this.mCurve[v5][v4];
            }
            double f = this.mTotalLength;
            double[] arr_f1 = this.mCurveLength;
            double f1 = this.approxLength(arr_hyperSpline$Cubic);
            arr_f1[v4] = f1;
            this.mTotalLength = f + f1;
        }
    }
}

