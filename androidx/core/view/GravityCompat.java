package androidx.core.view;

import android.graphics.Rect;
import android.view.Gravity;

public final class GravityCompat {
    public static final int END = 0x800005;
    public static final int RELATIVE_HORIZONTAL_GRAVITY_MASK = 0x800007;
    public static final int RELATIVE_LAYOUT_DIRECTION = 0x800000;
    public static final int START = 0x800003;

    public static void apply(int v, int v1, int v2, Rect rect0, int v3, int v4, Rect rect1, int v5) {
        Gravity.apply(v, v1, v2, rect0, v3, v4, rect1, v5);
    }

    public static void apply(int v, int v1, int v2, Rect rect0, Rect rect1, int v3) {
        Gravity.apply(v, v1, v2, rect0, rect1, v3);
    }

    public static void applyDisplay(int v, Rect rect0, Rect rect1, int v1) {
        Gravity.applyDisplay(v, rect0, rect1, v1);
    }

    public static int getAbsoluteGravity(int v, int v1) {
        return Gravity.getAbsoluteGravity(v, v1);
    }
}

