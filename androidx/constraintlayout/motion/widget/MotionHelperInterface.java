package androidx.constraintlayout.motion.widget;

import android.graphics.Canvas;
import java.util.HashMap;

public interface MotionHelperInterface extends Animatable, TransitionListener {
    boolean isDecorator();

    boolean isUseOnHide();

    boolean isUsedOnShow();

    void onFinishedMotionScene(MotionLayout arg1);

    void onPostDraw(Canvas arg1);

    void onPreDraw(Canvas arg1);

    void onPreSetup(MotionLayout arg1, HashMap arg2);
}

