package androidx.core.widget;

import android.os.Build.VERSION;
import android.util.Log;
import android.view.View;
import android.widget.PopupWindow;
import androidx.annotation.ReplaceWith;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public final class PopupWindowCompat {
    static class Api23Impl {
        static boolean getOverlapAnchor(PopupWindow popupWindow0) {
            return popupWindow0.getOverlapAnchor();
        }

        static int getWindowLayoutType(PopupWindow popupWindow0) {
            return popupWindow0.getWindowLayoutType();
        }

        static void setOverlapAnchor(PopupWindow popupWindow0, boolean z) {
            popupWindow0.setOverlapAnchor(z);
        }

        static void setWindowLayoutType(PopupWindow popupWindow0, int v) {
            popupWindow0.setWindowLayoutType(v);
        }
    }

    private static final String TAG = "PopupWindowCompatApi21";
    private static Method sGetWindowLayoutTypeMethod;
    private static boolean sGetWindowLayoutTypeMethodAttempted;
    private static Field sOverlapAnchorField;
    private static boolean sOverlapAnchorFieldAttempted;
    private static Method sSetWindowLayoutTypeMethod;
    private static boolean sSetWindowLayoutTypeMethodAttempted;

    public static boolean getOverlapAnchor(PopupWindow popupWindow0) {
        if(Build.VERSION.SDK_INT >= 23) {
            return Api23Impl.getOverlapAnchor(popupWindow0);
        }
        if(!PopupWindowCompat.sOverlapAnchorFieldAttempted) {
            try {
                Field field0 = PopupWindow.class.getDeclaredField("mOverlapAnchor");
                PopupWindowCompat.sOverlapAnchorField = field0;
                field0.setAccessible(true);
            }
            catch(NoSuchFieldException noSuchFieldException0) {
                Log.i("PopupWindowCompatApi21", "Could not fetch mOverlapAnchor field from PopupWindow", noSuchFieldException0);
            }
            PopupWindowCompat.sOverlapAnchorFieldAttempted = true;
        }
        Field field1 = PopupWindowCompat.sOverlapAnchorField;
        if(field1 != null) {
            try {
                return ((Boolean)field1.get(popupWindow0)).booleanValue();
            }
            catch(IllegalAccessException illegalAccessException0) {
                Log.i("PopupWindowCompatApi21", "Could not get overlap anchor field in PopupWindow", illegalAccessException0);
            }
        }
        return false;
    }

    public static int getWindowLayoutType(PopupWindow popupWindow0) {
        if(Build.VERSION.SDK_INT >= 23) {
            return Api23Impl.getWindowLayoutType(popupWindow0);
        }
        if(!PopupWindowCompat.sGetWindowLayoutTypeMethodAttempted) {
            try {
                Method method0 = PopupWindow.class.getDeclaredMethod("getWindowLayoutType", null);
                PopupWindowCompat.sGetWindowLayoutTypeMethod = method0;
                method0.setAccessible(true);
            }
            catch(Exception unused_ex) {
            }
            PopupWindowCompat.sGetWindowLayoutTypeMethodAttempted = true;
        }
        Method method1 = PopupWindowCompat.sGetWindowLayoutTypeMethod;
        if(method1 != null) {
            try {
                return (int)(((Integer)method1.invoke(popupWindow0, null)));
            }
            catch(Exception unused_ex) {
            }
        }
        return 0;
    }

    public static void setOverlapAnchor(PopupWindow popupWindow0, boolean z) {
        if(Build.VERSION.SDK_INT >= 23) {
            Api23Impl.setOverlapAnchor(popupWindow0, z);
            return;
        }
        if(!PopupWindowCompat.sOverlapAnchorFieldAttempted) {
            try {
                Field field0 = PopupWindow.class.getDeclaredField("mOverlapAnchor");
                PopupWindowCompat.sOverlapAnchorField = field0;
                field0.setAccessible(true);
            }
            catch(NoSuchFieldException noSuchFieldException0) {
                Log.i("PopupWindowCompatApi21", "Could not fetch mOverlapAnchor field from PopupWindow", noSuchFieldException0);
            }
            PopupWindowCompat.sOverlapAnchorFieldAttempted = true;
        }
        Field field1 = PopupWindowCompat.sOverlapAnchorField;
        if(field1 != null) {
            try {
                field1.set(popupWindow0, Boolean.valueOf(z));
            }
            catch(IllegalAccessException illegalAccessException0) {
                Log.i("PopupWindowCompatApi21", "Could not set overlap anchor field in PopupWindow", illegalAccessException0);
            }
        }
    }

    public static void setWindowLayoutType(PopupWindow popupWindow0, int v) {
        if(Build.VERSION.SDK_INT >= 23) {
            Api23Impl.setWindowLayoutType(popupWindow0, v);
            return;
        }
        if(!PopupWindowCompat.sSetWindowLayoutTypeMethodAttempted) {
            try {
                Method method0 = PopupWindow.class.getDeclaredMethod("setWindowLayoutType", Integer.TYPE);
                PopupWindowCompat.sSetWindowLayoutTypeMethod = method0;
                method0.setAccessible(true);
            }
            catch(Exception unused_ex) {
            }
            PopupWindowCompat.sSetWindowLayoutTypeMethodAttempted = true;
        }
        Method method1 = PopupWindowCompat.sSetWindowLayoutTypeMethod;
        if(method1 != null) {
            try {
                method1.invoke(popupWindow0, v);
            }
            catch(Exception unused_ex) {
            }
        }
    }

    @ReplaceWith(expression = "popup.showAsDropDown(anchor, xoff, yoff, gravity)")
    @Deprecated
    public static void showAsDropDown(PopupWindow popupWindow0, View view0, int v, int v1, int v2) {
        popupWindow0.showAsDropDown(view0, v, v1, v2);
    }
}

