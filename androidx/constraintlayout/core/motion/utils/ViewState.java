package androidx.constraintlayout.core.motion.utils;

import androidx.constraintlayout.core.motion.MotionWidget;

public class ViewState {
    public int bottom;
    public int left;
    public int right;
    public float rotation;
    public int top;

    public void getState(MotionWidget motionWidget0) {
        this.left = motionWidget0.getLeft();
        this.top = motionWidget0.getTop();
        this.right = motionWidget0.getRight();
        this.bottom = motionWidget0.getBottom();
        this.rotation = (float)(((int)motionWidget0.getRotationZ()));
    }

    public int height() {
        return this.bottom - this.top;
    }

    public int width() {
        return this.right - this.left;
    }
}

