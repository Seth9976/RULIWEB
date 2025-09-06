package androidx.constraintlayout.core.motion.utils;

public interface StopEngine {
    String debug(String arg1, float arg2);

    float getInterpolation(float arg1);

    float getVelocity();

    float getVelocity(float arg1);

    boolean isStopped();
}

