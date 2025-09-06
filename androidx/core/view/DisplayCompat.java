package androidx.core.view;

import android.app.UiModeManager;
import android.content.Context;
import android.graphics.Point;
import android.os.Build.VERSION;
import android.os.Build;
import android.text.TextUtils;
import android.view.Display.Mode;
import android.view.Display;
import androidx.core.util.Preconditions;

public final class DisplayCompat {
    static class Api23Impl {
        static ModeCompat getMode(Context context0, Display display0) {
            Display.Mode display$Mode0 = display0.getMode();
            Point point0 = DisplayCompat.getCurrentDisplaySizeFromWorkarounds(context0, display0);
            return point0 == null || Api23Impl.physicalSizeEquals(display$Mode0, point0) ? new ModeCompat(display$Mode0, true) : new ModeCompat(display$Mode0, point0);
        }

        public static ModeCompat[] getSupportedModes(Context context0, Display display0) {
            Display.Mode[] arr_display$Mode = display0.getSupportedModes();
            ModeCompat[] arr_displayCompat$ModeCompat = new ModeCompat[arr_display$Mode.length];
            Display.Mode display$Mode0 = display0.getMode();
            Point point0 = DisplayCompat.getCurrentDisplaySizeFromWorkarounds(context0, display0);
            if(point0 != null && !Api23Impl.physicalSizeEquals(display$Mode0, point0)) {
                for(int v1 = 0; v1 < arr_display$Mode.length; ++v1) {
                    arr_displayCompat$ModeCompat[v1] = Api23Impl.physicalSizeEquals(arr_display$Mode[v1], display$Mode0) ? new ModeCompat(arr_display$Mode[v1], point0) : new ModeCompat(arr_display$Mode[v1], false);
                }
                return arr_displayCompat$ModeCompat;
            }
            for(int v = 0; v < arr_display$Mode.length; ++v) {
                boolean z = Api23Impl.physicalSizeEquals(arr_display$Mode[v], display$Mode0);
                arr_displayCompat$ModeCompat[v] = new ModeCompat(arr_display$Mode[v], z);
            }
            return arr_displayCompat$ModeCompat;
        }

        static boolean isCurrentModeTheLargestMode(Display display0) {
            Display.Mode display$Mode0 = display0.getMode();
            Display.Mode[] arr_display$Mode = display0.getSupportedModes();
            int v = 0;
            while(v < arr_display$Mode.length) {
                Display.Mode display$Mode1 = arr_display$Mode[v];
                if(display$Mode0.getPhysicalHeight() >= display$Mode1.getPhysicalHeight() && display$Mode0.getPhysicalWidth() >= display$Mode1.getPhysicalWidth()) {
                    ++v;
                    continue;
                }
                return false;
            }
            return true;
        }

        // 去混淆评级： 低(20)
        static boolean physicalSizeEquals(Display.Mode display$Mode0, Point point0) {
            return display$Mode0.getPhysicalWidth() == point0.x && display$Mode0.getPhysicalHeight() == point0.y || display$Mode0.getPhysicalWidth() == point0.y && display$Mode0.getPhysicalHeight() == point0.x;
        }

        static boolean physicalSizeEquals(Display.Mode display$Mode0, Display.Mode display$Mode1) {
            return display$Mode0.getPhysicalWidth() == display$Mode1.getPhysicalWidth() && display$Mode0.getPhysicalHeight() == display$Mode1.getPhysicalHeight();
        }
    }

    public static final class ModeCompat {
        static class androidx.core.view.DisplayCompat.ModeCompat.Api23Impl {
            static int getPhysicalHeight(Display.Mode display$Mode0) {
                return display$Mode0.getPhysicalHeight();
            }

            static int getPhysicalWidth(Display.Mode display$Mode0) {
                return display$Mode0.getPhysicalWidth();
            }
        }

        private final boolean mIsNative;
        private final Display.Mode mMode;
        private final Point mPhysicalSize;

        ModeCompat(Point point0) {
            Preconditions.checkNotNull(point0, "physicalSize == null");
            this.mPhysicalSize = point0;
            this.mMode = null;
            this.mIsNative = true;
        }

        ModeCompat(Display.Mode display$Mode0, Point point0) {
            Preconditions.checkNotNull(display$Mode0, "mode == null, can\'t wrap a null reference");
            Preconditions.checkNotNull(point0, "physicalSize == null");
            this.mPhysicalSize = point0;
            this.mMode = display$Mode0;
            this.mIsNative = true;
        }

        ModeCompat(Display.Mode display$Mode0, boolean z) {
            Preconditions.checkNotNull(display$Mode0, "mode == null, can\'t wrap a null reference");
            this.mPhysicalSize = new Point(androidx.core.view.DisplayCompat.ModeCompat.Api23Impl.getPhysicalWidth(display$Mode0), androidx.core.view.DisplayCompat.ModeCompat.Api23Impl.getPhysicalHeight(display$Mode0));
            this.mMode = display$Mode0;
            this.mIsNative = z;
        }

        public int getPhysicalHeight() {
            return this.mPhysicalSize.y;
        }

        public int getPhysicalWidth() {
            return this.mPhysicalSize.x;
        }

        @Deprecated
        public boolean isNative() {
            return this.mIsNative;
        }

        public Display.Mode toMode() {
            return this.mMode;
        }
    }

    private static final int DISPLAY_SIZE_4K_HEIGHT = 0x870;
    private static final int DISPLAY_SIZE_4K_WIDTH = 0xF00;

    static Point getCurrentDisplaySizeFromWorkarounds(Context context0, Display display0) {
        Point point0 = Build.VERSION.SDK_INT >= 28 ? DisplayCompat.parsePhysicalDisplaySizeFromSystemProperties("vendor.display-size", display0) : DisplayCompat.parsePhysicalDisplaySizeFromSystemProperties("sys.display-size", display0);
        if(point0 != null) {
            return point0;
        }
        return !DisplayCompat.isSonyBravia4kTv(context0) || !DisplayCompat.isCurrentModeTheLargestMode(display0) ? null : new Point(0xF00, 0x870);
    }

    private static Point getDisplaySize(Context context0, Display display0) {
        Point point0 = DisplayCompat.getCurrentDisplaySizeFromWorkarounds(context0, display0);
        if(point0 != null) {
            return point0;
        }
        Point point1 = new Point();
        display0.getRealSize(point1);
        return point1;
    }

    public static ModeCompat getMode(Context context0, Display display0) {
        return Build.VERSION.SDK_INT < 23 ? new ModeCompat(DisplayCompat.getDisplaySize(context0, display0)) : Api23Impl.getMode(context0, display0);
    }

    public static ModeCompat[] getSupportedModes(Context context0, Display display0) {
        return Build.VERSION.SDK_INT < 23 ? new ModeCompat[]{DisplayCompat.getMode(context0, display0)} : Api23Impl.getSupportedModes(context0, display0);
    }

    private static String getSystemProperty(String s) {
        try {
            Class class0 = Class.forName("android.os.SystemProperties");
            return (String)class0.getMethod("get", String.class).invoke(class0, s);
        }
        catch(Exception unused_ex) {
            return null;
        }
    }

    static boolean isCurrentModeTheLargestMode(Display display0) {
        return Build.VERSION.SDK_INT < 23 ? true : Api23Impl.isCurrentModeTheLargestMode(display0);
    }

    // 去混淆评级： 低(40)
    private static boolean isSonyBravia4kTv(Context context0) {
        return DisplayCompat.isTv(context0) && "Sony".equals(Build.MANUFACTURER) && Build.MODEL.startsWith("BRAVIA") && context0.getPackageManager().hasSystemFeature("com.sony.dtv.hardware.panel.qfhd");
    }

    private static boolean isTv(Context context0) {
        UiModeManager uiModeManager0 = (UiModeManager)context0.getSystemService("uimode");
        return uiModeManager0 != null && uiModeManager0.getCurrentModeType() == 4;
    }

    private static Point parseDisplaySize(String s) throws NumberFormatException {
        String[] arr_s = s.trim().split("x", -1);
        if(arr_s.length == 2) {
            int v = Integer.parseInt(arr_s[0]);
            int v1 = Integer.parseInt(arr_s[1]);
            if(v > 0 && v1 > 0) {
                return new Point(v, v1);
            }
        }
        throw new NumberFormatException();
    }

    private static Point parsePhysicalDisplaySizeFromSystemProperties(String s, Display display0) {
        if(display0.getDisplayId() != 0) {
            return null;
        }
        String s1 = DisplayCompat.getSystemProperty(s);
        if(!TextUtils.isEmpty(s1) && s1 != null) {
            try {
                return DisplayCompat.parseDisplaySize(s1);
            }
            catch(NumberFormatException unused_ex) {
            }
        }
        return null;
    }
}

