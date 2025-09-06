package androidx.core.hardware.display;

import android.content.Context;
import android.hardware.display.DisplayManager;
import android.os.Build.VERSION;
import android.view.Display;
import java.util.Objects;

public final class DisplayManagerCompat {
    static final String DISPLAY_CATEGORY_ALL = "android.hardware.display.category.ALL_INCLUDING_DISABLED";
    public static final String DISPLAY_CATEGORY_BUILT_IN_DISPLAYS = "android.hardware.display.category.BUILT_IN_DISPLAYS";
    public static final String DISPLAY_CATEGORY_PRESENTATION = "android.hardware.display.category.PRESENTATION";
    static final int DISPLAY_TYPE_INTERNAL = 1;
    private final Context mContext;

    private DisplayManagerCompat(Context context0) {
        this.mContext = context0;
    }

    private static Display[] computeBuiltInDisplays(DisplayManager displayManager0) {
        Display[] arr_display = Build.VERSION.SDK_INT < 0x20 ? displayManager0.getDisplays() : displayManager0.getDisplays("android.hardware.display.category.ALL_INCLUDING_DISABLED");
        Display[] arr_display1 = new Display[DisplayManagerCompat.numberOfDisplaysByType(1, arr_display)];
        int v1 = 0;
        for(int v = 0; v < arr_display.length; ++v) {
            Display display0 = arr_display[v];
            if(1 == DisplayManagerCompat.getTypeCompat(display0)) {
                arr_display1[v1] = display0;
                ++v1;
            }
        }
        return arr_display1;
    }

    public Display getDisplay(int v) {
        return ((DisplayManager)this.mContext.getSystemService("display")).getDisplay(v);
    }

    public Display[] getDisplays() {
        return ((DisplayManager)this.mContext.getSystemService("display")).getDisplays();
    }

    // 去混淆评级： 低(20)
    public Display[] getDisplays(String s) {
        return "android.hardware.display.category.BUILT_IN_DISPLAYS".equals(s) ? DisplayManagerCompat.computeBuiltInDisplays(((DisplayManager)this.mContext.getSystemService("display"))) : ((DisplayManager)this.mContext.getSystemService("display")).getDisplays(s);
    }

    public static DisplayManagerCompat getInstance(Context context0) {
        return new DisplayManagerCompat(context0);
    }

    static int getTypeCompat(Display display0) {
        try {
            return (int)(((Integer)Objects.requireNonNull(Display.class.getMethod("getType", null).invoke(display0, null))));
        }
        catch(NoSuchMethodException unused_ex) {
            return 0;
        }
        catch(Exception exception0) {
            throw new RuntimeException(exception0);
        }
    }

    private static int numberOfDisplaysByType(int v, Display[] arr_display) {
        int v2 = 0;
        for(int v1 = 0; v1 < arr_display.length; ++v1) {
            if(v == DisplayManagerCompat.getTypeCompat(arr_display[v1])) {
                ++v2;
            }
        }
        return v2;
    }
}

