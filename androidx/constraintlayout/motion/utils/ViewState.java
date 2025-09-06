package androidx.constraintlayout.motion.utils;

import android.view.View;

public class ViewState {
    public int bottom;
    public int left;
    public int right;
    public float rotation;
    public int top;

    public void getState(View view0) {
        this.left = view0.getLeft();
        this.top = view0.getTop();
        this.right = view0.getRight();
        this.bottom = view0.getBottom();
        this.rotation = view0.getRotation();
    }

    public int height() {
        return this.bottom - this.top;
    }

    public int width() {
        return this.right - this.left;
    }
}

