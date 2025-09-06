package androidx.transition;

import android.graphics.Matrix;
import android.view.View;

class ViewUtilsApi29 extends ViewUtilsApi23 {
    @Override  // androidx.transition.ViewUtilsApi19
    public float getTransitionAlpha(View view0) {
        return view0.getTransitionAlpha();
    }

    @Override  // androidx.transition.ViewUtilsApi21
    public void setAnimationMatrix(View view0, Matrix matrix0) {
        view0.setAnimationMatrix(matrix0);
    }

    @Override  // androidx.transition.ViewUtilsApi22
    public void setLeftTopRightBottom(View view0, int v, int v1, int v2, int v3) {
        view0.setLeftTopRightBottom(v, v1, v2, v3);
    }

    @Override  // androidx.transition.ViewUtilsApi19
    public void setTransitionAlpha(View view0, float f) {
        view0.setTransitionAlpha(f);
    }

    @Override  // androidx.transition.ViewUtilsApi23
    public void setTransitionVisibility(View view0, int v) {
        view0.setTransitionVisibility(v);
    }

    @Override  // androidx.transition.ViewUtilsApi21
    public void transformMatrixToGlobal(View view0, Matrix matrix0) {
        view0.transformMatrixToGlobal(matrix0);
    }

    @Override  // androidx.transition.ViewUtilsApi21
    public void transformMatrixToLocal(View view0, Matrix matrix0) {
        view0.transformMatrixToLocal(matrix0);
    }
}

