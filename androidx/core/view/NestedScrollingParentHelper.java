package androidx.core.view;

import android.view.View;
import android.view.ViewGroup;

public class NestedScrollingParentHelper {
    private int mNestedScrollAxesNonTouch;
    private int mNestedScrollAxesTouch;

    public NestedScrollingParentHelper(ViewGroup viewGroup0) {
    }

    public int getNestedScrollAxes() {
        return this.mNestedScrollAxesTouch | this.mNestedScrollAxesNonTouch;
    }

    public void onNestedScrollAccepted(View view0, View view1, int v) {
        this.onNestedScrollAccepted(view0, view1, v, 0);
    }

    public void onNestedScrollAccepted(View view0, View view1, int v, int v1) {
        if(v1 == 1) {
            this.mNestedScrollAxesNonTouch = v;
            return;
        }
        this.mNestedScrollAxesTouch = v;
    }

    public void onStopNestedScroll(View view0) {
        this.onStopNestedScroll(view0, 0);
    }

    public void onStopNestedScroll(View view0, int v) {
        if(v == 1) {
            this.mNestedScrollAxesNonTouch = 0;
            return;
        }
        this.mNestedScrollAxesTouch = 0;
    }
}

