package androidx.dynamicanimation.animation;

import android.os.Looper;
import android.util.AndroidRuntimeException;

public final class SpringAnimation extends DynamicAnimation {
    private static final float UNSET = 3.402823E+38f;
    private boolean mEndRequested;
    private float mPendingPosition;
    private SpringForce mSpring;

    public SpringAnimation(FloatValueHolder floatValueHolder0) {
        super(floatValueHolder0);
        this.mSpring = null;
        this.mPendingPosition = 3.402823E+38f;
        this.mEndRequested = false;
    }

    public SpringAnimation(Object object0, FloatPropertyCompat floatPropertyCompat0) {
        super(object0, floatPropertyCompat0);
        this.mSpring = null;
        this.mPendingPosition = 3.402823E+38f;
        this.mEndRequested = false;
    }

    public SpringAnimation(Object object0, FloatPropertyCompat floatPropertyCompat0, float f) {
        super(object0, floatPropertyCompat0);
        this.mPendingPosition = 3.402823E+38f;
        this.mEndRequested = false;
        this.mSpring = new SpringForce(f);
    }

    public void animateToFinalPosition(float f) {
        if(this.isRunning()) {
            this.mPendingPosition = f;
            return;
        }
        if(this.mSpring == null) {
            this.mSpring = new SpringForce(f);
        }
        this.mSpring.setFinalPosition(f);
        this.start();
    }

    public boolean canSkipToEnd() {
        return this.mSpring.mDampingRatio > 0.0;
    }

    @Override  // androidx.dynamicanimation.animation.DynamicAnimation
    float getAcceleration(float f, float f1) {
        return this.mSpring.getAcceleration(f, f1);
    }

    public SpringForce getSpring() {
        return this.mSpring;
    }

    @Override  // androidx.dynamicanimation.animation.DynamicAnimation
    boolean isAtEquilibrium(float f, float f1) {
        return this.mSpring.isAtEquilibrium(f, f1);
    }

    private void sanityCheck() {
        SpringForce springForce0 = this.mSpring;
        if(springForce0 == null) {
            throw new UnsupportedOperationException("Incomplete SpringAnimation: Either final position or a spring force needs to be set.");
        }
        double f = (double)springForce0.getFinalPosition();
        if(f > ((double)this.mMaxValue)) {
            throw new UnsupportedOperationException("Final position of the spring cannot be greater than the max value.");
        }
        if(f < ((double)this.mMinValue)) {
            throw new UnsupportedOperationException("Final position of the spring cannot be less than the min value.");
        }
    }

    public SpringAnimation setSpring(SpringForce springForce0) {
        this.mSpring = springForce0;
        return this;
    }

    @Override  // androidx.dynamicanimation.animation.DynamicAnimation
    void setValueThreshold(float f) {
    }

    public void skipToEnd() {
        if(!this.canSkipToEnd()) {
            throw new UnsupportedOperationException("Spring animations can only come to an end when there is damping");
        }
        if(Looper.myLooper() != Looper.getMainLooper()) {
            throw new AndroidRuntimeException("Animations may only be started on the main thread");
        }
        if(this.mRunning) {
            this.mEndRequested = true;
        }
    }

    @Override  // androidx.dynamicanimation.animation.DynamicAnimation
    public void start() {
        this.sanityCheck();
        this.mSpring.setValueThreshold(((double)this.getValueThreshold()));
        super.start();
    }

    @Override  // androidx.dynamicanimation.animation.DynamicAnimation
    boolean updateValueAndVelocity(long v) {
        if(this.mEndRequested) {
            float f = this.mPendingPosition;
            if(f != 3.402823E+38f) {
                this.mSpring.setFinalPosition(f);
                this.mPendingPosition = 3.402823E+38f;
            }
            this.mValue = this.mSpring.getFinalPosition();
            this.mVelocity = 0.0f;
            this.mEndRequested = false;
            return true;
        }
        if(this.mPendingPosition == 3.402823E+38f) {
            MassState dynamicAnimation$MassState2 = this.mSpring.updateValues(((double)this.mValue), ((double)this.mVelocity), v);
            this.mValue = dynamicAnimation$MassState2.mValue;
            this.mVelocity = dynamicAnimation$MassState2.mVelocity;
        }
        else {
            MassState dynamicAnimation$MassState0 = this.mSpring.updateValues(((double)this.mValue), ((double)this.mVelocity), v / 2L);
            this.mSpring.setFinalPosition(this.mPendingPosition);
            this.mPendingPosition = 3.402823E+38f;
            MassState dynamicAnimation$MassState1 = this.mSpring.updateValues(((double)dynamicAnimation$MassState0.mValue), ((double)dynamicAnimation$MassState0.mVelocity), v / 2L);
            this.mValue = dynamicAnimation$MassState1.mValue;
            this.mVelocity = dynamicAnimation$MassState1.mVelocity;
        }
        this.mValue = Math.min(this.mValue, this.mMaxValue);
        if(this.isAtEquilibrium(this.mValue, this.mVelocity)) {
            this.mValue = this.mSpring.getFinalPosition();
            this.mVelocity = 0.0f;
            return true;
        }
        return false;
    }
}

