package androidx.constraintlayout.motion.widget;

import android.view.animation.Interpolator;

public abstract class MotionInterpolator implements Interpolator {
    @Override  // android.animation.TimeInterpolator
    public abstract float getInterpolation(float arg1);

    public abstract float getVelocity();
}

