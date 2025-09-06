package androidx.dynamicanimation.animation;

public final class SpringForce implements Force {
    public static final float DAMPING_RATIO_HIGH_BOUNCY = 0.2f;
    public static final float DAMPING_RATIO_LOW_BOUNCY = 0.75f;
    public static final float DAMPING_RATIO_MEDIUM_BOUNCY = 0.5f;
    public static final float DAMPING_RATIO_NO_BOUNCY = 1.0f;
    public static final float STIFFNESS_HIGH = 10000.0f;
    public static final float STIFFNESS_LOW = 200.0f;
    public static final float STIFFNESS_MEDIUM = 1500.0f;
    public static final float STIFFNESS_VERY_LOW = 50.0f;
    private static final double UNSET = 1.797693E+308;
    private static final double VELOCITY_THRESHOLD_MULTIPLIER = 62.5;
    private double mDampedFreq;
    double mDampingRatio;
    private double mFinalPosition;
    private double mGammaMinus;
    private double mGammaPlus;
    private boolean mInitialized;
    private final MassState mMassState;
    double mNaturalFreq;
    private double mValueThreshold;
    private double mVelocityThreshold;

    public SpringForce() {
        this.mNaturalFreq = 38.729833;
        this.mDampingRatio = 0.5;
        this.mInitialized = false;
        this.mFinalPosition = 1.797693E+308;
        this.mMassState = new MassState();
    }

    public SpringForce(float f) {
        this.mNaturalFreq = 38.729833;
        this.mDampingRatio = 0.5;
        this.mInitialized = false;
        this.mFinalPosition = 1.797693E+308;
        this.mMassState = new MassState();
        this.mFinalPosition = (double)f;
    }

    @Override  // androidx.dynamicanimation.animation.Force
    public float getAcceleration(float f, float f1) {
        return (float)(-(this.mNaturalFreq * this.mNaturalFreq) * ((double)(f - this.getFinalPosition())) - this.mNaturalFreq * 2.0 * this.mDampingRatio * ((double)f1));
    }

    public float getDampingRatio() {
        return (float)this.mDampingRatio;
    }

    public float getFinalPosition() {
        return (float)this.mFinalPosition;
    }

    public float getStiffness() {
        return (float)(this.mNaturalFreq * this.mNaturalFreq);
    }

    private void init() {
        if(this.mInitialized) {
            return;
        }
        if(this.mFinalPosition == 1.797693E+308) {
            throw new IllegalStateException("Error: Final position of the spring must be set before the animation starts");
        }
        double f = this.mDampingRatio;
        if(f > 1.0) {
            this.mGammaPlus = -f * this.mNaturalFreq + this.mNaturalFreq * Math.sqrt(f * f - 1.0);
            this.mGammaMinus = -this.mDampingRatio * this.mNaturalFreq - this.mNaturalFreq * Math.sqrt(this.mDampingRatio * this.mDampingRatio - 1.0);
        }
        else if(f >= 0.0 && f < 1.0) {
            this.mDampedFreq = this.mNaturalFreq * Math.sqrt(1.0 - f * f);
        }
        this.mInitialized = true;
    }

    @Override  // androidx.dynamicanimation.animation.Force
    public boolean isAtEquilibrium(float f, float f1) {
        return ((double)Math.abs(f1)) < this.mVelocityThreshold && ((double)Math.abs(f - this.getFinalPosition())) < this.mValueThreshold;
    }

    public SpringForce setDampingRatio(float f) {
        if(f < 0.0f) {
            throw new IllegalArgumentException("Damping ratio must be non-negative");
        }
        this.mDampingRatio = (double)f;
        this.mInitialized = false;
        return this;
    }

    public SpringForce setFinalPosition(float f) {
        this.mFinalPosition = (double)f;
        return this;
    }

    public SpringForce setStiffness(float f) {
        if(f <= 0.0f) {
            throw new IllegalArgumentException("Spring stiffness constant must be positive.");
        }
        this.mNaturalFreq = Math.sqrt(f);
        this.mInitialized = false;
        return this;
    }

    void setValueThreshold(double f) {
        double f1 = Math.abs(f);
        this.mValueThreshold = f1;
        this.mVelocityThreshold = f1 * 62.5;
    }

    MassState updateValues(double f, double f1, long v) {
        double f9;
        double f8;
        this.init();
        double f2 = f - this.mFinalPosition;
        double f3 = this.mDampingRatio;
        if(f3 > 1.0) {
            double f4 = this.mGammaMinus;
            double f5 = this.mGammaPlus;
            double f6 = f2 - (f4 * f2 - f1) / (f4 - f5);
            double f7 = (f2 * f4 - f1) / (f4 - f5);
            f8 = Math.pow(2.718282, f4 * (((double)v) / 1000.0)) * f6 + Math.pow(2.718282, this.mGammaPlus * (((double)v) / 1000.0)) * f7;
            f9 = f6 * this.mGammaMinus * Math.pow(2.718282, this.mGammaMinus * (((double)v) / 1000.0)) + f7 * this.mGammaPlus * Math.pow(2.718282, this.mGammaPlus * (((double)v) / 1000.0));
        }
        else if(f3 == 1.0) {
            double f10 = f1 + this.mNaturalFreq * f2;
            double f11 = f2 + f10 * (((double)v) / 1000.0);
            f8 = Math.pow(2.718282, -this.mNaturalFreq * (((double)v) / 1000.0)) * f11;
            f9 = f10 * Math.pow(2.718282, -this.mNaturalFreq * (((double)v) / 1000.0)) + f11 * Math.pow(2.718282, -this.mNaturalFreq * (((double)v) / 1000.0)) * -this.mNaturalFreq;
        }
        else {
            double f12 = this.mNaturalFreq;
            double f13 = 1.0 / this.mDampedFreq * (f3 * f12 * f2 + f1);
            f8 = Math.pow(2.718282, -f3 * f12 * (((double)v) / 1000.0)) * (Math.cos(this.mDampedFreq * (((double)v) / 1000.0)) * f2 + Math.sin(this.mDampedFreq * (((double)v) / 1000.0)) * f13);
            f9 = -this.mNaturalFreq * f8 * this.mDampingRatio + Math.pow(2.718282, -this.mDampingRatio * this.mNaturalFreq * (((double)v) / 1000.0)) * (-this.mDampedFreq * f2 * Math.sin(this.mDampedFreq * (((double)v) / 1000.0)) + f13 * this.mDampedFreq * Math.cos(this.mDampedFreq * (((double)v) / 1000.0)));
        }
        this.mMassState.mValue = (float)(f8 + this.mFinalPosition);
        this.mMassState.mVelocity = (float)f9;
        return this.mMassState;
    }
}

