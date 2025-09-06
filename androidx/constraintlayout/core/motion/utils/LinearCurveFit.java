package androidx.constraintlayout.core.motion.utils;

public class LinearCurveFit extends CurveFit {
    private static final String TAG = "LinearCurveFit";
    private boolean mExtrapolate;
    double[] mSlopeTemp;
    private double[] mT;
    private double mTotalLength;
    private double[][] mY;

    public LinearCurveFit(double[] arr_f, double[][] arr2_f) {
        this.mTotalLength = NaN;
        this.mExtrapolate = true;
        int v = arr2_f[0].length;
        this.mSlopeTemp = new double[v];
        this.mT = arr_f;
        this.mY = arr2_f;
        if(v > 2) {
            for(int v1 = 0; v1 < arr_f.length; ++v1) {
                double f = arr2_f[v1][0];
            }
            this.mTotalLength = 0.0;
        }
    }

    private double getLength2D(double f) {
        if(Double.isNaN(this.mTotalLength)) {
            return 0.0;
        }
        double[] arr_f = this.mT;
        if(f <= arr_f[0]) {
            return 0.0;
        }
        int v = arr_f.length - 1;
        if(f >= arr_f[v]) {
            return this.mTotalLength;
        }
        double f1 = 0.0;
        double f2 = 0.0;
        int v1 = 0;
        for(double f3 = 0.0; v1 < v; f3 = f5) {
            double[] arr_f1 = this.mY[v1];
            double f4 = arr_f1[0];
            double f5 = arr_f1[1];
            if(v1 > 0) {
                f1 += Math.hypot(f4 - f2, f5 - f3);
            }
            double[] arr_f2 = this.mT;
            double f6 = arr_f2[v1];
            if(f == f6) {
                return f1;
            }
            double f7 = arr_f2[v1 + 1];
            if(f < f7) {
                double f8 = (f - f6) / (f7 - f6);
                double[][] arr2_f = this.mY;
                double[] arr_f3 = arr2_f[v1];
                double f9 = arr_f3[0];
                double[] arr_f4 = arr2_f[v1 + 1];
                return f1 + Math.hypot(f5 - (arr_f3[1] * (1.0 - f8) + arr_f4[1] * f8), f4 - (f9 * (1.0 - f8) + arr_f4[0] * f8));
            }
            ++v1;
            f2 = f4;
        }
        return 0.0;
    }

    @Override  // androidx.constraintlayout.core.motion.utils.CurveFit
    public double getPos(double f, int v) {
        double[] arr_f = this.mT;
        if(this.mExtrapolate) {
            double f1 = arr_f[0];
            if(f <= f1) {
                return this.mY[0][v] + (f - f1) * this.getSlope(f1, v);
            }
            int v2 = arr_f.length - 1;
            double f2 = arr_f[v2];
            if(f >= f2) {
                return this.mY[v2][v] + (f - f2) * this.getSlope(f2, v);
            }
        }
        else {
            if(f <= arr_f[0]) {
                return this.mY[0][v];
            }
            int v3 = arr_f.length - 1;
            if(f >= arr_f[v3]) {
                return this.mY[v3][v];
            }
        }
        for(int v1 = 0; v1 < arr_f.length - 1; ++v1) {
            double[] arr_f1 = this.mT;
            double f3 = arr_f1[v1];
            if(f == f3) {
                return this.mY[v1][v];
            }
            double f4 = arr_f1[v1 + 1];
            if(f < f4) {
                double f5 = (f - f3) / (f4 - f3);
                return this.mY[v1][v] * (1.0 - f5) + this.mY[v1 + 1][v] * f5;
            }
        }
        return 0.0;
    }

    @Override  // androidx.constraintlayout.core.motion.utils.CurveFit
    public void getPos(double f, double[] arr_f) {
        int v4;
        double[] arr_f1 = this.mT;
        int v = 0;
        int v1 = this.mY[0].length;
        if(this.mExtrapolate) {
            double f1 = arr_f1[0];
            if(f <= f1) {
                this.getSlope(f1, this.mSlopeTemp);
                for(int v2 = 0; v2 < v1; ++v2) {
                    arr_f[v2] = this.mY[0][v2] + (f - this.mT[0]) * this.mSlopeTemp[v2];
                }
                return;
            }
            int v3 = arr_f1.length - 1;
            double f2 = arr_f1[v3];
            if(f >= f2) {
                this.getSlope(f2, this.mSlopeTemp);
                while(v < v1) {
                    arr_f[v] = this.mY[v3][v] + (f - this.mT[v3]) * this.mSlopeTemp[v];
                    ++v;
                }
                return;
            }
        }
        else {
            if(f <= arr_f1[0]) {
                for(int v5 = 0; v5 < v1; ++v5) {
                    arr_f[v5] = this.mY[0][v5];
                }
                return;
            }
            int v6 = arr_f1.length - 1;
            if(f >= arr_f1[v6]) {
                while(v < v1) {
                    arr_f[v] = this.mY[v6][v];
                    ++v;
                }
                return;
            }
        }
        for(v4 = 0; v4 < arr_f1.length - 1; ++v4) {
            if(f == this.mT[v4]) {
                for(int v7 = 0; v7 < v1; ++v7) {
                    arr_f[v7] = this.mY[v4][v7];
                }
            }
            double[] arr_f2 = this.mT;
            double f3 = arr_f2[v4 + 1];
            if(f < f3) {
                double f4 = arr_f2[v4];
                double f5 = (f - f4) / (f3 - f4);
                while(v < v1) {
                    arr_f[v] = this.mY[v4][v] * (1.0 - f5) + this.mY[v4 + 1][v] * f5;
                    ++v;
                }
                return;
            }
        }
    }

    @Override  // androidx.constraintlayout.core.motion.utils.CurveFit
    public void getPos(double f, float[] arr_f) {
        int v4;
        double[] arr_f1 = this.mT;
        int v = 0;
        int v1 = this.mY[0].length;
        if(this.mExtrapolate) {
            double f1 = arr_f1[0];
            if(f <= f1) {
                this.getSlope(f1, this.mSlopeTemp);
                for(int v2 = 0; v2 < v1; ++v2) {
                    arr_f[v2] = (float)(this.mY[0][v2] + (f - this.mT[0]) * this.mSlopeTemp[v2]);
                }
                return;
            }
            int v3 = arr_f1.length - 1;
            double f2 = arr_f1[v3];
            if(f >= f2) {
                this.getSlope(f2, this.mSlopeTemp);
                while(v < v1) {
                    arr_f[v] = (float)(this.mY[v3][v] + (f - this.mT[v3]) * this.mSlopeTemp[v]);
                    ++v;
                }
                return;
            }
        }
        else {
            if(f <= arr_f1[0]) {
                for(int v5 = 0; v5 < v1; ++v5) {
                    arr_f[v5] = (float)this.mY[0][v5];
                }
                return;
            }
            int v6 = arr_f1.length - 1;
            if(f >= arr_f1[v6]) {
                while(v < v1) {
                    arr_f[v] = (float)this.mY[v6][v];
                    ++v;
                }
                return;
            }
        }
        for(v4 = 0; v4 < arr_f1.length - 1; ++v4) {
            if(f == this.mT[v4]) {
                for(int v7 = 0; v7 < v1; ++v7) {
                    arr_f[v7] = (float)this.mY[v4][v7];
                }
            }
            double[] arr_f2 = this.mT;
            double f3 = arr_f2[v4 + 1];
            if(f < f3) {
                double f4 = arr_f2[v4];
                double f5 = (f - f4) / (f3 - f4);
                while(v < v1) {
                    arr_f[v] = (float)(this.mY[v4][v] * (1.0 - f5) + this.mY[v4 + 1][v] * f5);
                    ++v;
                }
                return;
            }
        }
    }

    @Override  // androidx.constraintlayout.core.motion.utils.CurveFit
    public double getSlope(double f, int v) {
        double[] arr_f = this.mT;
        double f1 = arr_f[0];
        if(f >= f1) {
            f1 = arr_f[arr_f.length - 1];
            if(f >= f1) {
                f = f1;
            }
        }
        else {
            f = f1;
        }
        for(int v1 = 0; v1 < arr_f.length - 1; ++v1) {
            double[] arr_f1 = this.mT;
            double f2 = arr_f1[v1 + 1];
            if(f <= f2) {
                return (this.mY[v1 + 1][v] - this.mY[v1][v]) / (f2 - arr_f1[v1]);
            }
        }
        return 0.0;
    }

    @Override  // androidx.constraintlayout.core.motion.utils.CurveFit
    public void getSlope(double f, double[] arr_f) {
        double[] arr_f1 = this.mT;
        int v1 = this.mY[0].length;
        double f1 = arr_f1[0];
        if(f > f1) {
            f1 = arr_f1[arr_f1.length - 1];
            if(f >= f1) {
                f = f1;
            }
        }
        else {
            f = f1;
        }
        for(int v2 = 0; v2 < arr_f1.length - 1; ++v2) {
            double[] arr_f2 = this.mT;
            double f2 = arr_f2[v2 + 1];
            if(f <= f2) {
                double f3 = f2 - arr_f2[v2];
                for(int v = 0; v < v1; ++v) {
                    arr_f[v] = (this.mY[v2 + 1][v] - this.mY[v2][v]) / f3;
                }
                return;
            }
        }
    }

    @Override  // androidx.constraintlayout.core.motion.utils.CurveFit
    public double[] getTimePoints() {
        return this.mT;
    }
}

