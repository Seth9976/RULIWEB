package androidx.window.layout;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Build.VERSION;
import android.util.Log;
import android.view.Display;
import android.view.DisplayCutout;
import androidx.work.impl.utils.NetworkApi23..ExternalSyntheticApiModelOutline0;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000E\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\bÀ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0005\u001A\u00020\u00062\u0006\u0010\u0007\u001A\u00020\bH\u0016J\u0010\u0010\t\u001A\u00020\u00062\u0006\u0010\u0007\u001A\u00020\bH\u0016J\u0015\u0010\n\u001A\u00020\u000B2\u0006\u0010\u0007\u001A\u00020\bH\u0001¢\u0006\u0002\b\fJ\u0015\u0010\r\u001A\u00020\u000B2\u0006\u0010\u0007\u001A\u00020\bH\u0001¢\u0006\u0002\b\u000EJ\u0015\u0010\u000F\u001A\u00020\u000B2\u0006\u0010\u0007\u001A\u00020\bH\u0001¢\u0006\u0002\b\u0010J\u0015\u0010\u0011\u001A\u00020\u000B2\u0006\u0010\u0007\u001A\u00020\bH\u0001¢\u0006\u0002\b\u0012J\u0012\u0010\u0013\u001A\u0004\u0018\u00010\u00142\u0006\u0010\u0015\u001A\u00020\u0016H\u0003J\u0010\u0010\u0017\u001A\u00020\u00182\u0006\u0010\u0019\u001A\u00020\u001AH\u0002J\u0015\u0010\u001B\u001A\u00020\u001C2\u0006\u0010\u0015\u001A\u00020\u0016H\u0001¢\u0006\u0002\b\u001DJ\u0018\u0010\u001E\u001A\u00020\u001F2\u0006\u0010\u0007\u001A\u00020\b2\u0006\u0010 \u001A\u00020\u000BH\u0002R\u000E\u0010\u0003\u001A\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006!"}, d2 = {"Landroidx/window/layout/WindowMetricsCalculatorCompat;", "Landroidx/window/layout/WindowMetricsCalculator;", "()V", "TAG", "", "computeCurrentWindowMetrics", "Landroidx/window/layout/WindowMetrics;", "activity", "Landroid/app/Activity;", "computeMaximumWindowMetrics", "computeWindowBoundsIceCreamSandwich", "Landroid/graphics/Rect;", "computeWindowBoundsIceCreamSandwich$window_release", "computeWindowBoundsN", "computeWindowBoundsN$window_release", "computeWindowBoundsP", "computeWindowBoundsP$window_release", "computeWindowBoundsQ", "computeWindowBoundsQ$window_release", "getCutoutForDisplay", "Landroid/view/DisplayCutout;", "display", "Landroid/view/Display;", "getNavigationBarHeight", "", "context", "Landroid/content/Context;", "getRealSizeForDisplay", "Landroid/graphics/Point;", "getRealSizeForDisplay$window_release", "getRectSizeFromDisplay", "", "bounds", "window_release"}, k = 1, mv = {1, 6, 0}, xi = 0x30)
public final class WindowMetricsCalculatorCompat implements WindowMetricsCalculator {
    public static final WindowMetricsCalculatorCompat INSTANCE;
    private static final String TAG;

    static {
        WindowMetricsCalculatorCompat.INSTANCE = new WindowMetricsCalculatorCompat();
        Intrinsics.checkNotNullExpressionValue("WindowMetricsCalculatorCompat", "WindowMetricsCalculatorC…at::class.java.simpleName");
        WindowMetricsCalculatorCompat.TAG = "WindowMetricsCalculatorCompat";
    }

    @Override  // androidx.window.layout.WindowMetricsCalculator
    public WindowMetrics computeCurrentWindowMetrics(Activity activity0) {
        Intrinsics.checkNotNullParameter(activity0, "activity");
        if(Build.VERSION.SDK_INT >= 30) {
            return new WindowMetrics(ActivityCompatHelperApi30.INSTANCE.currentWindowBounds(activity0));
        }
        if(Build.VERSION.SDK_INT >= 29) {
            return new WindowMetrics(this.computeWindowBoundsQ$window_release(activity0));
        }
        if(Build.VERSION.SDK_INT >= 28) {
            return new WindowMetrics(this.computeWindowBoundsP$window_release(activity0));
        }
        return Build.VERSION.SDK_INT < 24 ? new WindowMetrics(this.computeWindowBoundsIceCreamSandwich$window_release(activity0)) : new WindowMetrics(this.computeWindowBoundsN$window_release(activity0));
    }

    @Override  // androidx.window.layout.WindowMetricsCalculator
    public WindowMetrics computeMaximumWindowMetrics(Activity activity0) {
        Intrinsics.checkNotNullParameter(activity0, "activity");
        if(Build.VERSION.SDK_INT >= 30) {
            return new WindowMetrics(ActivityCompatHelperApi30.INSTANCE.maximumWindowBounds(activity0));
        }
        Display display0 = activity0.getWindowManager().getDefaultDisplay();
        Intrinsics.checkNotNullExpressionValue(display0, "display");
        Point point0 = this.getRealSizeForDisplay$window_release(display0);
        return new WindowMetrics(new Rect(0, 0, point0.x, point0.y));
    }

    public final Rect computeWindowBoundsIceCreamSandwich$window_release(Activity activity0) {
        Intrinsics.checkNotNullParameter(activity0, "activity");
        Display display0 = activity0.getWindowManager().getDefaultDisplay();
        Intrinsics.checkNotNullExpressionValue(display0, "defaultDisplay");
        Point point0 = this.getRealSizeForDisplay$window_release(display0);
        Rect rect0 = new Rect();
        if(point0.x != 0 && point0.y != 0) {
            rect0.right = point0.x;
            rect0.bottom = point0.y;
            return rect0;
        }
        display0.getRectSize(rect0);
        return rect0;
    }

    public final Rect computeWindowBoundsN$window_release(Activity activity0) {
        Intrinsics.checkNotNullParameter(activity0, "activity");
        Rect rect0 = new Rect();
        Display display0 = activity0.getWindowManager().getDefaultDisplay();
        display0.getRectSize(rect0);
        if(!ActivityCompatHelperApi24.INSTANCE.isInMultiWindowMode(activity0)) {
            Intrinsics.checkNotNullExpressionValue(display0, "defaultDisplay");
            Point point0 = this.getRealSizeForDisplay$window_release(display0);
            int v = this.getNavigationBarHeight(activity0);
            if(rect0.bottom + v == point0.y) {
                rect0.bottom += v;
                return rect0;
            }
            if(rect0.right + v == point0.x) {
                rect0.right += v;
            }
        }
        return rect0;
    }

    public final Rect computeWindowBoundsP$window_release(Activity activity0) {
        Intrinsics.checkNotNullParameter(activity0, "activity");
        Rect rect0 = new Rect();
        Configuration configuration0 = activity0.getResources().getConfiguration();
        try {
            Field field0 = Configuration.class.getDeclaredField("windowConfiguration");
            field0.setAccessible(true);
            Object object0 = field0.get(configuration0);
            if(ActivityCompatHelperApi24.INSTANCE.isInMultiWindowMode(activity0)) {
                Object object1 = object0.getClass().getDeclaredMethod("getBounds", null).invoke(object0, null);
                if(object1 == null) {
                    throw new NullPointerException("null cannot be cast to non-null type android.graphics.Rect");
                }
                rect0.set(((Rect)object1));
            }
            else {
                Object object2 = object0.getClass().getDeclaredMethod("getAppBounds", null).invoke(object0, null);
                if(object2 == null) {
                    throw new NullPointerException("null cannot be cast to non-null type android.graphics.Rect");
                }
                rect0.set(((Rect)object2));
            }
        }
        catch(NoSuchFieldException noSuchFieldException0) {
            Log.w("WindowMetricsCalculatorCompat", noSuchFieldException0);
            this.getRectSizeFromDisplay(activity0, rect0);
        }
        catch(NoSuchMethodException noSuchMethodException0) {
            Log.w("WindowMetricsCalculatorCompat", noSuchMethodException0);
            this.getRectSizeFromDisplay(activity0, rect0);
        }
        catch(IllegalAccessException illegalAccessException0) {
            Log.w("WindowMetricsCalculatorCompat", illegalAccessException0);
            this.getRectSizeFromDisplay(activity0, rect0);
        }
        catch(InvocationTargetException invocationTargetException0) {
            Log.w("WindowMetricsCalculatorCompat", invocationTargetException0);
            this.getRectSizeFromDisplay(activity0, rect0);
        }
        Display display0 = activity0.getWindowManager().getDefaultDisplay();
        Point point0 = new Point();
        Intrinsics.checkNotNullExpressionValue(display0, "currentDisplay");
        DisplayCompatHelperApi17.INSTANCE.getRealSize(display0, point0);
        if(!ActivityCompatHelperApi24.INSTANCE.isInMultiWindowMode(activity0)) {
            int v = this.getNavigationBarHeight(activity0);
            if(rect0.bottom + v == point0.y) {
                rect0.bottom += v;
            }
            else if(rect0.right + v == point0.x) {
                rect0.right += v;
            }
            else if(rect0.left == v) {
                rect0.left = 0;
            }
        }
        if((rect0.width() < point0.x || rect0.height() < point0.y) && !ActivityCompatHelperApi24.INSTANCE.isInMultiWindowMode(activity0)) {
            DisplayCutout displayCutout0 = this.getCutoutForDisplay(display0);
            if(displayCutout0 != null) {
                if(rect0.left == DisplayCompatHelperApi28.INSTANCE.safeInsetLeft(displayCutout0)) {
                    rect0.left = 0;
                }
                if(point0.x - rect0.right == DisplayCompatHelperApi28.INSTANCE.safeInsetRight(displayCutout0)) {
                    rect0.right += DisplayCompatHelperApi28.INSTANCE.safeInsetRight(displayCutout0);
                }
                if(rect0.top == DisplayCompatHelperApi28.INSTANCE.safeInsetTop(displayCutout0)) {
                    rect0.top = 0;
                }
                if(point0.y - rect0.bottom == DisplayCompatHelperApi28.INSTANCE.safeInsetBottom(displayCutout0)) {
                    rect0.bottom += DisplayCompatHelperApi28.INSTANCE.safeInsetBottom(displayCutout0);
                }
            }
        }
        return rect0;
    }

    public final Rect computeWindowBoundsQ$window_release(Activity activity0) {
        Intrinsics.checkNotNullParameter(activity0, "activity");
        Configuration configuration0 = activity0.getResources().getConfiguration();
        try {
            Field field0 = Configuration.class.getDeclaredField("windowConfiguration");
            field0.setAccessible(true);
            Object object0 = field0.get(configuration0);
            Object object1 = object0.getClass().getDeclaredMethod("getBounds", null).invoke(object0, null);
            if(object1 == null) {
                throw new NullPointerException("null cannot be cast to non-null type android.graphics.Rect");
            }
            return new Rect(((Rect)object1));
        }
        catch(NoSuchFieldException noSuchFieldException0) {
            Log.w("WindowMetricsCalculatorCompat", noSuchFieldException0);
            return this.computeWindowBoundsP$window_release(activity0);
        }
        catch(NoSuchMethodException noSuchMethodException0) {
            Log.w("WindowMetricsCalculatorCompat", noSuchMethodException0);
            return this.computeWindowBoundsP$window_release(activity0);
        }
        catch(IllegalAccessException illegalAccessException0) {
            Log.w("WindowMetricsCalculatorCompat", illegalAccessException0);
            return this.computeWindowBoundsP$window_release(activity0);
        }
        catch(InvocationTargetException invocationTargetException0) {
            Log.w("WindowMetricsCalculatorCompat", invocationTargetException0);
            return this.computeWindowBoundsP$window_release(activity0);
        }
    }

    private final DisplayCutout getCutoutForDisplay(Display display0) {
        try {
            Constructor constructor0 = Class.forName("android.view.DisplayInfo").getConstructor(null);
            constructor0.setAccessible(true);
            Object object0 = constructor0.newInstance(null);
            Method method0 = display0.getClass().getDeclaredMethod("getDisplayInfo", object0.getClass());
            method0.setAccessible(true);
            method0.invoke(display0, object0);
            Field field0 = object0.getClass().getDeclaredField("displayCutout");
            field0.setAccessible(true);
            Object object1 = field0.get(object0);
            if(NetworkApi23..ExternalSyntheticApiModelOutline0.m(object1)) {
                return (DisplayCutout)object1;
            }
        }
        catch(ClassNotFoundException classNotFoundException0) {
            Log.w("WindowMetricsCalculatorCompat", classNotFoundException0);
        }
        catch(NoSuchMethodException noSuchMethodException0) {
            Log.w("WindowMetricsCalculatorCompat", noSuchMethodException0);
        }
        catch(NoSuchFieldException noSuchFieldException0) {
            Log.w("WindowMetricsCalculatorCompat", noSuchFieldException0);
        }
        catch(IllegalAccessException illegalAccessException0) {
            Log.w("WindowMetricsCalculatorCompat", illegalAccessException0);
        }
        catch(InvocationTargetException invocationTargetException0) {
            Log.w("WindowMetricsCalculatorCompat", invocationTargetException0);
        }
        catch(InstantiationException instantiationException0) {
            Log.w("WindowMetricsCalculatorCompat", instantiationException0);
        }
        return null;
    }

    private final int getNavigationBarHeight(Context context0) {
        Resources resources0 = context0.getResources();
        int v = resources0.getIdentifier("navigation_bar_height", "dimen", "android");
        return v <= 0 ? 0 : resources0.getDimensionPixelSize(v);
    }

    public final Point getRealSizeForDisplay$window_release(Display display0) {
        Intrinsics.checkNotNullParameter(display0, "display");
        Point point0 = new Point();
        DisplayCompatHelperApi17.INSTANCE.getRealSize(display0, point0);
        return point0;
    }

    private final void getRectSizeFromDisplay(Activity activity0, Rect rect0) {
        activity0.getWindowManager().getDefaultDisplay().getRectSize(rect0);
    }
}

