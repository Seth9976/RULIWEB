package androidx.constraintlayout.core.motion.utils;

public abstract class CurveFit {
    static class Constant extends CurveFit {
        double mTime;
        double[] mValue;

        Constant(double f, double[] arr_f) {
            this.mTime = f;
            this.mValue = arr_f;
        }

        @Override  // androidx.constraintlayout.core.motion.utils.CurveFit
        public double getPos(double f, int v) {
            return this.mValue[v];
        }

        @Override  // androidx.constraintlayout.core.motion.utils.CurveFit
        public void getPos(double f, double[] arr_f) {
            System.arraycopy(this.mValue, 0, arr_f, 0, this.mValue.length);
        }

        @Override  // androidx.constraintlayout.core.motion.utils.CurveFit
        public void getPos(double f, float[] arr_f) {
            for(int v = 0; true; ++v) {
                double[] arr_f1 = this.mValue;
                if(v >= arr_f1.length) {
                    break;
                }
                arr_f[v] = (float)arr_f1[v];
            }
        }

        @Override  // androidx.constraintlayout.core.motion.utils.CurveFit
        public double getSlope(double f, int v) {
            return 0.0;
        }

        @Override  // androidx.constraintlayout.core.motion.utils.CurveFit
        public void getSlope(double f, double[] arr_f) {
            for(int v = 0; v < this.mValue.length; ++v) {
                arr_f[v] = 0.0;
            }
        }

        @Override  // androidx.constraintlayout.core.motion.utils.CurveFit
        public double[] getTimePoints() {
            return new double[]{this.mTime};
        }
    }

    public static final int CONSTANT = 2;
    public static final int LINEAR = 1;
    public static final int SPLINE;

    public static CurveFit get(int v, double[] arr_f, double[][] arr2_f) {
        if(arr_f.length == 1) {
            v = 2;
        }
        switch(v) {
            case 0: {
                return new MonotonicCurveFit(arr_f, arr2_f);
            }
            case 2: {
                return new Constant(arr_f[0], arr2_f[0]);
            }
            default: {
                return new LinearCurveFit(arr_f, arr2_f);
            }
        }
    }

    public static CurveFit getArc(int[] arr_v, double[] arr_f, double[][] arr2_f) {
        return new ArcCurveFit(arr_v, arr_f, arr2_f);
    }

    public abstract double getPos(double arg1, int arg2);

    public abstract void getPos(double arg1, double[] arg2);

    public abstract void getPos(double arg1, float[] arg2);

    public abstract double getSlope(double arg1, int arg2);

    public abstract void getSlope(double arg1, double[] arg2);

    public abstract double[] getTimePoints();
}

