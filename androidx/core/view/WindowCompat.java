package androidx.core.view;

import android.os.Build.VERSION;
import android.view.View;
import android.view.Window;

public final class WindowCompat {
    static class Api16Impl {
        static void setDecorFitsSystemWindows(Window window0, boolean z) {
            View view0 = window0.getDecorView();
            int v = view0.getSystemUiVisibility();
            view0.setSystemUiVisibility((z ? v & 0xFFFFF8FF : v | 0x700));
        }
    }

    static class Api28Impl {
        static Object requireViewById(Window window0, int v) {
            return window0.requireViewById(v);
        }
    }

    static class Api30Impl {
        static void setDecorFitsSystemWindows(Window window0, boolean z) {
            View view0 = window0.getDecorView();
            int v = view0.getSystemUiVisibility();
            view0.setSystemUiVisibility((z ? v & 0xFFFFFEFF : v | 0x100));
            window0.setDecorFitsSystemWindows(z);
        }
    }

    static class Api35Impl {
        static void setDecorFitsSystemWindows(Window window0, boolean z) {
            window0.setDecorFitsSystemWindows(z);
        }
    }

    public static final int FEATURE_ACTION_BAR = 8;
    public static final int FEATURE_ACTION_BAR_OVERLAY = 9;
    public static final int FEATURE_ACTION_MODE_OVERLAY = 10;

    public static WindowInsetsControllerCompat getInsetsController(Window window0, View view0) {
        return new WindowInsetsControllerCompat(window0, view0);
    }

    public static View requireViewById(Window window0, int v) {
        if(Build.VERSION.SDK_INT >= 28) {
            return (View)Api28Impl.requireViewById(window0, v);
        }
        View view0 = window0.findViewById(v);
        if(view0 == null) {
            throw new IllegalArgumentException("ID does not reference a View inside this Window");
        }
        return view0;
    }

    public static void setDecorFitsSystemWindows(Window window0, boolean z) {
        if(Build.VERSION.SDK_INT >= 35) {
            Api35Impl.setDecorFitsSystemWindows(window0, z);
            return;
        }
        if(Build.VERSION.SDK_INT >= 30) {
            Api30Impl.setDecorFitsSystemWindows(window0, z);
            return;
        }
        Api16Impl.setDecorFitsSystemWindows(window0, z);
    }
}

