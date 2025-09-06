package androidx.core.view;

import android.content.Context;
import android.content.res.Resources;
import android.os.Build.VERSION;
import android.util.Log;
import android.util.TypedValue;
import android.view.InputDevice;
import android.view.ViewConfiguration;
import androidx.annotation.ReplaceWith;
import androidx.core.util.Supplier;
import java.lang.reflect.Method;
import java.util.Objects;

public final class ViewConfigurationCompat {
    static class Api26Impl {
        static float getScaledHorizontalScrollFactor(ViewConfiguration viewConfiguration0) {
            return viewConfiguration0.getScaledHorizontalScrollFactor();
        }

        static float getScaledVerticalScrollFactor(ViewConfiguration viewConfiguration0) {
            return viewConfiguration0.getScaledVerticalScrollFactor();
        }
    }

    static class Api28Impl {
        static int getScaledHoverSlop(ViewConfiguration viewConfiguration0) {
            return viewConfiguration0.getScaledHoverSlop();
        }

        static boolean shouldShowMenuShortcutsWhenKeyboardPresent(ViewConfiguration viewConfiguration0) {
            return viewConfiguration0.shouldShowMenuShortcutsWhenKeyboardPresent();
        }
    }

    static class Api34Impl {
        static int getScaledMaximumFlingVelocity(ViewConfiguration viewConfiguration0, int v, int v1, int v2) {
            return viewConfiguration0.getScaledMaximumFlingVelocity(v, v1, v2);
        }

        static int getScaledMinimumFlingVelocity(ViewConfiguration viewConfiguration0, int v, int v1, int v2) {
            return viewConfiguration0.getScaledMinimumFlingVelocity(v, v1, v2);
        }
    }

    private static final int NO_FLING_MAX_VELOCITY = 0x80000000;
    private static final int NO_FLING_MIN_VELOCITY = 0x7FFFFFFF;
    private static final int RESOURCE_ID_NOT_SUPPORTED = -1;
    private static final int RESOURCE_ID_SUPPORTED_BUT_NOT_FOUND = 0;
    private static final String TAG = "ViewConfigCompat";
    private static Method sGetScaledScrollFactorMethod;

    static {
        if(Build.VERSION.SDK_INT == 25) {
            try {
                ViewConfigurationCompat.sGetScaledScrollFactorMethod = ViewConfiguration.class.getDeclaredMethod("getScaledScrollFactor", null);
            }
            catch(Exception unused_ex) {
                Log.i("ViewConfigCompat", "Could not find method getScaledScrollFactor() on ViewConfiguration");
            }
        }
    }

    private static int getCompatFlingVelocityThreshold(Resources resources0, int v, Supplier supplier0, int v1) {
        switch(v) {
            case -1: {
                return (int)(((Integer)supplier0.get()));
            }
            case 0: {
                return v1;
            }
            default: {
                int v2 = resources0.getDimensionPixelSize(v);
                return v2 < 0 ? v1 : v2;
            }
        }
    }

    private static float getLegacyScrollFactor(ViewConfiguration viewConfiguration0, Context context0) {
        if(Build.VERSION.SDK_INT >= 25) {
            Method method0 = ViewConfigurationCompat.sGetScaledScrollFactorMethod;
            if(method0 != null) {
                try {
                    return (float)(((int)(((Integer)method0.invoke(viewConfiguration0, null)))));
                }
                catch(Exception unused_ex) {
                    Log.i("ViewConfigCompat", "Could not find method getScaledScrollFactor() on ViewConfiguration");
                }
            }
        }
        TypedValue typedValue0 = new TypedValue();
        return context0.getTheme().resolveAttribute(0x101004D, typedValue0, true) ? typedValue0.getDimension(context0.getResources().getDisplayMetrics()) : 0.0f;
    }

    private static int getPlatformResId(Resources resources0, String s, String s1) {
        return resources0.getIdentifier(s, s1, "android");
    }

    private static int getPreApi34MaximumFlingVelocityResId(Resources resources0, int v, int v1) {
        return v != 0x400000 || v1 != 26 ? -1 : ViewConfigurationCompat.getPlatformResId(resources0, "config_viewMaxRotaryEncoderFlingVelocity", "dimen");
    }

    private static int getPreApi34MinimumFlingVelocityResId(Resources resources0, int v, int v1) {
        return v != 0x400000 || v1 != 26 ? -1 : ViewConfigurationCompat.getPlatformResId(resources0, "config_viewMinRotaryEncoderFlingVelocity", "dimen");
    }

    public static float getScaledHorizontalScrollFactor(ViewConfiguration viewConfiguration0, Context context0) {
        return Build.VERSION.SDK_INT < 26 ? ViewConfigurationCompat.getLegacyScrollFactor(viewConfiguration0, context0) : Api26Impl.getScaledHorizontalScrollFactor(viewConfiguration0);
    }

    public static int getScaledHoverSlop(ViewConfiguration viewConfiguration0) {
        return Build.VERSION.SDK_INT < 28 ? viewConfiguration0.getScaledTouchSlop() / 2 : Api28Impl.getScaledHoverSlop(viewConfiguration0);
    }

    public static int getScaledMaximumFlingVelocity(Context context0, ViewConfiguration viewConfiguration0, int v, int v1, int v2) {
        if(Build.VERSION.SDK_INT >= 34) {
            return Api34Impl.getScaledMaximumFlingVelocity(viewConfiguration0, v, v1, v2);
        }
        if(!ViewConfigurationCompat.isInputDeviceInfoValid(v, v1, v2)) {
            return 0x80000000;
        }
        Resources resources0 = context0.getResources();
        int v3 = ViewConfigurationCompat.getPreApi34MaximumFlingVelocityResId(resources0, v2, v1);
        Objects.requireNonNull(viewConfiguration0);
        return ViewConfigurationCompat.getCompatFlingVelocityThreshold(resources0, v3, new ViewConfigurationCompat..ExternalSyntheticLambda0(viewConfiguration0), 0x80000000);
    }

    public static int getScaledMinimumFlingVelocity(Context context0, ViewConfiguration viewConfiguration0, int v, int v1, int v2) {
        if(Build.VERSION.SDK_INT >= 34) {
            return Api34Impl.getScaledMinimumFlingVelocity(viewConfiguration0, v, v1, v2);
        }
        if(!ViewConfigurationCompat.isInputDeviceInfoValid(v, v1, v2)) {
            return 0x7FFFFFFF;
        }
        Resources resources0 = context0.getResources();
        int v3 = ViewConfigurationCompat.getPreApi34MinimumFlingVelocityResId(resources0, v2, v1);
        Objects.requireNonNull(viewConfiguration0);
        return ViewConfigurationCompat.getCompatFlingVelocityThreshold(resources0, v3, new ViewConfigurationCompat..ExternalSyntheticLambda1(viewConfiguration0), 0x7FFFFFFF);
    }

    @ReplaceWith(expression = "config.getScaledPagingTouchSlop()")
    @Deprecated
    public static int getScaledPagingTouchSlop(ViewConfiguration viewConfiguration0) {
        return viewConfiguration0.getScaledPagingTouchSlop();
    }

    public static float getScaledVerticalScrollFactor(ViewConfiguration viewConfiguration0, Context context0) {
        return Build.VERSION.SDK_INT < 26 ? ViewConfigurationCompat.getLegacyScrollFactor(viewConfiguration0, context0) : Api26Impl.getScaledVerticalScrollFactor(viewConfiguration0);
    }

    @ReplaceWith(expression = "config.hasPermanentMenuKey()")
    @Deprecated
    public static boolean hasPermanentMenuKey(ViewConfiguration viewConfiguration0) {
        return viewConfiguration0.hasPermanentMenuKey();
    }

    private static boolean isInputDeviceInfoValid(int v, int v1, int v2) {
        InputDevice inputDevice0 = InputDevice.getDevice(v);
        return inputDevice0 != null && inputDevice0.getMotionRange(v1, v2) != null;
    }

    public static boolean shouldShowMenuShortcutsWhenKeyboardPresent(ViewConfiguration viewConfiguration0, Context context0) {
        if(Build.VERSION.SDK_INT >= 28) {
            return Api28Impl.shouldShowMenuShortcutsWhenKeyboardPresent(viewConfiguration0);
        }
        Resources resources0 = context0.getResources();
        int v = ViewConfigurationCompat.getPlatformResId(resources0, "config_showMenuShortcutsWhenKeyboardPresent", "bool");
        return v != 0 && resources0.getBoolean(v);
    }
}

