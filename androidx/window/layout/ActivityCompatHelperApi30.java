package androidx.window.layout;

import android.app.Activity;
import android.graphics.Rect;
import androidx.work.impl.utils.NetworkApi23..ExternalSyntheticApiModelOutline0;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u001A\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bÁ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000E\u0010\u0003\u001A\u00020\u00042\u0006\u0010\u0005\u001A\u00020\u0006J\u000E\u0010\u0007\u001A\u00020\u00042\u0006\u0010\u0005\u001A\u00020\u0006¨\u0006\b"}, d2 = {"Landroidx/window/layout/ActivityCompatHelperApi30;", "", "()V", "currentWindowBounds", "Landroid/graphics/Rect;", "activity", "Landroid/app/Activity;", "maximumWindowBounds", "window_release"}, k = 1, mv = {1, 6, 0}, xi = 0x30)
public final class ActivityCompatHelperApi30 {
    public static final ActivityCompatHelperApi30 INSTANCE;

    static {
        ActivityCompatHelperApi30.INSTANCE = new ActivityCompatHelperApi30();
    }

    public final Rect currentWindowBounds(Activity activity0) {
        Intrinsics.checkNotNullParameter(activity0, "activity");
        Rect rect0 = NetworkApi23..ExternalSyntheticApiModelOutline0.m(NetworkApi23..ExternalSyntheticApiModelOutline0.m$1(activity0.getWindowManager()));
        Intrinsics.checkNotNullExpressionValue(rect0, "activity.windowManager.currentWindowMetrics.bounds");
        return rect0;
    }

    public final Rect maximumWindowBounds(Activity activity0) {
        Intrinsics.checkNotNullParameter(activity0, "activity");
        Rect rect0 = NetworkApi23..ExternalSyntheticApiModelOutline0.m(NetworkApi23..ExternalSyntheticApiModelOutline0.m(activity0.getWindowManager()));
        Intrinsics.checkNotNullExpressionValue(rect0, "activity.windowManager.maximumWindowMetrics.bounds");
        return rect0;
    }
}

