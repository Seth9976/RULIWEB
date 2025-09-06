package androidx.dynamicanimation.animation;

interface Force {
    float getAcceleration(float arg1, float arg2);

    boolean isAtEquilibrium(float arg1, float arg2);
}

