package androidx.appcompat.widget;

import android.graphics.Insets;
import android.graphics.Rect;
import android.os.Build.VERSION;
import android.util.Log;
import android.view.View;
import android.view.WindowInsets.Builder;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ViewUtils {
    static class Api29Impl {
        static void computeFitSystemWindows(View view0, Rect rect0, Rect rect1) {
            Insets insets0 = view0.computeSystemWindowInsets(new WindowInsets.Builder().setSystemWindowInsets(Insets.of(rect0)).build(), rect1).getSystemWindowInsets();
            rect0.set(insets0.left, insets0.top, insets0.right, insets0.bottom);
        }
    }

    static final boolean SDK_LEVEL_SUPPORTS_AUTOSIZE = false;
    private static final String TAG = "ViewUtils";
    private static Method sComputeFitSystemWindowsMethod;
    private static boolean sInitComputeFitSystemWindowsMethod;

    static {
        ViewUtils.SDK_LEVEL_SUPPORTS_AUTOSIZE = Build.VERSION.SDK_INT >= 27;
    }

    public static void computeFitSystemWindows(View view0, Rect rect0, Rect rect1) {
        if(Build.VERSION.SDK_INT >= 29) {
            Api29Impl.computeFitSystemWindows(view0, rect0, rect1);
            return;
        }
        if(!ViewUtils.sInitComputeFitSystemWindowsMethod) {
            try {
                ViewUtils.sInitComputeFitSystemWindowsMethod = true;
                Method method0 = View.class.getDeclaredMethod("computeFitSystemWindows", Rect.class, Rect.class);
                ViewUtils.sComputeFitSystemWindowsMethod = method0;
                if(!method0.isAccessible()) {
                    ViewUtils.sComputeFitSystemWindowsMethod.setAccessible(true);
                }
            }
            catch(NoSuchMethodException unused_ex) {
                Log.d("ViewUtils", "Could not find method computeFitSystemWindows. Oh well.");
            }
        }
        Method method1 = ViewUtils.sComputeFitSystemWindowsMethod;
        if(method1 != null) {
            try {
                method1.invoke(view0, rect0, rect1);
            }
            catch(Exception exception0) {
                Log.d("ViewUtils", "Could not invoke computeFitSystemWindows", exception0);
            }
        }
    }

    public static boolean isLayoutRtl(View view0) {
        return view0.getLayoutDirection() == 1;
    }

    public static void makeOptionalFitsSystemWindows(View view0) {
        try {
            Method method0 = view0.getClass().getMethod("makeOptionalFitsSystemWindows", null);
            if(!method0.isAccessible()) {
                method0.setAccessible(true);
            }
            method0.invoke(view0, null);
        }
        catch(NoSuchMethodException unused_ex) {
            Log.d("ViewUtils", "Could not find method makeOptionalFitsSystemWindows. Oh well...");
        }
        catch(InvocationTargetException invocationTargetException0) {
            Log.d("ViewUtils", "Could not invoke makeOptionalFitsSystemWindows", invocationTargetException0);
        }
        catch(IllegalAccessException illegalAccessException0) {
            Log.d("ViewUtils", "Could not invoke makeOptionalFitsSystemWindows", illegalAccessException0);
        }
    }
}

