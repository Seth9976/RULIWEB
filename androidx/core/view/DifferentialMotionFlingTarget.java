package androidx.core.view;

public interface DifferentialMotionFlingTarget {
    float getScaledScrollFactor();

    boolean startDifferentialMotionFling(float arg1);

    void stopDifferentialMotionFling();
}

