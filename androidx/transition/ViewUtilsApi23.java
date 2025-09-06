package androidx.transition;

import android.os.Build.VERSION;
import android.view.View;

class ViewUtilsApi23 extends ViewUtilsApi22 {
    static class Api29Impl {
        static void setTransitionVisibility(View view0, int v) {
            view0.setTransitionVisibility(v);
        }
    }

    private static boolean sTryHiddenSetTransitionVisibility = true;

    static {
    }

    @Override  // androidx.transition.ViewUtilsApi19
    public void setTransitionVisibility(View view0, int v) {
        if(Build.VERSION.SDK_INT == 28) {
            super.setTransitionVisibility(view0, v);
            return;
        }
        if(ViewUtilsApi23.sTryHiddenSetTransitionVisibility) {
            try {
                Api29Impl.setTransitionVisibility(view0, v);
            }
            catch(NoSuchMethodError unused_ex) {
                ViewUtilsApi23.sTryHiddenSetTransitionVisibility = false;
            }
        }
    }
}

