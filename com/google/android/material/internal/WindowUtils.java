package com.google.android.material.internal;

import android.content.Context;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Build.VERSION;
import android.view.Display;
import android.view.WindowManager;

public class WindowUtils {
    static class Api14Impl {
        static Rect getCurrentWindowBounds(WindowManager windowManager0) {
            Display display0 = windowManager0.getDefaultDisplay();
            Point point0 = Api14Impl.getRealSizeForDisplay(display0);
            Rect rect0 = new Rect();
            if(point0.x != 0 && point0.y != 0) {
                rect0.right = point0.x;
                rect0.bottom = point0.y;
                return rect0;
            }
            display0.getRectSize(rect0);
            return rect0;
        }

        // 去混淆评级： 低(20)
        private static Point getRealSizeForDisplay(Display display0) {
            Point point0 = new Point();
            Display.class.getDeclaredMethod("getRealSize", Point.class).setAccessible(true);
            display0.getRealSize(point0);
            return point0;
        }
    }

    static class Api17Impl {
        static Rect getCurrentWindowBounds(WindowManager windowManager0) {
            Display display0 = windowManager0.getDefaultDisplay();
            Point point0 = new Point();
            display0.getRealSize(point0);
            Rect rect0 = new Rect();
            rect0.right = point0.x;
            rect0.bottom = point0.y;
            return rect0;
        }
    }

    static class Api30Impl {
        static Rect getCurrentWindowBounds(WindowManager windowManager0) {
            return windowManager0.getCurrentWindowMetrics().getBounds();
        }
    }

    private static final String TAG = "WindowUtils";

    static {
    }

    // 去混淆评级： 低(20)
    static String access$000() [...] // 潜在的解密器

    public static Rect getCurrentWindowBounds(Context context0) {
        WindowManager windowManager0 = (WindowManager)context0.getSystemService("window");
        return Build.VERSION.SDK_INT < 30 ? Api17Impl.getCurrentWindowBounds(windowManager0) : Api30Impl.getCurrentWindowBounds(windowManager0);
    }
}

