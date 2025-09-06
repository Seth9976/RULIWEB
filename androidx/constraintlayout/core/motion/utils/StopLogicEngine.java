package androidx.constraintlayout.core.motion.utils;

public class StopLogicEngine implements StopEngine {
    public static class Decelerate implements StopEngine {
        private float mAcceleration;
        private float mDestination;
        private boolean mDone;
        private float mDuration;
        private float mInitialPos;
        private float mInitialVelocity;
        private float mLastVelocity;

        public Decelerate() {
            this.mDone = false;
        }

        public void config(float f, float f1, float f2) {
            this.mDone = false;
            this.mDestination = f1;
            this.mInitialVelocity = f2;
            this.mInitialPos = f;
            float f3 = (f1 - f) / (f2 / 2.0f);
            this.mDuration = f3;
            this.mAcceleration = -f2 / f3;
        }

        @Override  // androidx.constraintlayout.core.motion.utils.StopEngine
        public String debug(String s, float f) {
            return this.mDuration + " " + this.mLastVelocity;
        }

        @Override  // androidx.constraintlayout.core.motion.utils.StopEngine
        public float getInterpolation(float f) {
            if(f > this.mDuration) {
                this.mDone = true;
                return this.mDestination;
            }
            this.getVelocity(f);
            return this.mInitialPos + (this.mInitialVelocity + this.mAcceleration * f / 2.0f) * f;
        }

        @Override  // androidx.constraintlayout.core.motion.utils.StopEngine
        public float getVelocity() {
            return this.mLastVelocity;
        }

        @Override  // androidx.constraintlayout.core.motion.utils.StopEngine
        public float getVelocity(float f) {
            if(f > this.mDuration) {
                return 0.0f;
            }
            float f1 = this.mInitialVelocity + this.mAcceleration * f;
            this.mLastVelocity = f1;
            return f1;
        }

        @Override  // androidx.constraintlayout.core.motion.utils.StopEngine
        public boolean isStopped() {
            return this.mDone;
        }
    }

    private static final float EPSILON = 0.00001f;
    private boolean mBackwards;
    private boolean mDone;
    private float mLastPosition;
    private float mLastTime;
    private int mNumberOfStages;
    private float mStage1Duration;
    private float mStage1EndPosition;
    private float mStage1Velocity;
    private float mStage2Duration;
    private float mStage2EndPosition;
    private float mStage2Velocity;
    private float mStage3Duration;
    private float mStage3EndPosition;
    private float mStage3Velocity;
    private float mStartPosition;
    private String mType;

    public StopLogicEngine() {
        this.mBackwards = false;
        this.mDone = false;
    }

    private float calcY(float f) {
        this.mDone = false;
        float f1 = this.mStage1Duration;
        if(f <= f1) {
            return this.mStage1Velocity * f + (this.mStage2Velocity - this.mStage1Velocity) * f * f / (f1 * 2.0f);
        }
        int v = this.mNumberOfStages;
        if(v == 1) {
            return this.mStage1EndPosition;
        }
        float f2 = f - f1;
        float f3 = this.mStage2Duration;
        if(f2 < f3) {
            return this.mStage1EndPosition + this.mStage2Velocity * f2 + (this.mStage3Velocity - this.mStage2Velocity) * f2 * f2 / (f3 * 2.0f);
        }
        if(v == 2) {
            return this.mStage2EndPosition;
        }
        float f4 = f2 - f3;
        float f5 = this.mStage3Duration;
        if(f4 <= f5) {
            return this.mStage2EndPosition + this.mStage3Velocity * f4 - this.mStage3Velocity * f4 * f4 / (f5 * 2.0f);
        }
        this.mDone = true;
        return this.mStage3EndPosition;
    }

    public void config(float f, float f1, float f2, float f3, float f4, float f5) {
        boolean z = false;
        this.mDone = false;
        this.mStartPosition = f;
        if(f > f1) {
            z = true;
        }
        this.mBackwards = z;
        if(z) {
            this.setup(-f2, f - f1, f4, f5, f3);
            return;
        }
        this.setup(f2, f1 - f, f4, f5, f3);
    }

    @Override  // androidx.constraintlayout.core.motion.utils.StopEngine
    public String debug(String s, float f) {
        StringBuilder stringBuilder0 = new StringBuilder();
        stringBuilder0.append(s + " ===== " + this.mType + "\n");
        stringBuilder0.append(s);
        stringBuilder0.append((this.mBackwards ? "backwards" : "forward "));
        stringBuilder0.append(" time = ");
        stringBuilder0.append(f);
        stringBuilder0.append("  stages ");
        stringBuilder0.append(this.mNumberOfStages);
        stringBuilder0.append("\n");
        String s1 = this.mNumberOfStages <= 1 ? stringBuilder0.toString() + s + " dur " + this.mStage1Duration + " vel " + this.mStage1Velocity + " pos " + this.mStage1EndPosition + "\n" : stringBuilder0.toString() + s + " dur " + this.mStage1Duration + " vel " + this.mStage1Velocity + " pos " + this.mStage1EndPosition + "\n" + s + " dur " + this.mStage2Duration + " vel " + this.mStage2Velocity + " pos " + this.mStage2EndPosition + "\n";
        if(this.mNumberOfStages > 2) {
            s1 = s1 + s + " dur " + this.mStage3Duration + " vel " + this.mStage3Velocity + " pos " + this.mStage3EndPosition + "\n";
        }
        float f1 = this.mStage1Duration;
        if(f <= f1) {
            return s1 + s + "stage 0\n";
        }
        int v = this.mNumberOfStages;
        if(v == 1) {
            return s1 + s + "end stage 0\n";
        }
        float f2 = f - f1;
        float f3 = this.mStage2Duration;
        if(f2 < f3) {
            return s1 + s + " stage 1\n";
        }
        if(v == 2) {
            return s1 + s + "end stage 1\n";
        }
        return f2 - f3 < this.mStage3Duration ? s1 + s + " stage 2\n" : s1 + s + " end stage 2\n";
    }

    @Override  // androidx.constraintlayout.core.motion.utils.StopEngine
    public float getInterpolation(float f) {
        float f1 = this.calcY(f);
        this.mLastPosition = f1;
        this.mLastTime = f;
        return this.mBackwards ? this.mStartPosition - f1 : this.mStartPosition + f1;
    }

    // 去混淆评级： 低(20)
    @Override  // androidx.constraintlayout.core.motion.utils.StopEngine
    public float getVelocity() {
        return this.mBackwards ? -this.getVelocity(this.mLastTime) : this.getVelocity(this.mLastTime);
    }

    @Override  // androidx.constraintlayout.core.motion.utils.StopEngine
    public float getVelocity(float f) {
        float f1 = this.mStage1Duration;
        if(f <= f1) {
            return this.mStage1Velocity + (this.mStage2Velocity - this.mStage1Velocity) * f / f1;
        }
        int v = this.mNumberOfStages;
        if(v == 1) {
            return 0.0f;
        }
        float f2 = f - f1;
        float f3 = this.mStage2Duration;
        if(f2 < f3) {
            return this.mStage2Velocity + (this.mStage3Velocity - this.mStage2Velocity) * f2 / f3;
        }
        if(v == 2) {
            return 0.0f;
        }
        float f4 = f2 - f3;
        return f4 < this.mStage3Duration ? this.mStage3Velocity - f4 * this.mStage3Velocity / this.mStage3Duration : 0.0f;
    }

    @Override  // androidx.constraintlayout.core.motion.utils.StopEngine
    public boolean isStopped() {
        return this.getVelocity() < 0.00001f && Math.abs(this.mStage3EndPosition - this.mLastPosition) < 0.00001f;
    }

    private void setup(float f, float f1, float f2, float f3, float f4) {
        this.mDone = false;
        this.mStage3EndPosition = f1;
        if(f == 0.0f) {
            f = 0.0001f;
        }
        float f5 = f / f2;
        float f6 = f5 * f / 2.0f;
        if(f < 0.0f) {
            float f7 = (float)Math.sqrt((f1 - -f / f2 * f / 2.0f) * f2);
            if(f7 < f3) {
                this.mType = "backward accelerate, decelerate";
                this.mNumberOfStages = 2;
                this.mStage1Velocity = f;
                this.mStage2Velocity = f7;
                this.mStage3Velocity = 0.0f;
                float f8 = (f7 - f) / f2;
                this.mStage1Duration = f8;
                this.mStage2Duration = f7 / f2;
                this.mStage1EndPosition = (f + f7) * f8 / 2.0f;
                this.mStage2EndPosition = f1;
                this.mStage3EndPosition = f1;
                return;
            }
            this.mType = "backward accelerate cruse decelerate";
            this.mNumberOfStages = 3;
            this.mStage1Velocity = f;
            this.mStage2Velocity = f3;
            this.mStage3Velocity = f3;
            float f9 = (f3 - f) / f2;
            this.mStage1Duration = f9;
            float f10 = f3 / f2;
            this.mStage3Duration = f10;
            float f11 = (f + f3) * f9 / 2.0f;
            float f12 = f10 * f3 / 2.0f;
            this.mStage2Duration = (f1 - f11 - f12) / f3;
            this.mStage1EndPosition = f11;
            this.mStage2EndPosition = f1 - f12;
            this.mStage3EndPosition = f1;
            return;
        }
        if(f6 >= f1) {
            this.mType = "hard stop";
            this.mNumberOfStages = 1;
            this.mStage1Velocity = f;
            this.mStage2Velocity = 0.0f;
            this.mStage1EndPosition = f1;
            this.mStage1Duration = 2.0f * f1 / f;
            return;
        }
        float f13 = f1 - f6;
        float f14 = f13 / f;
        if(f14 + f5 < f4) {
            this.mType = "cruse decelerate";
            this.mNumberOfStages = 2;
            this.mStage1Velocity = f;
            this.mStage2Velocity = f;
            this.mStage3Velocity = 0.0f;
            this.mStage1EndPosition = f13;
            this.mStage2EndPosition = f1;
            this.mStage1Duration = f14;
            this.mStage2Duration = f5;
            return;
        }
        float f15 = (float)Math.sqrt(f2 * f1 + f * f / 2.0f);
        float f16 = (f15 - f) / f2;
        this.mStage1Duration = f16;
        float f17 = f15 / f2;
        this.mStage2Duration = f17;
        if(f15 < f3) {
            this.mType = "accelerate decelerate";
            this.mNumberOfStages = 2;
            this.mStage1Velocity = f;
            this.mStage2Velocity = f15;
            this.mStage3Velocity = 0.0f;
            this.mStage1Duration = f16;
            this.mStage2Duration = f17;
            this.mStage1EndPosition = (f + f15) * f16 / 2.0f;
            this.mStage2EndPosition = f1;
            return;
        }
        this.mType = "accelerate cruse decelerate";
        this.mNumberOfStages = 3;
        this.mStage1Velocity = f;
        this.mStage2Velocity = f3;
        this.mStage3Velocity = f3;
        float f18 = (f3 - f) / f2;
        this.mStage1Duration = f18;
        float f19 = f3 / f2;
        this.mStage3Duration = f19;
        float f20 = (f + f3) * f18 / 2.0f;
        float f21 = f19 * f3 / 2.0f;
        this.mStage2Duration = (f1 - f20 - f21) / f3;
        this.mStage1EndPosition = f20;
        this.mStage2EndPosition = f1 - f21;
        this.mStage3EndPosition = f1;
    }
}

