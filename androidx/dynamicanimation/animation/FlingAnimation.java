package androidx.dynamicanimation.animation;

public final class FlingAnimation extends DynamicAnimation {
    static final class DragForce implements Force {
        private static final float DEFAULT_FRICTION = -4.2f;
        private static final float VELOCITY_THRESHOLD_MULTIPLIER = 62.5f;
        private float mFriction;
        private final MassState mMassState;
        private float mVelocityThreshold;

        DragForce() {
            this.mFriction = -4.2f;
            this.mMassState = new MassState();
        }

        @Override  // androidx.dynamicanimation.animation.Force
        public float getAcceleration(float f, float f1) {
            return f1 * this.mFriction;
        }

        float getFrictionScalar() {
            return this.mFriction / -4.2f;
        }

        @Override  // androidx.dynamicanimation.animation.Force
        public boolean isAtEquilibrium(float f, float f1) {
            return Math.abs(f1) < this.mVelocityThreshold;
        }

        void setFrictionScalar(float f) {
            this.mFriction = f * -4.2f;
        }

        void setValueThreshold(float f) {
            this.mVelocityThreshold = f * 62.5f;
        }

        MassState updateValueAndVelocity(float f, float f1, long v) {
            this.mMassState.mVelocity = (float)(((double)f1) * Math.exp(((float)v) / 1000.0f * this.mFriction));
            this.mMassState.mValue = (float)(((double)(f - f1 / this.mFriction)) + ((double)(f1 / this.mFriction)) * Math.exp(this.mFriction * ((float)v) / 1000.0f));
            if(this.isAtEquilibrium(this.mMassState.mValue, this.mMassState.mVelocity)) {
                this.mMassState.mVelocity = 0.0f;
            }
            return this.mMassState;
        }
    }

    private final DragForce mFlingForce;

    public FlingAnimation(FloatValueHolder floatValueHolder0) {
        super(floatValueHolder0);
        DragForce flingAnimation$DragForce0 = new DragForce();
        this.mFlingForce = flingAnimation$DragForce0;
        flingAnimation$DragForce0.setValueThreshold(this.getValueThreshold());
    }

    public FlingAnimation(Object object0, FloatPropertyCompat floatPropertyCompat0) {
        super(object0, floatPropertyCompat0);
        DragForce flingAnimation$DragForce0 = new DragForce();
        this.mFlingForce = flingAnimation$DragForce0;
        flingAnimation$DragForce0.setValueThreshold(this.getValueThreshold());
    }

    @Override  // androidx.dynamicanimation.animation.DynamicAnimation
    float getAcceleration(float f, float f1) {
        return this.mFlingForce.getAcceleration(f, f1);
    }

    public float getFriction() {
        return this.mFlingForce.getFrictionScalar();
    }

    @Override  // androidx.dynamicanimation.animation.DynamicAnimation
    boolean isAtEquilibrium(float f, float f1) {
        return f >= this.mMaxValue || f <= this.mMinValue || this.mFlingForce.isAtEquilibrium(f, f1);
    }

    public FlingAnimation setFriction(float f) {
        if(f <= 0.0f) {
            throw new IllegalArgumentException("Friction must be positive");
        }
        this.mFlingForce.setFrictionScalar(f);
        return this;
    }

    @Override  // androidx.dynamicanimation.animation.DynamicAnimation
    public DynamicAnimation setMaxValue(float f) {
        return this.setMaxValue(f);
    }

    public FlingAnimation setMaxValue(float f) {
        super.setMaxValue(f);
        return this;
    }

    @Override  // androidx.dynamicanimation.animation.DynamicAnimation
    public DynamicAnimation setMinValue(float f) {
        return this.setMinValue(f);
    }

    public FlingAnimation setMinValue(float f) {
        super.setMinValue(f);
        return this;
    }

    @Override  // androidx.dynamicanimation.animation.DynamicAnimation
    public DynamicAnimation setStartVelocity(float f) {
        return this.setStartVelocity(f);
    }

    public FlingAnimation setStartVelocity(float f) {
        super.setStartVelocity(f);
        return this;
    }

    @Override  // androidx.dynamicanimation.animation.DynamicAnimation
    void setValueThreshold(float f) {
        this.mFlingForce.setValueThreshold(f);
    }

    @Override  // androidx.dynamicanimation.animation.DynamicAnimation
    boolean updateValueAndVelocity(long v) {
        MassState dynamicAnimation$MassState0 = this.mFlingForce.updateValueAndVelocity(this.mValue, this.mVelocity, v);
        this.mValue = dynamicAnimation$MassState0.mValue;
        this.mVelocity = dynamicAnimation$MassState0.mVelocity;
        if(this.mValue < this.mMinValue) {
            this.mValue = this.mMinValue;
            return true;
        }
        if(this.mValue > this.mMaxValue) {
            this.mValue = this.mMaxValue;
            return true;
        }
        return this.isAtEquilibrium(this.mValue, this.mVelocity);
    }
}

