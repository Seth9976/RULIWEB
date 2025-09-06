package androidx.transition;

import android.view.View;

class ViewUtilsApi22 extends ViewUtilsApi21 {
    static class Api29Impl {
        static void setLeftTopRightBottom(View view0, int v, int v1, int v2, int v3) {
            view0.setLeftTopRightBottom(v, v1, v2, v3);
        }
    }

    private static boolean sTryHiddenSetLeftTopRightBottom = true;

    static {
    }

    @Override  // androidx.transition.ViewUtilsApi19
    public void setLeftTopRightBottom(View view0, int v, int v1, int v2, int v3) {
        if(ViewUtilsApi22.sTryHiddenSetLeftTopRightBottom) {
            try {
                Api29Impl.setLeftTopRightBottom(view0, v, v1, v2, v3);
            }
            catch(NoSuchMethodError unused_ex) {
                ViewUtilsApi22.sTryHiddenSetLeftTopRightBottom = false;
            }
        }
    }
}

