package androidx.core.view;

import android.os.Build.VERSION;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public final class HapticFeedbackConstantsCompat {
    @Retention(RetentionPolicy.SOURCE)
    public @interface HapticFeedbackFlags {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface HapticFeedbackType {
    }

    public static final int CLOCK_TICK = 4;
    public static final int CONFIRM = 16;
    public static final int CONTEXT_CLICK = 6;
    public static final int DRAG_START = 25;
    static final int FIRST_CONSTANT_INT = 0;
    public static final int FLAG_IGNORE_VIEW_SETTING = 1;
    public static final int GESTURE_END = 13;
    public static final int GESTURE_START = 12;
    public static final int GESTURE_THRESHOLD_ACTIVATE = 23;
    public static final int GESTURE_THRESHOLD_DEACTIVATE = 24;
    public static final int KEYBOARD_PRESS = 3;
    public static final int KEYBOARD_RELEASE = 7;
    public static final int KEYBOARD_TAP = 3;
    static final int LAST_CONSTANT_INT = 27;
    public static final int LONG_PRESS = 0;
    public static final int NO_HAPTICS = -1;
    public static final int REJECT = 17;
    public static final int SEGMENT_FREQUENT_TICK = 27;
    public static final int SEGMENT_TICK = 26;
    public static final int TEXT_HANDLE_MOVE = 9;
    public static final int TOGGLE_OFF = 22;
    public static final int TOGGLE_ON = 21;
    public static final int VIRTUAL_KEY = 1;
    public static final int VIRTUAL_KEY_RELEASE = 8;

    static int getFeedbackConstantOrFallback(int v) {
        int v1 = 0;
        int v2 = -1;
        if(v == -1) {
            return -1;
        }
        if(Build.VERSION.SDK_INT < 34) {
            switch(v) {
                case 25: {
                    v = 0;
                    break;
                }
                case 21: 
                case 23: 
                case 26: {
                    v = 6;
                    break;
                }
                case 22: 
                case 24: 
                case 27: {
                    v = 4;
                }
            }
        }
        if(Build.VERSION.SDK_INT < 30) {
            switch(v) {
                case 13: {
                    v1 = 6;
                    break;
                }
                case 12: 
                case 16: {
                    v1 = 1;
                    break;
                }
                case 17: {
                    break;
                }
                default: {
                    v1 = v;
                }
            }
        }
        else {
            v1 = v;
        }
        if(Build.VERSION.SDK_INT >= 27 || v1 != 7 && v1 != 8 && v1 != 9) {
            v2 = v1;
        }
        return Build.VERSION.SDK_INT < 23 && v2 == 6 ? 4 : v2;
    }
}

