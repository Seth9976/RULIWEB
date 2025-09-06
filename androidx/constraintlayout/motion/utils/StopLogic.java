package androidx.constraintlayout.motion.utils;

import androidx.constraintlayout.core.motion.utils.SpringStopEngine;
import androidx.constraintlayout.core.motion.utils.StopEngine;
import androidx.constraintlayout.core.motion.utils.StopLogicEngine;
import androidx.constraintlayout.motion.widget.MotionInterpolator;

public class StopLogic extends MotionInterpolator {
    private StopEngine mEngine;
    private SpringStopEngine mSpringStopEngine;
    private StopLogicEngine mStopLogicEngine;

    public StopLogic() {
        StopLogicEngine stopLogicEngine0 = new StopLogicEngine();
        this.mStopLogicEngine = stopLogicEngine0;
        this.mEngine = stopLogicEngine0;
    }

    public void config(float f, float f1, float f2, float f3, float f4, float f5) {
        this.mEngine = this.mStopLogicEngine;
        this.mStopLogicEngine.config(f, f1, f2, f3, f4, f5);
    }

    public String debug(String s, float f) {
        return this.mEngine.debug(s, f);
    }

    @Override  // androidx.constraintlayout.motion.widget.MotionInterpolator
    public float getInterpolation(float f) {
        return this.mEngine.getInterpolation(f);
    }

    @Override  // androidx.constraintlayout.motion.widget.MotionInterpolator
    public float getVelocity() {
        return this.mEngine.getVelocity();
    }

    public float getVelocity(float f) {
        return this.mEngine.getVelocity(f);
    }

    public boolean isStopped() {
        return this.mEngine.isStopped();
    }

    public void springConfig(float f, float f1, float f2, float f3, float f4, float f5, float f6, int v) {
        if(this.mSpringStopEngine == null) {
            this.mSpringStopEngine = new SpringStopEngine();
        }
        this.mEngine = this.mSpringStopEngine;
        this.mSpringStopEngine.springConfig(f, f1, f2, f3, f4, f5, f6, v);
    }
}

