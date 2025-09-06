package androidx.constraintlayout.core.motion.utils;

import java.util.Arrays;

public class ArcCurveFit extends CurveFit {
    static class Arc {
        private static final double EPSILON = 0.001;
        private static final String TAG = "Arc";
        double mArcDistance;
        double mArcVelocity;
        double mEllipseA;
        double mEllipseB;
        double mEllipseCenterX;
        double mEllipseCenterY;
        boolean mLinear;
        double[] mLut;
        double mOneOverDeltaTime;
        double mTime1;
        double mTime2;
        double mTmpCosAngle;
        double mTmpSinAngle;
        boolean mVertical;
        double mX1;
        double mX2;
        double mY1;
        double mY2;
        private static double[] sOurPercent;

        static {
            Arc.sOurPercent = new double[91];
        }

        Arc(int v, double f, double f1, double f2, double f3, double f4, double f5) {
            boolean z = false;
            this.mLinear = false;
            double f6 = f4 - f2;
            double f7 = f5 - f3;
            int v1 = 1;
            switch(v) {
                case 1: {
                    this.mVertical = true;
                    break;
                }
                case 4: {
                    if(f7 > 0.0) {
                        z = true;
                    }
                    this.mVertical = z;
                    break;
                }
                case 5: {
                    if(f7 < 0.0) {
                        z = true;
                    }
                    this.mVertical = z;
                    break;
                }
                default: {
                    this.mVertical = false;
                }
            }
            this.mTime1 = f;
            this.mTime2 = f1;
            this.mOneOverDeltaTime = 1.0 / (f1 - f);
            if(3 == v) {
                this.mLinear = true;
            }
            if(!this.mLinear && Math.abs(f6) >= 0.001 && Math.abs(f7) >= 0.001) {
                this.mLut = new double[101];
                boolean z1 = this.mVertical;
                this.mEllipseA = f6 * ((double)(z1 ? -1 : 1));
                if(!z1) {
                    v1 = -1;
                }
                this.mEllipseB = f7 * ((double)v1);
                this.mEllipseCenterX = z1 ? f4 : f2;
                this.mEllipseCenterY = z1 ? f3 : f5;
                this.buildTable(f2, f3, f4, f5);
                this.mArcVelocity = this.mArcDistance * this.mOneOverDeltaTime;
                return;
            }
            this.mLinear = true;
            this.mX1 = f2;
            this.mX2 = f4;
            this.mY1 = f3;
            this.mY2 = f5;
            double f8 = Math.hypot(f7, f6);
            this.mArcDistance = f8;
            this.mArcVelocity = f8 * this.mOneOverDeltaTime;
            double f9 = this.mTime2;
            double f10 = this.mTime1;
            this.mEllipseCenterX = f6 / (f9 - f10);
            this.mEllipseCenterY = f7 / (f9 - f10);
        }

        private void buildTable(double f, double f1, double f2, double f3) {
            int v = 0;
            double f4 = 0.0;
            double f5 = 0.0;
            for(double f6 = 0.0; true; f6 = f9) {
                double[] arr_f = Arc.sOurPercent;
                if(v >= arr_f.length) {
                    break;
                }
                double f7 = Math.toRadians(((double)v) * 90.0 / ((double)(arr_f.length - 1)));
                double f8 = Math.sin(f7) * (f2 - f);
                double f9 = Math.cos(f7) * (f1 - f3);
                if(v > 0) {
                    f4 += Math.hypot(f8 - f5, f9 - f6);
                    Arc.sOurPercent[v] = f4;
                }
                ++v;
                f5 = f8;
            }
            this.mArcDistance = f4;
            for(int v1 = 0; true; ++v1) {
                double[] arr_f1 = Arc.sOurPercent;
                if(v1 >= arr_f1.length) {
                    break;
                }
                arr_f1[v1] /= f4;
            }
            for(int v2 = 0; true; ++v2) {
                double[] arr_f2 = this.mLut;
                if(v2 >= arr_f2.length) {
                    break;
                }
                double f10 = ((double)v2) / ((double)(arr_f2.length - 1));
                int v3 = Arrays.binarySearch(Arc.sOurPercent, f10);
                if(v3 >= 0) {
                    this.mLut[v2] = ((double)v3) / ((double)(Arc.sOurPercent.length - 1));
                }
                else if(v3 == -1) {
                    this.mLut[v2] = 0.0;
                }
                else {
                    double[] arr_f3 = Arc.sOurPercent;
                    double f11 = arr_f3[-v3 - 2];
                    this.mLut[v2] = (((double)(-v3 - 2)) + (f10 - f11) / (arr_f3[-v3 - 1] - f11)) / ((double)(arr_f3.length - 1));
                }
            }
        }

        double getDX() {
            double f = this.mEllipseA * this.mTmpCosAngle;
            double f1 = this.mArcVelocity / Math.hypot(f, -this.mEllipseB * this.mTmpSinAngle);
            return this.mVertical ? -f * f1 : f * f1;
        }

        double getDY() {
            double f = -this.mEllipseB * this.mTmpSinAngle;
            double f1 = this.mArcVelocity / Math.hypot(this.mEllipseA * this.mTmpCosAngle, f);
            return this.mVertical ? -f * f1 : f * f1;
        }

        public double getLinearDX(double f) {
            return this.mEllipseCenterX;
        }

        public double getLinearDY(double f) {
            return this.mEllipseCenterY;
        }

        public double getLinearX(double f) {
            return this.mX1 + (f - this.mTime1) * this.mOneOverDeltaTime * (this.mX2 - this.mX1);
        }

        public double getLinearY(double f) {
            return this.mY1 + (f - this.mTime1) * this.mOneOverDeltaTime * (this.mY2 - this.mY1);
        }

        double getX() {
            return this.mEllipseCenterX + this.mEllipseA * this.mTmpSinAngle;
        }

        double getY() {
            return this.mEllipseCenterY + this.mEllipseB * this.mTmpCosAngle;
        }

        double lookup(double f) {
            if(f <= 0.0) {
                return 0.0;
            }
            if(f >= 1.0) {
                return 1.0;
            }
            double[] arr_f = this.mLut;
            double f1 = f * ((double)(arr_f.length - 1));
            double f2 = arr_f[((int)f1)];
            return f2 + (f1 - ((double)(((int)f1)))) * (arr_f[((int)f1) + 1] - f2);
        }

        void setPoint(double f) {
            double f1 = this.lookup((this.mVertical ? this.mTime2 - f : f - this.mTime1) * this.mOneOverDeltaTime);
            this.mTmpSinAngle = Math.sin(f1 * 1.570796);
            this.mTmpCosAngle = Math.cos(f1 * 1.570796);
        }
    }

    public static final int ARC_ABOVE = 5;
    public static final int ARC_BELOW = 4;
    public static final int ARC_START_FLIP = 3;
    public static final int ARC_START_HORIZONTAL = 2;
    public static final int ARC_START_LINEAR = 0;
    public static final int ARC_START_VERTICAL = 1;
    private static final int DOWN_ARC = 4;
    private static final int START_HORIZONTAL = 2;
    private static final int START_LINEAR = 3;
    private static final int START_VERTICAL = 1;
    private static final int UP_ARC = 5;
    Arc[] mArcs;
    private boolean mExtrapolate;
    private final double[] mTime;

    public ArcCurveFit(int[] arr_v, double[] arr_f, double[][] arr2_f) {
        this.mExtrapolate = true;
        this.mTime = arr_f;
        this.mArcs = new Arc[arr_f.length - 1];
        int v = 0;
        int v1 = 1;
        for(int v2 = 1; true; v2 = v4) {
            Arc[] arr_arcCurveFit$Arc = this.mArcs;
            if(v >= arr_arcCurveFit$Arc.length) {
                break;
            }
            int v3 = arr_v[v];
            int v4 = 3;
            if(v3 != 0) {
                if(v3 == 1) {
                    v1 = 1;
                    v4 = 1;
                }
                else {
                    switch(v3) {
                        case 2: {
                            v1 = 2;
                            v4 = 2;
                            break;
                        }
                        case 3: {
                            v1 = v1 == 1 ? 2 : 1;
                            v4 = v1;
                            break;
                        }
                        case 4: {
                            break;
                        }
                        default: {
                            v4 = v3 == 5 ? 5 : v2;
                        }
                    }
                }
            }
            double f = arr_f[v];
            double f1 = arr_f[v + 1];
            double[] arr_f1 = arr2_f[v];
            double f2 = arr_f1[0];
            double f3 = arr_f1[1];
            double[] arr_f2 = arr2_f[v + 1];
            arr_arcCurveFit$Arc[v] = new Arc(v4, f, f1, f2, f3, arr_f2[0], arr_f2[1]);
            ++v;
        }
    }

    @Override  // androidx.constraintlayout.core.motion.utils.CurveFit
    public double getPos(double f, int v) {
        double f2;
        if(this.mExtrapolate) {
            if(f < this.mArcs[0].mTime1) {
                double f1 = this.mArcs[0].mTime1;
                f2 = f - this.mArcs[0].mTime1;
                if(this.mArcs[0].mLinear) {
                    return v == 0 ? this.mArcs[0].getLinearX(f1) + f2 * this.mArcs[0].getLinearDX(f1) : this.mArcs[0].getLinearY(f1) + f2 * this.mArcs[0].getLinearDY(f1);
                }
                this.mArcs[0].setPoint(f1);
                return v == 0 ? this.mArcs[0].getX() + f2 * this.mArcs[0].getDX() : this.mArcs[0].getY() + f2 * this.mArcs[0].getDY();
            }
            if(f > this.mArcs[this.mArcs.length - 1].mTime2) {
                double f3 = this.mArcs[this.mArcs.length - 1].mTime2;
                f2 = f - f3;
                int v2 = this.mArcs.length - 1;
                return v == 0 ? this.mArcs[v2].getLinearX(f3) + f2 * this.mArcs[v2].getLinearDX(f3) : this.mArcs[v2].getLinearY(f3) + f2 * this.mArcs[v2].getLinearDY(f3);
            }
        }
        else if(f < this.mArcs[0].mTime1) {
            f = this.mArcs[0].mTime1;
        }
        else if(f > this.mArcs[this.mArcs.length - 1].mTime2) {
            f = this.mArcs[this.mArcs.length - 1].mTime2;
        }
        for(int v1 = 0; true; ++v1) {
            Arc[] arr_arcCurveFit$Arc = this.mArcs;
            if(v1 >= arr_arcCurveFit$Arc.length) {
                break;
            }
            if(f <= arr_arcCurveFit$Arc[v1].mTime2) {
                if(this.mArcs[v1].mLinear) {
                    return v == 0 ? this.mArcs[v1].getLinearX(f) : this.mArcs[v1].getLinearY(f);
                }
                this.mArcs[v1].setPoint(f);
                return v == 0 ? this.mArcs[v1].getX() : this.mArcs[v1].getY();
            }
        }
        return NaN;
    }

    @Override  // androidx.constraintlayout.core.motion.utils.CurveFit
    public void getPos(double f, double[] arr_f) {
        if(this.mExtrapolate) {
            if(f < this.mArcs[0].mTime1) {
                double f1 = this.mArcs[0].mTime1;
                double f2 = f - this.mArcs[0].mTime1;
                if(this.mArcs[0].mLinear) {
                    arr_f[0] = this.mArcs[0].getLinearX(f1) + this.mArcs[0].getLinearDX(f1) * f2;
                    arr_f[1] = this.mArcs[0].getLinearY(f1) + f2 * this.mArcs[0].getLinearDY(f1);
                    return;
                }
                this.mArcs[0].setPoint(f1);
                arr_f[0] = this.mArcs[0].getX() + this.mArcs[0].getDX() * f2;
                arr_f[1] = this.mArcs[0].getY() + f2 * this.mArcs[0].getDY();
                return;
            }
            if(f > this.mArcs[this.mArcs.length - 1].mTime2) {
                double f3 = this.mArcs[this.mArcs.length - 1].mTime2;
                double f4 = f - f3;
                int v = this.mArcs.length - 1;
                if(this.mArcs[v].mLinear) {
                    arr_f[0] = this.mArcs[v].getLinearX(f3) + this.mArcs[v].getLinearDX(f3) * f4;
                    arr_f[1] = this.mArcs[v].getLinearY(f3) + f4 * this.mArcs[v].getLinearDY(f3);
                    return;
                }
                this.mArcs[v].setPoint(f);
                arr_f[0] = this.mArcs[v].getX() + this.mArcs[v].getDX() * f4;
                arr_f[1] = this.mArcs[v].getY() + f4 * this.mArcs[v].getDY();
                return;
            }
        }
        else {
            if(f < this.mArcs[0].mTime1) {
                f = this.mArcs[0].mTime1;
            }
            if(f > this.mArcs[this.mArcs.length - 1].mTime2) {
                f = this.mArcs[this.mArcs.length - 1].mTime2;
            }
        }
        for(int v1 = 0; true; ++v1) {
            Arc[] arr_arcCurveFit$Arc = this.mArcs;
            if(v1 >= arr_arcCurveFit$Arc.length) {
                break;
            }
            if(f <= arr_arcCurveFit$Arc[v1].mTime2) {
                if(this.mArcs[v1].mLinear) {
                    arr_f[0] = this.mArcs[v1].getLinearX(f);
                    arr_f[1] = this.mArcs[v1].getLinearY(f);
                    return;
                }
                this.mArcs[v1].setPoint(f);
                arr_f[0] = this.mArcs[v1].getX();
                arr_f[1] = this.mArcs[v1].getY();
                return;
            }
        }
    }

    @Override  // androidx.constraintlayout.core.motion.utils.CurveFit
    public void getPos(double f, float[] arr_f) {
        if(this.mExtrapolate) {
            if(f < this.mArcs[0].mTime1) {
                double f1 = this.mArcs[0].mTime1;
                double f2 = f - this.mArcs[0].mTime1;
                if(this.mArcs[0].mLinear) {
                    arr_f[0] = (float)(this.mArcs[0].getLinearX(f1) + this.mArcs[0].getLinearDX(f1) * f2);
                    arr_f[1] = (float)(this.mArcs[0].getLinearY(f1) + f2 * this.mArcs[0].getLinearDY(f1));
                    return;
                }
                this.mArcs[0].setPoint(f1);
                arr_f[0] = (float)(this.mArcs[0].getX() + this.mArcs[0].getDX() * f2);
                arr_f[1] = (float)(this.mArcs[0].getY() + f2 * this.mArcs[0].getDY());
                return;
            }
            if(f > this.mArcs[this.mArcs.length - 1].mTime2) {
                double f3 = this.mArcs[this.mArcs.length - 1].mTime2;
                double f4 = f - f3;
                int v = this.mArcs.length - 1;
                if(this.mArcs[v].mLinear) {
                    arr_f[0] = (float)(this.mArcs[v].getLinearX(f3) + this.mArcs[v].getLinearDX(f3) * f4);
                    arr_f[1] = (float)(this.mArcs[v].getLinearY(f3) + f4 * this.mArcs[v].getLinearDY(f3));
                    return;
                }
                this.mArcs[v].setPoint(f);
                arr_f[0] = (float)this.mArcs[v].getX();
                arr_f[1] = (float)this.mArcs[v].getY();
                return;
            }
        }
        else if(f < this.mArcs[0].mTime1) {
            f = this.mArcs[0].mTime1;
        }
        else if(f > this.mArcs[this.mArcs.length - 1].mTime2) {
            f = this.mArcs[this.mArcs.length - 1].mTime2;
        }
        for(int v1 = 0; true; ++v1) {
            Arc[] arr_arcCurveFit$Arc = this.mArcs;
            if(v1 >= arr_arcCurveFit$Arc.length) {
                break;
            }
            if(f <= arr_arcCurveFit$Arc[v1].mTime2) {
                if(this.mArcs[v1].mLinear) {
                    arr_f[0] = (float)this.mArcs[v1].getLinearX(f);
                    arr_f[1] = (float)this.mArcs[v1].getLinearY(f);
                    return;
                }
                this.mArcs[v1].setPoint(f);
                arr_f[0] = (float)this.mArcs[v1].getX();
                arr_f[1] = (float)this.mArcs[v1].getY();
                return;
            }
        }
    }

    @Override  // androidx.constraintlayout.core.motion.utils.CurveFit
    public double getSlope(double f, int v) {
        if(f < this.mArcs[0].mTime1) {
            f = this.mArcs[0].mTime1;
        }
        if(f > this.mArcs[this.mArcs.length - 1].mTime2) {
            f = this.mArcs[this.mArcs.length - 1].mTime2;
        }
        for(int v1 = 0; true; ++v1) {
            Arc[] arr_arcCurveFit$Arc = this.mArcs;
            if(v1 >= arr_arcCurveFit$Arc.length) {
                break;
            }
            if(f <= arr_arcCurveFit$Arc[v1].mTime2) {
                if(this.mArcs[v1].mLinear) {
                    return v == 0 ? this.mArcs[v1].getLinearDX(f) : this.mArcs[v1].getLinearDY(f);
                }
                this.mArcs[v1].setPoint(f);
                return v == 0 ? this.mArcs[v1].getDX() : this.mArcs[v1].getDY();
            }
        }
        return NaN;
    }

    @Override  // androidx.constraintlayout.core.motion.utils.CurveFit
    public void getSlope(double f, double[] arr_f) {
        if(f < this.mArcs[0].mTime1) {
            f = this.mArcs[0].mTime1;
        }
        else if(f > this.mArcs[this.mArcs.length - 1].mTime2) {
            f = this.mArcs[this.mArcs.length - 1].mTime2;
        }
        for(int v = 0; true; ++v) {
            Arc[] arr_arcCurveFit$Arc = this.mArcs;
            if(v >= arr_arcCurveFit$Arc.length) {
                break;
            }
            if(f <= arr_arcCurveFit$Arc[v].mTime2) {
                if(this.mArcs[v].mLinear) {
                    arr_f[0] = this.mArcs[v].getLinearDX(f);
                    arr_f[1] = this.mArcs[v].getLinearDY(f);
                    return;
                }
                this.mArcs[v].setPoint(f);
                arr_f[0] = this.mArcs[v].getDX();
                arr_f[1] = this.mArcs[v].getDY();
                return;
            }
        }
    }

    @Override  // androidx.constraintlayout.core.motion.utils.CurveFit
    public double[] getTimePoints() {
        return this.mTime;
    }
}

