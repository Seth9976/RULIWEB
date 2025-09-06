package androidx.window.layout;

import android.app.Activity;
import androidx.work.impl.utils.NetworkApi23..ExternalSyntheticApiModelOutline0;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000B\n\u0000\n\u0002\u0018\u0002\n\u0000\bÁ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000E\u0010\u0003\u001A\u00020\u00042\u0006\u0010\u0005\u001A\u00020\u0006¨\u0006\u0007"}, d2 = {"Landroidx/window/layout/ActivityCompatHelperApi24;", "", "()V", "isInMultiWindowMode", "", "activity", "Landroid/app/Activity;", "window_release"}, k = 1, mv = {1, 6, 0}, xi = 0x30)
public final class ActivityCompatHelperApi24 {
    public static final ActivityCompatHelperApi24 INSTANCE;

    static {
        ActivityCompatHelperApi24.INSTANCE = new ActivityCompatHelperApi24();
    }

    public final boolean isInMultiWindowMode(Activity activity0) {
        Intrinsics.checkNotNullParameter(activity0, "activity");
        return NetworkApi23..ExternalSyntheticApiModelOutline0.m(activity0);
    }
}

