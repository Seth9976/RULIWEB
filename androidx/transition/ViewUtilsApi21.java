package androidx.transition;

import android.graphics.Matrix;
import android.view.View;

class ViewUtilsApi21 extends ViewUtilsApi19 {
    static class Api29Impl {
        static void setAnimationMatrix(View view0, Matrix matrix0) {
            view0.setAnimationMatrix(matrix0);
        }

        static void transformMatrixToGlobal(View view0, Matrix matrix0) {
            view0.transformMatrixToGlobal(matrix0);
        }

        static void transformMatrixToLocal(View view0, Matrix matrix0) {
            view0.transformMatrixToLocal(matrix0);
        }
    }

    private static boolean sTryHiddenSetAnimationMatrix = true;
    private static boolean sTryHiddenTransformMatrixToGlobal = true;
    private static boolean sTryHiddenTransformMatrixToLocal = true;

    static {
    }

    @Override  // androidx.transition.ViewUtilsApi19
    public void setAnimationMatrix(View view0, Matrix matrix0) {
        if(ViewUtilsApi21.sTryHiddenSetAnimationMatrix) {
            try {
                Api29Impl.setAnimationMatrix(view0, matrix0);
            }
            catch(NoSuchMethodError unused_ex) {
                ViewUtilsApi21.sTryHiddenSetAnimationMatrix = false;
            }
        }
    }

    @Override  // androidx.transition.ViewUtilsApi19
    public void transformMatrixToGlobal(View view0, Matrix matrix0) {
        if(ViewUtilsApi21.sTryHiddenTransformMatrixToGlobal) {
            try {
                Api29Impl.transformMatrixToGlobal(view0, matrix0);
            }
            catch(NoSuchMethodError unused_ex) {
                ViewUtilsApi21.sTryHiddenTransformMatrixToGlobal = false;
            }
        }
    }

    @Override  // androidx.transition.ViewUtilsApi19
    public void transformMatrixToLocal(View view0, Matrix matrix0) {
        if(ViewUtilsApi21.sTryHiddenTransformMatrixToLocal) {
            try {
                Api29Impl.transformMatrixToLocal(view0, matrix0);
            }
            catch(NoSuchMethodError unused_ex) {
                ViewUtilsApi21.sTryHiddenTransformMatrixToLocal = false;
            }
        }
    }
}

