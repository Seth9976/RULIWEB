package androidx.transition;

import android.os.Build.VERSION;
import android.view.ViewGroup;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

class ViewGroupUtils {
    static class Api29Impl {
        static int getChildDrawingOrder(ViewGroup viewGroup0, int v) {
            return viewGroup0.getChildDrawingOrder(v);
        }

        static void suppressLayout(ViewGroup viewGroup0, boolean z) {
            viewGroup0.suppressLayout(z);
        }
    }

    private static Method sGetChildDrawingOrderMethod = null;
    private static boolean sGetChildDrawingOrderMethodFetched = false;
    private static boolean sTryHiddenSuppressLayout = true;

    static {
    }

    static int getChildDrawingOrder(ViewGroup viewGroup0, int v) {
        if(Build.VERSION.SDK_INT >= 29) {
            return Api29Impl.getChildDrawingOrder(viewGroup0, v);
        }
        if(!ViewGroupUtils.sGetChildDrawingOrderMethodFetched) {
            try {
                Method method0 = ViewGroup.class.getDeclaredMethod("getChildDrawingOrder", Integer.TYPE, Integer.TYPE);
                ViewGroupUtils.sGetChildDrawingOrderMethod = method0;
                method0.setAccessible(true);
            }
            catch(NoSuchMethodException unused_ex) {
            }
            ViewGroupUtils.sGetChildDrawingOrderMethodFetched = true;
        }
        Method method1 = ViewGroupUtils.sGetChildDrawingOrderMethod;
        if(method1 != null) {
            try {
                return (int)(((Integer)method1.invoke(viewGroup0, viewGroup0.getChildCount(), v)));
            }
            catch(IllegalAccessException | InvocationTargetException unused_ex) {
            }
        }
        return v;
    }

    private static void hiddenSuppressLayout(ViewGroup viewGroup0, boolean z) {
        if(ViewGroupUtils.sTryHiddenSuppressLayout) {
            try {
                Api29Impl.suppressLayout(viewGroup0, z);
            }
            catch(NoSuchMethodError unused_ex) {
                ViewGroupUtils.sTryHiddenSuppressLayout = false;
            }
        }
    }

    static void suppressLayout(ViewGroup viewGroup0, boolean z) {
        if(Build.VERSION.SDK_INT >= 29) {
            Api29Impl.suppressLayout(viewGroup0, z);
            return;
        }
        ViewGroupUtils.hiddenSuppressLayout(viewGroup0, z);
    }
}

