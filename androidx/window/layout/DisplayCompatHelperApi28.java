package androidx.window.layout;

import android.view.DisplayCutout;
import androidx.work.impl.utils.NetworkApi23..ExternalSyntheticApiModelOutline0;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u001A\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\bÁ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000E\u0010\u0003\u001A\u00020\u00042\u0006\u0010\u0005\u001A\u00020\u0006J\u000E\u0010\u0007\u001A\u00020\u00042\u0006\u0010\u0005\u001A\u00020\u0006J\u000E\u0010\b\u001A\u00020\u00042\u0006\u0010\u0005\u001A\u00020\u0006J\u000E\u0010\t\u001A\u00020\u00042\u0006\u0010\u0005\u001A\u00020\u0006¨\u0006\n"}, d2 = {"Landroidx/window/layout/DisplayCompatHelperApi28;", "", "()V", "safeInsetBottom", "", "displayCutout", "Landroid/view/DisplayCutout;", "safeInsetLeft", "safeInsetRight", "safeInsetTop", "window_release"}, k = 1, mv = {1, 6, 0}, xi = 0x30)
public final class DisplayCompatHelperApi28 {
    public static final DisplayCompatHelperApi28 INSTANCE;

    static {
        DisplayCompatHelperApi28.INSTANCE = new DisplayCompatHelperApi28();
    }

    public final int safeInsetBottom(DisplayCutout displayCutout0) {
        Intrinsics.checkNotNullParameter(displayCutout0, "displayCutout");
        return NetworkApi23..ExternalSyntheticApiModelOutline0.m$1(displayCutout0);
    }

    public final int safeInsetLeft(DisplayCutout displayCutout0) {
        Intrinsics.checkNotNullParameter(displayCutout0, "displayCutout");
        return NetworkApi23..ExternalSyntheticApiModelOutline0.m$2(displayCutout0);
    }

    public final int safeInsetRight(DisplayCutout displayCutout0) {
        Intrinsics.checkNotNullParameter(displayCutout0, "displayCutout");
        return NetworkApi23..ExternalSyntheticApiModelOutline0.m(displayCutout0);
    }

    public final int safeInsetTop(DisplayCutout displayCutout0) {
        Intrinsics.checkNotNullParameter(displayCutout0, "displayCutout");
        return NetworkApi23..ExternalSyntheticApiModelOutline0.m$3(displayCutout0);
    }
}

