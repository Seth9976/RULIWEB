package androidx.core.view;

import android.graphics.Insets;
import android.graphics.Path;
import android.graphics.Rect;
import android.os.Build.VERSION;
import android.view.DisplayCutout.Builder;
import android.view.DisplayCutout;
import androidx.core.util.ObjectsCompat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class DisplayCutoutCompat {
    static class Api28Impl {
        static DisplayCutout createDisplayCutout(Rect rect0, List list0) {
            return new DisplayCutout(rect0, list0);
        }

        static List getBoundingRects(DisplayCutout displayCutout0) {
            return displayCutout0.getBoundingRects();
        }

        static int getSafeInsetBottom(DisplayCutout displayCutout0) {
            return displayCutout0.getSafeInsetBottom();
        }

        static int getSafeInsetLeft(DisplayCutout displayCutout0) {
            return displayCutout0.getSafeInsetLeft();
        }

        static int getSafeInsetRight(DisplayCutout displayCutout0) {
            return displayCutout0.getSafeInsetRight();
        }

        static int getSafeInsetTop(DisplayCutout displayCutout0) {
            return displayCutout0.getSafeInsetTop();
        }
    }

    static class Api29Impl {
        static DisplayCutout createDisplayCutout(Insets insets0, Rect rect0, Rect rect1, Rect rect2, Rect rect3) {
            return new DisplayCutout(insets0, rect0, rect1, rect2, rect3);
        }
    }

    static class Api30Impl {
        static DisplayCutout createDisplayCutout(Insets insets0, Rect rect0, Rect rect1, Rect rect2, Rect rect3, Insets insets1) {
            return new DisplayCutout(insets0, rect0, rect1, rect2, rect3, insets1);
        }

        static Insets getWaterfallInsets(DisplayCutout displayCutout0) {
            return displayCutout0.getWaterfallInsets();
        }
    }

    static class Api31Impl {
        static Path getCutoutPath(DisplayCutout displayCutout0) {
            return displayCutout0.getCutoutPath();
        }
    }

    static class Api33Impl {
        static DisplayCutout createDisplayCutout(Insets insets0, Rect rect0, Rect rect1, Rect rect2, Rect rect3, Insets insets1, Path path0) {
            DisplayCutout.Builder displayCutout$Builder0 = new DisplayCutout.Builder().setSafeInsets(insets0).setWaterfallInsets(insets1);
            if(rect0 != null) {
                displayCutout$Builder0.setBoundingRectLeft(rect0);
            }
            if(rect1 != null) {
                displayCutout$Builder0.setBoundingRectTop(rect1);
            }
            if(rect2 != null) {
                displayCutout$Builder0.setBoundingRectRight(rect2);
            }
            if(rect3 != null) {
                displayCutout$Builder0.setBoundingRectBottom(rect3);
            }
            if(path0 != null) {
                displayCutout$Builder0.setCutoutPath(path0);
            }
            return displayCutout$Builder0.build();
        }
    }

    private final DisplayCutout mDisplayCutout;

    public DisplayCutoutCompat(Rect rect0, List list0) {
        this((Build.VERSION.SDK_INT < 28 ? null : Api28Impl.createDisplayCutout(rect0, list0)));
    }

    private DisplayCutoutCompat(DisplayCutout displayCutout0) {
        this.mDisplayCutout = displayCutout0;
    }

    public DisplayCutoutCompat(androidx.core.graphics.Insets insets0, Rect rect0, Rect rect1, Rect rect2, Rect rect3, androidx.core.graphics.Insets insets1) {
        this(DisplayCutoutCompat.constructDisplayCutout(insets0, rect0, rect1, rect2, rect3, insets1, null));
    }

    public DisplayCutoutCompat(androidx.core.graphics.Insets insets0, Rect rect0, Rect rect1, Rect rect2, Rect rect3, androidx.core.graphics.Insets insets1, Path path0) {
        this(DisplayCutoutCompat.constructDisplayCutout(insets0, rect0, rect1, rect2, rect3, insets1, path0));
    }

    private static DisplayCutout constructDisplayCutout(androidx.core.graphics.Insets insets0, Rect rect0, Rect rect1, Rect rect2, Rect rect3, androidx.core.graphics.Insets insets1, Path path0) {
        if(Build.VERSION.SDK_INT >= 33) {
            return Api33Impl.createDisplayCutout(insets0.toPlatformInsets(), rect0, rect1, rect2, rect3, insets1.toPlatformInsets(), path0);
        }
        if(Build.VERSION.SDK_INT >= 30) {
            return Api30Impl.createDisplayCutout(insets0.toPlatformInsets(), rect0, rect1, rect2, rect3, insets1.toPlatformInsets());
        }
        if(Build.VERSION.SDK_INT >= 29) {
            return Api29Impl.createDisplayCutout(insets0.toPlatformInsets(), rect0, rect1, rect2, rect3);
        }
        if(Build.VERSION.SDK_INT >= 28) {
            Rect rect4 = new Rect(insets0.left, insets0.top, insets0.right, insets0.bottom);
            ArrayList arrayList0 = new ArrayList();
            if(rect0 != null) {
                arrayList0.add(rect0);
            }
            if(rect1 != null) {
                arrayList0.add(rect1);
            }
            if(rect2 != null) {
                arrayList0.add(rect2);
            }
            if(rect3 != null) {
                arrayList0.add(rect3);
            }
            return Api28Impl.createDisplayCutout(rect4, arrayList0);
        }
        return null;
    }

    @Override
    public boolean equals(Object object0) {
        if(this == object0) {
            return true;
        }
        return object0 == null || this.getClass() != object0.getClass() ? false : ObjectsCompat.equals(this.mDisplayCutout, ((DisplayCutoutCompat)object0).mDisplayCutout);
    }

    public List getBoundingRects() {
        return Build.VERSION.SDK_INT < 28 ? Collections.EMPTY_LIST : Api28Impl.getBoundingRects(this.mDisplayCutout);
    }

    public Path getCutoutPath() {
        return Build.VERSION.SDK_INT < 0x1F ? null : Api31Impl.getCutoutPath(this.mDisplayCutout);
    }

    public int getSafeInsetBottom() {
        return Build.VERSION.SDK_INT < 28 ? 0 : Api28Impl.getSafeInsetBottom(this.mDisplayCutout);
    }

    public int getSafeInsetLeft() {
        return Build.VERSION.SDK_INT < 28 ? 0 : Api28Impl.getSafeInsetLeft(this.mDisplayCutout);
    }

    public int getSafeInsetRight() {
        return Build.VERSION.SDK_INT < 28 ? 0 : Api28Impl.getSafeInsetRight(this.mDisplayCutout);
    }

    public int getSafeInsetTop() {
        return Build.VERSION.SDK_INT < 28 ? 0 : Api28Impl.getSafeInsetTop(this.mDisplayCutout);
    }

    public androidx.core.graphics.Insets getWaterfallInsets() {
        return Build.VERSION.SDK_INT < 30 ? androidx.core.graphics.Insets.NONE : androidx.core.graphics.Insets.toCompatInsets(Api30Impl.getWaterfallInsets(this.mDisplayCutout));
    }

    @Override
    public int hashCode() {
        return this.mDisplayCutout == null ? 0 : this.mDisplayCutout.hashCode();
    }

    @Override
    public String toString() {
        return "DisplayCutoutCompat{" + this.mDisplayCutout + "}";
    }

    DisplayCutout unwrap() {
        return this.mDisplayCutout;
    }

    static DisplayCutoutCompat wrap(DisplayCutout displayCutout0) {
        return displayCutout0 == null ? null : new DisplayCutoutCompat(displayCutout0);
    }
}

