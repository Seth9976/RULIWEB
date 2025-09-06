package androidx.constraintlayout.core.motion.utils;

public class SpringStopEngine implements StopEngine {
    private static final double UNSET = 1.797693E+308;
    private int mBoundaryMode;
    double mDamping;
    private boolean mInitialized;
    private float mLastTime;
    private double mLastVelocity;
    private float mMass;
    private float mPos;
    private double mStiffness;
    private float mStopThreshold;
    private double mTargetPos;
    private float mV;

    public SpringStopEngine() {
        this.mDamping = 0.5;
        this.mInitialized = false;
        this.mBoundaryMode = 0;
    }

    private void compute(double f) {
        if(f > 0.0) {
            double f1 = this.mStiffness;
            double f2 = this.mDamping;
            int v = (int)(9.0 / (Math.sqrt(f1 / ((double)this.mMass)) * f * 4.0) + 1.0);
            double f3 = f / ((double)v);
            for(int v1 = 0; v1 < v; ++v1) {
                float f4 = this.mPos;
                double f5 = this.mTargetPos;
                float f6 = this.mV;
                float f7 = this.mMass;
                double f8 = ((double)f6) + (-f1 * (((double)f4) - f5) - ((double)f6) * f2) / ((double)f7) * f3 / 2.0;
                double f9 = (-(((double)f4) + f3 * f8 / 2.0 - f5) * f1 - f8 * f2) / ((double)f7) * f3;
                float f10 = f6 + ((float)f9);
                this.mV = f10;
                float f11 = f4 + ((float)((((double)f6) + f9 / 2.0) * f3));
                this.mPos = f11;
                int v2 = this.mBoundaryMode;
                if(v2 > 0) {
                    if(f11 < 0.0f && (v2 & 1) == 1) {
                        this.mPos = -f11;
                        this.mV = -f10;
                    }
                    float f12 = this.mPos;
                    if(f12 > 1.0f && (v2 & 2) == 2) {
                        this.mPos = 2.0f - f12;
                        this.mV = -this.mV;
                    }
                }
            }
        }
    }

    @Override  // androidx.constraintlayout.core.motion.utils.StopEngine
    public String debug(String s, float f) {
        return null;
    }

    public float getAcceleration() {
        return ((float)(-this.mStiffness * (((double)this.mPos) - this.mTargetPos) - this.mDamping * ((double)this.mV))) / this.mMass;
    }

    @Override  // androidx.constraintlayout.core.motion.utils.StopEngine
    public float getInterpolation(float f) {
        this.compute(((double)(f - this.mLastTime)));
        this.mLastTime = f;
        if(this.isStopped()) {
            this.mPos = (float)this.mTargetPos;
        }
        return this.mPos;
    }

    @Override  // androidx.constraintlayout.core.motion.utils.StopEngine
    public float getVelocity() {
        return 0.0f;
    }

    @Override  // androidx.constraintlayout.core.motion.utils.StopEngine
    public float getVelocity(float f) {
        return this.mV;
    }

    @Override  // androidx.constraintlayout.core.motion.utils.StopEngine
    public boolean isStopped() {
        double f = ((double)this.mPos) - this.mTargetPos;
        double f1 = (double)this.mV;
        return Math.sqrt((f1 * f1 * ((double)this.mMass) + this.mStiffness * f * f) / this.mStiffness) <= ((double)this.mStopThreshold);
    }

    void log(String s) {
        StackTraceElement stackTraceElement0 = new Throwable().getStackTrace()[1];
        System.out.println(".(" + stackTraceElement0.getFileName() + ":" + stackTraceElement0.getLineNumber() + ") " + "log" + "() " + s);
    }

    public void springConfig(float f, float f1, float f2, float f3, float f4, float f5, float f6, int v) {
        this.mTargetPos = (double)f1;
        this.mDamping = (double)f5;
        this.mInitialized = false;
        this.mPos = f;
        this.mLastVelocity = (double)f2;
        this.mStiffness = (double)f4;
        this.mMass = f3;
        this.mStopThreshold = f6;
        this.mBoundaryMode = v;
        this.mLastTime = 0.0f;
    }
}

